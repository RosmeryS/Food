package com.food.entidad;

public class VMPermiso {
    private int idmenu;
    private int ischecked;
    private String menu;
    private String url;
    private String padre;

    public VMPermiso(int idmenu, int ischecked, String menu, String url, String padre) {
        this.idmenu = idmenu;
        this.ischecked = ischecked;
        this.menu = menu;
        this.url = url;
        this.padre = padre;
    }

    public VMPermiso() {
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getIschecked() {
        return ischecked;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }
}
