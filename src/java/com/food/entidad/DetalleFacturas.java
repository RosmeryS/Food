package com.food.entidad;

import com.food.anotaciones.*;

@Entity (table = "DetalleFacuras")
public class DetalleFacturas {
    @PrimaryKey
    @AutoIncrement
    private int idDetalle;
    private int idProducto;
    private int cantidad;
    private int idFactura;

    public DetalleFacturas() {
    }

    public DetalleFacturas(int idDetalle, int idProducto, int cantidad, int idFactura) {
        this.idDetalle = idDetalle;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idFactura = idFactura;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
}
