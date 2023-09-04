package com.transacciones.model;

import java.util.List;

public class Transacciones {
    private int id;
    private List<Transaccion> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transaccion> getItems() {
        return items;
    }

    public void setItems(List<Transaccion> items) {
        this.items = items;
    }
}
