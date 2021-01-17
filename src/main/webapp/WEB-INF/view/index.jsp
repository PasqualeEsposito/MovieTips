<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home page"/>
</jsp:include>
<style>
    .film {
        width: 272px;
        height: 404px;
    }

    .demo-card-image.mdl-card {
        width: inherit;
        height: inherit;
        background: url('https://m.media-amazon.com/images/M/MV5BMjlmZmI5MDctNDE2YS00YWE0LWE5ZWItZDBhYWQ0NTcxNWRhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UY404_.jpg%20272w') center / cover;
    }
</style>
<main class="mdl-layout__content">
    <div class="page-content">
        <div class="mdl-grid" style="justify-content: space-evenly;">
            <div class="mdl-cell film">
                <div class="demo-card-image mdl-card mdl-shadow--2dp"></div>
            </div>
            <div class="mdl-cell film">
                <div class="demo-card-image mdl-card mdl-shadow--2dp"></div>
            </div>
            <div class="mdl-cell film">
                <div class="demo-card-image mdl-card mdl-shadow--2dp"></div>
            </div>
            <div class="mdl-cell film">
                <div class="demo-card-image mdl-card mdl-shadow--2dp"></div>
            </div>
            <div class="mdl-cell film">
                <div class="demo-card-image mdl-card mdl-shadow--2dp"></div>
            </div>
            <div class="mdl-cell film">
                <div class="demo-card-image mdl-card mdl-shadow--2dp"></div>
            </div>
        </div>
    </div>
</main>
</div>
</body>
</html>