<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore ${requestScope['javax.control.error.status_code']}"/>
</jsp:include>
</header>
<main class="mdl-layout__content">
    <div class="page-content">
        <h2 style="text-align: center;" id="error">Errore ${requestScope['javax.servlet.error.status_code']}</h2>
    </div>
</main>
</div>
</body>
</html>
