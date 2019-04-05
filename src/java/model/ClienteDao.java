/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otavio
 */
public class ClienteDao {

    private Connection conexao;
    private PreparedStatement st;

    public int conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dados", "root", "");
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            return 0;
        }
    }
    
    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int salvarCliente(Cliente cli) {
        try {
            String comando = "INSERT INTO `dados`.`cliente` (`nome`, `telefone`, `idade`, `email`) VALUES (?, ?, ?, ?)";
            st = (PreparedStatement) this.conexao.prepareStatement(comando);
            st.setString(1, cli.getNome());
            st.setString(2, cli.getTel());
            st.setInt(3, cli.getIdade());
            st.setString(4, cli.getEmail());
            st.execute();
            return 1;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                return 2;
            } else {
                return 0;
            }
        }
    }
    
    public ArrayList consultarClienteNome(String nome) {
        try {
            String sql = "SELECT * FROM CLIENTE WHERE NOME LIKE ?";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = (PreparedStatement) conexao.prepareStatement(sql);
            st.setString(1, "%"+nome+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setNome(rs.getString("nome"));
                cli.setTel(rs.getString("telefone"));
                cli.setEmail(rs.getString("email"));
                cli.setIdade(rs.getInt("idade"));
                
                lista.add(cli);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ArrayList consultarTodosCliente() {
        try {
            String sql = "SELECT * FROM CLIENTE";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = (PreparedStatement) conexao.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setNome(rs.getString("nome"));
                cli.setTel(rs.getString("telefone"));
                cli.setEmail(rs.getString("email"));
                cli.setIdade(rs.getInt("idade"));
                
                lista.add(cli);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }
}
