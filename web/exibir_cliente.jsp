<%@page import="model.Cliente" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibir Clientes</title>
        <style>
            table {
                border-collapse: collapse;
            }

            table, td, th {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <div id="conteudo">
            <%
                ArrayList lista = (ArrayList) request.getAttribute("lista");
            %>
            <table>
                <tr id="tabela">
                    <td>Cliente </td>
                </tr>
                <%
                    for (int i = 0; i < lista.size(); i++) {
                        Cliente cliente = (Cliente) lista.get(i);
                %>
                <tr id="tabela">
                <td>
                    <form action="Controle" method="post">
                    <input id="metodo" type="hidden" name="flag" value="" />
                    <p>Id: <input id="id" type="hidden" name="id" value="<%=cliente.getId()%>" size="30">
                    <p>Nome: <input type="text" name="nome" value="<%=cliente.getNome()%>" size="30">
                    <p>Telefone: <input type="text" name="telefone" value="<%=cliente.getTel()%>" size="15">
                    <p>Idade: <input type="text" name="idade" value="<%=cliente.getIdade()%>" size="8">
                    <p>E-mail: <input type="text" name="email" value="<%=cliente.getEmail()%>" size="15">
                    <p><input onclick="alterarMetodo(false)" type="submit" value="Alterar">
                    <p><input onclick="alterarMetodo(true)" type="submit" value="Excluir">
                    <input type="reset" value="Limpar">
                    </form>
                </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
            <script type="text/javascript">
                function alterarMetodo(alterar) {
                    if (alterar) {
                        var element = document.getElementById("metodo")
                        element.value = "excluircliente"
                        element.defaultValue = "excluircliente"
                    }
                    else {
                        var element = document.getElementById("metodo")
                        element.value = "alterarcliente"
                        element.defaultValue = "alterarcliente"
                    }
                }
            </script>
    </body>
</html>
