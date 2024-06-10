package interfaces.objetos.contas.agencia;

import interfaces.objetos.Cliente;
import interfaces.objetos.Conta;
import interfaces.objetos.contas.ContaCorrente;
import interfaces.objetos.contas.ContaPoupanca;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AgenciaBancaria {
    public static void main(String[] args) {
        //Iniciando o programa
        AgenciaBancaria agenciaBancaria = new AgenciaBancaria();

        //Adicionando pessoas basicas
        agenciaBancaria.adicionarClienteConta("Davi", 28, 500, "Corrente");
        agenciaBancaria.adicionarClienteConta("Marco", 19, 5000, "Corrente");
        agenciaBancaria.adicionarClienteConta("Andrew", 18, 10000, "poupanca");
        agenciaBancaria.adicionarClienteConta("Jenifer", 18, 20000, "poupanca");

        //Conferência inicial
        agenciaBancaria.conferirClientesContas();
        agenciaBancaria.conferirClientes();
        agenciaBancaria.conferirContas();
        agenciaBancaria.conferirNumerosContas();
        agenciaBancaria.conferirTransacoesGeral();


        //Processo principal
        boolean funcionando = true;
        while (funcionando) { 
            System.out.println("Selecione o tipo de operacao:");
            System.out.println(" 1 - Operacoes de Cliente; \n 2 - Operacoes de Agente Bancario;\n\n 0 - Encerrar Programa.");
            int opcaoPrincipal = scanner.nextInt();
            switch (opcaoPrincipal) {
                case 1 -> {
                    boolean mesmaConta = true;
                    System.out.println("Digite o numero da Conta:");
                    long numeroConta = scanner.nextLong();
                    while (mesmaConta) {
                        agenciaBancaria.operacionarConta(numeroConta);
                        System.out.println("\nContinuar nesta conta?\n 1 - Sim.\n 0 - Nao.");
                        int continuarConta = scanner.nextInt();
                        if (continuarConta==0) {
                            mesmaConta = false;
                        }
                    }
                }
                case 2 -> agenciaBancaria.operacionarAgencia();
                case 0 -> {
                    System.out.println("Encerrando programa.");
                    funcionando = false;
                }
                default -> System.out.println("Operacao invalida.");
            }
        }
        scanner.close();
    }



    //atributos
    private int sequencia = 1;
    private List<Long> listaNumeroContas;
    private List<ContaCorrente> listaContaCorrentes;
    private List<ContaPoupanca> listaContaPoupancas;
    private Map<Cliente, Conta> agenciaMap;
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public AgenciaBancaria() {
        this.agenciaMap = new HashMap<>();
        this.listaNumeroContas = new ArrayList<>();
        this.listaContaCorrentes = new ArrayList<>();
        this.listaContaPoupancas = new ArrayList<>();
    }


    //metodos

    


    //metodos iniciais

    public void conferirContas() {
        System.out.println("\nContas de Clientes do banco:\n"+agenciaMap.values());
    }
    
    public void conferirClientesContas() {
        System.out.println("\nClientes do banco e suas contas:\n"+agenciaMap.entrySet());
    }

    public void conferirClientes() {
        System.out.println("\nClientes do banco:\n"+agenciaMap.keySet());
    }

    public void conferirNumerosContas() {
        System.out.println("\nNumeros das Contas existentes:\n"+listaNumeroContas);
    }

    private long gerarNumeroConta() {
        long numeroConta = (ThreadLocalRandom.current().nextLong(1, 999));
        numeroConta += getSequencia()*1000;
        listaNumeroContas.add(numeroConta);
        setSequencia(getSequencia()+1);
        return numeroConta;
    }



    //metodos para operacoes de contas

    private Conta selecionarConta(long numeroConta) {
        Conta contaEscolhida = null;
        if (listaNumeroContas.contains(numeroConta)) {
            for (Conta c : agenciaMap.values()) {
                if (c.getNumeroConta() == numeroConta) {
                    contaEscolhida = c;
                }
            }
        }
        return contaEscolhida;
    }

    public void operacionarConta(long numeroConta) {
        Conta conta = selecionarConta(numeroConta);
        System.out.println("\nX-----X-----X-----X\nSelecione a operacao desejada:\n 1 - Conferir Saldo;\n 2 - Depositar;\n 3 - Sacar;\n 4 - Imprimir Extrato;\n 5 - Transferir dinheiro.");
        if (conta.getTipoConta().equalsIgnoreCase("poupanca")) {
            System.out.println("\n  Operacoes adicionais:\n 6 - Conferir Taxa de Juros;");
        } else if (conta.getTipoConta().equalsIgnoreCase("corrente")) {
            System.out.println("\n  Operacoes adicionais:\n 6 - Conferir Valor Disponivel para Emprestimo; \n 7 - Pedir emprestimo;\n 8 - Pagar Emprestimo.");
        }
        int operacao = scanner.nextInt();
        if (listaNumeroContas.contains(numeroConta)) {
            switch (operacao) {
                case 1 -> conta.conferirSaldo();
                case 2 -> {
                    System.out.println("Digite a quantia do deposito:\n ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                }
                case 3 -> {
                    System.out.println("Digite a quantia do saque:\n ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                }
                case 4 -> conta.imprimirExtrato();
                case 5 -> {
                    System.out.println("Digite o valor da transferencia:\n ");
                    double valorTransferencia = scanner.nextDouble();
                    System.out.println("Digite o numero da conta do destinatario:\n ");
                    long numeroContaTransferida = scanner.nextLong();
                    intermediarTransferencia(valorTransferencia, numeroConta, numeroContaTransferida);
                }
                case 6 -> {
                    if (conta.getTipoConta().equalsIgnoreCase("poupanca")) {
                        for (ContaPoupanca c : listaContaPoupancas) {
                            if (c.getNumeroConta() == numeroConta) {
                                c.conferirTaxaJuros();
                                break;
                            }
                        }
                    } else if (conta.getTipoConta().equalsIgnoreCase("corrente")) {
                        for (ContaCorrente c : listaContaCorrentes) {
                            if (c.getNumeroConta() == numeroConta) {
                                c.conferirValorEmprestimoDisponivel();
                            }
                        }
                    } else {
                        System.out.println("Operacao invalida!");
                    }
                }
                case 7 -> {
                    if (conta.getTipoConta().equalsIgnoreCase("corrente")) {
                        for (ContaCorrente c : listaContaCorrentes) {
                            if (c.getNumeroConta() == numeroConta) {
                                System.out.println("Digite o valor que deseja receber:\n ");
                                double valorEmprestimo = scanner.nextDouble();
                                c.requisitarEmprestimo(valorEmprestimo);
                            }
                        }
                    } else {
                        System.out.println("Operacao invalida!");
                    }
                }
                case 8 -> {
                    if (conta.getTipoConta().equalsIgnoreCase("corrente")) {
                        for (ContaCorrente c : listaContaCorrentes) {
                            if (c.getNumeroConta() == numeroConta) {
                                System.out.println("Digite o valor que deseja pagar:\n ");
                                double valorPagamento = scanner.nextDouble();
                                c.pagarEmprestimo(valorPagamento);
                            }
                        }
                    } else {
                        System.out.println("Operacao invalida!");
                    }
                }
                default -> System.out.println("Operacao invalida!");
            }
        }
    }



    //metodos de operacoes da agencia

    public void adicionarClienteConta(String nome, int idade, double saldo, String tipoConta) {
        Cliente clienteNovo = new Cliente(nome, idade);
        if (tipoConta.equalsIgnoreCase("poupanca")) {
            ContaPoupanca contaPoupanca = new ContaPoupanca(saldo, gerarNumeroConta(), 1d);
            contaPoupanca.ListaTransacoes();
            clienteNovo.setNumeroContaCliente(contaPoupanca.getNumeroConta());
            agenciaMap.put(clienteNovo, contaPoupanca);
            listaContaPoupancas.add(contaPoupanca);
        } else if (tipoConta.equalsIgnoreCase("corrente")) {
            ContaCorrente contaCorrente = new ContaCorrente(saldo, gerarNumeroConta(), 1000d);
            contaCorrente.ListaTransacoes();
            clienteNovo.setNumeroContaCliente(contaCorrente.getNumeroConta());
            agenciaMap.put(clienteNovo, contaCorrente);
            listaContaCorrentes.add(contaCorrente);
        } else {
            System.out.println("Tipo de conta inexistente.");
        }
    }

    public void conferirTransacoesGeral() {
        if (!agenciaMap.isEmpty()) {
            System.out.println("Imprimindo todas as transacoes realizadas entre todas as contas:\n");
            for (Conta c : agenciaMap.values()) {
                c.imprimirExtrato();
            }
        }
    }

    public void conferirTransacoesConta(long numeroConta) {
        if (!agenciaMap.isEmpty()) {
            if (listaNumeroContas.contains(numeroConta)) {
                for (Conta c : agenciaMap.values()) {
                    if (c.getNumeroConta() == numeroConta) {
                        System.out.println("Imprimindo todas as transacoes realizadas na conta "+numeroConta+":\n");
                        c.imprimirExtrato();
                        break;
                    }
                }
            }
        }
    }

    private void intermediarTransferencia(double valorTransferencia, long numeroContaTransferencia, long numeroContaTransferida) {
        if (listaNumeroContas.contains(numeroContaTransferencia) && listaNumeroContas.contains(numeroContaTransferida)) {
            for (Conta c : agenciaMap.values()) {
                if (c.getNumeroConta() == numeroContaTransferencia) {
                    if (c.getSaldo() >= valorTransferencia) {
                        c.transferir(valorTransferencia, numeroContaTransferencia);
                    } else {
                        System.out.println("Saldo insuficiente!");
                        break;
                    }
                }
                if (c.getNumeroConta() == numeroContaTransferida) {
                    c.receberTransferencia(valorTransferencia, numeroContaTransferida);                
                }
            }
        } else {
            System.out.println("A conta desejada nao foi encontrada.");
        }
    }

    public void removerConta(long numeroConta) {
        if (!agenciaMap.isEmpty()) {
            if (listaNumeroContas.contains(numeroConta)) {
                for (Cliente c : agenciaMap.keySet()) {
                    if (c.getNumeroContaCliente() == numeroConta) {
                        agenciaMap.remove(c);
                        break;
                    }
                }
                for (ContaCorrente c : listaContaCorrentes) {
                    if (c.getNumeroConta() == numeroConta) {
                        listaContaCorrentes.remove(c);
                        break;
                    }
                }
                for (ContaPoupanca c : listaContaPoupancas) {
                    if (c.getNumeroConta() == numeroConta) {
                        listaContaPoupancas.remove(c);
                        break;
                    }
                }
                for (Long l : listaNumeroContas) {
                    if (l.equals(numeroConta)) {
                        listaNumeroContas.remove(l);
                        break;
                    }
                }
            }
        }
    }

    public void operacionarAgencia() {
        System.out.println("\nX-----X-----X-----X\nSelecione a operacao desejada:");
        System.out.println(" 1 - Conferir Transacoes Realizadas na agencia;");
        System.out.println(" 2 - Conferir Transacoes de Conta Especifica;");
        System.out.println(" 3 - Conferir Lista de Clientes do Banco;");
        System.out.println(" 4 - Conferir Lista de Contas Existentes no Banco;");
        System.out.println(" 5 - Conferir Lista de Clientes e Respectivas Contas;");
        System.out.println(" 6 - Conferir Numeros de Todas as Contas do Banco;");
        System.out.println(" 7 - Adicionar Nova Conta e Cliente;");
        System.out.println(" 8 - Remover Conta e Cliente;\n");
        int operacao = scanner.nextInt();
        switch (operacao) {
            case 1 -> conferirTransacoesGeral();
            case 2 -> {
                System.out.println("Digite o Numero da Conta desejada:\n ");
                long numeroConta = scanner.nextInt();
                conferirTransacoesConta(numeroConta);
            }
            case 3 -> conferirClientes();
            case 4 -> conferirContas();
            case 5 -> conferirClientesContas();
            case 6 -> conferirNumerosContas();
            case 7 -> {
                System.out.println("Digite os Dados Necessarios para criar a conta.");
                System.out.println(" Nome: ");
                String nome = scanner.next();
                System.out.println(" Idade: ");
                int idade = scanner.nextInt();
                while (idade < 18 || idade > 100) {
                    System.out.println("Idade invalida!\n Idade minima = 18\n Idade maxima = 100;");
                    System.out.println(" Idade: ");
                    idade = scanner.nextInt();
                }
                System.out.println(" Saldo: ");
                int saldo = scanner.nextInt();
                System.out.println(" Tipo da Conta:\n 1 = Conta Poupanca;\n 2 = Conta Corrente;\n ");
                String tipoConta = "";
                int opcao = 0;
                while (opcao != 1 && opcao != 2) {
                    opcao = scanner.nextInt();
                    switch (opcao) {
                        case 1 -> tipoConta = "poupanca";
                        case 2 -> tipoConta = "corrente";
                        default -> System.out.println("Tipo de conta inexistente.");
                    }
                }
                adicionarClienteConta(nome, idade, saldo, tipoConta);
            }
            case 8 -> {
                long numeroContaRemover = scanner.nextInt();
                removerConta(numeroContaRemover);
            }
            default -> System.out.println("Operacao invalida!");
        }
    }



    //getters e setters

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }

    public int getSequencia() {
        return sequencia;
    }
}
