package com.transacciones.client;

import com.transacciones.model.Transaccion;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class TransaccionesApiClient {
    private final TransacionesService transaccionesService;

    public TransaccionesApiClient(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        transaccionesService = retrofit.create(TransacionesService.class);
    }

    public List<Transaccion> fetcTransacciones() throws IOException {
        Response<List<Transaccion>> response = transaccionesService.getTransacciones().execute();
        return response.body();
    }
}
