package com.food.control;



import com.food.control.*;
import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.Comida;
import com.food.operaciones.Operaciones;
import com.food.utilerias.Tabla;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "Reportes", urlPatterns = {"/Reportes"})
public class Reportes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        try{
            Conexion conn = new ConexionPool();
            conn.conectar();
            Connection conexion = conn.getConexion();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            
            if(accion.equals("")){
                
            }else if(accion.equals("factura")){
                
                int idorden = Integer.parseInt(request.getParameter("id"));
                com.food.entidad.Ordenes o = Operaciones.get(idorden, new com.food.entidad.Ordenes());
                com.food.entidad.Usuarios u = Operaciones.get(o.getIdUsuario(), new com.food.entidad.Usuarios());
                
                ServletContext context = request.getServletContext();
                File reportFile = new File(context.getRealPath("/")+"reportes/Factura.jasper");
                String reportName = "Factura";
                Map parameters = new HashMap();
                parameters.put("idorden", idorden);
                parameters.put("usuario", u.getNombre()+" "+u.getApellido());
                parameters.put("direccion", u.getDireccion());
                
                byte[] bytes = null;
                try{
                    bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion);
                }catch(JRException ex){
                    
                }
                if(bytes != null){
                    response.setContentLength(bytes.length);
                    try(ServletOutputStream outputStream = response.getOutputStream()){
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.flush();
                    }
                }
                
            }
        }catch(Exception ex){
            try {
                Operaciones.rollback();
                request.getSession().setAttribute("resultado", 0);
                response.sendRedirect("Ordenes");
            } catch (SQLException ex1) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
                response.sendRedirect("Comidas");
            } catch (SQLException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
