<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Utente utente = (Utente) session.getAttribute("utente");
    int check = 0;
    if (utente != null) {
        if (utente.isFilmino())
            check = 1;
    }
%>
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
    <section class="mdl-layout__tab-panel" id="fixed-tab-2">
        <div class="page-content">
                <% if (check == 1) { %>
            <form action="Recensione" method="post">
                <input type="hidden" name="idFilm" value="${film.idFilm}">
                <div class="submit">

                    <input class="mdl-slider mdl-js-slider" type="range"
                           min="0" max="5" value="0" tabindex="0" id="valutazione">

                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                        Aggiungi recensione
                    </button>
                </div>
                <div style="width: -webkit-fill-available;" class="mdl-textfield mdl-js-textfield">
                    <textarea class="mdl-textfield__input" type="text" rows="1" id="testo"></textarea>
                    <label class="mdl-textfield__label" for="testo">Testo recensione:</label>
                </div>
            </form>
                <% } %>
            <hr>
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
                            <c:if test="${utente != null}">
                                <a href="Segnala?id=<c:out value="${recensione.idRecensione}"/>">Segnala recensione</a>
                            </c:if>
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