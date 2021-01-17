<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>
<div class="page-content">
    <h1>Accedi</h1>
    <div style="max-width: 400px;margin: 8px;">
        <form action="login" method="post">
            <div><label style="width: 100px;text-align: left;" for="email">Email:</label>
                <div class="mdl-textfield">
                    <input class="mdl-textfield__input" name="email" type="text" id="email"></div>
            </div>
            <div><label style="width: 100px;text-align: left;" for="password">Password:</label>
                <div class="mdl-textfield " data-upgraded=",MaterialTextfield">
                    <input class="mdl-textfield__input" name="password" type="text" id="password">
                </div>
            </div>
            <div>
                <a></a>
                <button type="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                        value="login">Accedi
                </button>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>