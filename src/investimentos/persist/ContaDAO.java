package investimentos.persist;

import investimentos.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ContaDAO 
{
    private Connection connection;
    public ContaDAO() throws ClassNotFoundException 
    {
        connection = new ConnectionFactory().getConnection();
    }
    
    public Conta getConta(int numero) throws SQLException
    {        
        Conta conta = new Conta();                        
        String sql = "SELECT * FROM Conta where numeroConta=?";
        try{
        PreparedStatement stmt = connection.prepareStatement(sql);        
        stmt.setInt(1,numero);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) 
        {
            conta.setNumeroConta(rs.getInt("numeroConta"));
            conta.setSaldo(rs.getDouble("saldo"));
            conta.setPoupanca(rs.getDouble("poupanca"));
            conta.setCdb(rs.getDouble("cdb"));            
        }
        rs.close();
        stmt.close();        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conta;
    }
    
    public void inserir(Conta conta) 
    {    
        String sql = "INSERT INTO Conta(numeroConta,saldo,poupanca,cdb) " +
        "VALUES (?,?,?,?);";
    try 
    {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, conta.getNumeroConta());
        stmt.setDouble(2, conta.getSaldo());
        stmt.setDouble(3, conta.getPoupanca());
        stmt.setDouble(4, conta.getCdb());
        stmt.execute();
        stmt.close();
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }
}
    
    public void alterar(Conta conta) {
     String sql = "UPDATE Conta SET saldo = ?, poupanca =  ?, cdb = ? " +
        " WHERE numeroConta = ?";
     try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, conta.getSaldo());
            stmt.setDouble(2, conta.getPoupanca());
            stmt.setDouble(3, conta.getCdb());
            stmt.setInt(4, conta.getNumeroConta());
            stmt.execute();
        stmt.close();
        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }
     }

}
