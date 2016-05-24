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
              
            int id=Integer.parseInt(request.getParameter("idOgg"));
            int idCliente=Integer.parseInt(request.getParameter("idClient"));
            
            session.setAttribute("loggedId", true);
            session.setAttribute("id", idCliente);
            
            for(  OggettoInVendita oggetto : listaOggett){
               
                if( oggetto.getIdOggetto() == id){
                   
             
                    
                    double prezzo = oggetto.getPrezzo();
                    c=1;
                    
                    for(Utente u : listaClienti){
                      
                         
            
                         if(u.getId() == idCliente){
                          // saldo=u.getSaldoCliente(); 
                          double saldo=u.getSaldo();
                           if(saldo >= prezzo){
                               
                              if(session.getAttribute("loggedId").equals(true)){
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
        else if(request.getParameter("idOggetto") != null)
        {
                ArrayList<OggettoInVendita> listaOggett=UtentiFactory.getInstance().getOggetti();

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
