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
        
        PrintWriter out= response.getWriter();
        
        if(request.getParameter("modifica") != null){
          int idVend=Integer.parseInt(request.getParameter("modifica"));
          int controlloLink=Integer.parseInt(request.getParameter("controlloLink"));
          session.setAttribute("loggedId", true);
          session.setAttribute("id", idVend);
          
          UtentiFactory.getInstance().OggettiVenditore(idVend);
          
          if(controlloLink == 1) {request.setAttribute("controlloLink", 1);}
          else if(controlloLink == 2){request.setAttribute("controlloLink", 2);}
          
          request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));          
          request.getRequestDispatcher("modificaOggetto.jsp").forward(request, response);  
        }
        else if(request.getParameter("idElimina") != null){
           int id= Integer.parseInt(request.getParameter("idElimina"));
           int idVend= Integer.parseInt(request.getParameter("idVend"));
           
           oggetto= UtentiFactory.getInstance().getOggetto(id);
           
           try{
               UtentiFactory.getInstance().eliminaOggetto(id, oggetto.getNomeEAutore(), oggetto.getPrezzo());
            }
            catch(SQLException e) {
                Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE,null,e);
            }
           
           oggetto= UtentiFactory.getInstance().getOggetto(id);
           
           request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));
           request.setAttribute("oggetto",oggetto);
           request.getRequestDispatcher("conferma.jsp").forward(request, response);
        }
        else if(request.getParameter("idModifica") != null)
        {
           int id= Integer.parseInt(request.getParameter("idModifica"));
           int idVend= Integer.parseInt(request.getParameter("idVend"));
           int controllo = Integer.parseInt(request.getParameter("controllo"));
           if(controllo == 1){
            request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));
            request.setAttribute("oggetto",UtentiFactory.getInstance().getOggetto(id));
            request.setAttribute("controllo", controllo);
            request.getRequestDispatcher("oggettoModifica.jsp").forward(request, response); 
           }else if(controllo == 2){
            request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));
            request.setAttribute("oggetto",UtentiFactory.getInstance().getOggetto(id));
            request.setAttribute("controllo", controllo);
            request.getRequestDispatcher("oggettoModifica.jsp").forward(request, response);               
           }
             
        }
        else if(request.getParameter("ModificaImage") != null){
            
            int idVend= Integer.parseInt(request.getParameter("idVend"));
            String image="img/";
            image=image+request.getParameter("file");
            
            oggetto.setImage(image);
            
            try{
               UtentiFactory.getInstance().modificaImage(idVend,image); 
            }
            catch(SQLException e) {
                Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE,null,e);
            }
            
            request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));
            request.setAttribute("oggetto", oggetto);
            request.setAttribute("controllo", 2);
            request.getRequestDispatcher("conferma.jsp").forward(request, response);
        }
        else if(request.getParameter("Modifica") != null){
            //zona servlet per la modifica del libro 
            
            int idVend=Integer.parseInt(request.getParameter("idVend"));
            int idOgg=Integer.parseInt(request.getParameter("idOgg"));
            String nome = request.getParameter("name");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            String descrizione = request.getParameter("descrizione");
            
            
            session.setAttribute("loggedId", true);
            session.setAttribute("id", idVend);
            
            oggetto.setIdOggetto(idOgg);
            oggetto.setNomeEAutore(nome);
            oggetto.setPrezzo(prezzo);
            oggetto.setQuantita(quantita);
            oggetto.setDescrizione(descrizione);
            
            
            try{
               UtentiFactory.getInstance().modificaOggetto(idOgg,nome,descrizione,prezzo,quantita); 
            }
            catch(SQLException e) {
                Logger.getLogger(Venditore.class.getName()).log(Level.SEVERE,null,e);
            }
            
            request.setAttribute("venditore",UtentiFactory.getInstance().getVenditore(idVend));
            request.setAttribute("oggetto", oggetto);
            request.setAttribute("controllo", 1);
            request.getRequestDispatcher("conferma.jsp").forward(request, response);
            
            
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
