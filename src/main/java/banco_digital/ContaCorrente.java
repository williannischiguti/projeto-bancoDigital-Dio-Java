package banco_digital;

public class ContaCorrente extends Conta {

    public ContaCorrente(String nome, Integer agencia, String conta) {
        super(nome, agencia, conta);
    }
    
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
    }
}
