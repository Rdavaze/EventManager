<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../commons/header.jspf"%>
<body>


<div id="login-parent">

    <form id="form-login" action="LoginServlet">

        Please enter your username
        <input type="text" name="un"/><br>

        Please enter your password
        <input type="text" name="pw"/>

        <input type="submit" value="submit">

    </form>

</div>


</body>
</html>
