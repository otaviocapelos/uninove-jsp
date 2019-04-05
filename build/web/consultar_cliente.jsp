<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Consultar Clientes pelo nome </h1>
        <form method="get" action="Controle">
            <input type="hidden" name="flag" value="consultarclientenome"/>
            <p>
                <label>Nome: <input type="text" name="nome" size="50"/></label>
                <input type="submit" value="Buscar">
            </p>
        </form>
        
        <h1> Consultar Todos Clientes cadastrados</h1>
        <<form method="get" action="Controle">
            <input type="hidden" name="flag" value="consultartodosclientes" />
            <input type="submit" value="Buscar">
        </form>
    </body>
</html>
