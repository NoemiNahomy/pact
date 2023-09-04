package com.transacciones;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.transacciones.client.TransaccionesApiClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.*;

@ExtendWith(PactConsumerTestExt.class)
public class TransaccionApiClientPactTest {
    @Pact(provider = "transacciones_provider", consumer = "transacciones_consumer")
    public V4Pact TransaccionesListPact(PactDslWithProvider builder) {
        return builder
                .given("there are transacciones")
                .uponReceiving("a request for transacciones")
                .path("/transacciones")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(newJsonArrayMinLike(1, a -> a.object(o -> {
                    o.id("id");
                    o.eachLike("items", i -> {
                        i.stringType("code");
                        i.stringType("descripcion");
                        i.numberType("monto");
                    });
                })).build())
                .toPact(V4Pact.class);
    }

    @Pact(provider = "transacciones_provider", consumer = "transacciones_consumer")
    public V4Pact noTransaccionsPact(PactDslWithProvider builder) {
        return builder
                .given("there are no transacciones")
                .uponReceiving("a request for transacciones")
                .path("/transacciones")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body("[]")
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "TransaccionesListPact")
    void getTransaccionesList(MockServer mockServer) throws IOException {
        var client = new TransaccionesApiClient(mockServer.getUrl());
        var transaccions = client.fetcTransacciones();
        Assertions.assertNotNull(transaccions);
        Assertions.assertFalse(transaccions.isEmpty());
        Assertions.assertNotNull(transaccions.get(0).getItems());
        Assertions.assertFalse(transaccions.get(0).getItems().isEmpty());
    }

    @Test
    @PactTestFor(pactMethod = "noTransaccionsPact")
    void getEmptyListOfTransacciones(MockServer mockServer) throws IOException {
        var client = new TransaccionesApiClient(mockServer.getUrl());
        var transaccions = client.fetcTransacciones();
        Assertions.assertNotNull(transaccions);
        Assertions.assertTrue(transaccions.isEmpty());
    }
}
