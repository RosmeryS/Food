<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.food.conexion.*" %>
<%@page import="com.food.entidad.*" %>
<%@page import="com.food.operaciones.*" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test consulta a varias tablas</h1>
        <%
            try {
                ConexionPool con = new ConexionPool(); // inicializamos el pool deconexiones
                con.conectar();
                Operaciones.abrirConexion(con); // abrimos la conexión de la bd
                String consulta = "";//consulta SQL
                List<Object> params = new ArrayList<Object>();
                params.add("Crepa");
                params.add("Smothies");
                String[][] listado = Operaciones.consultar(consulta, params); //obtenemos los registros de la consulta
                        // el método consultar retorna todos los registros de la consulta
                        // NOTA: Si la consulta no recibe parámetros entonces el segundo parámetro 
                        // del método consultar debe ser null.
                        // El método consultar retorna null si no encuentra ningún registro de la búsqueda
            } catch (Exception ex) {
                //Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null,ex);
            } finally {
                try {
                    Operaciones.cerrarConexion(); // se cierra la conexión al final de todo
                } catch (SQLException ex) {
                    //Logger.getLogger(Principal.class.getName()).log(Level.SEVERE,null, ex);
                }
            }

 

        %>
    </body>
</html>
 