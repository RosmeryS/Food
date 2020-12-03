package com.food.entidad;

import com.food.anotaciones.*;

@Entity (table = "Permisos")
public class Permisos {
    @PrimaryKey
    @AutoIncrement
    private int idpermiso;
    private int idmenu;
    private int idrol;

    public Permisos() {
    }

    public Permisos(int idpermiso, int idmenu, int idrol) {
        this.idpermiso = idpermiso;
        this.idmenu = idmenu;
        this.idrol = idrol;
    }

    public int getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(int idpermiso) {
        this.idpermiso = idpermiso;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }
}
    