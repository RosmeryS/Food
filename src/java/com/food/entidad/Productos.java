package com.food.entidad;

import com.food.anotaciones.*;

@Entity (table = "Productos")
public class Productos {
    @PrimaryKey
    @AutoIncrement
    private int idProducto;
    private String nombreProducto;
    private int precio;
    private String descripcion;

    public Productos() {
    }

    public Productos(int idProducto, String nombreProducto, int precio, String descripcion) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Productos(int parseInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
