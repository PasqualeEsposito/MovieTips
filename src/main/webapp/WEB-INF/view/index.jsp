<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home page"/>
</jsp:include>
<main class="mdl-layout__content">
    <div class="page-content">
        <h2>Film aggiunti di recente</h2>
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