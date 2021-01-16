<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>
<main class="mdl-layout__content">
    <div class="page-content" style="
    text-align: -webkit-center;
">
        <div class="mdl-cell">
            <h1>Accedi</h1>
            <form action="login" method="post">
                <div class="mdl-textfield mdl-js-textfield is-upgraded" data-upgraded=",MaterialTextfield">
                    <label for="email">E-mail...</label>
                    <input class="mdl-textfield__input" name="email" type="text" id="email"></div>
                <br>
                <div class="mdl-textfield mdl-js-textfield is-upgraded" data-upgraded=",MaterialTextfield">
                    <label for="password">Password...</label>
                    <input class="mdl-textfield__input" name="password" type="text" id="password">
                </div>
                <div style="display: flex;justify-content: space-between;align-items: center;width: 300px;">
                    <a style="color: rgb(244,67,54);">Non sei registrato?</a>
                    <button type="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                            value="login" data-upgraded=",MaterialButton,MaterialRipple">Accedi<span
                            class="mdl-button__ripple-container"><span class="mdl-ripple"></span></span></button>
                </div>
            </form>
        </div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>