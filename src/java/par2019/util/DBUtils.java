/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package par2019.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauricio
 */
public class DBUtils {

    public static Connection getConnection() throws SQLException {
        try {
//            Class.forName("org.postgresql.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/home", "postgres", "1996");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homeresident", "root", "");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homer", "root", "");
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
