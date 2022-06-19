package banco_digital;

import org.apache.commons.lang3.RandomStringUtils;

public class Conta implements Iconta   { 

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void transferir(double valor, Iconta contaDestino){
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
    
	public void gerarChavePixAleatoria() {
		String pix = RandomStringUtils.randomAlphanumeric(32);
		 System.out.printf("Pix: " + pix.substring(0, 8) + "-" + pix.substring(8, 12)
	        + "-" + pix.substring(12, 16) + "-" + pix.substring(16, 20) + "-" + pix.substring(20, 32));
    	// máscara: ########-####-####-####-############
    	//8-4-4-4-12
    }
    
    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
    
    public void imprimirExtrato() {
    	
    }

}
