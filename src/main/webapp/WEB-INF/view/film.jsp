<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
<div style="justify-content: space-around;" class="mdl-layout__tab-bar mdl-js-ripple-effect">
    <a href="#fixed-tab-1" class="mdl-layout__tab is-active">Trama e cast</a>
    <a href="#fixed-tab-2" class="mdl-layout__tab">Recensioni</a>
</div>
</header>
<main class="mdl-layout__content">
    <div class="page-content">
        <h2>${film.titolo} (${film.anno})</h2>
    </div>
    <section class="mdl-layout__tab-panel is-active" id="fixed-tab-1">
        <div class="page-content">
            <div class="film">
                <div>
                    <img src="img/<c:out value="${film.idFilm}"/>.png">
                </div>
                <div>
                    <p><b>Genere:</b> ${film.genere}</p>
                    <p><b>Anno:</b> ${film.anno}</p>
                    <p><b>Regia:</b> ${film.regia}</p>
                    <p><b>Attori:</b> ${film.attori}</p>
                    <p><b>Paese:</b> ${film.paese}</p>
                    <p><b>Durata:</b> ${film.durata}</p>
                    <p><b>Distribuzione:</b> ${film.distribuzione}</p>
                    <p><b>Sceneggiatura:</b> ${film.sceneggiatura}</p>
                    <p><b>Fotografia:</b> ${film.fotografia}</p>
                    <p><b>Musiche:</b> ${film.musiche}</p>
                    <p><b>Produzione:</b> ${film.produzione}</p>
                </div>
            </div>
            <hr>
            <div>
                <h3>Trama</h3>
                <p>${film.trama}</p>
            </div>
            <hr>
        </div>
    </section>
    <section>
        <div class="page-content">
            <div>
                <c:forEach items="${recensioni}" var="recensione">
                    <div class="recensione">
                        <div>
                            <b>${recensione.usernameUtente}</b>
                            <p><b>Voto:</b> ${recensione.valutazione}</p>
                        </div>
                        <div>
                            <p>${recensione.testo}</p>
                        </div>
                        <div>
                            <a href="Segnala?id=<c:out value="${recensione.idRecensione}"/>">Segnala recensione</a>
                        </div>
                    </div>
                    <hr>
                </c:forEach>
            </div>
    </section>
</main>
</div>
</body>
</html>