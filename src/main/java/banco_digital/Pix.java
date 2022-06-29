package banco_digital;

import org.apache.commons.lang3.RandomStringUtils;

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

		System.out.println("\n=== Cadastre sua chave Pix ===\n");
		System.out.println("1 - Celular | 2 - CPF | 3 - E-mail | 4 - Chave Aleatória\n");
	}

	public void cadastrarPix(int opcaoPix) {

		switch (opcaoPix) {
		
		case 1:
			if (this.celularPix == null) {

				System.out.print("Digite seu celular com DDD (apenas números): ");
				this.celularPix = sc.next();
				this.celularPix = "(" + this.celularPix.toString().substring(0, 2) + ")"
						+ this.celularPix.toString().substring(2, 7) + "-"
						+ this.celularPix.toString().substring(7, 11);

				System.out.print("Chave Pix cadastrada (celular): " + this.celularPix + "\n");
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.celularPix);
			}
			break;

		case 2:

			if (this.cpfPix == null) {
				System.out.print("Digite seu CPF (apenas números): ");
				this.cpfPix = sc.next();
				this.cpfPix = this.cpfPix.substring(0, 3) + "." + this.cpfPix.substring(3, 6) + "."
						+ this.cpfPix.substring(6, 9) + "-" + this.cpfPix.substring(9, 11);
				System.out.print("Chave Pix cadastrada (CPF): " + this.cpfPix + "\n");
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.cpfPix);
			}
			break;

		case 3:

			if (this.emailPix == null) {
				System.out.print("Digite seu e-mail: ");
				this.emailPix = sc.next();
				System.out.print("Chave Pix cadastrada (E-mail): " + this.emailPix + "\n");
			} else {
				System.out.println("Chave Pix já cadastrada: " + this.emailPix);
			}
			break;

		case 4:
			
			if (this.chaveAleatoriaPix == null) {
				this.chaveAleatoriaPix = RandomStringUtils.randomAlphanumeric(32);
				this.chaveAleatoriaPix = this.chaveAleatoriaPix.substring(0, 8) + "-"
						+ this.chaveAleatoriaPix.substring(8, 12) + "-" + this.chaveAleatoriaPix.substring(12, 16) + "-"
						+ this.chaveAleatoriaPix.substring(16, 20) + "-" + this.chaveAleatoriaPix.substring(20, 32);
				System.out.print("Chave Pix cadastrada (Chave aleatória): " + this.chaveAleatoriaPix + "\n");
			}
			else {
				System.out.println("Chave Pix já cadastrada: " + this.chaveAleatoriaPix);
			}
			break;

		default:
			System.out.println("Opção inválida");

		}
	}

	public void imprimirExtrato() {
		super.imprimirInfosComuns();
	}

}
