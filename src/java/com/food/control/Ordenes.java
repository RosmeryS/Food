package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.DetalleOrden;
import com.food.operaciones.Operaciones;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ordenes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        String servlet = "/Ordenes";
        String servlet_name = "ordenes";
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
            
            Operaciones.commit();
        }catch(Exception ex){
            try {
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Ordenes.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ordenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idorden = request.getParameter("idorden");
        String[] idcomidas = request.getParameterValues("idcomidas");
        String[] cantidades = request.getParameterValues("cantidades");
        String[] precios = request.getParameterValues("precios");
        String[] subtotales = request.getParameterValues("subtotales");
        String formaPago = request.getParameter("formaPago");
        String total = request.getParameter("total");
        
        try{        
            com.food.entidad.Ordenes v = new com.food.entidad.Ordenes();
            String u = request.getSession().getAttribute("Usuario") != null ? request.getSession().getAttribute("Usuario").toString() : "";
            v.setIdUsuario(u);
            v.setFormaPago(formaPago);
            v.setEstado("Pendiente");
            v.setFechaOrden(new Date());
            v.setSubtotal(new BigDecimal(total));
            v.setDescuentos(BigDecimal.ZERO);
            v.setTotal(new BigDecimal(total));
            
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            
            if(idorden != null && idorden != ""){
                
            }else{
                v = Operaciones.insertar(v);
                
                for(int i=0; i<idcomidas.length; i++){
                    DetalleOrden d = new DetalleOrden();
                    d.setIdorden(v.getIdOrden());
                    d.setIdmenu(Integer.parseInt(idcomidas[i]));
                    d.setCantidad(Integer.parseInt(cantidades[i]));
                    d.setPrecio(new BigDecimal(precios[i]));
                    d.setSubtotal(new BigDecimal(subtotales[i]));
                    
                    d = Operaciones.insertar(d);
                }
            }
            
            Operaciones.commit();
        }catch(Exception ex){
            try {
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Ordenes.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ordenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
