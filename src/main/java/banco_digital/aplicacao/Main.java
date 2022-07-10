package banco_digital.aplicacao;

import banco_digital.contas.Conta;

public class Main {

	public static void main(String[] args) {
		
		Conta banco = new Conta();
		banco.criarConta();
		banco.menu();
	}
}
