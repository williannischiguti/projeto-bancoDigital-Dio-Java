package banco_digital.contas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import banco_digital.exceptions.ExceptionTreatment;
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

		try {

			System.out.println("\n=== Cadastre sua chave Pix ===\n");
			System.out.println("1 - Celular | 2 - CPF | 3 - E-mail | 4 - Chave Aleatória | 5 - Sair\n");
			int opcaoPix = sc.nextInt();

			while (opcaoPix < 1 || opcaoPix > 5) {
				System.out.println("Opção inválida");
				sc.nextLine();
				System.out.println("\nTecle ENTER para continuar...");
				sc.nextLine();
				Conta.limparTela();
				System.out.println("\n=== Cadastre sua chave Pix ===\n");
				System.out.println("1 - Celular | 2 - CPF | 3 - E-mail | 4 - Chave Aleatória | 5 - Sair\n");
				opcaoPix = sc.nextInt();
			}

			cadastrarPix(opcaoPix);

		} catch (InputMismatchException error) {
			System.out.println("Opção inválida!");
			sc.nextLine();
			System.out.println("Tecle ENTER para continuar...");
			sc.nextLine();
			Conta.limparTela();
		}
	}

	public void cadastrarPix(int opcaoPix) {

		switch (opcaoPix) {

		case 1:
			if (this.celularPix == null) {
				try {

					System.out.print("Digite seu celular com DDD (apenas números): ");
					this.celularPix = sc.next();

					while (this.celularPix.length() != 11) {
						sc.nextLine();
						System.out.println("O número deve ter 11 dígitos numéricos.");
						System.out.println("Tecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
						System.out.print("Digite seu celular com DDD (apenas números): ");
						this.celularPix = sc.next();
					}

					this.celularPix = "(" + this.celularPix.toString().substring(0, 2) + ")"
							+ this.celularPix.toString().substring(2, 7) + "-"
							+ this.celularPix.toString().substring(7, 11);

					System.out.print("Chave Pix cadastrada (celular): " + this.celularPix + "\n");
				} catch (StringIndexOutOfBoundsException error) {
					System.out.println("Formato inválido!");
				} catch (ExceptionTreatment error) {
					System.out.println("Número inválido");
				}
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.celularPix);
			}
			break;

		case 2:

			if (this.cpfPix == null) {
				try {
					System.out.print("Digite seu CPF (apenas números): ");
					this.cpfPix = sc.next();

					while (this.cpfPix.length() != 11) {
						sc.nextLine();
						System.out.println("O CPF precisa ter 11 dígitos numéricos.");
						System.out.println("Tecle ENTER para continuar...");
						sc.nextLine();
						Conta.limparTela();
						System.out.print("Digite seu CPF (apenas números): ");
						this.cpfPix = sc.next();
					}

					this.cpfPix = this.cpfPix.substring(0, 3) + "." + this.cpfPix.substring(3, 6) + "."
							+ this.cpfPix.substring(6, 9) + "-" + this.cpfPix.substring(9, 11);
					System.out.print("Chave Pix cadastrada (CPF): " + this.cpfPix + "\n");
				} catch (StringIndexOutOfBoundsException error) {
					System.out.println("Formato inválido");
				} catch (ExceptionTreatment error) {
					System.out.println("Formato inválido");
				}
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.cpfPix);
			}
			break;

		case 3:

			if (this.emailPix == null) {
				System.out.print("Digite seu e-mail: ");
				this.emailPix = sc.next();

				while (true) {
					if (!this.emailPix.contains("@") || !this.emailPix.contains(".com")) {

						sc.nextLine();
						System.out.println("Formato inválido!");
						System.out.println("Tecle ENTER para continuar");
						sc.nextLine();
						Conta.limparTela();
						System.out.print("Digite seu e-mail: ");
						this.emailPix = sc.next();
					} else {
						break;
					}
				}
				System.out.print("Chave Pix cadastrada (E-mail): " + this.emailPix + "\n");
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.emailPix);
			}
			break;

		case 4:

			if (this.chaveAleatoriaPix == null) {

				List<String> listOfStrings = new ArrayList<String>();
				String strings = "abcdef0123456789";
				Random random = new Random();

				for (int i = 0; i < 32; i++) {
					int randomStrings = random.nextInt(strings.length());
					char charAt = strings.charAt(randomStrings);
					String charAtString = Character.toString(charAt);
					listOfStrings.add(charAtString);
				}
				this.chaveAleatoriaPix = listOfStrings.toString().replaceAll("\\[|\\]", "").replaceAll(", ", "");
				this.chaveAleatoriaPix = this.chaveAleatoriaPix.substring(0, 8) + "-"
						+ this.chaveAleatoriaPix.substring(8, 12) + "-" + this.chaveAleatoriaPix.substring(12, 16) + "-"
						+ this.chaveAleatoriaPix.substring(16, 20) + "-" + this.chaveAleatoriaPix.substring(20, 32);
				System.out.print("Chave Pix cadastrada (chave aleatória): " + this.chaveAleatoriaPix + "\n");
				sc.nextLine();
				System.out.println("Tecle ENTER para continuar");
				sc.nextLine();
				Conta.limparTela();
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.chaveAleatoriaPix);
				sc.nextLine();
				System.out.println("Tecle ENTER para continuar");
				sc.nextLine();
				Conta.limparTela();
			}

		case 5:

			Conta.limparTela();
			break;

		default:
			System.out.println("Opção inválida");

		}
	}
}
