<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home page"/>
</jsp:include>
<main class="mdl-layout__content">
    <div class="page-content">
        <div class="mdl-cell">
            <h1>Accedi</h1>
            <form action="#">
                <div class="mdl-textfield mdl-js-textfield is-upgraded" data-upgraded=",MaterialTextfield">
                    <input class="mdl-textfield__input" type="text" id="sample1">
                    <label class="mdl-textfield__label" for="sample1">E-mail...</label></div>
                <div class="mdl-textfield mdl-js-textfield is-upgraded" data-upgraded=",MaterialTextfield">
                    <input class="mdl-textfield__input" type="text" id="sample2">
                    <label class="mdl-textfield__label" for="sample2">Password...</label>
                </div>
                <div class="mdl-cell">
                    <span>Non sei registrato?"</span>
                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
                        Accedi
                    </button>
                </div>
            </form>
        </div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>