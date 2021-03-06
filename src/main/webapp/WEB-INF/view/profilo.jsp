<%@ page import="model.gestioneUtente.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    Utente profilo = (Utente) request.getAttribute("profilo");
    int check = 0;
    if (utente != null && !profilo.isBanned()) {
        if (utente.isModeratore())
            check = 1;
    }
%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${profilo.username}"/>
</jsp:include>
</header>
<div class="page-content">
    <c:choose>
        <c:when test="${utente.username != profilo.username}">
            <% if (check == 1) { %>
            <div class="submit">
                <h2>Profilo di ${profilo.username}</h2>
                <form action="BannaUtente">
                    <input type="hidden" name="username" value="${profilo.username}">
                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                        Elimina utente
                    </button>
                </form>
            </div>
            <% } else { %>
            <h2>Profilo di ${profilo.username}</h2>
            <% } %>
        </c:when>
        <c:otherwise>
            <h2>Profilo di ${profilo.username}</h2>
        </c:otherwise>
    </c:choose>
    <div>
        <p><b>Nome:</b> ${profilo.nome}</p>
        <p><b>Cognome:</b> ${profilo.cognome}</p>
        <p><b>E-mail:</b> ${profilo.mail}</p>
        <p><b>Genere:</b> ${profilo.genere}</p>
        <p><b>Data di nascita:</b> ${profilo.dataNascita}</p>
    </div>
    <div>
        <c:choose>
            <c:when test="${empty recensioni}">
                <hr style="visibility: hidden">
                <h2>Nessuna recensione da mostrare...</h2>
            </c:when>
            <c:otherwise>
                <hr style="visibility: hidden">
                <h2>Recensioni di ${profilo.username}</h2>
                <c:forEach items="${recensioni}" var="recensione">
                    <div>
                        <b>${recensione.usernameUtente}</b>
                        <p><b>Voto:</b> ${recensione.valutazione}</p>
                        <p>${recensione.testo}</p>
                        <c:if test="${utente.username == profilo.username}">
                            <form action="EliminaRecensione">
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
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>