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
                    <td>Nome: </td>
                    <td>Telefone: </td>
                    <td>E-mail </td>
                    <td>Idade: </td>
                </tr>
                <%
                    for (int i = 0; i < lista.size(); i++) {
                        Cliente cliente = (Cliente) lista.get(i);
                %>
                <tr id="tabela">
                    <td><%=cliente.getNome()%></td>
                    <td><%=cliente.getTel()%></td>
                    <td><%=cliente.getEmail()%></td>
                    <td><%=cliente.getIdade()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
