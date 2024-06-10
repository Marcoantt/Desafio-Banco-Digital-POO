package interfaces.objetos.contas;

import interfaces.objetos.Conta;

public class ContaPoupanca extends Conta{
    //atributos
    private double taxaJuros;

    public ContaPoupanca(double saldo, long numeroConta, double taxaJuros) {
        super(saldo, numeroConta, "poupanca");
        this.taxaJuros = taxaJuros;
    }

    public void conferirTaxaJuros() {
        System.out.println("A taxa de juros desta conta e de "+taxaJuros+"% por mes.");
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }


}
