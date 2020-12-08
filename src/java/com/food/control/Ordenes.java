package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.DetalleOrden;
import com.food.entidad.VMDetallesOrden;
import com.food.entidad.VMOrdenes;
import com.food.operaciones.Operaciones;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
            
            switch(accion){
                case "":{
                    Operaciones.iniciarTransaccion();
                    
                    sql = "SELECT\n" +
                        "	a.iddetalle_orden, a.idorden,\n" +
                        "	b.menu, a.cantidad, a.precio, a.subtotal\n" +
                        "FROM DetallesOrden a, Comidas b\n" +
                        "WHERE a.idmenu = b.idcomida;";
                    rs = Operaciones.consultar(sql, params);
                    
                    if(rs != null){
                        List<VMDetallesOrden> Detalles = new ArrayList();
                        for(int i=0; i<rs[0].length; i++){
                            VMDetallesOrden o = new VMDetallesOrden();
                            o.setIddetalle_orden(Integer.parseInt(rs[0][i]));
                            o.setIdorden(Integer.parseInt(rs[1][i]));
                            o.setMenu(rs[2][i]);
                            o.setCantidad(Integer.parseInt(rs[3][i]));
                            o.setPrecio(new BigDecimal(rs[4][i]));
                            o.setSubtotal(new BigDecimal(rs[5][i]));
                            Detalles.add(o);
                        }

                        String usuario = request.getParameter("usuario");
                        String estado = request.getParameter("estado");
                        String idusuario = request.getSession().getAttribute("Usuario").toString();
                        Integer idrol = Integer.parseInt(request.getSession().getAttribute("Rol").toString());
                        
                        request.setAttribute("usuario", usuario);
                        request.setAttribute("estado", estado);
                        
                        sql = "SELECT\n" +
                            "	a.idOrden,\n" +
                            "	CONCAT(b.nombre, ' ', b.apellido) AS usuario,\n" +
                            "	b.direccion,\n" +
                            "	a.fechaOrden, a.formaPago, a.estado,\n" +
                            "	a.total\n" +
                            "FROM Ordenes a, Usuarios b\n" +
                            "WHERE a.idUsuario = b.idUsuario";
                        if(usuario != null && usuario != "") sql += " AND CONCAT(b.nombre, ' ', b.apellido) LIKE '%"+usuario+"%'";
                        if(estado != null && !estado.equals("0")) sql += " AND a.estado = '"+estado+"'";
                        if(idrol != 1) {
                            sql += " AND a.idUsuario = '"+idusuario+"'";
                            request.setAttribute("disable", true);
                        }
                        
                        rs = Operaciones.consultar(sql, params);
                        
                        if(rs != null){
                            List<VMOrdenes> Ordenes = new ArrayList();
                            for(int i=0; i<rs[0].length; i++){
                                VMOrdenes o = new VMOrdenes();
                                o.setIdorden(Integer.parseInt(rs[0][i]));
                                o.setUsuario(rs[1][i]);
                                o.setDireccion(rs[2][i]);
                                o.setFechaOrden(rs[3][i]);
                                o.setFormaPago(rs[4][i]);
                                o.setEstado(rs[5][i]);
                                o.setTotal(new BigDecimal(rs[6][i]));

                                for(VMDetallesOrden c: Detalles){
                                    if(c.getIdorden() == o.getIdorden()){
                                        o.getDetalles().add(c);
                                    }
                                }

                                Ordenes.add(o);
                            }

                            request.setAttribute("Ordenes", Ordenes);
                        }
                    }
                    
                    request.getRequestDispatcher(servlet_name+"/consulta.jsp").forward(request, response);
            
                    Operaciones.commit();
                }break;
            }
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
        String accion = request.getParameter("accion");
        String idorden = request.getParameter("idorden");        
        
        try{  
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            
            if(accion == null){
                Operaciones.iniciarTransaccion();   
                String[] idcomidas = request.getParameterValues("idcomidas");
                String[] cantidades = request.getParameterValues("cantidades");
                String[] precios = request.getParameterValues("precios");
                String[] subtotales = request.getParameterValues("subtotales");
                String formaPago = request.getParameter("formaPago");
                String total = request.getParameter("total"); 

                com.food.entidad.Ordenes v = new com.food.entidad.Ordenes();
                String u = request.getSession().getAttribute("Usuario") != null ? request.getSession().getAttribute("Usuario").toString() : "";
                v.setIdUsuario(u);
                v.setFormaPago(formaPago);
                v.setEstado("Pendiente");
                v.setFechaOrden(new Date());
                v.setSubtotal(new BigDecimal(total));
                v.setDescuentos(BigDecimal.ZERO);
                v.setTotal(new BigDecimal(total));

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
            }else if(accion.equals("cambiarEstado")){
                String estado = request.getParameter("estado");
                
                Operaciones.iniciarTransaccion();
                
                com.food.entidad.Ordenes v = Operaciones.get(Integer.parseInt(idorden), new com.food.entidad.Ordenes());
                v.setEstado(estado);
                
                v = Operaciones.actualizar(v.getIdOrden(), v);
                
                Operaciones.commit();
            }
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
