package banco_digital.operacoes;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import banco_digital.contas.Conta;
import banco_digital.contas.ContaCorrente;
import banco_digital.contas.ContaPoupanca;
import banco_digital.contatos.Contatos;
import banco_digital.historico.Historico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OperacaoConta {

	DateTimeFormatter dateFormat;
	Scanner sc = new Scanner(System.in);
	Double valor;
	Integer idContato = 0;
	List<Historico> transacao = new ArrayList<Historico>();
	String historicoEntrada = null;
	String historicoSaida = null;
	String tipoConta = null;
	String dataHoraOperacao = null;
	Historico historico = new Historico();
	Historico entradas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);
	Historico saidas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);

	public OperacaoConta() {
		super();
	}

	public void selecionarTipoConta(int operacao, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca,
			String tipoOperacao) throws UnsupportedEncodingException {

		while (true) {

			int opcaoConta = 0;

			if (operacao != 6) {
				System.out.println("\nDeseja fazer " + tipoOperacao + "?");
				System.out.print("\n1- Conta Corrente | 2- Conta Poupança | 3- Voltar: ");
				opcaoConta = sc.nextInt();
			}

			if (opcaoConta == 3) {
				Conta.limparTela();
				break;
			}

			if (opcaoConta < 0 || opcaoConta > 3) {
				Conta.limparTela();
				System.out.println("Opção inválida!");
				continue;
			}

			if (operacao == 3 && opcaoConta == 1 || operacao == 3 && opcaoConta == 2) {
				depositar(opcaoConta, contaCorrente, contaPoupanca);
			}

			if (operacao == 4 && opcaoConta == 1 || operacao == 4 && opcaoConta == 2) {
				sacar(opcaoConta, contaCorrente, contaPoupanca);
			}

			if (operacao == 5 && opcaoConta == 1 || operacao == 5 && opcaoConta == 2) {
				transferir(opcaoConta, contaCorrente, contaPoupanca);
			}

			if (operacao == 6) {
				int tipoTransacao = 0;
				System.out.println("\n=== Extratos ===\n");
				System.out.println("1- Extrato geral");
				System.out.println("2- Entradas");
				System.out.println("3- Saídas");
				System.out.println("4- Voltar");
				System.out.print("\nDigite a opção de extrato que deseja visualizar: ");
				tipoTransacao = sc.nextInt();

				if (tipoTransacao == 4) {
					Conta.limparTela();
					break;

				} else {

					verExtrato(tipoTransacao);

				}
			}

		}
	}

	protected void depositar(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca)
			throws UnsupportedEncodingException {

		if (opcaoConta == 1) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para depósito em conta corrente: ");
			valor = sc.nextDouble();
			contaCorrente.depositar(valor);
			dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataHoraOperacao = dateFormat.format(LocalDateTime.now());
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			historicoEntrada = "Depósito";
			tipoConta = "conta corrente";
			entradas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);
			transacao.add(entradas);
			historico.gerarComprovante(historicoEntrada, opcaoConta, valor, dataHoraOperacao, idContato);
			System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
		}

		if (opcaoConta == 2) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para depósito em conta poupança: ");
			valor = sc.nextDouble();
			contaPoupanca.depositar(valor);
			dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataHoraOperacao = dateFormat.format(LocalDateTime.now());
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			historicoEntrada = "Depósito";
			tipoConta = "conta poupança";
			entradas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);
			transacao.add(entradas);
			historico.gerarComprovante(historicoEntrada, opcaoConta, valor, dataHoraOperacao, idContato);
			System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
		}
	}

	protected void sacar(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca)
			throws UnsupportedEncodingException {

		if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			System.out.println("\nVocê não possui saldo para fazer saque.");
		}

		if (opcaoConta == 1 && contaCorrente.getSaldo() > 0 || opcaoConta == 1 && contaPoupanca.getSaldo() > 0) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para saque da conta corrente: ");
			valor = sc.nextDouble();

			if (contaCorrente.getSaldo() >= valor) {

				contaCorrente.sacar(valor);
				dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				dataHoraOperacao = dateFormat.format(LocalDateTime.now());

				Conta.limparTela();
				System.out.println("=== Saldo ===");
				System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
				historicoSaida = "Saque";
				tipoConta = "conta corrente";
				saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao);
				transacao.add(saidas);
				historico.gerarComprovante(historicoSaida, opcaoConta, valor, dataHoraOperacao, idContato);
				System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
			} else {
				System.out.println("Você não possui saldo para saque");
			}
		}

		if (opcaoConta == 2 && contaCorrente.getSaldo() > 0 || opcaoConta == 2 && contaPoupanca.getSaldo() > 0) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para saque da conta poupança: ");
			valor = sc.nextDouble();

			if (contaPoupanca.getSaldo() >= valor) {

				contaPoupanca.sacar(valor);
				dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				dataHoraOperacao = dateFormat.format(LocalDateTime.now());

				Conta.limparTela();
				System.out.println("=== Saldo ===");
				System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
				historicoSaida = "Saque";
				tipoConta = "conta poupança";
				saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao);
				transacao.add(saidas);
				historico.gerarComprovante(historicoSaida, opcaoConta, valor, dataHoraOperacao, idContato);
				System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
			}else {
				System.out.println("Você não possui saldo para saque");
			}
		} 
	}

	protected void transferir(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca)
			throws UnsupportedEncodingException {

		PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
		Conta.limparTela();

		if (Contatos.getCadastrarContatos().isEmpty()) {
			System.out.println("Ainda não há contatos cadastrados.");
		}

		if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
			System.out.println("Você não possui saldo para fazer transferência.");
		}

		if (!Contatos.getCadastrarContatos().isEmpty() && contaCorrente.getSaldo() > 0
				|| !Contatos.getCadastrarContatos().isEmpty() && contaPoupanca.getSaldo() > 0) {

			System.out.println("Para qual contato deseja fazer a transferência: ");

			System.out.println("\n=== Lista de contatos ===");
			for (int i = 0; i < Contatos.getCadastrarContatos().size(); i++) {
				ps.println("ID conta: " + Contatos.getCadastrarContatos().get(i).getIdConta() + " | Nome: "
						+ Contatos.getCadastrarContatos().get(i).getNome() + " | " + "Agencia: "
						+ Contatos.getCadastrarContatos().get(i).getAgencia() + " | Conta: "
						+ Contatos.getCadastrarContatos().get(i).getConta());
			}

			if (opcaoConta == 1) {

				System.out.printf("\nSaldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				System.out.print("Digite o ID do contato: ");
				idContato = sc.nextInt();

				if (idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					System.out.print("\nDigite o valor para transferência: ");
					valor = sc.nextDouble();
				}

				if (contaCorrente.getSaldo() < valor) {
					System.out.println("\nVocê não possui saldo na conta.");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				} else if (contaCorrente.getSaldo() >= valor
						&& idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					contaCorrente.transferir(valor, Contatos.getConta());
					dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					dataHoraOperacao = dateFormat.format(LocalDateTime.now());
					historicoSaida = "Transferência";
					tipoConta = "conta corrente";
					saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao, idContato);
					transacao.add(saidas);
					historico.gerarComprovante(historicoSaida, Contatos.getIdConta(), valor, dataHoraOperacao,
							idContato);
					System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
				}
			}

			if (opcaoConta == 2) {

				System.out.printf("\nSaldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
				System.out.print("Digite o ID do contato: ");
				idContato = sc.nextInt();
				if (idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					System.out.print("\nDigite o valor para transferência: ");
					valor = sc.nextDouble();
				}

				if (contaPoupanca.getSaldo() < valor) {
					System.out.println("\nVocê não possui saldo na conta.");
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
				} else if (contaPoupanca.getSaldo() >= valor
						&& idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					contaPoupanca.transferir(valor, Contatos.getConta());
					dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					dataHoraOperacao = dateFormat.format(LocalDateTime.now());

					historicoSaida = "Transferência";
					tipoConta = "conta poupança";
					saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao, idContato);
					transacao.add(saidas);
					historico.gerarComprovante(historicoSaida, Contatos.getIdConta(), valor, dataHoraOperacao,
							idContato);
					System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
				}
			}
		}
	}

	protected void verExtrato(int tipoTransacao) {

		// 1 - Extrato geral
		// 2 - Entradas
		// 3 - Saídas

		if (tipoTransacao == 1 && transacao.isEmpty()) {
			Conta.limparTela();
			System.out.println("Ainda não há registros de entradas e saídas.\n");
		}

		if (tipoTransacao == 2 && historicoEntrada == null) {
			Conta.limparTela();
			System.out.println("Ainda não há registros de entradas.\n");
		}

		if (tipoTransacao == 3 && historicoSaida == null) {
			Conta.limparTela();
			System.out.println("Ainda não há registros de saídas.\n");
		}

		if (tipoTransacao == 1 && !transacao.isEmpty()) {
			historico.gerarExtrato(tipoTransacao, transacao);

		}

		if (tipoTransacao == 2 && !transacao.isEmpty() && historicoEntrada != null) {
			historico.gerarExtrato(tipoTransacao, transacao);

		}
		if (tipoTransacao == 3 && !transacao.isEmpty() && historicoSaida != null) {
			historico.gerarExtrato(tipoTransacao, transacao);
		}
	}

}
