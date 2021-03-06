package com.food.entidad;

import com.food.anotaciones.AutoIncrement;
import com.food.anotaciones.Entity;
import com.food.anotaciones.PrimaryKey;


@Entity(table = "Roles")
public class Roles {
    @PrimaryKey
    @AutoIncrement
    private int idRol;
    private String rol;

    public Roles() {
    }

    public Roles(int idRol, String rol) {
        this.idRol = idRol;
        this.rol = rol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
 }
