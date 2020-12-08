package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.Comida;
import com.food.entidad.VMPermiso;
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

@WebServlet(name = "Permisos", urlPatterns = {"/Permisos"})
public class Permisos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer resultado = (Integer)request.getSession().getAttribute("resultado");
        if(resultado != null){
            request.setAttribute("resultado", resultado);
            request.getSession().removeAttribute("resultado");
        }
        
        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        
        String servlet = "/Permisos";
        String servlet_name = "permisos";
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
                
                Integer idrol = request.getParameter("idrol") != null ? Integer.parseInt(request.getParameter("idrol")) : null;
                request.setAttribute("idrol", idrol);
                
                if(idrol != null){
                    sql = "SELECT \n" +
                        "	a.menu, a.url,\n" +
                        "	CASE\n" +
                        "		WHEN b.idmenu IS NULL THEN '-'\n" +
                        "		WHEN b.idmenu IS NOT NULL THEN b.menu\n" +
                        "	END AS padre,\n" +
                        "	(SELECT COUNT(*) FROM permiso p WHERE p.idmenu = a.idmenu AND p.idrol = ?) AS ischecked\n" +
                        "FROM menu a\n" +
                        "LEFT JOIN menu b ON a.idpadre = b.idmenu;";
                    params.add(idrol);
                    rs = Operaciones.consultar(sql, params);
                    
                    List<VMPermiso> Permisos = new ArrayList();
                    if(rs != null){
                        for(int i=0; i<rs[0].length; i++){
                            VMPermiso p = new VMPermiso();
                            p.setMenu(rs[0][i]);
                            p.setUrl(rs[1][i]);
                            p.setPadre(rs[2][i]);
                            p.setIschecked(Integer.parseInt(rs[3][i]));
                            Permisos.add(p);
                        }
                        
                        request.setAttribute("PermisosList", Permisos);
                    }
                }
                
                
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
                response.sendRedirect(servlet);
                
                Operaciones.commit();
            }
        }catch(Exception ex){
            try {
                Operaciones.rollback();
                request.getSession().setAttribute("resultado", 0);
                response.sendRedirect(servlet);
            } catch (SQLException ex1) {
                Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                Operaciones.cerrarConexion();
                response.sendRedirect("Comidas");
            } catch (SQLException ex) {
                Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
