package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.*;
import com.food.operaciones.Operaciones;
import com.food.utilerias.Hash;
import com.food.utilerias.Tabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        String servlet = "/Usuarios";
        String servlet_name = "usuarios";
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
                
                sql = "SELECT\n" +
                        "	a.idUsuario,\n" +
                        "	a.nombre,\n" +
                        "	a.apellido,\n" +
                        "	a.telefono,\n" +
                        "	a.fechaNac,\n" +
                        "	b.rol\n" +
                        "FROM Usuarios a, Roles b\n" +
                        "WHERE a.idrol = b.idrol;";
                rs = Operaciones.consultar(sql, params);
                
                cabeceras = new String[]{"ID", "Nombre", "Apellido", "Teléfono", "Fecha nac.", "Rol"};
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
                
                String id = request.getParameter("id");
                com.food.entidad.Usuarios v = Operaciones.get(id, new com.food.entidad.Usuarios());
                com.food.entidad.Roles r = Operaciones.get(v.getIdRol(), new com.food.entidad.Roles());
                request.setAttribute("valor", v);
                request.setAttribute("rol", r);
                
                request.setAttribute("op", "Modificar");
                request.setAttribute("block_id", true);
                request.getRequestDispatcher(servlet_name+"/insertar_modificar.jsp").forward(request, response);
                
                Operaciones.commit();
            }else if(accion.equals("eliminar")){
                Operaciones.iniciarTransaccion();
                
                int id = Integer.parseInt(request.getParameter("id"));
                com.food.entidad.Usuarios v = Operaciones.eliminar(id, new com.food.entidad.Usuarios());
                
                request.getSession().setAttribute("resultado", 1);
                response.sendRedirect(servlet);
                
                Operaciones.commit();
            }else if(accion.equals("rol")){
                Operaciones.iniciarTransaccion();
                
                sql = "SELECT * FROM Roles;";
                rs = Operaciones.consultar(sql, params);
                cabeceras = new String[]{"ID", "Rol"};
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
                request.setAttribute("seleccionar", "rol");
                request.getRequestDispatcher(servlet_name+"/roles.jsp").forward(request, response);
                
                Operaciones.commit();
            }
        }catch(Exception ex){
            try {
                Operaciones.rollback();
                request.getSession().setAttribute("resultado", 0);
                response.sendRedirect(servlet);
            } catch (SQLException ex1) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idusuario = request.getParameter("tidusuario");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String fecha_nacimiento = request.getParameter("fecha_nacimiento");
        String clave = request.getParameter("tclave");
        String idrol = request.getParameter("idrol");
        
        try{
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            
            com.food.entidad.Usuarios v = new com.food.entidad.Usuarios();
            v.setIdUsuario(idusuario);
            v.setNombre(nombre);
            v.setApellido(apellido);
            v.setTelefono(telefono);
            v.setDireccion(direccion);
            v.setEmail(email);
            v.setFechaNac(new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nacimiento));
            v.setIdRol(Integer.parseInt(idrol));
            
            com.food.entidad.Usuarios flag = Operaciones.get(v.getIdUsuario(), new com.food.entidad.Usuarios());
                    
            if(flag.getIdUsuario() != null){
                if(!clave.equals(""))
                    v.setClave(Hash.generarHash(clave, Hash.SHA256));
                else
                    v.setClave(flag.getClave());
                v = Operaciones.actualizar(idusuario, v);
            }else{
                if(!clave.equals(""))
                    v.setClave(Hash.generarHash(clave, Hash.SHA256));
                v = Operaciones.insertar(v);
            }
            
            request.getSession().setAttribute("resultado", 1);
            Operaciones.commit();
        }catch(Exception ex){
            try {
                request.getSession().setAttribute("resultado", 0);
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
                response.sendRedirect("Usuarios");
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
