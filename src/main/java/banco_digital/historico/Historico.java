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

		if (opcao == "Dep�sito" && opcaoConta == 1) {
			this.historico = "Dep�sito em conta corrente: ";
			return this.historico;
		}else if (opcao == "Dep�sito" && opcaoConta == 2) {
			this.historico = "Dep�sito em conta poupan�a: ";
			return this.historico;
		}

		if (opcao == "Saque") {
			this.historico = "Saque realizado no valor de: ";
			return this.historico;
		}
		
		if (opcao == "Transfer�ncia") {
			this.historico = "\nTransfer�ncia realizada para: ";
			return this.historico;
		}else {
			return "";
		}
	}
	
	public List<Historico> gerarExtrato (List<Historico> transacao) {
		return transacao;
	}

}
