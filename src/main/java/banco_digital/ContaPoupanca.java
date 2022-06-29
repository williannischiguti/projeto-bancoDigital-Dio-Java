package banco_digital;

public class ContaPoupanca extends Conta {

    public ContaPoupanca (String nome, Integer agencia, String conta) {
        super(nome, agencia, conta);
    }
    
    public ContaPoupanca () {
    	super();
    }

    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        super.imprimirInfosComuns();
    }
}
