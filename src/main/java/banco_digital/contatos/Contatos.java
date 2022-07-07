package banco_digital.contatos;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import banco_digital.comparator.ComparatorContatosCrescente;
import banco_digital.comparator.ComparatorContatosDecrescente;
import banco_digital.comparator.ComparatorContatosIDCrescente;
import banco_digital.contas.Conta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Contatos {

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

			try {

				System.out.println("\n1- Cadastrar novo contato | 2- Listar contatos | 3- Voltar");
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
									+ cadastrarContatos.get(i).getNome() + " | " + "Agencia: "
									+ cadastrarContatos.get(i).getAgencia() + " | Conta: "
									+ cadastrarContatos.get(i).getConta());
						}

						sc.nextLine();

						System.out.print("\nTecle 1 para ordenação alfabética (A- Z)");
						System.out.print("\nTecle 2 para ordenação alfabética (Z- A)");
						System.out.print("\nTecle 3 para ordenação por ID (padrão)");
						System.out.println("\nTecle 4 para voltar");
						int ordenacao = sc.nextInt();

						if (ordenacao == 1) {
							Conta.limparTela();
							System.out.println("=== Lista de contatos (A-Z) ===");
							for (int i = 0; i < cadastrarContatos.size(); i++) {
								Collections.sort(cadastrarContatos, new ComparatorContatosCrescente());
								ps.println("ID conta: " + cadastrarContatos.get(i).getIdConta() + " | Nome: "
										+ cadastrarContatos.get(i).getNome() + " | " + "Agencia: "
										+ cadastrarContatos.get(i).getAgencia() + " | Conta: "
										+ cadastrarContatos.get(i).getConta());
							}
						}

						else if (ordenacao == 2) {
							Conta.limparTela();
							System.out.println("=== Lista de contatos (Z-A) ===");
							for (int i = 0; i < cadastrarContatos.size(); i++) {
								Collections.sort(cadastrarContatos, new ComparatorContatosDecrescente());
								ps.println("ID conta: " + cadastrarContatos.get(i).getIdConta() + " | Nome: "
										+ cadastrarContatos.get(i).getNome() + " | " + "Agencia: "
										+ cadastrarContatos.get(i).getAgencia() + " | Conta: "
										+ cadastrarContatos.get(i).getConta());
							}
						}

						else if (ordenacao == 3) {
							Conta.limparTela();
							System.out.println("=== Lista de contatos ===");
							for (int i = 0; i < cadastrarContatos.size(); i++) {
								Collections.sort(cadastrarContatos, new ComparatorContatosIDCrescente());
								ps.println("ID conta: " + cadastrarContatos.get(i).getIdConta() + " | Nome: "
										+ cadastrarContatos.get(i).getNome() + " | " + "Agencia: "
										+ cadastrarContatos.get(i).getAgencia() + " | Conta: "
										+ cadastrarContatos.get(i).getConta());
							}
						}

						else if (ordenacao == 4) {
							sc.nextLine();
							Conta.limparTela();
							continue;
						}
						else {
							System.out.println("Opção inválida");
							sc.nextLine();
							System.out.println("\nTecle ENTER para continuar...");
							sc.nextLine();
							Conta.limparTela();
							continue;
						}
					}

				} else if (opcao == 3) {
					Conta.limparTela();
					break;

				} else {
					System.out.println("Opção inválida");
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					continue;
				}
			} catch (InputMismatchException erro) {
				System.out.println("Opção inválida!");
				sc.nextLine();
				System.out.println("Tecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
			}
		}
	}

	public static List<Conta> cadastrarContatos() throws UnsupportedEncodingException {

		System.out.print("Nome do contato: ");
		sc.nextLine();
		String nomeContato = sc.nextLine();

		while (nomeContato.isBlank() || !nomeContato.matches("[A-Z][a-z][A-z]*")) {
			System.out.println("Nome inválido");
			System.out.println("\nTecle ENTER para continuar...");
			sc.nextLine();
			Conta.limparTela();
			System.out.println("\n1- Cadastrar novo contato | 2- Listar contatos | 3- Voltar");
			System.out.print("\nNome do contato: ");
			nomeContato = sc.nextLine();
		}

		int agencia = random.nextInt((9999 - 1000) + 1) + 1000;

		int contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
		String contaPadrao = Integer.toString(contaRandom).substring(0, 4) + "-"
				+ Integer.toString(contaRandom).substring(4, 5);

		idConta = idConta + 1;
		conta = new Conta(idConta, nomeContato, agencia, contaPadrao);
		cadastrarContatos.add(new Conta(idConta, nomeContato.toUpperCase(), agencia, contaPadrao));
		System.out.println("\nContato cadastrado!");
		System.out.println("Tecle ENTER para continuar...");
		sc.nextLine();
		Conta.limparTela();

		return cadastrarContatos;
	}

}
