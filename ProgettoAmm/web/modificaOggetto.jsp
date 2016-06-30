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
        <div id="contenentCliente"> 
            <c:if test="${errore.length() != null}">
                <p>Accesso negato</p>
            </c:if>
            <c:if test="${errore.length() == null}">
            <!-- secondo titolo -->
            <h2 id="autenticazione">Ciao <span>${venditore.nome}</span> </h2>
            <c:if test="${controlloLink == 1}">
              <h2>Quale libro vuoi modificare ?</h2>  
            </c:if>
            <c:if test="${controlloLink == 2}">
              <h2>Quale libro vuoi eliminare ?</h2>  
            </c:if>
            <div id="divtable">
            <table id="tableCliente">
                <tr class="titleTr">
                    <th class="tdCliente">TITOLE E AUTORE</th>
                    <th class="tdCliente">FOTO</th>
                    <th class="tdCliente">NUMERO PEZZI</th>
                    <th class="tdCliente">PREZZO</th>
                    <th class="tdCliente"></th>
                </tr>
            <c:forEach var="oggetto" items="${venditore.listaOggetti}">
                
                  <c:if test="${((oggetto.getIdOggetto())% 2) == 0}">
                      <tr class="pari">
                  </c:if>
                  <c:if test="${((oggetto.getIdOggetto())% 2) != 0}">
                       <tr class="dispari">
                   </c:if> 
                         <td class="tdCliente">·${oggetto.nomeEAutore}</td>
                            <td class="tdCliente"><img title="${oggetto.nomeEAutore}" id="image" alt="foto del libro" src="${oggetto.image}" width="40" height="50"></td>
                            <td class="tdCliente">·${oggetto.getQuantita()}</td>
                            <td class="tdCliente">·${oggetto.prezzo}</td>
                            <td class="tdCliente">
                                <c:if test="${controlloLink == 1}">
                                <a class="link" href="venditore.html?idModifica=${oggetto.getIdOggetto()}&idVend=${venditore.getId()}&controllo=1">Questo Libro</a>
                                </c:if>
                                <c:if test="${controlloLink == 2}">
                                 <a class="link" href="venditore.html?idElimina=${oggetto.getIdOggetto()}&idVend=${venditore.getId()}">Questo Libro</a>  
                                </c:if>
                            </td>
                       </tr>
                  </c:forEach>
            </table>
            </div>
            </c:if>
        </div>
        <div id="clear">                
        </div>
        <footer id="footerDiv">
            <p id="footerP">Creato da Paolo Corpino - Progetto AMM 2016 </p>
        </footer>
      </div>
    </body>
</html>