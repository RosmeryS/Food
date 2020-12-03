package com.food.entidad;

import com.food.anotaciones.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity (table = "Ordenes")
public class Ordenes {
    @PrimaryKey
    @AutoIncrement
    private int idOrden;
    private String idUsuario;
    private Date fechaOrden;
    private String formaPago;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal descuentos;
    private BigDecimal total;

    public Ordenes() {
    }

    public Ordenes(int idOrden, String idUsuario, Date fechaOrden, String formaPago, String estado, BigDecimal subtotal, BigDecimal descuentos, BigDecimal total) {
        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.fechaOrden = fechaOrden;
        this.formaPago = formaPago;
        this.estado = estado;
        this.subtotal = subtotal;
        this.descuentos = descuentos;
        this.total = total;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(BigDecimal descuentos) {
        this.descuentos = descuentos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
