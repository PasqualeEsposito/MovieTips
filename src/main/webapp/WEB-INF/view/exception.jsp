<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore"/>
</jsp:include>
</header>
<main class="mdl-layout__content">
    <div class="page-content">
        <h2 style="text-align: center;"><%= exception.getMessage() %>
        </h2>
    </div>
</main>
</div>
</body>
</html>
