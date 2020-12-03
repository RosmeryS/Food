package com.food.entidad;

import com.food.anotaciones.*;
import java.util.Date;

@Entity(table = "Usuarios")
public class Usuarios {
    @PrimaryKey
    @NotNull
    private String idUsuario;
    @FieldName(name = "nombre")
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private int idRol;
    private String email;
    private String clave;
    private Date fechaNac;

    public Usuarios() {
    }

    public Usuarios(String idUsuario, String nombre, String apellido, String telefono, String direccion, int idRol, String email, String clave, Date fechaNac) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idRol = idRol;
        this.email = email;
        this.clave = clave;
        this.fechaNac = fechaNac;

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

}
