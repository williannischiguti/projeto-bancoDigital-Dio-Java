package banco_digital;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		List<Conta> cadastrarContatos = new ArrayList();
		Conta conta = new Conta();
		Pix pix = new Pix();
		
		System.out.println("=== Olá, seja bem vindo ao Banco Digital ===\n");
		System.out.println("Cadastre a sua conta");

		System.out.print("Digite o seu nome: ");
		String meuNome = sc.nextLine();
		int agencia = random.nextInt((9999 - 1000) + 1) + 1000;
		int contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
		String contaPadrao = Integer.toString(contaRandom).substring(0, 4) + "-" + Integer.toString(contaRandom).substring(4, 5);

		Conta minhaConta = new Conta(meuNome, agencia, contaPadrao);
		// ContaCorrente contaCorrente = new ContaCorrente(meuNome, agencia, contaPadrao);
		// ContaPoupanca contaPoupanca = new ContaPoupanca(meuNome, agencia, contaPadrao);
		System.out.println();
		
		minhaConta.imprimirDadosConta();
	
		while (true) {
			
			minhaConta.menu();
			int opcao = sc.nextInt();
			sc.nextLine();
			
			if (opcao == 1) {
				System.out.print("Nome do contato: ");
				String nomeContato = sc.nextLine();
				agencia = random.nextInt((9999 - 1000) + 1) + 1000;
				contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
				contaPadrao = Integer.toString(contaRandom).substring(0, 4) + "-" + Integer.toString(contaRandom).substring(4, 5);
				conta = new Conta (nomeContato, agencia, contaPadrao);
				cadastrarContatos.add(new Conta(nomeContato, agencia, contaPadrao));

			}else if (opcao == 2) {
				pix.menuPix();
				System.out.print("Digite a opção de cadastro Pix: ");
				int opcaoPix = sc.nextInt();
				pix.cadastrarPix(opcaoPix);
				
			}else if (opcao == 3) {
				System.out.print("Digite o valor para depósito: ");
				double valor = sc.nextDouble();
				minhaConta.depositar(valor);
				
			}else if (opcao == 4) {
				System.out.print("Digite o valor para saque: ");
				double valor = sc.nextDouble();
				minhaConta.sacar(valor);
				
			}else if (opcao == 7) {
				
				if (cadastrarContatos.isEmpty()) {
					System.out.println("Ainda não há contatos cadastrados.");
				}
				else {
					
					for (int i = 0; i < cadastrarContatos.size(); i++) {
						System.out.println("Nome: " + cadastrarContatos.get(i).nome + " | "
								+ "Agência: " + cadastrarContatos.get(i).agencia +
								" | Conta: " + cadastrarContatos.get(i).conta);
					}
				}
				
			}else if (opcao == 8) {
				break;
			}
			else {
				System.out.println("Opção inexistente!");
			}
		}
		
		sc.close();
		System.out.println("Obrigado por usar o Banco Digital! Volte sempre!");

	}
}
