<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Utente utente = (Utente) session.getAttribute("utente");
    int check = 0;
    if (utente != null) {
        if (utente.isModeratore())
            check = 1;
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>MovieTips - ${param.pageTitle}</title>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.red-indigo.min.css"/>
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
    <% if (check == 1) { %>
<form action="GestioneSegnalazioni">
    <button style="background: rgb(244,67,54);"
            class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored float">
        <i class="material-icons">admin_panel_settings</i>
    </button>
</form>
    <% } %>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--no-drawer-button">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <a href="."><img class="mdl-layout-icon" src="./img/logo.png"></a>
            <div class="mdl-layout-spacer"></div>
            <form action="Ricerca" method="get" accept-charset="utf-8">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
                          mdl-textfield--floating-label mdl-textfield--align-right">
                    <label class="mdl-button mdl-js-button mdl-button--icon"
                           for="fixed-header-drawer-exp">
                        <i class="material-icons">search</i>
                    </label>
                    <div class="mdl-textfield__expandable-holder">
                        <input class="mdl-textfield__input" type="text" name="inputRicerca"
                               id="fixed-header-drawer-exp">
                    </div>
                </div>
            </form>
            <nav class="mdl-navigation">
                <c:choose>
                    <c:when test="${utente == null}">
                        <a class="mdl-navigation__link" href="Accesso">Accedi</a>
                    </c:when>
                    <c:otherwise>
                        <a class="account-navigation" href="Profilo?username=<c:out value="${utente.username}"/>">
                            <span style="color: rgb(255,255,255)" class="material-icons">account_circle</span>
                        </a>
                        <a class="mdl-navigation__link" href="Disconnessione">Disconnetti</a>
                    </c:otherwise>
                </c:choose>
            </nav>
        </div>