package banco_digital;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in, "ISO-8859-1");
		PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
		Random random = new Random();
		List<Conta> cadastrarContatos = new ArrayList<Conta>();
		List<Historico> transacao = new ArrayList<Historico>();
		List<Historico> transacaoEntradas = new ArrayList<Historico>();
		List<Historico> transacaoSaidas = new ArrayList<Historico>();
		Conta conta = new Conta();
		Pix pix = new Pix();
		Historico historico = new Historico();
		String historicoEntrada = null;
		String historicoSaida = null;
		String tipoConta = null;
		Double valor = 0.0;
		Historico entradas = new Historico(historicoEntrada, tipoConta, valor);
		Historico saidas = new Historico(historicoEntrada, tipoConta, valor);
		int idConta = 0;

		System.out.println("=== Olá, seja bem vindo ao Banco Digital ===\n");
		System.out.println("Cadastre a sua conta");

		System.out.print("Digite o seu nome: ");
		String meuNome = sc.nextLine();
		int agencia = random.nextInt((9999 - 1000) + 1) + 1000;
		int contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
		String contaPadrao = Integer.toString(contaRandom).substring(0, 4) + "-"
				+ Integer.toString(contaRandom).substring(4, 5);

		Conta minhaConta = new Conta(meuNome, agencia, contaPadrao);
		ContaCorrente contaCorrente = new ContaCorrente(meuNome, agencia, contaPadrao);
		ContaPoupanca contaPoupanca = new ContaPoupanca(meuNome, agencia, contaPadrao);
		System.out.println();

		minhaConta.imprimirDadosConta();

		System.out.println("Tecle ENTER para continuar...");
		sc.nextLine();
		minhaConta.limparTela();

		while (true) {

			minhaConta.menu();
			int opcao = sc.nextInt();
			sc.nextLine();

			if (opcao == 1) {
				System.out.print("Nome do contato: ");
				String nomeContato = sc.nextLine();
				agencia = random.nextInt((9999 - 1000) + 1) + 1000;
				contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
				contaPadrao = Integer.toString(contaRandom).substring(0, 4) + "-"
						+ Integer.toString(contaRandom).substring(4, 5);
				idConta = idConta + 1;
				conta = new Conta(idConta, nomeContato, agencia, contaPadrao);
				cadastrarContatos.add(new Conta(idConta, nomeContato, agencia, contaPadrao));
				conta.limparTela();

			} else if (opcao == 2) {
				conta.limparTela();
				pix.menuPix();
				System.out.print("Digite a opção de cadastro Pix: ");
				int opcaoPix = sc.nextInt();
				pix.cadastrarPix(opcaoPix);

			} else if (opcao == 3) {
				conta.limparTela();
				System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
				System.out.println("\nPara qual conta deseja fazer o depósito?");
				System.out.print("\nDigite 1 para conta corrente ou 2 para conta poupança: ");
				int opcaoConta = sc.nextInt();
				
				if (opcaoConta == 1) {
					System.out.print("\nDigite o valor para depósito: ");
					valor = sc.nextDouble();
					contaCorrente.depositar(valor);
					historicoEntrada = "Depósito";
					tipoConta = "conta corrente";
					entradas = new Historico(historicoEntrada, tipoConta, valor);
					transacaoEntradas.add(entradas);
					transacao.add(entradas);
					System.out.printf("%s%.2f%n",historico.gerarComprovante(opcao, opcaoConta, valor), entradas.getValor());
					System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
				} else if (opcaoConta == 2) {
					System.out.print("\nDigite o valor para depósito: ");
					valor = sc.nextDouble();
					contaPoupanca.depositar(valor);
					historicoEntrada = "Depósito";
					tipoConta = "conta poupança";
					entradas = new Historico(historicoEntrada, tipoConta, valor);
					transacaoEntradas.add(entradas);
					transacao.add(entradas);
					System.out.printf("%s%.2f%n",historico.gerarComprovante(opcao, opcaoConta, valor), entradas.getValor());
					System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
				}

			} else if (opcao == 4) {
				conta.limparTela();
				System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
				System.out.println("\nDe qual conta deseja fazer o saque?");
				System.out.print("\nDigite 1 para conta corrente ou 2 para conta poupança: ");
				int opcaoConta = sc.nextInt();
				
				if (opcaoConta == 1) {
					System.out.print("\nDigite o valor para saque: ");
					valor = sc.nextDouble();
				
					if (contaCorrente.getSaldo() < valor) {
						System.out.println("Saldo insuficiente.");
						System.out.printf("Tentativa de saque: %.2f | saldo conta corrente: %.2f%n", valor,
								contaCorrente.getSaldo());
					} else if (contaCorrente.getSaldo() >= valor) {
						contaCorrente.sacar(valor);
						historicoSaida = "Saque";
						tipoConta = "conta corrente";
						saidas = new Historico(historicoSaida, tipoConta, valor);
						transacaoSaidas.add(saidas);
						transacao.add(saidas);
						System.out.printf("%s%.2f%n",historico.gerarComprovante(opcao, opcaoConta, valor), saidas.getValor());
						System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
					}
				} else if (opcaoConta == 2) {
					System.out.print("\nDigite o valor para saque: ");
					valor = sc.nextDouble();
					
					if (contaPoupanca.getSaldo() < valor) {
						System.out.println("Saldo insuficiente.");
						System.out.printf("Tentativa de saque: %.2f | saldo conta poupança: %.2f%n", valor,
								contaCorrente.getSaldo());
					} else if (contaPoupanca.getSaldo() >= valor) {
						contaPoupanca.sacar(valor);
						historicoSaida = "Saque";
						tipoConta = "conta poupança";
						saidas = new Historico(historicoSaida, tipoConta, valor);
						transacaoSaidas.add(saidas);
						transacao.add(saidas);
						System.out.printf("%s%.2f%n",historico.gerarComprovante(opcao, opcaoConta, valor), saidas.getValor());
						System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
					}
				}

			} else if (opcao == 5) {
				conta.limparTela();
				
				if (cadastrarContatos.isEmpty()) {
					System.out.println("Ainda não há contatos cadastrados.");
				}

				if (!cadastrarContatos.isEmpty()) {

					System.out.println("Para qual contato deseja fazer a transferência: ");

					System.out.println("\n=== Lista de contatos ===");
					for (int i = 0; i < cadastrarContatos.size(); i++) {
						ps.println("ID conta: " + cadastrarContatos.get(i).idConta + " | Nome: "
								+ cadastrarContatos.get(i).nome + " | " + "Agencia: " + cadastrarContatos.get(i).agencia
								+ " | Conta: " + cadastrarContatos.get(i).conta);
					}

					System.out.printf("\nSaldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.print("Digite o ID do contato: ");
					int idContato = sc.nextInt();
					System.out.print("\nDigite o valor para transferência: ");
					valor = sc.nextDouble();

					if (contaCorrente.getSaldo() < valor) {
						System.out.println("\nVocê não possui saldo na conta.");
						System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					} else if (contaCorrente.getSaldo() >= valor
							&& idContato == cadastrarContatos.get(idContato - 1).idConta) {
						contaCorrente.transferir(valor, conta);
						historicoSaida = "Transferência";
						tipoConta = "conta corrente";
						saidas = new Historico(historicoSaida, tipoConta, valor);
						transacaoSaidas.add(saidas);
						transacao.add(saidas);
						historico.gerarComprovante(opcao, idConta, valor);
						ps.println("\n\nNome: " + cadastrarContatos.get(idContato - 1).nome);
						System.out.println("Agência: " + cadastrarContatos.get(idContato - 1).agencia);
						System.out.println("Conta: " + cadastrarContatos.get(idContato - 1).conta);
						System.out.printf("Valor transferência: %.2f%n", valor);
						System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
					}
				}

			} else if (opcao == 6) {
				
				int tipoTransacao = 0;
				System.out.println("=== Extratos ===");
				System.out.println("1- Extrato geral");
				System.out.println("2- Entradas");
				System.out.println("3- Saídas");
				System.out.print("\nDigite a opção de extrato que deseja visualizar: ");
				tipoTransacao = sc.nextInt();
				
				if (tipoTransacao == 1) {
					conta.limparTela();
					System.out.println("\n=== Extrato geral ===");
					for (int i = 0; i < historico.gerarExtrato(transacao).size(); i++ ) {
						
						if (i < transacaoEntradas.size()) {
							
						System.out.printf("%s%s%.2f%n", transacaoEntradas.get(i).getHistorico() + " em ",
														transacaoEntradas.get(i).getTipoConta() + ": + R$ ",
														transacaoEntradas.get(i).getValor());
						}
						
						if (i < transacaoSaidas.size()) {
							
						System.out.printf("%s%s%.2f%n",transacaoSaidas.get(i).getHistorico() + " em ",
													   transacaoSaidas.get(i).getTipoConta() + ": - R$ ",
													   transacaoSaidas.get(i).getValor());
						}
														
					}
					
				}
				
				if (tipoTransacao == 2) {
					conta.limparTela();
					System.out.println("\n=== Extrato entradas ===");
					for (int i = 0; i < transacaoEntradas.size(); i++ ) {
				
						System.out.printf("%s%s%.2f%n", transacaoEntradas.get(i).getHistorico() + " em ",
														transacaoEntradas.get(i).getTipoConta() + ": + R$ ",
														transacaoEntradas.get(i).getValor());
					}
				}
				if (tipoTransacao == 3) {
					conta.limparTela();
					System.out.println("\n=== Extrato saídas ===");
					for (int i = 0; i < transacaoSaidas.size(); i++ ) {
						
						System.out.printf("%s%s%.2f%n", transacaoSaidas.get(i).getHistorico() + " em ",
														transacaoSaidas.get(i).getTipoConta() + ": - R$ ",
														transacaoSaidas.get(i).getValor());
					}
				}
				
			} else if (opcao == 7) {
				conta.limparTela();
				
				if (cadastrarContatos.isEmpty()) {
					System.out.println("Ainda não há contatos cadastrados.");
				} else {
					System.out.println("=== Lista de contatos ===");
					for (int i = 0; i < cadastrarContatos.size(); i++) {
						ps.println("ID conta: " + cadastrarContatos.get(i).idConta + " | Nome: "
								+ cadastrarContatos.get(i).nome + " | " + "Agencia: " + cadastrarContatos.get(i).agencia
								+ " | Conta: " + cadastrarContatos.get(i).conta);
					}
				}

			} else if (opcao == 8) {
				break;
				
			} else {
				conta.limparTela();
				System.out.println("Opção inexistente!");
			}
		}

		sc.close();
		System.out.println("Obrigado por usar o Banco Digital! Volte sempre!");
	}
}
