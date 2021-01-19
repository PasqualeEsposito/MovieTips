<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
</header>
<div class="page-content">
    <c:forEach items="${recensioni}" var="recensione">
        <div>
            <a href="Profilo?username=<c:out value="${recensione.usernameUtente}"/>"${recensione.usernameUtente}>
                    ${recensione.usernameUtente}
            </a>
            <p><b>Numero segnalazioni:</b> ${segnalazioni.counter}</p>
            <p><b>Voto:</b> ${recensione.valutazione}</p>
            <p>${recensione.testo}</p>
            <div style="display:flex;">
                <form action="EliminaRecensione">
                    <input type="hidden" name="idRecensione" value="${recensione.idRecensione}">
                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                        Elimina recensione
                    </button>
                </form>
                <form action="IgnoraRecensione">
                    <input type="hidden" name="idRecensione" value="${recensione.idRecensione}">
                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                        Ignora recensione
                    </button>
                </form>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>
</body>
</html>