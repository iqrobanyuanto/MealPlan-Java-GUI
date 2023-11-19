/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;
import java.util.logging.*;
/**
 *
 * @author alift
 */
public class Database {
    private static Connection connection;
    public static Connection getConnection() throws SQLException{
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mealplan","root","");
                
            } catch (ClassNotFoundException | SQLException e){
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return connection;
    }
}
