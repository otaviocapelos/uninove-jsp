/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
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
        int idade;
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
            } else {
                r = dao.salvarCliente(cli);
                if (r == 1) {
                    mensagem = "Cliente Cadastrado!!!";
                } else {
                    mensagem = "Ocorreu algum erro!!!";
                }
            }
            dao.desconectar();
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher d = request.getRequestDispatcher("cadastrosucesso.jsp");
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
