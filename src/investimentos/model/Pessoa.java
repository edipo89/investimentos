package investimentos.model;

import java.util.Date;

public class Pessoa {

    private String nome;
    private String cpf;
    private String sexo;
    private String nasc;
    private String tel;
    private String logradouro;
    private int num;
    private String cep;
    private String uf;
    private String cidade;
    private String senha;
    private int numeroConta;

    public Pessoa()
    {
        
    }
    
    public Pessoa(String nome, String cpf,String sexo, String nasc, String tel, String logradouro, int num, String cep, String uf, String pais, String senha,int numeroConta) {
        this.nome = nome;
        this.cpf = cpf;        
        this.sexo = sexo;
        this.nasc = nasc;
        this.tel = tel;
        this.logradouro = logradouro;
        this.num = num;
        this.cep = cep;
        this.uf = uf;
        this.cidade = pais;
        this.senha = senha;
        this.numeroConta = numeroConta;
    }

    //---------Getters---------
    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }
   
    public String getSexo() {
        return this.sexo;
    }

    public String getNasc() {
        return this.nasc;
    }

    public String getTel() {
        return this.tel;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    ;
    public int getNum() {
        return this.num;
    }

    public String getCep() {
        return this.cep;
    }

    public String getUf() {
        return this.uf;
    }

    public String getCidade() {
        return this.cidade;
    }
    public String getSenha()
    {
        return this.senha;
    }
    public int getNumeroConta()
    {
        return this.numeroConta;
    }

    //---------Fim dos Getters---------
    

    //---------Setters---------
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public void setTel(String tel) {
        this.tel =tel;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    ;
    public void setNum(int num) {
        this.num = num;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCidade(String Cidade) {
        this.cidade = Cidade;
    }
    
    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    public void setNumeroConta(int numeroConta)
    {
        this.numeroConta = numeroConta;
    }

    //---------Fim dos Setters---------

}
