package com.food.entidad;

import com.food.anotaciones.*;
import java.math.BigDecimal;

@Entity(table="Comidas")
public class Comida {
    @PrimaryKey
    @AutoIncrement
    private int idcomida;
    private String menu;
    private BigDecimal precio;
    private String descripcion;
    private String imagen_url;

    public Comida(int idcomida, String menu, BigDecimal precio, String descripcion, String imagen_url) {
        this.idcomida = idcomida;
        this.menu = menu;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen_url = imagen_url;
    }

    public Comida() {
    }

    public int getIdcomida() {
        return idcomida;
    }

    public void setIdcomida(int idcomida) {
        this.idcomida = idcomida;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
    
    
}
