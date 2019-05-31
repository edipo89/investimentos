/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investimentos.persist;

import investimentos.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import investimentos.Extrato;
import investimentos.model.Pessoa;
import java.util.ArrayList;

/**
 *
 * @author Édipo
 */
public class ExtratoDAO {

    private Connection connection;

    public ExtratoDAO() throws ClassNotFoundException {
        connection = new ConnectionFactory().getConnection();
    }

    
    public ArrayList<Extrato> getExtrato(int numero) throws SQLException
    {
        ArrayList<Extrato> extratos = new ArrayList<Extrato>();
         
        String sql = "SELECT * FROM Extrato where conta=?";      
        try{
             PreparedStatement stmt = this.connection.prepareStatement(sql);             
             stmt.setInt(1,numero);
             ResultSet rs = stmt.executeQuery();             
        while (rs.next()) 
        {
            Extrato extrato = new Extrato();
            extrato.setConta(rs.getInt("conta"));
            extrato.setData(rs.getString("data"));
            extrato.setTipo(rs.getString("tipo"));
            extrato.setInvestimento(rs.getString("investimento"));
            extrato.setValor(rs.getDouble("valor"));
            extratos.add(extrato);
        }
        rs.close();
        stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return extratos;
    }
    

    public void inserir(Extrato extrato) {
        String sql = "INSERT INTO Extrato(conta,data,tipo,investimento,valor) "
                + "VALUES (?,?,?,?,?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, extrato.getConta());            
            stmt.setString(2, extrato.getData());
            stmt.setString(3, extrato.getTipo());
            stmt.setString(4, extrato.getInvestimento());
            stmt.setDouble(5, extrato.getValor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
