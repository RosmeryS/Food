package com.food.entidad;

import com.food.anotaciones.*;
import java.math.BigDecimal;

@Entity(table="DetallesOrden")
public class DetalleOrden {
    @PrimaryKey
    @AutoIncrement
    private int iddetalle_orden;
    private int idorden;
    private int idmenu;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;

    public DetalleOrden() {
    }

    public DetalleOrden(int iddetalle_orden, int idorden, int idmenu, int cantidad, BigDecimal precio, BigDecimal subtotal) {
        this.iddetalle_orden = iddetalle_orden;
        this.idorden = idorden;
        this.idmenu = idmenu;
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

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
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
