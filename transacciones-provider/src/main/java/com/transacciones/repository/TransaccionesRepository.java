package com.transacciones.repository;

import com.transacciones.model.Transacciones;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class TransaccionesRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Transacciones> getTransaccions() throws IOException {
        URL resource = getClass().getClassLoader().getResource("transacciones.json");
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }
}
