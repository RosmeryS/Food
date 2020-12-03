package com.food.entidad;

import com.food.anotaciones.*;
import java.sql.Date;

@Entity (table = "Facturas")
public class Facturas {
    @PrimaryKey
    @AutoIncrement
    private int idFacturas;
    private int idUsuario;
    private Date fechaFact;
    private int monto;
    private String formaPago;
    private int descuento;

    public Facturas() {
    }

    public Facturas(int idFacturas, int idUsuario, Date fechaFact, int monto, String formaPago, int descuento) {
        this.idFacturas = idFacturas;
        this.idUsuario = idUsuario;
        this.fechaFact = fechaFact;
        this.monto = monto;
        this.formaPago = formaPago;
        this.descuento = descuento;
    }

    public int getIdFacturas() {
        return idFacturas;
    }

    public void setIdFacturas(int idFacturas) {
        this.idFacturas = idFacturas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaFact() {
        return fechaFact;
    }

    public void setFechaFact(Date fechaFact) {
        this.fechaFact = fechaFact;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
 
}
