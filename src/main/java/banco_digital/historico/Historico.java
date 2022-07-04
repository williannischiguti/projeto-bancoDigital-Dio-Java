package banco_digital.historico;

import java.util.List;
import banco_digital.contas.Conta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Historico extends Conta {

	private String historico;
	private List<Historico> transacao;
	private Double valor;
	private String tipoConta;
	
	public Historico () {
		
	}
	
	public Historico(String historico, String tipoConta, Double valor) {
		this.historico = historico;
		this.tipoConta = tipoConta;
		this.valor = valor;
	}

	public String gerarComprovante(String opcao, int opcaoConta, double valor) {

		if (opcao == "Depósito" && opcaoConta == 1) {
			this.historico = "Depósito em conta corrente: ";
			return this.historico;
		}else if (opcao == "Depósito" && opcaoConta == 2) {
			this.historico = "Depósito em conta poupança: ";
			return this.historico;
		}

		if (opcao == "Saque") {
			this.historico = "Saque realizado no valor de: ";
			return this.historico;
		}
		
		if (opcao == "Transferência") {
			this.historico = "\nTransferência realizada para: ";
			return this.historico;
		}else {
			return "";
		}
	}
	
	public List<Historico> gerarExtrato (List<Historico> transacao) {
		return transacao;
	}

}
