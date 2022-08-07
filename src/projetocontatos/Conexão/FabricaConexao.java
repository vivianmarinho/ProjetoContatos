/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocontatos.Conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static projetocontatos.Conexão.FabricaConexao.closeConnection;

/**
 *
 * @author vivia
 */
public class FabricaConexao {

    private static final String url = "jdbc:postgresql://localhost:5432/pessoas";
    private static final String driver = "org.postgresql.Driver";
    private static final String usuario = "postgres";
    private static final String senha = " postgre";

    public static Connection getConnection() {

        try {

            Class.forName(driver);

            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {

            throw new RuntimeException("Erro na Conexão " + ex);
        }

    }

    public static void closeConnection(Connection c) {

        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException ex) {
           throw new RuntimeException("ERRO" + ex);
        }
    }

    public static void closeConnection(Connection c, PreparedStatement s) {

        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO" + ex);
        }

        closeConnection(c);
    }

    public static void closeConnection(Connection c, PreparedStatement s, ResultSet st) {

        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO" + ex);
        }

        closeConnection(c, s);

    }
}
