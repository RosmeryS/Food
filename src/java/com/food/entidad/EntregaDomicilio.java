package com.food.entidad;

import com.food.anotaciones.*;
import java.sql.Date;

@Entity (table = "EntregaDomicilio")
public class EntregaDomicilio {
    @PrimaryKey
    @AutoIncrement
    private int idEntrega;
    private Date fechaEstablecida;
    private Date fechaEntrega;
    private float gastosDeEnvio;
    private String estadoEntrega;

    public EntregaDomicilio() {
    }

    public EntregaDomicilio(int idEntrega, Date fechaEstablecida, Date fechaEntrega, float gastosDeEnvio, String estadoEntrega) {
        this.idEntrega = idEntrega;
        this.fechaEstablecida = fechaEstablecida;
        this.fechaEntrega = fechaEntrega;
        this.gastosDeEnvio = gastosDeEnvio;
        this.estadoEntrega = estadoEntrega;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getFechaEstablecida() {
        return fechaEstablecida;
    }

    public void setFechaEstablecida(Date fechaEstablecida) {
        this.fechaEstablecida = fechaEstablecida;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public float getGastosDeEnvio() {
        return gastosDeEnvio;
    }

    public void setGastosDeEnvio(float gastosDeEnvio) {
        this.gastosDeEnvio = gastosDeEnvio;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }
    
}
