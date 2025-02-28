package com.example.provespringboot.service;

import com.example.provespringboot.model.Nodo;

import java.util.List;

public interface NodoService {
    public String newNodo();
    public boolean updateNodo(Nodo nodo);
    public void controlConnections();
}
