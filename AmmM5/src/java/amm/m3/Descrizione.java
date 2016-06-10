/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import amm.m3.Classi.Utente;
import amm.m3.Classi.Venditore;
import amm.m3.Classi.Cliente;
import amm.m3.Classi.UtentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Descrizione", urlPatterns = {"/descrizione.html"})
public class Descrizione extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        
         
       if(request.getParameter("idVend") != null)
        {           
           ArrayList<Utente> listaVenditori = UtentiFactory.getInstance().getVenditori();
            
           int idVend=Integer.parseInt(request.getParameter("idVend"));
           for( Utente u : listaVenditori)
            { 
               if(u.getId() == idVend) 
               {
                    session.setAttribute("loggedId", true);
                    session.setAttribute("id", u.getId());
                    if(u instanceof Venditore)
                    {
                        request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore((int)session.getAttribute("id")));
                        request.getRequestDispatcher("descrizione.jsp").forward(request, response);
                    }
               }
            
            }
        }
        else if(request.getParameter("idClient") != null){
            
            ArrayList<Utente> listaClienti = UtentiFactory.getInstance().getClienti();
           int idClient=Integer.parseInt(request.getParameter("idClient"));
           
            for(Utente u : listaClienti)
            { 
               if(u.getId() == idClient) 
               {
                    session.setAttribute("loggedId", true);
                    session.setAttribute("id", u.getId());
                    if(u instanceof Cliente)
                    {
                        request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
                        request.getRequestDispatcher("descrizione.jsp").forward(request, response);
                    }
               }
            
            }
            
        } else {
            
            request.getRequestDispatcher("descrizione.jsp").forward(request, response);
           
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
