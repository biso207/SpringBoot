package com.example.provespringboot.repository;

import com.example.provespringboot.model.Nodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NodoRepository extends JpaRepository<Nodo,Integer> {
    Nodo findNodoByIdNodo(int idNodo);
}
