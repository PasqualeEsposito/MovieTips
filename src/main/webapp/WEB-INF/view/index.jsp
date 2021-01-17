<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home page"/>
</jsp:include>
<style>
    .film {
        width: 272px;
        height: 404px;
    }

    .demo-card-image.mdl-card {
        width: inherit;
        height: inherit;
        background: url('https://m.media-amazon.com/images/M/MV5BMjlmZmI5MDctNDE2YS00YWE0LWE5ZWItZDBhYWQ0NTcxNWRhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UY404_.jpg%20272w') center / cover;
    }
</style>
<main class="mdl-layout__content">
    <div class="page-content">
        <c:forEach items="${films}" var="film">
            <div>
                <a href="film?id=<c:out value="${film.id_film}"/>"><img src="img/<c:out value="${film.id_film}"/>.jpg"></a>
                <h3><c:out value="${film.titolo}"/></h3>
            </div>
        </c:forEach>
    </div>
</main>
</div>
</body>
</html>