/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessObjet;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farid
 */
public class Connections {
    private static Connection connection;
    
    public Connections() {
        try {
            if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/control_gastos?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull", "root", "");
            }
        } catch (SQLException | ClassNotFoundException se) {
            throw new RuntimeException("Error al crear la conexion: " + se.toString());
        }
    }

    public Connection getConnection() {
        return connection;
    }   
    
    public void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
