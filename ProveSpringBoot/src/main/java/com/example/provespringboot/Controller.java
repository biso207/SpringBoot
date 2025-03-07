package com.example.provespringboot;
import com.example.provespringboot.model.Nodo;
import com.example.provespringboot.service.NodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    NodoService nodoService;

    @GetMapping("/getNewIdNodo")
    public String getNewIdNodo(){
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
