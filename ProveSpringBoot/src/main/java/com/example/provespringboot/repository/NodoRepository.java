package com.example.provespringboot.repository;

import com.example.provespringboot.model.Nodo;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NodoRepository extends JpaRepository<Nodo,Integer> {
    Nodo findNodoByIdNodo(int idNodo);

    //query per cancellare dei nodi
    @Modifying
    @Transactional
    @Query("DELETE FROM Nodo WHERE :currentTime - timestamp > :maxInactivityTime")
    void deleteInactiveNodes(@Param("currentTime") long currentTime, @Param("maxInactivityTime") long maxInactivityTime);

    //query per il lock pessimistico
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT n FROM Nodo n WHERE n.idNodo = :idNodo")
    Nodo findAndLockByIdNodo(@Param("idNodo") int idNodo);

    //Query custom per ottenere tutti i nodi liberi
    @Query(value = """
    WITH RECURSIVE numbers AS (
        SELECT 0 AS n
        UNION ALL
        SELECT n + 1 FROM numbers WHERE n < :maxId
    )
    SELECT numbers.n 
    FROM numbers
    LEFT JOIN identificators i ON numbers.n = i.idNodo
    WHERE i.idNodo IS NULL
    """, nativeQuery = true)
    List<Integer> findAvailableIdNodo(@Param("maxId") int maxId);
}
