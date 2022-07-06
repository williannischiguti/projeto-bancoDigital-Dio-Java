package banco_digital.contas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ContaPoupanca extends Conta {

    public ContaPoupanca (String nome, Integer agencia, String conta) {
        super(nome, agencia, conta);
    }
    
    public ContaPoupanca () {
    	super();
    }
}
