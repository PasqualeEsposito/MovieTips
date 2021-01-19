<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
</header>
<div class="page-content">
    <c:set var="i" scope="page" value="1"/>
    <c:forEach items="${recensioni}" var="recensione">
        <div>
            <a href="Profilo?username=<c:out value="${recensione.usernameUtente}"/>"${recensione.usernameUtente}>
                    ${recensione.usernameUtente}
            </a>
            <p><b>Numero segnalazioni:</b> ${segnalazioni[i]}</p>
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
        <c:set var="i" scope="page" value="${i + 1}"/>
    </c:forEach>
</div>
</body>
</html>