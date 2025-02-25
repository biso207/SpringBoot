package com.example.provespringboot;
import com.example.provespringboot.model.Nodo;
import com.example.provespringboot.service.NodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    NodoService nodoService;
    //funzione di test per aggiungere un nodo manulmente
    //TODO: toglierla
    @PostMapping("/add")
    public Nodo addNodo(@RequestBody Nodo nodo){
        return nodoService.create(nodo);
    }
    @GetMapping("/getNewIdNodo")
    public Nodo getNewIdNodo(){
        return nodoService.newNodo();
    }
    @PostMapping("/updateTimestamp")
    public String updateTimestamp(@RequestBody Nodo nodo){
        if(nodoService.updateNodo(nodo)){
            return "{\"message\":\"Nodo updated\"}";
        }
        else{
            return "{\"message\":\"Nodo could not be updated\"}";
        }
    }
}
