package banco_digital.contas;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import banco_digital.contatos.Contatos;
import banco_digital.operacoes.OperacaoConta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// Superclasse responsável pela criação da conta e direcionamento das operações (depósito, saque, transferência etc)
// Utiliza a interface Iconta que tem por "contrato" a utilização dos métodos (depositar, sacar e transferir)

public class Conta implements Iconta {

	protected Integer agencia;
	protected String conta;
	protected double saldo;
	protected String nome;
	protected Integer idConta;

	static Scanner sc = new Scanner(System.in);
	Random random = new Random();

	protected Conta minhaConta;
	protected ContaCorrente contaCorrente;
	protected ContaPoupanca contaPoupanca;
	protected Pix pix;
	protected OperacaoConta operacaoConta;

	public Conta() {

	}

	public Conta(String nome, Integer agencia, String conta) {
		this.nome = nome;
		this.agencia = agencia;
		this.conta = conta;
	}

	public Conta(Integer idConta, String nome, Integer agencia, String conta) {
		this.idConta = idConta;
		this.nome = nome;
		this.agencia = agencia;
		this.conta = conta;
	}

	public void criarConta() {

		System.out.println("=== Olá, seja bem vindo ao Banco Digital ===");
		System.out.println("\nCadastre a sua conta");
		System.out.print("Digite o seu nome: ");
		this.nome = sc.nextLine();
		this.nome = nome.toUpperCase();

		while (nome.isBlank() || !Pattern.compile("[^a-z\0-9@]").matcher(nome).find()
				|| Pattern.compile("[0-9@]").matcher(nome).find()) {
			System.out.println("Nome inválido");
			System.out.println("\nTecle ENTER para continuar...");
			sc.nextLine();
			Conta.limparTela();
			System.out.println("=== Olá, seja bem vindo ao Banco Digital ===");
			System.out.println("\nCadastre a sua conta");
			System.out.print("Digite o seu nome: ");
			this.nome = sc.nextLine();
			this.nome = nome.toUpperCase();
		}

		this.agencia = random.nextInt((9999 - 1000) + 1) + 1000;
		int contaRandom = random.nextInt((99999 - 10000) + 1) + 10000;
		this.conta = Integer.toString(contaRandom).substring(0, 4) + "-"
				+ Integer.toString(contaRandom).substring(4, 5);

		minhaConta = new Conta(this.nome, this.agencia, this.conta);
		contaCorrente = new ContaCorrente(this.nome, agencia, this.conta);
		contaPoupanca = new ContaPoupanca(this.nome, agencia, this.conta);
		pix = new Pix();
		operacaoConta = new OperacaoConta();
		System.out.println();

		imprimirDadosConta();
	}

	public void imprimirDadosConta() {

		try {
			System.out.println("=== Dados da sua conta criada ===\n");
			PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
			ps.println("Nome: " + this.nome);
			System.out.println("Agência: " + this.agencia);
			System.out.println("Conta: " + this.conta);

			System.out.println("Tecle ENTER para continuar...");
			sc.nextLine();
			limparTela();

		} catch (UnsupportedEncodingException erro) {
			System.out.println("Formato de encoding inválido.");
		}
	}

	public void menu() {

		while (true) {

			try {

				System.out.println();
				System.out.println("O que deseja fazer a seguir? ");
				System.out.println("1- Contatos | 2- Cadastrar Pix | 3- Depositar | 4-Sacar | 5- Transferir | "
						+ "6- Pagamentos | 7- Extratos | 8- Sair");
				int operacao = sc.nextInt();

				if (operacao == 1) {
					Conta.limparTela();
					Contatos.menuContatos();
				}

				else if (operacao == 2) {
					Conta.limparTela();
					pix.menuPix();
				}

				else if (operacao == 3) {
					Conta.limparTela();
					mostrarSaldo();
					String tipoOperacao = "depósito";
					operacaoConta.selecionarTipoConta(operacao, contaCorrente, contaPoupanca, tipoOperacao);
				}

				else if (operacao == 4) {
					Conta.limparTela();
					mostrarSaldo();
					String tipoOperacao = "saque";
					operacaoConta.selecionarTipoConta(operacao, contaCorrente, contaPoupanca, tipoOperacao);
				}

				else if (operacao == 5) {
					Conta.limparTela();
					mostrarSaldo();
					String tipoOperacao = "transferência";
					operacaoConta.selecionarTipoConta(operacao, contaCorrente, contaPoupanca, tipoOperacao);
				}

				else if (operacao == 6) {
					Conta.limparTela();
					String tipoOperacao = "pagamento";
					operacaoConta.selecionarTipoConta(operacao, contaCorrente, contaPoupanca, tipoOperacao);
				}

				else if (operacao == 7) {
					Conta.limparTela();
					String tipoOperacao = "extrato";
					operacaoConta.selecionarTipoConta(operacao, contaCorrente, contaPoupanca, tipoOperacao);
				}

				else if (operacao == 8) {
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
		System.out.println("Obrigado por usar o Banco Digital! Volte sempre!");
	}

	public void sacar(double valor) {

		if (this.saldo >= valor) {
			saldo -= valor;
		}
	}

	public void depositar(double valor) {

		saldo += valor;
	}

	public void transferir(double valor, Iconta contaDestino) {

		if (this.saldo >= valor) {
			this.sacar(valor);
			contaDestino.depositar(valor);
		}
	}

	public void pagarConta(double valor) {

		if (this.saldo >= valor) {
			saldo -= valor;
		}
	}

	protected void mostrarSaldo() {
		System.out.println("=== Saldo ===");
		System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
		System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
	}

	public static void limparTela() {

		try {
			String operatingSystem = System.getProperty("os.name");

			if (operatingSystem.contains("Windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();

				startProcess.waitFor();
			}
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}

}
