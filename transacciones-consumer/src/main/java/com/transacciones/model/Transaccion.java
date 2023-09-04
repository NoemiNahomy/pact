package com.transacciones.model;

import java.util.List;

public class Transaccion {
    private int id;
    private List<Transacciones> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transacciones> getItems() {
        return items;
    }

    public void setItems(List<Transacciones> items) {
        this.items = items;
    }
}
