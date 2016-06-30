<%-- 
    Document   : tabella
    Created on : 10-giu-2016, 10.25.47
    Author     : paolo
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="oggetto" items="${listaOggetti}">
        <json:object> 
            <json:property name="nomeEAutore" value="${oggetto.nomeEAutore}"/>
            <json:property name="image" value="${oggetto.image}"/>
            <json:property name="quantita" value="${oggetto.quantita}"/>            
            <json:property name="prezzo" value="${oggetto.prezzo}"/>
            <json:property name="idOggetto" value="${oggetto.getIdOggetto()}"/>
        </json:object>
    </c:forEach>
</json:array>

