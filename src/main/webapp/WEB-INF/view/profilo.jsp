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
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
</header>
<div class="page-content">
    <% if (check == 1) { %>
    <form action="Ban">
        <input type="hidden" name="username" value="${profilo.username}">
        <button type="submit"
                class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
            Elimina utente
        </button>
    </form>
    <% } %>
    <div>
        <p><b>Username:</b> ${profilo.username}</p>
        <p><b>Nome:</b>${profilo.nome}</p>
        <p><b>Cognome:</b>${profilo.cognome}</p>
        <p><b>E-mail:</b>${profilo.email}</p>
        <p><b>Genere:</b>${profilo.genere}</p>
        <p><b>Data di nascita:</b>${profilo.ddn}</p>
    </div>
    <hr>
    <div>
        <c:forEach items="${recensioni}" var="recensione">
            <div>
                <b>${recensione.usernameUtente}</b>
                <p><b>Voto:</b> ${recensione.valutazione}</p>
                <p>${recensione.testo}</p>
                <c:if test="${utente.username == profilo.username}">
                    <form action="EliminaRecensioneProfilo">
                        <input type="hidden" name="idRecensione" value="${recensione.idRecensione}">
                        <button type="submit"
                                class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                            Elimina recensione
                        </button>
                    </form>
                </c:if>
            </div>
            <hr>
        </c:forEach>
    </div>
</div>
</body>
</html>