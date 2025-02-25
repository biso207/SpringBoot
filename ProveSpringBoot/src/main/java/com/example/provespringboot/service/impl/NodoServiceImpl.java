package com.example.provespringboot.service.impl;

import com.example.provespringboot.model.Nodo;
import com.example.provespringboot.repository.NodoRepository;
import com.example.provespringboot.service.NodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class NodoServiceImpl implements NodoService {
    @Autowired
    NodoRepository nodoRepository;

    @Value("${application.service.max-id}")
    public int maxId;

    @Value("${application.service.max-inactivity-time}")
    public long maxInactivityTime;

    @Override
    public Nodo create(Nodo nodo) {
        nodo.setId(null);
        return nodoRepository.save(nodo);
    }

    @Override
    public Nodo newNodo() {
        List<Nodo> db = nodoRepository.findAll();
        boolean[] used = new boolean[maxId+1];
        Arrays.fill(used, false);
        if(db.size()<=maxId){
            //cerca il primo id disponibile da assegnare al nodo
            for(Nodo nodo : db){
                used[nodo.getIdNodo()] = true;
            }
            int i=0;
            while(i<maxId+1){
                if(!used[i]){
                    Nodo nodo = new Nodo();
                    nodo.setId(null);
                    nodo.setIdNodo(i);
                    nodo.setTimestamp(System.currentTimeMillis());
                    nodoRepository.save(nodo);
                    return nodo;
                }
                i++;
            }
        }
        return null;
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
        return true;
    }

    @Override
    public void controlConnections() {
        long currentTime = System.currentTimeMillis();
        nodoRepository.deleteInactiveNodes(currentTime, maxInactivityTime);
    }


}
