package com.food.entidad;

import com.food.anotaciones.*;

@Entity (table = "Menu")
public class Menu {
    @PrimaryKey
    private int idmenu;
    private String menu;
    private String descripcion;
    private String url;
    private Integer idpadre;
    private String icono;

    public Menu() {
    }

    public Menu(int idMenu, String menu, String descripcion, String url, Integer idpadre, String icono) {
        this.idmenu = idMenu;
        this.menu = menu;
        this.descripcion = descripcion;
        this.url = url;
        this.idpadre = idpadre;
        this.icono = icono;
    }

    public int getIdMenu() {
        return idmenu;
    }

    public void setIdMenu(int idMenu) {
        this.idmenu = idMenu;
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

    public void setIdpadre(Integer idpadre) {
        this.idpadre = idpadre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    
    
}
