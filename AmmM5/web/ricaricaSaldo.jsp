<%-- 
    Document   : ricaricaSaldo
    Created on : 8-giu-2016, 16.06.33
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
            <tr  class="trLogin">
                <td>
                    <a href="descrizione.html?idClient=${cliente.id}">Descrizione</a>
                </td>
            <tr  class="trLogin"> 
            <form method="post" action="logout.html">
                <td>
                    <input type="hidden" name="OggettoInVendita" id="OggettoInVendita" value="${cliente.id}"/>
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
        <div id="contenentCliente">  
            <c:if test="${errore.length() != null}">
                <p>Accesso negato</p>
            </c:if>
            <c:if test="${errore.length() == null}">
            <!-- secondo titolo -->
            <h2 id="autenticazione">Ciao <span>${cliente.nome}</span> </h2>
            <c:if test="${messaggio.length() == null}">
            <h2>Ricarica il tuo saldo</h2>
            <!-- Form venditore -->
            <form action="cliente.html" method="post">
            <input type="hidden" name="idClie" value="${cliente.getId()}"/>
           <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- nome linbro - autore -->
            <label class="label" id="labelNome" for="name">Inserisci il credito </label><input type="input" name="credito" id="name">
            <!-- label vuoto -->
            <label class="labelVuoto"></label>
            <!-- submit e reset -->
            <input type="submit" name="Credito" value="Invia" id="submitVenditore"/>
            <input type="reset" value="Reset" id="reset"/>
            </form>
            </c:if>
            <c:if test="${messaggio.length() != null}">
            <h2>Il tuo credito &egrave; satto caricato con successo</h2>
            </c:if>
            </c:if>
        </div>
        <footer>
        </footer>
      </div>
    </body>
</html>
