package com.transacciones.controller;


import com.transacciones.model.Transacciones;
import com.transacciones.repository.TransaccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TransaccionesController {
    private final TransaccionesRepository transaccRepository;

    @Autowired
    public TransaccionesController(TransaccionesRepository ordersRepository) {
        this.transaccRepository = ordersRepository;
    }

    @GetMapping("/transacciones")
    List<Transacciones> getAllPropiedades() throws IOException {
        return transaccRepository.getTransaccions();
    }
}
