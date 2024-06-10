package interfaces.objetos.contas;

import interfaces.objetos.Conta;

public class ContaCorrente extends Conta{
    //atributos
    private double valorEmprestimoDisponivel;
    private double emprestimoRecebido;

    public ContaCorrente(double saldo, long numeroConta, double valorEmprestimoDisponivel) {
        super(saldo, numeroConta, "corrente");
        this.valorEmprestimoDisponivel = valorEmprestimoDisponivel;
    }

    public void conferirValorEmprestimoDisponivel() {
        System.out.printf("\nValor disponivel para emprestimo: 'R$"+valorEmprestimoDisponivel+"'.");
    }

    public void requisitarEmprestimo(double valorEmprestimo) {
        if (valorEmprestimo <= valorEmprestimoDisponivel) {
            setValorEmprestimoDisponivel(valorEmprestimoDisponivel-valorEmprestimo);
            setSaldo(getSaldo()+valorEmprestimo);
            setEmprestimoRecebido(getEmprestimoRecebido()+valorEmprestimo);
            adicionarTransacao("emprestimo requisitado", valorEmprestimo, super.getNumeroConta());
            
        } else {
            System.out.println("Valor indisponivel para emprestimo.");
            conferirValorEmprestimoDisponivel();
        }
    }

    public void pagarEmprestimo(double valorPagamento) {
        if (getSaldo() > valorPagamento) {
            if (valorPagamento > emprestimoRecebido) {
                setValorEmprestimoDisponivel(getValorEmprestimoDisponivel()+emprestimoRecebido);
                setSaldo(getSaldo()-emprestimoRecebido);
                setEmprestimoRecebido(getEmprestimoRecebido()-valorPagamento);
                System.out.println("Emprestimo completamente pago.\nO excedente retornou para a conta.");
            } else {
                if (valorPagamento == emprestimoRecebido) {
                    System.out.println("Emprestimo completamente pago.");
                } else {
                    System.out.println("Emprestimo parcialmente pago.");
                }
                setValorEmprestimoDisponivel(getValorEmprestimoDisponivel()+valorPagamento);
                setSaldo(getSaldo()-valorPagamento);
                setEmprestimoRecebido(getEmprestimoRecebido()-valorPagamento);
            }
            adicionarTransacao("emprestimo pago", valorPagamento, super.getNumeroConta());
        } else {
            System.out.println("Saldo insuficiente.");
            conferirSaldo();
        }
    }

    public double getValorEmprestimoDisponivel() {
        return valorEmprestimoDisponivel;
    }

    public void setValorEmprestimoDisponivel(double valorEmprestimoDisponivel) {
        this.valorEmprestimoDisponivel = valorEmprestimoDisponivel;
    }

    public double getEmprestimoRecebido() {
        return emprestimoRecebido;
    }

    public void setEmprestimoRecebido(double emprestimoRecebido) {
        this.emprestimoRecebido = emprestimoRecebido;
    }

    
}
