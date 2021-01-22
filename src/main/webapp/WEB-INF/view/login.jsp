<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Accedi"/>
</jsp:include>
</header>
<div class="page-content" style="place-self: center;">
    <h2 style="text-align: center;">Accedi</h2>
    <div>
        <form class="form" action="Login" method="post" accept-charset="utf-8">
            <div>
                <label for="mail">Email:</label>
                <div class="mdl-textfield">
                    <input class="mdl-textfield__input" name="mail" type="text" id="mail">
                </div>
            </div>
            <div>
                <label for="password">Password:</label>
                <div class="mdl-textfield " data-upgraded=",MaterialTextfield">
                    <input class="mdl-textfield__input" name="password" type="text" id="password">
                </div>
            </div>
            <div class="submit">
                <a></a>
                <button type="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                    Accedi
                </button>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>