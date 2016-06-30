<%-- 
    Document   : venditore
    Created on : 26-apr-2016, 20.04.52
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
            <c:if test="${errore.length() != null}">
             <tr class="trLogin">
                
                <td>
                    <a href="descrizione.html">Descrizione</a>
                </td>
            </tr>      
            </c:if>
            <c:if test="${errore.length() == null}">
            <tr class="trLogin">
                
                <td>
                    <a href="descrizione.html?idVend=${venditore.getId()}">Descrizione</a>
                </td>
            </tr>
            <tr class="trLogin">
             <form method="post" action="logout.html">
                <td>
                    <input type="hidden" name="Venditore" value="${venditore.getId()}"/>
                    <input type="submit" name="Logout" value="Logout" id="logout">
                </td>
            </tr> 
            <tr>
                <td>
                    <a class="trLogin" href="venditore.html?modifica=${venditore.getId()}&controlloLink=1">modifica i tuoi Libri</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="trLogin" href="venditore.html?modifica=${venditore.getId()}&controlloLink=2">elimina i tuoi Libri</a>
                </td>
            </tr>
             </form>
            </c:if>
            </table> 
        </div>
        <div id="contenentVenditore"> 
            <c:if test="${errore.length() != null}">
                <p>Accesso negato</p>
            </c:if>
            <c:if test="${errore.length() == null}">
            <!-- secondo titolo -->
            <h2 id="autenticazione">Ciao <span>${venditore.nome}</span> il tuo saldo &egrave; <span>${venditore.getSaldo()} euro</span> e questi sono i  </h2>
            <h2>Inserisci i dati del libro</h2>
            <!-- Form venditore -->
            <form action="venditore.html" method="post">
            <input type="hidden" name="idVend" value="${venditore.getId()}"/>
            <!-- nome linbro - autore -->
            <div class="cont-formVed">
                <div class="formVed">
                    <label class="label" id="labelNome" for="name">Nome libro - Autore </label>
                </div>
                <div class="formVed">    
                    <input type="text" name="name" placeholder="Nome del libro - E autore" id="name"/>
                </div>
            </div>
            <div class="cont-formVed">
                <div class="formVed">
                <!-- immagine libro -->
                    <label class="label" id="labelImmagine" for="file">Immagine libro </label>
                </div>
                <div class="formVed">
                    <input type="file" name="file" id="file"/>  
                </div>
            </div>
            <!-- prezzo del libro -->
            <div class="cont-formVed">
                <div class="formVed">
                    <label class="label" id="labelPrezzo" for="prezzo">Prezzo</label>
                </div>
                <div class="formVed">
                    <input type="text" name="prezzo" id="prezzo" placeholder="prezzo in euro">
                </div>
            </div>
            <!-- quantità -->
            <div class="cont-formVed">
                <div class="formVed">
                    <label class="label" id="labelQuantita" for="quantita">Quantit&agrave;</label>
                </div>
                <div class="formVed">
                    <input type="number" name="quantita" id="quantita">
                </div>
            </div>
            <!-- descrizione -->
            <div class="cont-formVed">
                <div class="formVed">
                    <label class="label" id="labelDescrizione" for="descrizione">Descrizione libro</label>
                </div>
                <div class="formVed">
                    <textarea rows="4" cols="20" placeholder="categoria del libro o le sue caratteristiche" name="descrizione" id="descrizione"></textarea>
                </div>
            </div>
            <div id="clear">                
            </div>
            <!-- submit e reset -->
            <div class="form" id="formSUbmitVend">
                <input type="submit" name="Submit" value="Invia" id="submitVenditore"/>
                <input type="reset" value="Reset" id="reset"/>
            </div>
            </form>
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
