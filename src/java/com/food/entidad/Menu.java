package com.food.entidad;

import com.food.anotaciones.*;

@Entity (table = "Menu")
public class Menu {
    @PrimaryKey
    @AutoIncrement
    private int idMenu;
    private String menu;
    private String descripcion;
    private String url;
    private int idpadre;
    private String icono;

    public Menu() {
    }

    public Menu(int idMenu, String menu, String descripcion, String url, int idpadre, String icono) {
        this.idMenu = idMenu;
        this.menu = menu;
        this.descripcion = descripcion;
        this.url = url;
        this.idpadre = idpadre;
        this.icono = icono;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(int idpadre) {
        this.idpadre = idpadre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    
    
}
