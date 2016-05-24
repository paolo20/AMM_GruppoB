<%-- 
    Document   : form_login
    Created on : 26-apr-2016, 20.27.39
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
                <td><a href="descrizione.jsp">Descrizione</a></td>
            </tr>
            </table> 
        </div>
            <div id="contenentLogin">         
            <h2 id="h2Login">Login</h2>
            <!-- Form login -->
            <form action="login.html" method="post">
                <c:if test="${errore.length() != null}">
                <p id="errore">Errore : username o password errate</p>
                </c:if>
            <!-- username e password -->
            <label class="label" id="labelUser" for="username">Username </label><input type="text" name="username" value="" id="username"/>
            <label class="label" id="labelPassword"  for="password">Password </label><input type="password" name="password" value="" id="password"/>    
            <!-- submit -->
            <input type="submit" name="Submit" value="Accedi" id="submit"/>
            </form>
        </div>
        <footer>
        </footer>
      </div>
    </body>
</html>
