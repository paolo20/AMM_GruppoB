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
            <div id="sideBarVend">
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
            <tr  class="trLogin">
                <td>
                    <a href="cliente.html?pagina=1">Cliente</a>
                </td>
            </tr>
            </table> 
        </div>
        <div id="contenentVenditore"> 
          <c:if test="${contattore == 0}">
             <form method="post" action="cliente.html">
            <h1 id="titoloConferma">Conferma dell'ordine !</h1>
            <div class="cont-formVed">
            <p class="pConferma" id="pConferma">Nome : ${oggetto.nomeEAutore}</p>
            </div>
            <div class="cont-formVed">
            <p class="pConferma">Immagine : <img title="${oggetto.getImage()}" alt="foto del libro" src="${oggetto.getImage()}" width="40" height="50"/></p>
            </div>
            <div class="cont-formVed">
            <p class="pConferma">Prezzo : ${oggetto.prezzo} euro</p>
            </div>
            <div class="cont-formVed">
            <label class="pConferma" id="labelQuantitaConferma" for="numero">Quantita :</label>
            <input type="number" value="1" id="numero" name="numeroQuantita"/>
            </div>
            <div id="clear">                
            </div>
            <div class="cont-formVed">
            <p class="pConferma">Descrizine : ${oggetto.descrizione}</p>
            </div>
              <input type="hidden" name="idClient" value="${cliente.id}"/>
              <input type="hidden" name="quantita" value="${oggetto.getQuantita()}"/>
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
         <c:if test="${erroreQuantita.length() != null}">
             <h1>Attenzione scelta della quantit&agrave; sbagliata da comprare !</h1>
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
