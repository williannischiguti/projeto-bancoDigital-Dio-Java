package banco_digital.operacoes;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
	protected List<Historico> transacao = new ArrayList<Historico>();
	String historicoEntrada = null;
	String historicoSaida = null;
	String tipoConta = null;
	String dataHoraOperacao = null;
	protected Historico historico = new Historico();
	protected Historico entradas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);
	protected Historico saidas = new Historico(historicoEntrada, tipoConta, valor, dataHoraOperacao);

	public OperacaoConta() {
		super();
	}

	public void selecionarTipoConta(int operacao, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca,
			String tipoOperacao) {

		while (true) {

			try {

				int opcaoConta = 0;

				if (operacao != 7) {
					System.out.println("\nDeseja fazer " + tipoOperacao + "?");
					System.out.print("\n1- Conta Corrente | 2- Conta Poupança | 3- Voltar: ");
					opcaoConta = sc.nextInt();
				}

				if (opcaoConta == 3) {
					Conta.limparTela();
					break;
				}

				if (opcaoConta < 0 || opcaoConta > 3) {
					System.out.println("Opção inválida!");
					opcaoConta = 0;
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
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

				if (operacao == 6 && opcaoConta == 1 || operacao == 6 && opcaoConta == 2) {
					pagarConta(opcaoConta, contaCorrente, contaPoupanca);
				}

				if (operacao == 7) {
					int tipoTransacao = 0;
					System.out.println("\n=== Extratos ===\n");
					System.out.println("1- Extrato geral");
					System.out.println("2- Entradas");
					System.out.println("3- Saídas");
					System.out.println("4- Voltar");
					System.out.print("\nDigite a opção de extrato que deseja visualizar: ");
					tipoTransacao = sc.nextInt();

					if (tipoTransacao < 1 || tipoTransacao > 4) {
						System.out.println("Opção inválida!");
						sc.nextLine();
						System.out.println("Tecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
					}

					if (tipoTransacao == 4) {
						Conta.limparTela();
						break;

					} else {

						verExtrato(tipoTransacao);

					}
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

	protected void depositar(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {

		while (true) {

			try {

				if (opcaoConta == 1) {
					Conta.limparTela();
					System.out.println("Digite '0' para retornar para tela anterior");
					System.out.println("\n=== Saldo ===");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
					System.out.print("\nDigite o valor para depósito em conta corrente: ");
					valor = sc.nextDouble();

					if (valor == 0) {
						Conta.limparTela();
						break;
					}

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
					break;
				}

				if (opcaoConta == 2) {
					Conta.limparTela();
					System.out.println("Digite '0' para retornar para tela anterior");
					System.out.println("\n=== Saldo ===");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
					System.out.print("\nDigite o valor para depósito em conta poupança: ");
					valor = sc.nextDouble();

					if (valor == 0) {
						Conta.limparTela();
						break;
					}

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
					break;
				}

			} catch (InputMismatchException erro) {
				System.out.println("\nFormato inválido!");
				System.out.println("Certifique-se de utilizar a vírgula como "
						+ "separador de casa decimal caso seja necessário.");
				sc.nextLine();
				System.out.println("\nTecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				break;
			} 
		}
	}

	protected void sacar(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {

		while (true) {

			try {

				if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
					Conta.limparTela();
					System.out.println("=== Saldo ===");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
					System.out.println("\nVocê não possui saldo para fazer saque.");
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (opcaoConta == 1 && contaCorrente.getSaldo() > 0
						|| opcaoConta == 1 && contaPoupanca.getSaldo() > 0) {
					Conta.limparTela();
					System.out.println("Digite '0' para retornar para tela anterior");
					System.out.println("\n=== Saldo ===");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
					System.out.print("\nDigite o valor para saque da conta corrente: ");
					valor = sc.nextDouble();

					if (valor == 0) {
						Conta.limparTela();
						break;
					}

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
						break;

					} else {
						System.out.println("Você não possui saldo para saque");
						sc.nextLine();
						System.out.println("\nTecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
					}
				}

				if (opcaoConta == 2 && contaCorrente.getSaldo() > 0
						|| opcaoConta == 2 && contaPoupanca.getSaldo() > 0) {
					Conta.limparTela();
					System.out.println("Digite '0' para retornar para tela anterior");
					System.out.println("\n=== Saldo ===");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
					System.out.print("\nDigite o valor para saque da conta poupança: ");
					valor = sc.nextDouble();

					if (valor == 0) {
						Conta.limparTela();
						break;
					}

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
						break;

					} else {
						System.out.println("Você não possui saldo para saque");
						sc.nextLine();
						System.out.println("\nTecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
					}
				}
			} catch (InputMismatchException erro) {
				System.out.println("\nFormato inválido!");
				System.out.println("Certifique-se de utilizar a vírgula como "
						+ "separador de casa decimal caso seja necessário.");
				sc.nextLine();
				System.out.println("\nTecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				break;
			}
		}
	}

	protected void transferir(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {

		while (true) {

			try {

				PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
				Conta.limparTela();

				if (Contatos.getCadastrarContatos().isEmpty() && contaCorrente.getSaldo() == 0
						&& contaPoupanca.getSaldo() == 0) {

					System.out.println("Ainda não há contatos cadastrados.");
					System.out.println("Você não possui saldo para fazer transferência.");
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (Contatos.getCadastrarContatos().isEmpty()) {
					System.out.println("Ainda não há contatos cadastrados.");
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
					System.out.println("Você não possui saldo para fazer transferência.");
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					break;
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

						if (contaCorrente.getSaldo() == 0) {
							System.out.println("\nVocê não possui saldo na conta.");
							sc.nextLine();
							System.out.println("\nTecle ENTER para continuar");
							sc.nextLine();
							Conta.limparTela();
							break;
						}

						System.out.println("\nDigite '0' para retornar para tela anterior");
						System.out.printf("\nSaldo conta corrente: %.2f%n", contaCorrente.getSaldo());
						System.out.print("Digite o ID do contato: ");
						idContato = sc.nextInt();

						if (idContato == 0) {
							Conta.limparTela();
							break;
						}

						if (idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
							System.out.println("\nDigite '0' para retornar para tela anterior");
							System.out.print("\nDigite o valor para transferência: ");
							valor = sc.nextDouble();

							if (valor == 0) {
								Conta.limparTela();
								break;
							}
						}

						if (contaCorrente.getSaldo() < valor) {
							System.out.println("\nVocê não possui saldo na conta.");
							System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
							sc.nextLine();
							System.out.println("\nTecle ENTER para continuar");
							sc.nextLine();
							Conta.limparTela();

						} else if (contaCorrente.getSaldo() >= valor
								&& idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
							contaCorrente.transferir(valor, Contatos.getConta());
							dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
							dataHoraOperacao = dateFormat.format(LocalDateTime.now());
							historicoSaida = "Transferência";
							tipoConta = "conta corrente";
							saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao, idContato);
							transacao.add(saidas);
							historico.gerarComprovante(historicoSaida, opcaoConta, valor, dataHoraOperacao, idContato);
							System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
							sc.nextLine();
							System.out.println("Tecle ENTER para continuar...");
							sc.nextLine();
							Conta.limparTela();
							break;
						}
					}

					if (opcaoConta == 2) {

						if (contaPoupanca.getSaldo() == 0) {
							System.out.println("\nVocê não possui saldo na conta.");
							sc.nextLine();
							System.out.println("\nTecle ENTER para continuar");
							sc.nextLine();
							Conta.limparTela();
							break;
						}

						System.out.println("\nDigite '0' para retornar para tela anterior");
						System.out.printf("\nSaldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
						System.out.print("Digite o ID do contato: ");
						idContato = sc.nextInt();

						if (idContato == 0) {
							Conta.limparTela();
							break;
						}

						if (idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
							System.out.println("\nDigite '0' para retornar para tela anterior");
							System.out.print("\nDigite o valor para transferência: ");
							valor = sc.nextDouble();

							if (valor == 0) {
								Conta.limparTela();
								break;
							}
						}

						if (contaPoupanca.getSaldo() < valor) {
							System.out.println("\nVocê não possui saldo na conta.");
							System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
							sc.nextLine();
							System.out.println("\nTecle ENTER para continuar");
							sc.nextLine();
							Conta.limparTela();

						} else if (contaPoupanca.getSaldo() >= valor
								&& idContato == Contatos.getCadastrarContatos().get(idContato - 1).getIdConta()) {
							contaPoupanca.transferir(valor, Contatos.getConta());
							dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
							dataHoraOperacao = dateFormat.format(LocalDateTime.now());

							historicoSaida = "Transferência";
							tipoConta = "conta poupança";
							saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao, idContato);
							transacao.add(saidas);
							historico.gerarComprovante(historicoSaida, opcaoConta, valor, dataHoraOperacao, idContato);
							System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
							sc.nextLine();
							System.out.println("Tecle ENTER para continuar...");
							sc.nextLine();
							Conta.limparTela();
							break;
						}
					}
				}
			} catch (InputMismatchException erro) {
				System.out.println("\nFormato inválido!");
				sc.nextLine();
				System.out.println("\nTecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				break;
			} catch (IndexOutOfBoundsException erro) {
				System.out.println("\nO ID de contato solicitado não existe.");
				sc.nextLine();
				System.out.println("\nTecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				break;
			} catch (UnsupportedEncodingException erro) {
				System.out.println("Formato de encoding inválido.");
			}
		}
	}

	protected void pagarConta(int opcaoConta, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {

		while (true) {

			try {

				if (contaCorrente.getSaldo() == 0 && contaPoupanca.getSaldo() == 0) {
					Conta.limparTela();
					System.out.println("Você não possui saldo para fazer pagamento.");
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (opcaoConta == 1 && contaCorrente.getSaldo() > 0
						|| opcaoConta == 2 && contaPoupanca.getSaldo() > 0) {
					Conta.limparTela();
					System.out.println("Digite '0' para retornar para tela anterior");
					System.out.println("\n=== Saldo ===");
					System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
					System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());
					System.out.print("\nDigite o histórico de pagamento (opcional): ");
					sc.nextLine();
					historicoSaida = sc.nextLine();

					if (historicoSaida != null && historicoSaida != "") {
						char charHistorico = historicoSaida.charAt(0);
						if (charHistorico == '0') {
							Conta.limparTela();
							break;
						}

					}

					System.out.print("\nDigite o valor para pagamento: ");
					valor = sc.nextDouble();

					if (valor == 0) {
						Conta.limparTela();
						break;
					}

					if (opcaoConta == 1 && contaCorrente.getSaldo() >= valor) {
						contaCorrente.pagarConta(valor);
						dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
						dataHoraOperacao = dateFormat.format(LocalDateTime.now());

						Conta.limparTela();
						System.out.println("=== Saldo ===");
						System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
						System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());

						if (historicoSaida == "") {
							historicoSaida = "Pagamento";
						}

						tipoConta = "conta corrente";
						saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao);
						transacao.add(saidas);
						String pagamento = "Pagamento";
						historico.gerarComprovante(pagamento, opcaoConta, valor, dataHoraOperacao, idContato);
						System.out.printf("%nSaldo atual conta corrente: %.2f%n", contaCorrente.getSaldo());
						sc.nextLine();
						System.out.println("\nTecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
						break;

					} else {
						System.out.println("Você não possui saldo para saque");
					}

					if (opcaoConta == 2 && contaPoupanca.getSaldo() >= valor) {
						contaPoupanca.pagarConta(valor);
						dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
						dataHoraOperacao = dateFormat.format(LocalDateTime.now());

						Conta.limparTela();
						System.out.println("=== Saldo ===");
						System.out.printf("Saldo conta corrente: %.2f%n", contaCorrente.getSaldo());
						System.out.printf("Saldo conta poupança: %.2f%n", contaPoupanca.getSaldo());

						if (historicoSaida == "") {
							historicoSaida = "Pagamento";
						}

						tipoConta = "conta poupança";
						saidas = new Historico(historicoSaida, tipoConta, valor, dataHoraOperacao);
						transacao.add(saidas);
						String pagamento = "Pagamento";
						historico.gerarComprovante(pagamento, opcaoConta, valor, dataHoraOperacao, idContato);
						System.out.printf("%nSaldo atual conta poupança: %.2f%n", contaPoupanca.getSaldo());
						sc.nextLine();
						System.out.println("\nTecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
						break;

					} else {
						System.out.println("Você não possui saldo para saque");
					}

				}

			} catch (InputMismatchException erro) {
				System.out.println("\nFormato inválido!");
				System.out.println("Certifique-se de utilizar a vírgula como "
						+ "separador de casa decimal caso seja necessário.");
				sc.nextLine();
				System.out.println("\nTecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				break;
			} catch (StringIndexOutOfBoundsException erro) {
				System.out.println();
			}
		}
	}

	protected void verExtrato(int tipoTransacao) {

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
