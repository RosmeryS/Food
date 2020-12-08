package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.Comida;
import com.food.entidad.Menu;
import com.food.operaciones.Operaciones;
import com.food.utilerias.Tabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Menus", urlPatterns = {"/Menus"})
public class Menus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        String servlet = "/Menus";
        String servlet_name = "menus";
        String sql = "";
        String rs[][];
        String cabeceras[];
        List<Object> params = new ArrayList();
        
        request.setAttribute("servlet", servlet);
        request.setAttribute("servlet_name", servlet_name);
        
        try{
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            if(accion.equals("")){
                Operaciones.iniciarTransaccion();
                
                sql = "SELECT \n" +
                    "	a.idmenu, a.menu, a.descripcion, a.url,\n" +
                    "	CASE\n" +
                    "		WHEN b.idmenu IS NULL THEN '-'\n" +
                    "		WHEN b.idmenu IS NOT NULL THEN b.menu\n" +
                    "	END AS padre,\n" +
                    "	CASE\n" +
                    "		WHEN a.icono IS NULL THEN '-'\n" +
                    "		WHEN a.icono IS NOT NULL THEN a.icono\n" +
                    "	END AS icono\n" +
                    "FROM menu a\n" +
                    "LEFT JOIN menu b ON a.idpadre = b.idmenu;";
                rs = Operaciones.consultar(sql, params);
                
                cabeceras = new String[]{"ID", "Menú", "Descripción", "URL", "Padre", "Ícono"};
                Tabla tab = new Tabla(rs, //array quecontiene los datos
                        "50%", //ancho de la tabla px | % 
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.LEFT, // alineacion de la tabla
                        cabeceras);
                tab.setModificable(true); //url del proyecto
                tab.setPageContext(request.getContextPath());//pagina encargada de eliminar
                tab.setPaginaModificable(servlet+"?accion=modificar");//pagina encargada de seleccion para operaciones
                tab.setPaginaSeleccionable(servlet+"?accion=modificar");//icono para modificar y eliminar
                tab.setIconoModificable("/iconos/edit.png");
                tab.setColumnasSeleccionables(new int[]{1});//pie de tabla
                tab.setPie("Resultado "+servlet_name);
                
                String tabla01 = tab.getTabla();
                request.setAttribute("tabla", tabla01);
                request.getRequestDispatcher(servlet_name+"/consulta.jsp").forward(request, response);
                
                Operaciones.commit();
            }else if(accion.equals("insertar")){
                request.setAttribute("op", "Insertar");
                request.getRequestDispatcher(servlet_name+"/insertar_modificar.jsp").forward(request, response);
            }else if(accion.equals("modificar")){
                Operaciones.iniciarTransaccion();
                
                int id = Integer.parseInt(request.getParameter("id"));
                Menu v = Operaciones.get(id, new Menu());
                Menu p = null;
                if(v.getIdpadre() != null)
                    p = Operaciones.get(v.getIdpadre(), new Menu());
                request.setAttribute("valor", v);
                request.setAttribute("p", p);
                
                request.setAttribute("op", "Modificar");
                request.getRequestDispatcher(servlet_name+"/insertar_modificar.jsp").forward(request, response);
                
                Operaciones.commit();
            }else if(accion.equals("eliminar")){
                Operaciones.iniciarTransaccion();
                
                int id = Integer.parseInt(request.getParameter("id"));
                Comida v = Operaciones.eliminar(id, new Comida());
                
                request.getSession().setAttribute("resultado", 1);
                response.sendRedirect("Menus");
                
                Operaciones.commit();
            }else if(accion.equals("padres")){
                Operaciones.iniciarTransaccion();
                
                sql = "SELECT idmenu, menu, url FROM Menu WHERE idpadre IS NULL;";
                rs = Operaciones.consultar(sql, params);
                cabeceras = new String[]{"ID", "Menu", "Descripcion"};
                Tabla tab = new Tabla(rs, //array quecontiene los datos
                        "50%", //ancho de la tabla px | % 
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.LEFT, // alineacion de la tabla
                        cabeceras);
                tab.setFilaSeleccionable(true);
                tab.setMetodoFilaSeleccionable("_Seleccionar_");
                tab.setPageContext(request.getContextPath());//pagina encargada de eliminar
                tab.setPie("Resultado "+servlet_name);
                
                String tabla01 = tab.getTabla();
                request.setAttribute("tabla", tabla01);
                request.setAttribute("seleccionar", "padre");
                request.getRequestDispatcher(servlet_name+"/padres.jsp").forward(request, response);
                
                Operaciones.commit();
            }
        }catch(Exception ex){
            try {
                Operaciones.rollback();
                request.getSession().setAttribute("resultado", 0);
                response.sendRedirect("Menus");
            } catch (SQLException ex1) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idmenu = request.getParameter("idmenu");
        String menu = request.getParameter("menu");
        String descripcion = request.getParameter("descripcion");
        String url = request.getParameter("url");
        String idpadre = request.getParameter("idpadre");
        String icono = request.getParameter("icono");
        
        try{
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            
            Menu m = new Menu();
            m.setMenu(menu);
            m.setDescripcion(descripcion);
            m.setUrl(url);
            if(idpadre != null && !idpadre.isEmpty()) m.setIdpadre(Integer.parseInt(idpadre));
            m.setIcono(icono);
            
            if(idmenu != null && !idmenu.equals("")){
                m.setIdmenu(Integer.parseInt(idmenu));
                
                m = Operaciones.actualizar(Integer.parseInt(idmenu), m);
            }else{
                m = Operaciones.insertar(m);
            }
            
            request.getSession().setAttribute("resultado", 1);
            Operaciones.commit();
        }catch(Exception ex){
            try {
                request.getSession().setAttribute("resultado", 0);
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
                response.sendRedirect("Menus");
            } catch (SQLException ex) {
                Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
