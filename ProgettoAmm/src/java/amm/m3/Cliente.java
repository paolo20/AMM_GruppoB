/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import amm.m3.Classi.OggettoInVendita;
import amm.m3.Classi.Utente;
import amm.m3.Classi.UtentiFactory;
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
@WebServlet(name = "Cliente", urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

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
        
        int c=0;
        
        PrintWriter out = response.getWriter();
        
        
        if(request.getParameter("Conferma") != null)
        {    
            ArrayList<OggettoInVendita> listaOggett=UtentiFactory.getInstance().getOggetti();
            ArrayList<Utente> listaClienti=UtentiFactory.getInstance().getClienti();
            
            int numeroQuantita=Integer.parseInt(request.getParameter("numeroQuantita"));
            int idCliente=Integer.parseInt(request.getParameter("idClient"));
            int quantita=Integer.parseInt(request.getParameter("quantita"));            
            int id=Integer.parseInt(request.getParameter("idOgg"));
                       
            
            session.setAttribute("loggedId", true);
            session.setAttribute("id", idCliente);
            
            for(  OggettoInVendita oggetto : listaOggett){
               
                if( oggetto.getIdOggetto() == id){
                   //out.println("ciao");
                    double prezzo = oggetto.getPrezzo();
                    c=1;
                    out.println(listaClienti);
                    for(Utente u : listaClienti){
                      
                         //out.println("ciao1");
            
                         if(u.getId() == idCliente){
                          // saldo=u.getSaldoCliente(); 
                          double saldo=u.getSaldo();
                           if(saldo >= prezzo){
                            // controllo della quantita da acquistare
                            if(numeroQuantita <= quantita || numeroQuantita != 0){
                                
                              if(session.getAttribute("loggedId").equals(true)){
                                try{
                                    UtentiFactory.getInstance().compraOggetto(id, oggetto.getNomeEAutore(), oggetto.getPrezzo(),quantita, numeroQuantita, idCliente);
                                }
                                catch(SQLException e) {
                                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,null,e);
                                } catch (Exception ex) {
                                      Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                  
                                request.setAttribute("contattore", c);
                                request.setAttribute("oggetto", oggetto);
                                request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
                                request.getRequestDispatcher("confermaLibro.jsp").forward(request, response);
                              }
                              else
                              {
                                    //massaggio di errore
                                    request.setAttribute("errore","accesso negato");
                                    request.getRequestDispatcher("descrizione.jsp").forward(request, response);
                              }
                            }else { //errore saldo
                               request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
                               request.setAttribute("erroreQuantita","acquisto negato");
                               request.getRequestDispatcher("confermaLibro.jsp").forward(request, response);
                            }
                           }  
                           else{
                               //errore saldo
                               request.setAttribute("oggetto", oggetto);
                               request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
                               request.setAttribute("errore","acquisto negato");
                               request.getRequestDispatcher("confermaLibro.jsp").forward(request, response);
                           }
                               
                        }
                    }
                    
                    
                }
            }
        }
        else if(request.getParameter("ricarica") != null){
            int id=Integer.parseInt(request.getParameter("ricarica"));
            
            session.setAttribute("loggedId", true);
            session.setAttribute("id", id);
            
            request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
            request.getRequestDispatcher("ricaricaSaldo.jsp").forward(request, response);
                        
        }
        else if(request.getParameter("Credito") != null){
            double credito=Double.parseDouble(request.getParameter("credito"));
            int id=Integer.parseInt(request.getParameter("idClie"));
            
            try{
                UtentiFactory.getInstance().ricaricaSaldo(id, credito);
            } catch(SQLException e) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE,null,e);
            }
            
            request.setAttribute("messaggio", "non vuoto");
            request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
            request.getRequestDispatcher("ricaricaSaldo.jsp").forward(request, response);
        }        
        else if(request.getParameter("idOggetto") != null)
        {
                ArrayList<OggettoInVendita> listaOggett=UtentiFactory.getInstance().getOggetti();
                
                ArrayList<OggettoInVendita> oggetti=UtentiFactory.getInstance().getOggetti("La chimera");
                
                int id=Integer.parseInt(request.getParameter("idOggetto"));
                
            for(  OggettoInVendita oggetto : listaOggett){
            if( oggetto.getIdOggetto() == id){
            //out.println(oggetto.getNomeEAutore());
            if(session.getAttribute("loggedId").equals(true)){
            String nome = oggetto.getNomeEAutore();
            String file = oggetto.getImage();
            int quantita = oggetto.getQuantita();
            double prezzo = oggetto.getPrezzo();
            oggetto.setIdOggetto(id);
            oggetto.setNomeEAutore(nome);
            oggetto.setImage(file);
            oggetto.setPrezzo(prezzo);
            oggetto.setQuantita(quantita);
            request.setAttribute("contattore", c);
            request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
            request.setAttribute("oggetto", oggetto);
            request.getRequestDispatcher("confermaLibro.jsp").forward(request, response);
            }
            else
            {
            //massaggio di errore
            }
            }
            }
        }
        else if(session.getAttribute("loggedId").equals(true)){
           request.setAttribute("cliente",UtentiFactory.getInstance().getCliente((int)session.getAttribute("id")));
           request.setAttribute("listaOggetti",UtentiFactory.getInstance().getOggetti());
           request.getRequestDispatcher("cliente.jsp").forward(request, response);
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
