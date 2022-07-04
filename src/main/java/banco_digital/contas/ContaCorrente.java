package banco_digital.contas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ContaCorrente extends Conta {

    public ContaCorrente(String nome, Integer agencia, String conta) {
        super(nome, agencia, conta);
    }
    
    public ContaCorrente () {
    	super();
    }
    
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
    }
}
