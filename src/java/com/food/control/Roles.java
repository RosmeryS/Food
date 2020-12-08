package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.Comida;
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

@WebServlet(name = "Roles", urlPatterns = {"/Roles"})
public class Roles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        String servlet = "/Roles";
        String servlet_name = "roles";
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
                
                sql = "SELECT * FROM Roles;";
                rs = Operaciones.consultar(sql, params);
                
                cabeceras = new String[]{"ID", "Rol"};
                Tabla tab = new Tabla(rs, //array quecontiene los datos
                        "50%", //ancho de la tabla px | % 
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.LEFT, // alineacion de la tabla
                        cabeceras);
                tab.setEliminable(true);//boton actualizar
                tab.setModificable(true); //url del proyecto
                tab.setPageContext(request.getContextPath());//pagina encargada de eliminar
                tab.setPaginaEliminable(servlet+"?accion=eliminar");//pagina encargada de actualizacion
                tab.setPaginaModificable(servlet+"?accion=modificar");//pagina encargada de seleccion para operaciones
                tab.setPaginaSeleccionable(servlet+"?accion=modificar");//icono para modificar y eliminar
                tab.setIconoModificable("/iconos/edit.png");
                tab.setIconoEliminable("/iconos/delete.png"); //columnas seleccionables
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
                com.food.entidad.Roles valor = Operaciones.get(id, new com.food.entidad.Roles());
                
                request.setAttribute("valor", valor);
                request.setAttribute("op", "Modificar");
                request.getRequestDispatcher(servlet_name+"/insertar_modificar.jsp").forward(request, response);
                
                Operaciones.commit();
            }else if(accion.equals("eliminar")){
                Operaciones.iniciarTransaccion();
                
                int id = Integer.parseInt(request.getParameter("id"));
                com.food.entidad.Roles v = Operaciones.eliminar(id, new com.food.entidad.Roles());
                
                request.getSession().setAttribute("resultado", 1);
                response.sendRedirect(servlet);
                
                Operaciones.commit();
            }
        }catch(Exception ex){
            try {
                Operaciones.rollback();
                request.getSession().setAttribute("resultado", 0);
                response.sendRedirect(servlet);
            } catch (SQLException ex1) {
                Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idrol = request.getParameter("idrol");
        String rol = request.getParameter("rol");
        
        try{
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            
            Operaciones.iniciarTransaccion();
            
            com.food.entidad.Roles c = new com.food.entidad.Roles();
            c.setRol(rol);
            
            if(idrol != null && !idrol.equals("")){
                //c.setIdRol(Integer.parseInt(idrol));
                
                c = Operaciones.actualizar(Integer.parseInt(idrol), c);
            }else{
                c = Operaciones.insertar(c);
            }
            
            request.getSession().setAttribute("resultado", 1);
            Operaciones.commit();
        }catch(Exception ex){
            try {
                request.getSession().setAttribute("resultado", 0);
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
                response.sendRedirect("Roles");
            } catch (SQLException ex) {
                Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
