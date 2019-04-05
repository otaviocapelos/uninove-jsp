<%-- 
    Document   : erro
    Created on : 23/03/2019, 16:13:23
    Author     : otavio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplo Insert</title>
    </head>
    <body>
        <h1>
            ${mensagem}
            <%
                String msg = (String) request.getAttribute("mensagem");
                out.print(msg);
            %>
        </h1>
    </body>
</html>
