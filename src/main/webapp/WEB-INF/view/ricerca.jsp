<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${inputRicerca}"/>
</jsp:include>
</header>
<main class="mdl-layout__content">
    <div class="page-content">
        <c:choose>
            <c:when test="${empty films}">
                <h2>Nessun risultato trovato per "${inputRicerca}"</h2>
            </c:when>
            <c:otherwise>
                <h2>Risultati trovati per "${inputRicerca}"</h2>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${films}" var="film">
            <div class="film">
                <div>
                    <a href="Film?id=<c:out value="${film.idFilm}"/>">
                        <img src="img/<c:out value="${film.idFilm}"/>.png">
                    </a>
                </div>
                <div>
                    <a href="Film?id=<c:out value="${film.idFilm}"/>">
                        <h3>${film.titolo}</h3>
                    </a>
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