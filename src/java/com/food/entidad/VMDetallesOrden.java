package com.food.entidad;

import java.math.BigDecimal;

public class VMDetallesOrden {
    private int iddetalle_orden;
    private int idorden;
    private String menu;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;

    public VMDetallesOrden() {
    }

    public VMDetallesOrden(int iddetalle_orden, int idorden, String menu, int cantidad, BigDecimal precio, BigDecimal subtotal) {
        this.iddetalle_orden = iddetalle_orden;
        this.idorden = idorden;
        this.menu = menu;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public int getIddetalle_orden() {
        return iddetalle_orden;
    }

    public void setIddetalle_orden(int iddetalle_orden) {
        this.iddetalle_orden = iddetalle_orden;
    }

    public int getIdorden() {
        return idorden;
    }

    public void setIdorden(int idorden) {
        this.idorden = idorden;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
