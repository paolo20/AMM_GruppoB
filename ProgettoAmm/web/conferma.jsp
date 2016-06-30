<%-- 
    Document   : conferma
    Created on : 30-apr-2016, 16.00.50
    Author     : paolo
--%>

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
             <form method="post" action="descrizione.html">
                <td>
                    <a href="descrizione.html?idVend=${venditore.id}">Descrizione</a></td>
             </form>
            </tr>
            <tr class="trLogin">
             <form method="post" action="logout.html">
                <td>
                    <input type="hidden" name="Venditore" value="${venditore.id}"/>
                    <input type="submit" name="Logout" value="Logout" id="logout"></td>
             </form>
            </tr>
            <tr  class="trLogin">
                <td>
                    <a href="venditore.html?idVend=${venditore.id}">Venditore</a>
                </td>
            </tr>
            </table> 
        </div>
        <div id="contenentVenditore"> 
            <h1 id="titoloConferma">I dati inseriti sono stati Registrati , con successo !</h1>
            <p class="pConferma" id="pConferma">Nome : ${oggetto.nomeEAutore}</p>
            <p class="pConferma">Immagine : ${oggetto.image}</p>
            <p class="pConferma">Prezzo : ${oggetto.prezzo} euro</p>
            <p class="pConferma">Quantita : ${oggetto.quantita}</p>
            <p class="pConferma">Descrizine : ${oggetto.descrizione}</p>
        </div>
        <div id="clear">                
        </div>
        <footer id="footerDiv">
            <p id="footerP">Creato da Paolo Corpino - Progetto AMM 2016 </p>
        </footer>
      </div>
    </body>
</html>

