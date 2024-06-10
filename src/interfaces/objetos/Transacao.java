package interfaces.objetos;
public class Transacao {
    //atributos
    private String tipoTransacao;
    private double valorTransacao;
    private long numero;


    //constructor
    public Transacao(String tipoTransacao, double valorTransacao, long numero) {
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.numero = numero;
    }
    

    //getters e setters
    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public long getNumero() {
        return numero;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }


    @Override
    public String toString() {
        return "\nTransacao = [ Tipo de Transacao = " + tipoTransacao + ", Valor = 'R$ " + valorTransacao + "', Numero da Conta = "+ numero + " ]";
    }

    
}
