package investimentos.persist;

import investimentos.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO 
{
    private Connection connection;
    public PessoaDAO() throws ClassNotFoundException 
    {
        connection = new ConnectionFactory().getConnection();
    }
    
    public ArrayList<Pessoa> getPessoa() throws SQLException
    {
        String sql = "SELECT * FROM Pessoa";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        while (rs.next()) 
        {
            Pessoa p = new Pessoa();
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setSexo(rs.getString("sexo"));
            p.setNasc(rs.getString("nasc"));
            p.setTel(rs.getString("tel"));
            p.setLogradouro(rs.getString("logradouro"));
            p.setNum(rs.getInt("num"));
            p.setCep(rs.getString("cep"));
            p.setUf(rs.getString("uf"));
            p.setCidade(rs.getString("cidade"));
            p.setSenha(rs.getString("senha"));
            p.setNumeroConta(rs.getInt("numeroConta"));
            listaPessoas.add(p);
        }
        rs.close();
        stmt.close();
        return listaPessoas;
    }
    
    public void inserir(Pessoa p) 
    {
    
        String sql = "INSERT INTO Pessoa(nome,cpf,sexo,nasc,tel,logradouro,num,cep,uf,cidade,senha,numeroConta) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    try 
    {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getCpf());
        stmt.setString(3, p.getSexo());
        stmt.setString(4, p.getNasc());
        stmt.setString(5, p.getTel());
        stmt.setString(6, p.getLogradouro());
        stmt.setInt(7, p.getNum());
        stmt.setString(8, p.getCep());
        stmt.setString(9, p.getUf());
        stmt.setString(10, p.getCidade());
        stmt.setString(11, p.getSenha());
        stmt.setInt(12, p.getNumeroConta());        
        stmt.execute();
        stmt.close();
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }
}
    
    public void alterar(Pessoa p) {
     String sql = "UPDATE Pessoa SET nome = ?, cpf =  ?, sexo = ? ,nasc = ?,tel = ?,logradouro = ? ,num = ?,cep = ? ,uf = ?,cidade = ? ,senha = ?, numeroConta = ?" +
        " WHERE cpf = ?";
     try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getSexo());
            stmt.setString(4, p.getNasc());
            stmt.setString(5, p.getTel());
            stmt.setString(6, p.getLogradouro());
            stmt.setInt(7, p.getNum());
            stmt.setString(8, p.getCep());
            stmt.setString(9, p.getUf());
            stmt.setString(10, p.getCidade());
            stmt.setString(11, p.getSenha());
            stmt.setInt(12, p.getNumeroConta());            
            stmt.setString(13, p.getCpf());                        
            stmt.execute();
        stmt.close();
        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }
     }

}
