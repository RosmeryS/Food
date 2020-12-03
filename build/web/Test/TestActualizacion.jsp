<%@page import="com.food.entidad.Menu"%>
<%@page import="com.food.operaciones.Operaciones"%>
<%@page import="com.food.conexion.*" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test Actualizacion</h1>
        <%
            try {
                ConexionPool con = new ConexionPool(); // inicializamos el pool deconexiones
                con.conectar();
                Menu a = new Menu(); // creamos la entidad 
                a.setIdMenu(1);
                a.setMenu("Conocenos"); 
                a.setDescripcion("este menu"); 
                a.setUrl("http://menu actializado");
                a.setIdpadre(1); 
                Operaciones.abrirConexion(con); 
                Operaciones.iniciarTransaccion(); 
               
                a = Operaciones.actualizar(1, a); 
                out.print("La nueva descripcion es: " + a.getUrl());
                Operaciones.commit(); // confirmamos los cambios de la transacción
            } catch (Exception ex) {
                throw ex;
                
            } finally {
                try {
                    Operaciones.cerrarConexion(); // se cierra la conexión al final detodo
                } catch (SQLException ex) {
                    throw ex;
                }
            }

 

        %>
    </body>
</html>