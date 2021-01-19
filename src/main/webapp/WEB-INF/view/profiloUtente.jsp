<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
</header>
<div class="page-content">
    <div>
        <p><b>Username:</b> ${profilo.username}</p>
        <p><b>Nome:</b>${profilo.nome}</p>
        <p><b>Cognome:</b>${profilo.cognome}</p>
        <p><b>E-mail:</b>${profilo.email}</p>
        <p><b>Genere:</b>${profilo.genere}</p>
        <p><b>Data di nascita:</b>${profilo.ddn}</p>
    </div>
    <hr>
    <div>
        <c:forEach items="${recensioni}" var="recensione">
            <div>
                <b>${recensione.usernameUtente}</b>
                <p><b>Voto:</b> ${recensione.valutazione}</p>
                <p>${recensione.testo}</p>
            </div>
            <hr>
        </c:forEach>
    </div>
</div>
</body>
</html>