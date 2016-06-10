/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import amm.m3.Classi.Utente;
import amm.m3.Classi.UtentiFactory;
import amm.m3.Classi.Cliente;
import amm.m3.Classi.OggettoInVendita;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author paolo
 */
@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db-definition/ammdb";

    private static final String DB_BUILD_PATH = "WEB-INF/db-definition/ammdb";
    
    @Override 
    public void init(){
        //String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        String dbConnection = "jdbc:derby://localhost:1527/ammdb;create=true";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtentiFactory.getInstance().setConnectionString(dbConnection);   
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        
        //int loginFallito=0;
        
        //PrintWriter out= response.getWriter();
        
        if(request.getParameter("Submit") != null)
        {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            //ArrayList<Utente> listaUtenti = UtentiFactory.getInstance().getList();
            Utente u = UtentiFactory.getInstance().getUtente(username, password);
            //login corretto
            if( u != null)
            {
                    session.setAttribute("loggedId", true);
                    session.setAttribute("id", u.getId());
                    
                    if(u instanceof Cliente)
                    {
                        request.setAttribute("cliente", u);
                        request.setAttribute("clienti",UtentiFactory.getInstance().getClienti());
                       // request.setAttribute("listaOggetti",UtentiFactory.getInstance().getOggetti());
                        request.getRequestDispatcher("cliente.jsp").forward(request, response);
                        //loginFallito++;
                    }
                    else
                    {
                        request.setAttribute("venditore", u);
                        request.getRequestDispatcher("venditore.jsp").forward(request, response);
                        //loginFallito++;
                    }
                
            }
            //login fallito
            else if (u == null){ 
                session.invalidate();
                request.setAttribute("errore", "errore");
                request.getRequestDispatcher("form_login.jsp").forward(request, response);
            }
            
        }
        else {
            request.getRequestDispatcher("form_login.jsp").forward(request, response);
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
