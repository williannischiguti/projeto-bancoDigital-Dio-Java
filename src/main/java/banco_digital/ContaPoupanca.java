package banco_digital;

public class ContaPoupanca extends Conta {

    public ContaPoupanca (String nome, Integer agencia, String conta) {
        super(nome, agencia, conta);
    }

    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupan�a ===");
        super.imprimirInfosComuns();
    }
}
