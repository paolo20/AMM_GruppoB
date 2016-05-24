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
         <div id="sidebarLogin">
            <!-- link esterni -->
            <table id="tabellaLink">
            <c:if test="${errore.length() != null}">
             <tr class="trLogin">
                
                <td>
                    <a href="descrizione.jsp">Descrizione</a>
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
                    <a class="trLogin" href="venditore.html?modifica=${venditore.getId()}">modifica i tuoi Libri</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="trLogin" href="venditore.html?elimina=${venditore.getId()}">elimina i tuoi Libri</a>
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
            <h2 id="autenticazione">Ciao <span>${venditore.nome}</span> </h2>
            <h2>Inserisci i dati del libro</h2>
            <!-- Form venditore -->
            <form action="venditore.html" method="post">
            <input type="hidden" name="idVend" value="${venditore.getId()}"/>
           <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- nome linbro - autore -->
            <label class="label" id="labelNome" for="name">Nome libro - Autore </label><input type="text" name="name" value="'Nome libro' - 'Autore libro'" id="name"/>
            <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- immagine libro -->
            <label class="label" id="labelImmagine" for="file">Immagine libro </label><input type="file" name="file" id="file"/>  
            <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- prezzo del libro -->
            <label class="label" id="labelPrezzo" for="prezzo">Prezzo</label><input type="text" name="prezzo" id="prezzo" value="prezzo in euro">
            <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- quantità -->
            <label class="label" id="labelQuantita" for="quantita">Quantit&agrave;</label><input type="number" name="quantita" id="quantita">
            <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- descrizione -->
            <label class="label" id="labelDescrizione" for="descrizione">Descrizione libro</label><br/><textarea rows="4" cols="20" name="descrizione" id="descrizione"></textarea>
            <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- submit e reset -->
            <input type="submit" name="Submit" value="Invia" id="submitVenditore"/>
            <input type="reset" value="Reset" id="reset"/>
            </form>
            </c:if>
        </div>
        <footer>
        </footer>
      </div>
    </body>
</html>
