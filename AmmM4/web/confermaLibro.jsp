<%-- 
    Document   : confermaLibro
    Created on : 30-apr-2016, 17.06.04
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
                    <a href="descrizione.html?idClient=${cliente.id}">Descrizione</a>
                </td>
            </tr>
            <tr class="trLogin">
             <form method="post" action="logout.html">
                <td>
                    <input type="hidden" name="Cliente" value="${cliente.id}"/>
                    <input type="submit" name="Logout" value="Logout" id="logout"></td>
             </form>
            </tr>
            </table> 
        </div>
        <div id="contenentVenditore"> 
          <c:if test="${contattore == 0}">
            <h1 id="titoloConferma">Conferma dell'ordine !</h1>
            <p class="pConferma" id="pConferma">Nome : ${oggetto.nomeEAutore}</p>
            <p class="pConferma">Immagine : <img title="${oggetto.nomeEAutore}" alt="foto del libro" src="${oggetto.image}" width="40" height="50"/></p>
            <p class="pConferma">Prezzo : ${oggetto.prezzo} euro</p>
            <p class="pConferma">Quantita : ${oggetto.quantita}</p>
            <p class="pConferma">Descrizine : ${oggetto.descrizione}</p>
            <form method="post" action="cliente.html">
              <input type="hidden" name="idClient" value="${cliente.id}"/>
              <input type="hidden" name="idOgg" value="${oggetto.idOggetto}"/>  
              <input type="submit" name="Conferma" value="Conferma" id="confermaLibro">
            </form>
         </c:if>
         <c:if test="${contattore == 1}">
             <h1>Ordine completato con successo !</h1>
             <p>Libro : ${oggetto.nomeEAutore} , comprato</p>
         </c:if>
         <c:if test="${errore.length() != null}">
             <h1>Attenzione non hai abbastanza soldi per comprare !</h1>
             <p>Libro : ${oggetto.nomeEAutore}</p>
             <p>e il suo prezzo &egrave; : ${oggetto.prezzo} </p>
         </c:if>
        </div>
        <footer>
        </footer>
      </div>
    </body>
</html>
