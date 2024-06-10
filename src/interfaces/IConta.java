package interfaces;

public interface IConta {
    public void sacar(double valorTransacao);

    public void depositar(double valorTransacao);

    public void conferirSaldo();

    public void transferir(double valorTransacao, long numeroContaTransferencia);

    public void imprimirExtrato();
}
