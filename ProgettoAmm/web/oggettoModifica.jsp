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
         <div  id="sideBarVend">
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
                    <a class="trLogin" href="venditore.html?modifica=${venditore.getId()}controlloLink=2">elimina i tuoi Libri</a>
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
            <c:if test="${ controllo == 1}">
            <input type="hidden" name="idVend" value="${venditore.getId()}"/>
            <input type="hidden" name="idOgg" value="${oggetto.getIdOggetto()}"/>
            <div class="cont-formVed">
                <div class="formVed">
                <!-- nome linbro - autore -->
                <label class="label" id="labelNome" for="name">Nome libro - Autore </label>
                </div>
                <div class="formVed">
                    <input type="text" name="name" value="${oggetto.nomeEAutore}" id="name"/>
                </div>
            </div>
            <input type="hidden" name="image" value="${oggetto.image}"/>
            <div class="cont-formVed">
                <div class="formVed">
                <!-- prezzo del libro -->
                <label class="label" id="labelPrezzo" for="prezzo">Prezzo</label>
                </div>
                <div class="formVed">
                    <input type="text" name="prezzo" id="prezzo" value="${oggetto.prezzo}">
                </div>
            </div>
            <div class="cont-formVed">
                <div class="formVed">
                <!-- quantità -->
                <label class="label" id="labelQuantita" for="quantita">Quantit&agrave;</label>
                </div>
                <div class="formVed">
                    <input type="number" name="quantita" id="quantita" value="${oggetto.quantita}">
                </div>
            </div>
            <div class="cont-formVed">
                <div class="formVed">
            <!-- descrizione -->
                <label class="label" id="labelDescrizione" for="descrizione">Descrizione libro</label>
                </div>
                <div class="formVed">
                <textarea rows="4" cols="20" name="descrizione" value="${oggetto.descrizione}" id="descrizione"></textarea>
                </div>
            </div>
            <div class="cont-formVed">          
            <!-- immagine libro -->
            <p id="pModidica">Se vuoi modificare l'immagine , fallo da qui
            <a id="linkCambioImage" href="venditore.html?idModifica=${oggetto.getIdOggetto()}&idVend=${venditore.getId()}&controllo=2">Modifica immagine</a>
            </p>
            </div>
            <div id="clear">                
            </div>
            <div class="form" id="formSUbmitVend">
            <!-- submit e reset -->
            <input type="submit" name="Modifica" value="Modifica" id="submitVenditore"/>
            <input type="reset" value="Reset" id="reset"/>
            </div>            
            </c:if>
            <c:if test="${controllo == 2}">
                
            <input type="hidden" name="idVend" value="${venditore.getId()}"/>   
            <input type="hidden" name="idOgg" value="${oggetto.getIdOggetto()}"/>   
             
            <!-- immagine libro -->
            <label class="label" id="labelImmagine" for="file">Immagine libro </label><input type="file" name="file" id="file"/>  
             <div class="form" id="formSUbmitVend">              
            <!-- submit e reset -->
            <input type="submit" name="ModificaImage" value="Modifica" id="submitVenditore"/>
            <input type="reset" value="Reset" id="reset"/>
             </div>
            </c:if>         
            
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
