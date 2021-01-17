<%--
  Created by IntelliJ IDEA.
  User: Pasquale
  Date: 17/01/2021
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore ${requestScope['javax.servlet.error.status_code']}"/>
</jsp:include>

</header>
<main class="mdl-layout__content">
    <div class="page-content">
        <section>
            <h2>Errore ${requestScope['javax.servlet.error.status_code']}</h2>
        </section>
    </div>
</main>
</div>
</body>
</html>
