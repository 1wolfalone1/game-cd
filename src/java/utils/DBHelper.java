/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class DBHelper {
    public static Connection makeConnectDB()throws /*ClassNotFoundException,*/ SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        String url = "jdbc:sqlserver://localhost:1433;databaseName=GameCD;instanceName=NNHATTHIEN";
        
        Connection con = DriverManager.getConnection(url, "sa", "12345");

        
        return con;
    }
    
    
    public static void main(String[] args) throws SQLException, NamingException, ClassNotFoundException {
        Connection con = makeConnectDB();
        
        PreparedStatement stm = con.prepareStatement("delete Users where id = ?");
        stm.setInt(1, 1);

        int k = stm.executeUpdate();
        System.out.println(k);
    }
}
