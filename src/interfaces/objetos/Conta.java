package interfaces.objetos;

import interfaces.IConta;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta{
    //atributos
    private List<Transacao> transacoesList;
    private double saldo;
    private long numeroConta;
    private String tipoConta;
    public char[] getTransacoesList;

    public Conta(double saldo, long numeroConta, String tipoConta) {
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
    }



    //metodos

    

    public void ListaTransacoes() {
        this.transacoesList = new ArrayList<>();
    }

    protected void adicionarTransacao(String tipoTransacao, double valorTransacao, long numeroContaTransferencia) {
        transacoesList.add(new Transacao(tipoTransacao, valorTransacao, numeroContaTransferencia));
    }

    @Override
    public void conferirSaldo() {
        if (saldo != 0) {
            System.out.println("Seu saldo e de 'R$ " + saldo+"'.");
        } else {
            System.out.println("Voce nao possui saldo nesta conta.");
        }
    }

    @Override
    public void depositar(double valorTransacao) {
        saldo += valorTransacao;
        adicionarTransacao("deposito", valorTransacao, numeroConta);
    }

    @Override
    public void sacar(double valorTransacao) {
        if (saldo != 0) {
            saldo -= valorTransacao;
            adicionarTransacao("saque", valorTransacao, numeroConta);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Extrato da conta "+numeroConta+":\n"+transacoesList);
    }

    @Override
    public void transferir(double valorTransacao, long numeroContaTransferencia) {
        if (saldo != 0) {
            saldo -= valorTransacao;
            adicionarTransacao("transferencia enviada", valorTransacao, numeroContaTransferencia);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void receberTransferencia(double valorTransacao, long numeroConta) {
        adicionarTransacao("transferencia recebida", valorTransacao, numeroConta);
        saldo += valorTransacao;
    }



    //getters e setters

    

    public List<Transacao> getTransacoesList() {
        return transacoesList;
    }

    public double getSaldo() {
        return saldo;
    }

    public long getNumeroConta() {
        return numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTransacoesList(List<Transacao> transacoesList) {
        this.transacoesList = transacoesList;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNumeroConta(long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }



    @Override
    public String toString() {
        return "\nConta [ Saldo = 'R$ " + saldo + "', Numero da Conta = " + numeroConta + ", Tipo de Conta = " + tipoConta + " ]";
    }

    
}
