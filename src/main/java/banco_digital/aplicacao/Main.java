package banco_digital.aplicacao;

import java.io.UnsupportedEncodingException;
import banco_digital.contas.Conta;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		Conta banco = new Conta();
		banco.criarConta();
		banco.menu();
	}
}
