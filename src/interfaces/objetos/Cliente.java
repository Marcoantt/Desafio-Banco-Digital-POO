package interfaces.objetos;
public class Cliente {
    //atributos
    private String nome;
    private int idade;
    private long numeroContaCliente;

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    //getters e setters

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public long getNumeroContaCliente() {
        return numeroContaCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNumeroContaCliente(long numeroContaCliente) {
        this.numeroContaCliente = numeroContaCliente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCliente = [");
        sb.append("Nome = ").append(nome);
        sb.append(", Idade = ").append(idade);
        sb.append(", Numero da Conta do Cliente = ").append(numeroContaCliente);
        sb.append(" ]");
        return sb.toString();
    }


}
