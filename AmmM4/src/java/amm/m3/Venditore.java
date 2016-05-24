/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import amm.m3.Classi.OggettoInVendita;
import amm.m3.Classi.UtentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Venditore", urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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
        
        OggettoInVendita oggetto=new OggettoInVendita();
        
        if(request.getParameter("modifica") != null){
          int idVend=Integer.parseInt(request.getParameter("modifica"));
          session.setAttribute("loggedId", true);
          session.setAttribute("id", idVend);
          try{
               UtentiFactory.getInstance().OggettiVenditore(idVend); 
            }
            catch(SQLException e) {
                Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE,null,e);
            }
          request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore((int)session.getAttribute("id")));
          request.getRequestDispatcher("modificaOggetto.jsp").forward(request, response);  
        }        
        else if(request.getParameter("elimina") != null){
          int idVend=Integer.parseInt(request.getParameter("elimina"));
          session.setAttribute("loggedId", true);
          session.setAttribute("id", idVend);
          request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore((int)session.getAttribute("id")));
          request.getRequestDispatcher("eliminaOggetto.jsp").forward(request, response);  
        }
        else if(request.getParameter("Submit") != null)
        {
            int idVend=Integer.parseInt(request.getParameter("idVend"));
            String nome = request.getParameter("name");
            String file = "img/";
            file= file+request.getParameter("file");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            String descrizione = request.getParameter("descrizione");
            
            oggetto.setIdOggetto(8);
            oggetto.setNomeEAutore(nome);
            oggetto.setImage(file);
            oggetto.setPrezzo(prezzo);
            oggetto.setQuantita(quantita);
            oggetto.setDescrizione(descrizione);
            
            session.setAttribute("loggedId", true);
            session.setAttribute("id", idVend);
            
            try{
               UtentiFactory.getInstance().creaOggetto(idVend,nome,file,descrizione,prezzo,quantita); 
            }
            catch(SQLException e) {
                Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE,null,e);
            }
            
            request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));
            request.setAttribute("oggetto", oggetto);
            request.getRequestDispatcher("conferma.jsp").forward(request, response);
            
        }
        
        else if(session.getAttribute("loggedId").equals(true)){
           request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore((int)session.getAttribute("id")));
           request.getRequestDispatcher("venditore.jsp").forward(request, response);
        }
        else {
           //messaggio di accesso negato
           request.setAttribute("errore","accesso negato");
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
