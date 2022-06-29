package banco_digital;

import java.util.Scanner;

public class Conta implements Iconta {

	protected Integer agencia;
	protected String conta;
	protected double saldo;
	protected String nome;

	Scanner sc = new Scanner(System.in);

	public Conta() {

	}

	public Conta(String nome, Integer agencia, String conta) {
		this.nome = nome;
		this.agencia = agencia;
		this.conta = conta;
	}

	public void sacar(double valor) {
		if (this.saldo >= valor) {
			saldo -= valor;
		} else {
			System.out.println("Saldo insuficiente");
		}

	}

	public void menu() {

		System.out.println();
		System.out.println("O que deseja fazer a seguir? ");
		System.out.println("1- Cadastrar contatos | 2- Cadastrar chave Pix | "
				+ "3- Depositar | 4-Sacar | 5- Transferência | " + "6 - Ver extrato | 7- Listar contatos | 8- Sair");
	}
	
	public void limparTela() {
		
		 try{
	            String operatingSystem = System.getProperty("os.name"); 
	              
	            if(operatingSystem.contains("Windows")){        
	                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
	                Process startProcess = pb.inheritIO().start();
	                startProcess.waitFor();
	            } else {
	                ProcessBuilder pb = new ProcessBuilder("clear");
	                Process startProcess = pb.inheritIO().start();

	                startProcess.waitFor();
	            } 
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    }
	
		

	public void depositar(double valor) {
		saldo += valor;
	}

	public void transferir(double valor, Iconta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}
	
	public String getNome() {
		return nome;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void imprimirDadosConta() {
		System.out.println("=== Dados da sua conta criada ===\n");
		System.out.println("Nome: " + this.nome);
		System.out.println("Agência: " + this.agencia);
		System.out.println("Conta: " + this.conta);
	}

	public void infoBanco() {
		System.out.println("O Banco Digital é o banco do seu jeito! ;) ");
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.nome));
		System.out.println(String.format("Agência: %s", this.agencia));
		System.out.println(String.format("Número: %s", this.conta));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	public void imprimirExtrato() {

	}

}
