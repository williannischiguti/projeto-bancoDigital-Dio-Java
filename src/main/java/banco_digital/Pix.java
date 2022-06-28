package banco_digital;

import org.apache.commons.lang3.RandomStringUtils;

public class Pix extends Conta {
	public Pix(String nome, Integer agencia, String conta) {
		super(nome, agencia, conta);
	}
	
	public Pix() {
		super();
	}
	
	public void cadastrarPix() {
		
	}
    
    public void imprimirExtrato() {
        super.imprimirInfosComuns();
    }
    
	public void gerarChavePixAleatoria() {
		String pix = RandomStringUtils.randomAlphanumeric(32);
		 System.out.printf("Pix: " + pix.substring(0, 8) + "-" + pix.substring(8, 12)
	        + "-" + pix.substring(12, 16) + "-" + pix.substring(16, 20) + "-" + pix.substring(20, 32));
    }
    
}
