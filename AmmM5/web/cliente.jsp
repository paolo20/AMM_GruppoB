<%-- 
    Document   : Cliente
    Created on : 25-apr-2016, 17.32.20
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
            <tr>
                <td><a href="cliente.html?ricarica=${cliente.getId()}">Ricarica il tuo saldo</a></td>
            </tr>      
            </table> 
        </div>
        <div id="contenentCliente"> 
            <c:if test="${errore.length() != null}">
                <p>Accesso negato</p>
            </c:if>
            <c:if test="${errore.length() == null}">
            <!-- Tabella libri -->
            <h2 id="autenticazione">Ciao <span>${cliente.nome}</span> il tuo saldo &egrave; <span>${cliente.getSaldo()} euro</span> e questi sono i </h2>
            <h2 >Libri in Vendita</h2>
            <!-- Tasto Ricerca -->
            <span><label for="filtra" id="labelFiltra">Filtra</label>
                <input type="search" placeholder="per nome o categoria" name="filtra" id="filtra"/></span>
            <div id="divTableCliente">
            <table id="tableCliente">   
                <tr class="titleTr">
                    <th class="tdCliente">TITOLE E AUTORE</th>
                    <th class="tdCliente">FOTO</th>
                    <th class="tdCliente">NUMERO PEZZI</th>
                    <th class="tdCliente">PREZZO</th>
                    <th class="tdCliente"></th>
                </tr>
                <c:forEach var="oggetto" items="${listaOggetti}">
                  <c:choose>
                    <c:when test="${((oggetto.getIdOggetto())% 2) == 0}" >
                        <tr class="trCliente" class="pari" >
                    </c:when>
                    <c:otherwise>
                        <tr class="trCliente" class="dispari" >
                    </c:otherwise>
                  </c:choose> 
                        <td class="tdCliente">·${oggetto.nomeEAutore}</td>
                        <td class="tdCliente"><img title="${oggetto.nomeEAutore}" alt="foto del libro" src="${oggetto.image}" width="40" height="50"></td>
                        <td class="tdCliente">·${oggetto.quantita}</td>
                        <td class="tdCliente">·${oggetto.prezzo}</td>
                        <td class="tdCliente"><a class="link" href="cliente.html?idOggetto=${oggetto.getIdOggetto()}">Compra</a></td>
                    </tr>
                  </c:forEach>
                  
            </table>
            </div>
           </c:if>
        </div>
        <footer>
        </footer>
      </div>
    </body>
</html>