<%@ page import="model.gestioneUtente.Utente" %>
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
    <c:choose>
        <c:when test="${empty recensioni}">
            <hr style="visibility: hidden">
            <h2>Nessuna segnalazione da mostrare...</h2>
        </c:when>
        <c:otherwise>
            <c:set var="i" scope="page" value="0"/>
            <c:forEach items="${recensioni}" var="recensione">
                <div>
                    <a href="Profilo?username=<c:out value="${recensione.usernameUtente}"/>">
                            ${recensione.usernameUtente}
                    </a>
                    <p><b>Voto:</b> ${recensione.valutazione}</p>
                    <p>${recensione.testo}</p>
                    <% if (check == 1) { %>
                    <div class="submit">
                        <form action="ModeraRecensione">
                            <input type="hidden" name="idRecensione" value="${recensione.idRecensione}">
                            <button type="submit"
                                    class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                Modera recensione
                            </button>
                        </form>
                        <form action="IgnoraSegnalazione">
                            <input type="hidden" name="idRecensione" value="${recensione.idRecensione}">
                            <button type="submit"
                                    class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                                Ignora segnalazione
                            </button>
                        </form>
                    </div>
                    <% } %>
                </div>
                <hr>
                <c:set var="i" scope="page" value="${i + 1}"/>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>