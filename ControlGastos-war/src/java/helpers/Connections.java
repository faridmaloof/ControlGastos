/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import java.sql.Connection;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author farid
 */
public class Connections {
    public static Connection con = null;
    
    public static Connection getConnection() throws ClassNotFoundException{
        if(con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/control_gastos?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull", "root", "");
            } catch (SQLException | ClassNotFoundException se) {
                throw new RuntimeException("Error al crear la conexion: " + se.toString() +" Popo");
            }
        }
        return con;
    }
    
    /**
     * 
     * @param sql
     * @return
     * @throws ClassNotFoundException 
     */
    public static int insertarUpdate(String sql) throws ClassNotFoundException, SQLException{
        int results = 0;
        Statement command = null;
        try {
            con  = getConnection();
            command = (Statement) con.createStatement();
            results = command.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            System.out.println("ocurrio un error..." + ex.getMessage());
            //   setTitle(ex.toString());
        } finally {
            if (command != null)
                command.close();
        }
        
        return results;
    }
    
    /**
     * 
     * @param sql
     * @return
     * @throws ClassNotFoundException 
     */
    public static ResultSet select(String sql) throws ClassNotFoundException, SQLException{
        Statement command = null;
        try {
            con  = getConnection();
            command = (Statement) con.createStatement();
            return command.executeQuery(sql);
            //  labelResultado.setText("se registraron los datos");
        } catch (SQLException ex) {
            System.out.println("ocurrio un error..." + ex.getMessage());
            return null;
        }finally {
            if (command != null)
                command.close();
        }
    }
}
