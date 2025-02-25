package com.example.provespringboot.scheduled;

import com.example.provespringboot.service.NodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskService {
    @Autowired
    NodoService nodoService;
    @Scheduled(fixedRateString= "${application.scheduled.control-time}")
    public void execute() {
        nodoService.controlConnections();
    }
}
