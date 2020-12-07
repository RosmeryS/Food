package com.food.entidad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VMOrdenes {
    private int idorden;
    private String usuario;
    private String direccion;
    private String fechaOrden;
    private String formaPago;
    private String estado;
    private BigDecimal total;
    private List<VMDetallesOrden> detalles;

    public VMOrdenes() {
        total = BigDecimal.ZERO;
        detalles = new ArrayList();
    }

    public VMOrdenes(int idorden, String usuario, String direccion, String fechaOrden, String formaPago, String estado, BigDecimal total, List<VMDetallesOrden> detalles) {
        this.idorden = idorden;
        this.usuario = usuario;
        this.direccion = direccion;
        this.fechaOrden = fechaOrden;
        this.formaPago = formaPago;
        this.estado = estado;
        this.total = total;
        this.detalles = detalles;
    }

    public int getIdorden() {
        return idorden;
    }

    public void setIdorden(int idorden) {
        this.idorden = idorden;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<VMDetallesOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VMDetallesOrden> detalles) {
        this.detalles = detalles;
    }
    
    
}
