package banco_digital.contatos;

import java.util.Random;
import java.util.Scanner;
import banco_digital.contas.Conta;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Contatos  {

	static Scanner sc = new Scanner(System.in, "ISO-8859-1");
	static Random random = new Random();
	static Conta conta = new Conta();

	static List<Conta> cadastrarContatos = new ArrayList<Conta>();
	static int idConta = 0;

	public Contatos() {

	}
	
	public static List<Conta> getCadastrarContatos() {
		return cadastrarContatos;
	}
	
	public static Conta getConta() {
		return conta;
	}
	
	
	public static int getIdConta() {
		return idConta;
	}

	public static void menuContatos() throws UnsupportedEncodingException {

		while (true) {
			
			System.out.println("1- Cadastrar novo contato | 2- Listar contatos | 3- Voltar");
			int opcao = sc.nextInt();

			if (opcao == 1) {
				cadastrarContatos();

			} else if (opcao == 2) {
				Conta.limparTela();

				if (cadastrarContatos.isEmpty()) {
					System.out.println("Ainda não há contatos cadastrados.");

				} else {
					PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
					System.out.println("=== Lista de contatos ===");
					for (int i = 0; i < cadastrarContatos.size(); i++) {
						ps.println("ID conta: " + cadastrarContatos.get(i).getIdConta() + " | Nome: "
								+ cadastrarContatos.get(i).getNome() + " | " + "Agencia: " + cadastrarContatos.get(i).getAgencia()
								+ " | Conta: " + cadastrarContatos.get(i).getConta());
					}
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					continue;
				}
				
			} else if (opcao == 3) {
				Conta.limparTela();
				break;
				
			} else {
				System.out.println("Opção inválida");
			}
		}
	}

	public static List<Conta> cadastrarContatos() throws UnsupportedEncodingException {

		System.out.print("Nome do contato: ");
		sc.nextLine();
		String nomeContato = sc.nextLine();
		int agencia = random.nextInt((9999 - 1000) + 1) + 1000;
		int contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
		String contaPadrao = Integer.toString(contaRandom).substring(0, 4) + "-"
				+ Integer.toString(contaRandom).substring(4, 5);
		idConta = idConta + 1;
		conta = new Conta(idConta, nomeContato, agencia, contaPadrao);
		cadastrarContatos.add(new Conta(idConta, nomeContato, agencia, contaPadrao));
		System.out.println("\nContato cadastrado!");
		System.out.println("Tecle ENTER para continuar...");
		sc.nextLine();
		Conta.limparTela();

		return cadastrarContatos;
	}
}
