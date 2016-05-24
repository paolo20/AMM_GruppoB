<%-- 
    Document   : modificaOggetto
    Created on : 24-mag-2016, 18.53.51
    Author     : paolo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <jsp:include page="head.jsp"/>
    <body>
        <div id="page">
            <jsp:include page="header.jsp"/>
         <div id="sidebarLogin">
            <!-- link esterni -->
            <table id="tabellaLink">
            <tr class="trLogin">
                
                <td>
                    <a href="descrizione.html?idVend=${venditore.getId()}">Descrizione</a>
                </td>
            </tr>
            <tr class="trLogin">
             <form method="post" action="logout.html">
                <td>
                    <input type="hidden" name="Venditore" value="${venditore.getId()}"/>
                    <input type="submit" name="Logout" value="Logout" id="logout"></td>
             </form>
            </tr>
            </table> 
        </div>
        <div id="contenentVenditore"> 
            <c:if test="${errore.length() != null}">
                <p>Accesso negato</p>
            </c:if>
            <c:if test="${errore.length() == null}">
            <!-- secondo titolo -->
            <h2 id="autenticazione">Ciao <span>${venditore.nome}</span> </h2>
            <h2>Scegli i dati del libro</h2>
            <c:forEach var="oggetto" items="${venditore.listaOggetti}">
                <p>ciao</p>
                  <c:if test="${((oggetto.getIdOggetto())% 2) == 0}">
                       <tr class="pari">
                            <td class="tdCliente">·${oggetto.nomeEAutore}</td>
                            <td class="tdCliente"><img title="${oggetto.nomeEAutore}" alt="foto del libro" src="${oggetto.image}" width="40" height="50"></td>
                            <td class="tdCliente">·${oggetto.quantita}</td>
                            <td class="tdCliente">·${oggetto.prezzo}</td>
                            <td class="tdCliente">
                                <!--<input type="submit" name="Carrello" value="·Aggiungi al Carrello"  id="inputdescrizioneCarrello" class="inputdescrizione">-->
                                <a class="link" href="venditore.html?idOggetto=${oggetto.getIdOggetto()}">Questo Libro</a>
                            </td>
                       </tr>
                  </c:if>
                  <c:if test="${((oggetto.getIdOggetto())% 2) != 0}">
                       <tr class="dispari">
                            <td class="tdCliente">·${oggetto.nomeEAutore}</td>
                            <td class="tdCliente"><img title="${oggetto.nomeEAutore}" alt="foto del libro" src="${oggetto.image}" width="40" height="50"></td>
                            <td class="tdCliente">·${oggetto.quantita}</td>
                            <td class="tdCliente">·${oggetto.prezzo}</td>
                            <td class="tdCliente">
                                <!--<input type="submit" name="Carrello" value="·Aggiungi al Carrello"  id="inputdescrizioneCarrello" class="inputdescrizione">-->
                                <a class="link" href="venditore.html?idOggetto=${oggetto.getIdOggetto()}">Questo Libro</a>
                            </td></td>
                       </tr>
                   </c:if>  
                  </c:forEach>
            </c:if>
        </div>
        <footer>
        </footer>
      </div>
    </body>
</html>