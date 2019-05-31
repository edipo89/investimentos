/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package investimentos;

import investimento.model.CDB;
import investimento.model.Poupanca;
import investimentos.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Édipo
 */
public class Conta {
    
    private int numeroConta;    
    private Double saldo;
    private Double poupanca;
    private Double cdb;

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getPoupanca() {
        return poupanca;
    }

    public void setPoupanca(Double poupanca) {
        this.poupanca = poupanca;
    }

    public Double getCdb() {
        return cdb;
    }

    public void setCdb(Double cdb) {
        this.cdb = cdb;
    }
    
    
    
}
