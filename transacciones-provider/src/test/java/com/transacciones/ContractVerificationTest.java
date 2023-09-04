package com.transacciones;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.transacciones.model.Transacciones;
import com.transacciones.repository.TransaccionesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("transacciones_provider")
@PactBroker
public class ContractVerificationTest {
    @MockBean
    private TransaccionesRepository transccionsRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("there are transacciones")
    public void thereAreTransacciones() throws IOException {
        Mockito.when(transccionsRepository.getTransaccions()).thenReturn(getTransaccionesFromFile("transacciones.json"));
    }

    @State("there are no transacciones")
    public void thereAreNoTransacciones() throws IOException {
        Mockito.when(transccionsRepository.getTransaccions()).thenReturn(getTransaccionesFromFile("no_transacciones.json"));
    }

    private List<Transacciones> getTransaccionesFromFile(String filename) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }
}
