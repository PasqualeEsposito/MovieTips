<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value=""/>
</jsp:include>
<div style="justify-content: space-around;" class="mdl-layout__tab-bar mdl-js-ripple-effect">
    <a href="#fixed-tab-1" class="mdl-layout__tab is-active">Trama e cast</a>
</div>
</header>
<main class="mdl-layout__content">
    <section class="mdl-layout__tab-panel is-active" id="fixed-tab-1">
        <div class="page-content">
            <div class="film">
                <div>
                    <img src="img/<c:out value="${film.idFilm}"/>.png">
                </div>
                <div>
                    <h3>${film.titolo} (${film.anno})</h3>
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
                <h2>Trama</h2>
                <p>${film.trama}</p>
            </div>
            <hr>
        </div>
    </section>
</main>
</div>
</body>
</html>