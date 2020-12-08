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

@WebServlet(name = "Comidas", urlPatterns = {"/Comidas"})
public class Comidas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        String servlet = "/Comidas";
        String servlet_name = "comidas";
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
                
                sql = "SELECT idcomida, menu, precio, descripcion FROM Comidas;";
                rs = Operaciones.consultar(sql, params);
                
                cabeceras = new String[]{"ID", "Menú", "Precio", "Descripción"};
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
                Comida v = Operaciones.get(id, new Comida());
                request.setAttribute("valor", v);
                
                request.setAttribute("op", "Modificar");
                request.getRequestDispatcher(servlet_name+"/insertar_modificar.jsp").forward(request, response);
                
                Operaciones.commit();
            }else if(accion.equals("eliminar")){
                Operaciones.iniciarTransaccion();
                
                int id = Integer.parseInt(request.getParameter("id"));
                Comida v = Operaciones.eliminar(id, new Comida());
                
                request.getSession().setAttribute("resultado", 1);
                response.sendRedirect("Comidas");
                
                Operaciones.commit();
            }
        }catch(Exception ex){
            try {
                Operaciones.rollback();
                request.getSession().setAttribute("resultado", 0);
                response.sendRedirect("Comidas");
            } catch (SQLException ex1) {
                Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idcomida = request.getParameter("idcomida");
        String menu = request.getParameter("menu");
        String precio = request.getParameter("precio");
        String descripcion = request.getParameter("descripcion");
        String imagen_url = request.getParameter("imagen_url");
        
        try{
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            
            Comida c = new Comida();
            c.setMenu(menu);
            c.setPrecio(new BigDecimal(precio));
            c.setDescripcion(descripcion);
            c.setImagen_url(imagen_url);
            
            if(idcomida != null && !idcomida.equals("")){
                c.setIdcomida(Integer.parseInt(idcomida));
                
                c = Operaciones.actualizar(Integer.parseInt(idcomida), c);
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
                Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
                response.sendRedirect("Comidas");
            } catch (SQLException ex) {
                Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
