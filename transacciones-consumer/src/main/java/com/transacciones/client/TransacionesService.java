package com.transacciones.client;

import com.transacciones.model.Transaccion;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface TransacionesService {
    @GET("transacciones")
    Call<List<Transaccion>> getTransacciones();
}
