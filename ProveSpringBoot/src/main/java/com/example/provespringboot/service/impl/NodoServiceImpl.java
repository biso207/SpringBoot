package com.example.provespringboot.service.impl;

import com.example.provespringboot.model.Nodo;
import com.example.provespringboot.repository.NodoRepository;
import com.example.provespringboot.service.NodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class NodoServiceImpl implements NodoService {
    @Autowired
    NodoRepository nodoRepository;

    @Value("${application.service.max-id}")
    public int maxId;

    @Value("${application.service.max-inactivity-time}")
    public long maxInactivityTime;

    @Transactional
    @Override
    public String newNodo() {
        List<Integer> freeIdNodo = nodoRepository.findAvailableIdNodo(maxId);
        if(freeIdNodo.isEmpty()){
            return "{\"nodo\":"+null+", \"message\":\"Nodo not created\"}";
        }
        int idNodo = freeIdNodo.getFirst();
        try {
            Nodo nodo = new Nodo();
            nodo.setId(null);
            nodo.setIdNodo(idNodo);
            nodo.setTimestamp(System.currentTimeMillis());
            nodoRepository.saveAndFlush(nodo);
            System.out.println("LOG (" + (new Date()) + ") -> Nodo created : " + nodo);
            return "{\"nodo\":" + nodo + ", \"message\":\"Nodo created\"}";
        } catch (Exception e) {
            System.err.println("LOG (" + (new Date()) + ") -> Nodo creation failed : " + e.getMessage());
            return "{\"nodo\":" + null + ", \"message\":\"Nodo creation failed\"}";
        }

    }

    @Override
    public boolean updateNodo(Nodo nodo) {
        int idNodo = nodo.getIdNodo();
        Nodo elemento = nodoRepository.findNodoByIdNodo(idNodo);
        if(elemento==null){
            return false;
        }
        elemento.setTimestamp(System.currentTimeMillis());
        nodoRepository.save(elemento);
        System.out.println("LOG ("+(new Date())+") -> Nodo updated: "+elemento);
        return true;
    }

    @Override
    public void controlConnections() {
        long currentTime = System.currentTimeMillis();
        nodoRepository.deleteInactiveNodes(currentTime, maxInactivityTime);
        System.out.println("LOG ("+(new Date())+") -> Pulizia Nodi effettuata!");
    }
}
