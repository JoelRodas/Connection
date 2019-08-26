/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Sergio joel Rodas Valdez carne 2018085 fecha creación 13/08/2019 Salon c29
 */
@WebServlet(name = "Conecction", urlPatterns = {"/Conecction"})
public class Conecction extends HttpServlet {
    @Resource(name = "jdbcIN5BM2018085")
    private DataSource ds;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if     * @throws ServletException if a servlet-specific error occurs
 an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Conecction</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Conecction at " + request.getContextPath() + "</h1>");
            // Uso del Try y catch...
            try(Connection cnx = InitialContext.<DataSource>doLookup("jdbcIN5BM2018085").getConnection()){
                
                String sql ="select * from Usuario";    
                Statement star =cnx.createStatement();
                ResultSet rs= star.executeQuery(sql);
                while(rs.next()){
                    String nombre =rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    String Alias = rs.getString("Alias");
                    out.println("<center>"+"<table border =1 >"+"</center>");
                    out.println("<tr>");
                    out.println("<td>"+nombre+""+apellidos+"</td>");
                    out.println("<td>"+Alias+"</td>");
                    out.println("</tr>");
                    out.println("</table >");
                    
                }      
                
                
            }catch(NamingException |SQLException ex){
                System.out.println("Error........."+ex.getMessage());
                out.print(ex.getMessage());
                
            }
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
