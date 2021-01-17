<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
<main class="mdl-layout__content">
    <div class="page-content">
        <h2>Ricerca</h2>
        <c:forEach items="${films}" var="film">
            <div class="film">
                <div>
                    <a href="Film?id=<c:out value="${film.id_film}"/>">
                        <img src="img/<c:out value="${film.id_film}"/>.png">
                    </a>
                </div>
                <div>
                    <h3>${film.titolo}</h3>
                    <p><b>Genere:</b> ${film.genere}</p>
                    <p><b>Anno:</b> ${film.anno}</p>
                    <p><b>Regia:</b> ${film.regia}</p>
                </div>
            </div>
            <hr>
        </c:forEach>
    </div>
</main>
</div>
</body>
</html>