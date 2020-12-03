package com.food.control;

import com.food.conexion.*;
import com.food.entidad.Menu;
import com.food.entidad.Usuarios;
import com.food.operaciones.Operaciones;
import com.food.utilerias.Hash;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Cache-control", "no-store");
        response.setHeader("Cache-control", "must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String accion = request.getParameter("accion");
        if (accion == null) {
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        } else if (accion.equals("login")) {
            try {
                iniciarSesion(request, response);
            } catch (SQLException ex) {
            }
        }
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String usuario = request.getParameter("txtUsuario");
        String clave = request.getParameter("txtClave");
        PrintWriter io = response.getWriter();
        if (usuario == null) {
            usuario = "";
        }
        if (clave == null) {
            clave = null;
        }
        try {
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            if (conn.getConexion() == null) {
                request.setAttribute("error", 1);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } else {
                HttpSession sesion = request.getSession();
                Usuarios u = Operaciones.get(usuario, new Usuarios());
                //String query = "SELECT * FROM Usuarios WHERE idUsuario = '"+usuario+"'";
                //String[][] rs = Operaciones.consultar(query, null);
                //Usuarios u = new Usuarios(rs[0][0],rs[1][0],rs[2][0],rs[3][0],rs[4][0],Integer.parseInt(rs[5][0]),rs[6][0],rs[7][0], new SimpleDateFormat("yyyy-MM-dd").parse(rs[8][0]) );
                if (u.getIdUsuario() != null) {
                    if (u.getClave().equals(Hash.generarHash(clave, Hash.SHA256))) {
                        sesion.setAttribute("Usuario", u.getIdUsuario());
                        sesion.setAttribute("Nombre", u.getNombre()+ " " + u.getApellido());
                        sesion.setAttribute("Rol", u.getIdRol());
                        List<Menu> permisos = getPermisos(u.getIdRol());
                        sesion.setAttribute("Permisos", permisos);
                        response.sendRedirect("Principal");
                    } else {
                        request.setAttribute("error", 2);
                        request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", 2);
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                }
            }
            Operaciones.commit();
        } catch (Exception ex) {
            Operaciones.rollback();
            io.print(ex);
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<Menu> getPermisos(Integer idrol) throws SQLException {
        List<Menu> permisos = new ArrayList();
        try {
            String sql = "SELECT * FROM menu WHERE idmenu in (select idmenu from permiso WHERE idrol = ?)";
            List<Object> param = new ArrayList();
            param.add(idrol);
            String[][] rs = Operaciones.consultar(sql, param);
            for (int i = 0; i <rs[0].length;i++) {
                Menu m = new Menu(Integer.parseInt(rs[0][i]), rs[1][i], rs[2][i], rs[3][i], Integer.parseInt(rs[4][i] == null?"0" :rs[4][i]), rs[5][i]);
                permisos.add(m);
            }
        } catch (Exception ex) {
            Operaciones.rollback();
        }
        return permisos;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
