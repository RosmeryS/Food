/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.food.utilerias;

import com.food.entidad.Menu;
import java.util.ArrayList;
import java.util.List;

public class FullMenu {
    private Menu menu_principal;
    private List<Menu> sub_menus;

    public FullMenu() {
        sub_menus = new ArrayList();
    }

    public FullMenu(Menu menu_principal, List<Menu> sub_menus) {
        this.menu_principal = menu_principal;
        this.sub_menus = sub_menus;
    }

    public Menu getMenu_principal() {
        return menu_principal;
    }

    public void setMenu_principal(Menu menu_principal) {
        this.menu_principal = menu_principal;
    }

    public List<Menu> getSub_menus() {
        return sub_menus;
    }

    public void setSub_menus(List<Menu> sub_menus) {
        this.sub_menus = sub_menus;
    }
}
