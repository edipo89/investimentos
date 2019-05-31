package investimentos.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConnectionFactory
{
    
    public Connection getConnection() throws ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        try
        {
            return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Édipo\\Desktop\\Investimentos\\src\\investimentos\\persist\\investimentos.db");  
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
