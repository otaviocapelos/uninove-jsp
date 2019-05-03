/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDao;

/**
 *
 * @author otavio
 */
@WebServlet(name = "Controle", urlPatterns = {"/Controle"})
public class Controle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nome, tel, email;
        int id, idade;
        String flag = request.getParameter("flag");

        if (flag.equalsIgnoreCase("salvarcli")) {
            nome = request.getParameter("nome");
            tel = request.getParameter("telefone");
            idade = Integer.parseInt(request.getParameter("idade"));
            email = request.getParameter("email");

            Cliente cli = new Cliente();

            cli.setIdade(idade);
            cli.setNome(nome);
            cli.setTel(tel);
            cli.setEmail(email);

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagem = "Erro ao se conectar ao banco de dados";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.salvarCliente(cli);
                if (r == 1) {
                    mensagem = "Cliente Cadastrado!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("cadastrosucesso.jsp");
                    d.forward(request, response);
                } else {
                    mensagem = "Ocorreu algum erro!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
            }
            dao.desconectar();
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher d = request.getRequestDispatcher("cadastrosucesso.jsp");
            d.forward(request, response);
        } else if (flag.equalsIgnoreCase("consultarclientenome")) {
            nome = request.getParameter("nome");
            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();
            if (r == 0) {
                mensagem = "erro na conexão com o bd";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                ArrayList lista = dao.consultarClienteNome(nome);
                if (!lista.isEmpty()) {
                    request.setAttribute("lista", lista);
                    RequestDispatcher d = request.getRequestDispatcher("exibir_cliente.jsp");
                    d.forward(request, response);
                    return;
                } else {
                    mensagem = "Usuário não encontrado!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("mensagens.jsp");
                    d.forward(request, response);
                }
                dao.desconectar();
            }
        } else if (flag.equalsIgnoreCase("consultartodosclientes")) {
            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();
            if (r == 0) {
                mensagem = "erro na conexão com o bd";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                ArrayList lista = dao.consultarTodosCliente();
                if (!lista.isEmpty()) {
                    request.setAttribute("lista", lista);
                    RequestDispatcher d = request.getRequestDispatcher("exibir_cliente.jsp");
                    d.forward(request, response);
                    return;
                } else {
                    mensagem = "Usuário não encontrado!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("mensagens.jsp");
                    d.forward(request, response);
                }
                dao.desconectar();
            }
        } else if (flag.equalsIgnoreCase("alterarcliente")) {
            id = Integer.parseInt(request.getParameter("id"));
            nome = request.getParameter("nome");
            tel = request.getParameter("telefone");
            idade = Integer.parseInt(request.getParameter("idade"));
            email = request.getParameter("email");

            Cliente cli = new Cliente();
            cli.setId(id);
            cli.setIdade(idade);
            cli.setNome(nome);
            cli.setTel(tel);
            cli.setEmail(email);

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagem = "Erro ao se conectar ao banco de dados";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.alterarCliente(cli);
                if (r == 1) {
                    mensagem = "Cliente Alterado!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("index.jsp");
                    d.forward(request, response);
                } else {
                    mensagem = "Ocorreu algum erro!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
            }
            dao.desconectar();
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher d = request.getRequestDispatcher("index.jsp");
            d.forward(request, response);
        } else if (flag.equalsIgnoreCase("excluircliente")) {
            id = Integer.parseInt(request.getParameter("id"));

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagem = "Erro ao se conectar ao banco de dados";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.excluirCliente(id);
                if (r == 1) {
                    mensagem = "Cliente Excluido!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("index.jsp");
                    d.forward(request, response);
                } else {
                    mensagem = "Ocorreu algum erro!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
            }
            dao.desconectar();
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher d = request.getRequestDispatcher("index.jsp");
            d.forward(request, response);
        } else {
            mensagem = "Erro na flag!!!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
            d.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
