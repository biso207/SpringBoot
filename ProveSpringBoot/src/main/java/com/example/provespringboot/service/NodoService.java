package com.example.provespringboot.service;

import com.example.provespringboot.model.Nodo;

public interface NodoService {
    public Nodo create(Nodo nodo);
    public Nodo newNodo();
    public boolean updateNodo(Nodo nodo);
    public void controlConnections();
}
