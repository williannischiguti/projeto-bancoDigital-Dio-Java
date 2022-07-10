package banco_digital.contas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Pix extends ContaCorrente {

	String celularPix;
	String cpfPix;
	String emailPix;
	String chaveAleatoriaPix;

	public Pix(String nome, Integer agencia, String conta) {
		super(nome, agencia, conta);
	}

	public Pix() {
		super();
	}

	public void menuPix() {

		int opcaoPix;

		while (true) {

			try {

				System.out.println("\n=== Cadastre sua chave Pix ===\n");
				System.out.println("1 - Celular | 2 - CPF | 3 - E-mail | 4 - Chave Aleatória | 5 - Sair\n");
				opcaoPix = sc.nextInt();

				if (opcaoPix < 1 || opcaoPix > 5) {
					System.out.println("Opção inválida");
					sc.nextLine();
					System.out.println("\nTecle ENTER para continuar...");
					sc.nextLine();
					Conta.limparTela();
					continue;
				}

				if (opcaoPix == 5) {
					Conta.limparTela();
					break;
				}

				if (opcaoPix != 5) {
					cadastrarPix(opcaoPix);
				}

			} catch (InputMismatchException error) {
				System.out.println("Opção inválida!");
				sc.nextLine();
				System.out.println("Tecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
			}
		}
	}

	public void cadastrarPix(int opcaoPix) {

		while (true) {

			try {

				if (opcaoPix == 1 && this.celularPix == null) {

					System.out.println("Digite '0' caso queira retornar para tela anterior");
					System.out.print("\nDigite seu celular com DDD (apenas números): ");
					celularPix = sc.next();

					if (celularPix != null && celularPix != "") {
						char charCel = celularPix.charAt(0);

						if (charCel == '0') {
							Conta.limparTela();
							celularPix = null;
							break;
						}
					}

					if (celularPix.length() != 11 || !Pattern.compile("[0-9]").matcher(celularPix).find()) {
						sc.nextLine();
						celularPix = null;
						System.out.println("Formato inválido");
						System.out.println("Tecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
						continue;
					}

					this.celularPix = "(" + this.celularPix.toString().substring(0, 2) + ")"
							+ this.celularPix.toString().substring(2, 7) + "-"
							+ this.celularPix.toString().substring(7, 11);

					System.out.print("Chave Pix cadastrada (celular): " + this.celularPix + "\n");
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;

				} else if (opcaoPix == 1 && this.celularPix != null) {
					System.out.println("Chave Pix já cadastrada: " + this.celularPix);
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (opcaoPix == 2 && this.cpfPix == null) {
					System.out.println("Digite '0' caso queira retornar para tela anterior");
					System.out.print("\nDigite seu CPF (apenas números): ");
					this.cpfPix = sc.next();
					
					if (cpfPix != null && cpfPix != "") {
						char charCel = cpfPix.charAt(0);

						if (charCel == '0') {
							Conta.limparTela();
							cpfPix = null;
							break;
						}
					}

					if (cpfPix.length() != 11 || !Pattern.compile("[0-9]").matcher(cpfPix).find()) {
						sc.nextLine();
						cpfPix = null;
						System.out.println("Formato inválido");
						System.out.println("Tecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
						break;
					}

					cpfPix = cpfPix.substring(0, 3) + "." + cpfPix.substring(3, 6) + "." + cpfPix.substring(6, 9) + "-"
							+ cpfPix.substring(9, 11);
					System.out.print("Chave Pix cadastrada (CPF): " + cpfPix + "\n");
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;

				} else if (opcaoPix == 2 && this.cpfPix != null) {
					System.out.println("Chave Pix já cadastrada: " + cpfPix);
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (opcaoPix == 3 && this.emailPix == null) {
					System.out.println("Digite '0' caso queira retornar para tela anterior");
					System.out.print("\nDigite seu e-mail: ");
					this.emailPix = sc.next();
					
					if (emailPix != null && emailPix != "") {
						char charCel = emailPix.charAt(0);

						if (charCel == '0') {
							Conta.limparTela();
							emailPix = null;
							break;
						}
					}

					if (!emailPix.contains("@") || !emailPix.contains(".com")) {
						sc.nextLine();
						emailPix = null;
						System.out.println("\nFormato inválido!");
						System.out.println("Tecle ENTER para continuar");
						sc.nextLine();
						Conta.limparTela();
						break;
					}

					System.out.print("Chave Pix cadastrada (E-mail): " + emailPix + "\n");
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;

				} else if (opcaoPix == 3 && this.emailPix != null) {
					System.out.println("Chave Pix já cadastrada: " + emailPix);
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;
				}

				if (opcaoPix == 4 && this.chaveAleatoriaPix == null) {

					List<String> listOfStrings = new ArrayList<String>();
					String strings = "abcdef0123456789";
					Random random = new Random();

					for (int i = 0; i < 32; i++) {
						int randomStrings = random.nextInt(strings.length());
						char charAt = strings.charAt(randomStrings);
						String charAtString = Character.toString(charAt);
						listOfStrings.add(charAtString);
					}
					chaveAleatoriaPix = listOfStrings.toString().replaceAll("\\[|\\]", "").replaceAll(", ", "");
					chaveAleatoriaPix = chaveAleatoriaPix.substring(0, 8) + "-" + chaveAleatoriaPix.substring(8, 12)
							+ "-" + chaveAleatoriaPix.substring(12, 16) + "-" + chaveAleatoriaPix.substring(16, 20)
							+ "-" + chaveAleatoriaPix.substring(20, 32);
					System.out.print("Chave Pix cadastrada (chave aleatória): " + chaveAleatoriaPix + "\n");
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;

				} else if (opcaoPix == 4 && this.chaveAleatoriaPix != null) {
					System.out.println("Chave Pix já cadastrada: " + chaveAleatoriaPix);
					sc.nextLine();
					System.out.println("Tecle ENTER para continuar");
					sc.nextLine();
					Conta.limparTela();
					break;
				}
			} catch (StringIndexOutOfBoundsException erro) {
				System.out.println("Tamanho inválido");
				System.out.println("Precisa ter 11 dígitos numéricos");
				sc.nextLine();
				System.out.println("Tecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				break;
			}
		}
	}
}
