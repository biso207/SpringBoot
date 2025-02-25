package com.example.provespringboot.repository;

import com.example.provespringboot.model.Nodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NodoRepository extends JpaRepository<Nodo,Integer> {
    Nodo findNodoByIdNodo(int idNodo);
    @Modifying
    @Transactional
    @Query("DELETE FROM Nodo WHERE :currentTime - timestamp > :maxInactivityTime")
    void deleteInactiveNodes(@Param("currentTime") long currentTime, @Param("maxInactivityTime") long maxInactivityTime);
}
