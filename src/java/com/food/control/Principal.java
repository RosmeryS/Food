package com.food.control;

import com.food.conexion.Conexion;
import com.food.conexion.ConexionPool;
import com.food.entidad.Comida;
import com.food.entidad.Menu;
import com.food.operaciones.Operaciones;
import com.food.utilerias.FullMenu;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class Principal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        PrintWriter io = response.getWriter();
        String accion = request.getParameter("accion");
        
        String sql;
        List<Object> param = new ArrayList();
        String rs[][];
        if (accion == null) {
            HttpSession s = request.getSession();
            if(s.getAttribute("MenuPrincipal") == null){
                List<Menu> per = (List<Menu>) s.getAttribute("Permisos");
                List<Menu> MenuPrincipal = per.stream().filter(field -> field.getIdpadre() == 0).collect(Collectors.toList());
                s.setAttribute("MenuPrincipal", MenuPrincipal);
                List<Menu> Submenus = per.stream().filter(field -> field.getIdpadre() != 0).collect(Collectors.toList());
                s.setAttribute("Submenus", Submenus);
                List<FullMenu> FullMenus = new ArrayList();
                for(Menu m: MenuPrincipal){
                    FullMenu fm = new FullMenu();
                    List<Menu> sms = new ArrayList();
                    fm.setMenu_principal(m);
                    for(Menu sm: Submenus){
                        if(sm.getIdpadre() == m.getIdMenu()) sms.add(sm);
                    }
                    fm.setSub_menus(sms);
                    FullMenus.add(fm);
                }
                s.setAttribute("FullMenus", FullMenus);
            }
//            String op = request.getParameter("op");
//           if(op!=null){
//               List<Menu> PermisosAsignados = per.stream().filter(field-> field.getIdpadre()==Integer.parseInt(op)).collect(Collectors.toList());
//               request.setAttribute("PermisosAsignados",PermisosAsignados);   
//           }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (accion.equals("comidas")) {
            try{
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                List<Comida> Comidas = Operaciones.getTodos(new Comida());
                request.setAttribute("Comidas", Comidas);
                
                request.getRequestDispatcher("comidas.jsp").forward(request, response);
                
                Operaciones.commit();
            }catch(Exception ex){
                Operaciones.rollback();
            }finally{
                Operaciones.cerrarConexion();
            }
        } else if (accion.equals("conocenos")) {
                request.getRequestDispatcher("conocenos.jsp").forward(request, response);
        } else if (accion.equals("carrito")) {
            try{
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                Operaciones.commit();
            }catch(Exception ex){
                Operaciones.rollback();
            }finally{
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                Operaciones.cerrarConexion();
            }
        } else if (accion.equals("logout")) {
            logout(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        sesion.removeAttribute("Usuario");
        sesion.removeAttribute("Nombre");
        sesion.removeAttribute("Rol");
        sesion.invalidate();
        response.sendRedirect("Login");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
