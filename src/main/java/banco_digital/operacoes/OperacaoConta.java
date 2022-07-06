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
			System.out.print("\n1- Conta Corrente | 2- Conta Poupan�a | 3- Voltar: ");
			opcaoConta = sc.nextInt();
			}

			if (opcaoConta == 3) {
				Conta.limparTela();
				break;
			}

			if (opcaoConta < 0 || opcaoConta > 3) {
				Conta.limparTela();
				System.out.println("Op��o inv�lida!");
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
				System.out.println("3- Sa�das");
				System.out.println("4- Voltar");
				System.out.print("\nDigite a op��o de extrato que deseja visualizar: ");
				tipoTransacao = sc.nextInt();
				
				if (tipoTransacao == 4) {
					break;
					
				}else {
					
				verExtrato(tipoTransacao);
				
				}
			}

		}
	}

	protected void depositar(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) throws UnsupportedEncodingException {

		if (opcaoConta == 1) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para dep�sito em conta corrente: ");
			valor = sc.nextDouble();
			contaCorrente.depositar(valor);
			dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataHoraOperacao = dateFormat.format(LocalDateTime.now());
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			historicoEntrada = "Dep�sito";
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
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para dep�sito em conta poupan�a: ");
			valor = sc.nextDouble();
			contaPoupanca.depositar(valor);
			dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataHoraOperacao = dateFormat.format(LocalDateTime.now());
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			historicoEntrada = "Dep�sito";
			tipoConta = "conta poupan�a";
			entradas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);
			transacao.add(entradas);
			historico.gerarComprovante(historicoEntrada, opcaoConta, valor, dataHoraOperacao, idContato);
			System.out.printf("%nSaldo atual conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
		}
	}

	protected void sacar(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) throws UnsupportedEncodingException {

		if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			System.out.println("\nVoc� n�o possui saldo para fazer saque.");
		}

		if (opcaoConta == 1 && contaCorrente.getSaldo() > 0 || opcaoConta == 1 && contaPoupanca.getSaldo() > 0) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para saque da conta corrente: ");
			valor = sc.nextDouble();
			contaCorrente.sacar(valor);
			dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataHoraOperacao = dateFormat.format(LocalDateTime.now());
			
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			historicoSaida = "Saque";
			tipoConta = "conta corrente";
			saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao);
			transacao.add(saidas);
			historico.gerarComprovante(historicoSaida, opcaoConta, valor, dataHoraOperacao, idContato);
			System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
		}

		if (opcaoConta == 2 && contaCorrente.getSaldo() > 0 || opcaoConta == 2 && contaPoupanca.getSaldo() > 0) {
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			System.out.print("\nDigite o valor para saque da conta poupan�a: ");
			valor = sc.nextDouble();
			contaPoupanca.sacar(valor);
			dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataHoraOperacao = dateFormat.format(LocalDateTime.now());
			
			Conta.limparTela();
			System.out.println("=== Saldo ===");
			System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
			System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
			historicoSaida = "Saque";
			tipoConta = "conta poupan�a";
			saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao);
			transacao.add(saidas);
			historico.gerarComprovante(historicoSaida, opcaoConta, valor, dataHoraOperacao, idContato);
			System.out.printf("%nSaldo atual conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
		}
	}

	protected void transferir(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca)
			throws UnsupportedEncodingException {

		PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
		Conta.limparTela();

		if (Contatos.getCadastrarContatos().isEmpty()) {
			System.out.println("Ainda n�o h� contatos cadastrados.");
		}

		if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
			System.out.println("Voc� n�o possui saldo para fazer transfer�ncia.");
		}

		if (!Contatos.getCadastrarContatos().isEmpty() && contaCorrente.getSaldo() > 0
				|| !Contatos.getCadastrarContatos().isEmpty() && contaPoupanca.getSaldo() > 0) {

			System.out.println("Para qual contato deseja fazer a transfer�ncia: ");

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
					System.out.print("\nDigite o valor para transfer�ncia: ");
					valor = sc.nextDouble();
				}

				if (contaCorrente.getSaldo() < valor) {
					System.out.println("\nVoc� n�o possui saldo na conta.");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
				} else if (contaCorrente.getSaldo() >= valor
						&& idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					contaCorrente.transferir(valor, Contatos.getConta());
					dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					dataHoraOperacao = dateFormat.format(LocalDateTime.now());
					historicoSaida = "Transfer�ncia";
					tipoConta = "conta corrente";
					saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao, idContato);
					transacao.add(saidas);
					historico.gerarComprovante(historicoSaida, Contatos.getIdConta(), valor, dataHoraOperacao, idContato);
					System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
				}
			}

			if (opcaoConta == 2) {

				System.out.printf("\nSaldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
				System.out.print("Digite o ID do contato: ");
				idContato = sc.nextInt();
				if (idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					System.out.print("\nDigite o valor para transfer�ncia: ");
					valor = sc.nextDouble();
				}

				if (contaPoupanca.getSaldo() < valor) {
					System.out.println("\nVoc� n�o possui saldo na conta.");
					System.out.printf("Saldo conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
				} else if (contaPoupanca.getSaldo() >= valor
						&& idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
					contaPoupanca.transferir(valor, Contatos.getConta());
					dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					dataHoraOperacao = dateFormat.format(LocalDateTime.now());
					
					historicoSaida = "Transfer�ncia";
					tipoConta = "conta poupan�a";
					saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao, idContato);
					transacao.add(saidas);
					historico.gerarComprovante(historicoSaida, Contatos.getIdConta(), valor, dataHoraOperacao, idContato);
					System.out.printf("%nSaldo atual conta poupan�a: %.2f%n", contaPoupanca.getSaldo());
				}
			}
		}
	}

	protected void verExtrato(int tipoTransacao) {
		
		// 1 - Extrato geral
		// 2 - Entradas
		// 3 - Sa�das

		if (tipoTransacao == 1 && transacao.isEmpty()) {
			Conta.limparTela();
			System.out.println("Ainda n�o h� registros de entradas e sa�das.\n");
		}
		
		if (tipoTransacao == 2 && historicoEntrada == null) {
			Conta.limparTela();
			System.out.println("Ainda n�o h� registros de entradas.\n");
		}
		
		if (tipoTransacao == 3 && historicoSaida == null) {
			Conta.limparTela();
			System.out.println("Ainda n�o h� registros de sa�das.\n");
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
