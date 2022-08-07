/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocontatos.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projetocontatos.Conexão.FabricaConexao;

/**
 *
 * @author vivia
 */
public class Contatos {

    public static void incluir() {

        Connection c = FabricaConexao.getConnection();
        PreparedStatement comando   = null;

        String nome     = JOptionPane.showInputDialog(" Forneça o nome:  ");
        String endereco = JOptionPane.showInputDialog(" Forneça o endereço:  ");
        String fone1    = JOptionPane.showInputDialog(" Forneça um telefone:  ");
        String fone2    = JOptionPane.showInputDialog(" Forneça outro telefone:  ");
        String email     = JOptionPane.showInputDialog(" Forneça um e-mail:  ");

        try {

            comando = c.prepareStatement("INSERT INTO contatos ( nome, endereco, fone1, fone2 , email ) VALUES ( ? , ? , ? , ? , ? ) ");
            comando.setString(1, nome);
            comando.setString(2, endereco);
            comando.setString(3, fone1);
            comando.setString(4, fone2);
            comando.setString(5, email);

            comando.executeUpdate();

            System.out.println("Inclusão realizada com sucesso!");

        } catch (SQLException ex) {
            System.out.println("Erro ao incluir a pessoas" + ex.toString());
        } finally {
            try {
                comando.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao desconectar" + ex.toString());
            }

        }
    }

    public static void recuperar() {

        Connection c = FabricaConexao.getConnection();

        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            comando = c.prepareStatement(" SELECT * FROM contatos ORDER BY nome ASC");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Endereço: " + resultado.getString("endereco"));
                System.out.println("Telefone 1: " + resultado.getString("fone1"));
                System.out.println("Telefone 2: " + resultado.getString("fone2"));
                System.out.println("E-mail: " + resultado.getString("email"));
                System.out.println("_____________________________________________");
            }
            resultado.close();

        } catch (SQLException ex) {
            System.err.println("Erro ao recuperar" + ex.toString());
        } finally {
            try {
                comando.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao desconectar" + ex.toString());
            }
        }

    }

}
