<%-- 
    Document   : index
    Created on : 21/03/2019, 21:46:12
    Author     : otavio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head>
    <body>
        <h1>Cadastro de Cliente!</h1>
        <form action="Controle" method="post">
            <input type="hidden" name="flag" value="salvarcli" />
            <p>Nome: <input type="text" name="nome" size="30">
            <p>Telefone: <input type="text" name="telefone" size="15">
            <p>Idade: <input type="text" name="idade" size="8">
            <p>E-mail: <input type="text" name="email" size="15">
            <p><input type="submit" value="Enviar"> <input type="reset" value="Limpars">
        </form>
        <p>
            <a href="consultar_cliente.jsp"> Consulte aqui os Clientes cadastrados!!! </a>
        </p>
    </body>
</html>
