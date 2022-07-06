package banco_digital.historico;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import banco_digital.contas.Conta;
import banco_digital.contatos.Contatos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Historico extends Conta {

	private String historico;
	private List<Historico> transacao;
	private Double valor;
	private String tipoConta;
	private String dataHoraOperacao;
	private Integer idContato;
	
	
	public Historico () {
		
	}
	
	public Historico(String historico,  String tipoConta, Double valor,
			String dataHoraOperacao) {
		super();
		this.historico = historico;
		this.tipoConta = tipoConta;
		this.valor = valor;
		this.dataHoraOperacao = dataHoraOperacao;
	}
	
	public Historico(String historico,  String tipoConta, Double valor,
			String dataHoraOperacao, Integer idContato) {
		super();
		this.historico = historico;
		this.tipoConta = tipoConta;
		this.valor = valor;
		this.dataHoraOperacao = dataHoraOperacao;
		this.idContato = idContato;
	}

	public void gerarComprovante(String opcao, int opcaoConta, double valor, String dataHoraOPeracao, int idContato) throws UnsupportedEncodingException {
		
		if (opcao == "Dep�sito" && opcaoConta == 1) {
			System.out.println(String.format("%s �s %s  - Dep�sito em conta corrente: R$ %.2f", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19), valor)); 
			
		}else if (opcao == "Dep�sito" && opcaoConta == 2) {
			System.out.println(String.format("%s �s %s  - Dep�sito em conta poupan�a: R$ %.2f", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19), valor));
			
		}

		if (opcao == "Saque"  && opcaoConta == 1) {
			System.out.println(String.format("%s �s %s  - Saque em conta corrente: R$ %.2f", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19), valor));
			
		}
		
		if (opcao == "Saque"  && opcaoConta == 2) {
			System.out.println(String.format("%s �s %s  - Saque em conta poupan�a: R$ %.2f", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19), valor));
		}
		
		if (opcao == "Pagamento"  && opcaoConta == 1) {
			System.out.println(String.format("%s �s %s  - Pagamento com conta corrente: R$ %.2f", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19), valor));
		}
		
		if (opcao == "Pagamento"  && opcaoConta == 2) {
			System.out.println(String.format("%s �s %s  - Pagamento com conta poupan�a: R$ %.2f", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19), valor));
		}
		
		if (opcao == "Transfer�ncia"  && opcaoConta == 1) {
			PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
			System.out.print("\nTransferencia realizada para: ");
			ps.println("\n\nNome: " + Contatos.getCadastrarContatos().get(idContato - 1).getNome());
			System.out.println("Ag�ncia: " + Contatos.getCadastrarContatos().get(idContato - 1).getAgencia());
			System.out.println("Conta: " + Contatos.getCadastrarContatos().get(idContato - 1).getConta());
			System.out.printf("Valor transfer�ncia: %.2f%n", valor);
			System.out.println(String.format("Transfer�ncia realizada em %s �s %s", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19)));
		}
		
		if (opcao == "Transfer�ncia"  && opcaoConta == 2) {
			PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
			System.out.print("\nTransferencia realizada para: ");
			ps.println("\n\nNome: " + Contatos.getCadastrarContatos().get(idContato - 1).getNome());
			System.out.println("Ag�ncia: " + Contatos.getCadastrarContatos().get(idContato - 1).getAgencia());
			System.out.println("Conta: " + Contatos.getCadastrarContatos().get(idContato - 1).getConta());
			System.out.printf("Valor transfer�ncia: %.2f%n", valor);
			System.out.println(String.format("Transfer�ncia realizada em %s �s %s", 
					dataHoraOPeracao.substring(0, 10), 
					dataHoraOPeracao.substring(11, 19)));
		}
	}
	
	public void gerarExtrato (int tipoTransacao, List<Historico> transacao) {
		
		if (tipoTransacao == 1 && !transacao.isEmpty()) {
			Conta.limparTela();
			
			System.out.println("\n=== Extrato geral ===");

			for (int i = 0; i < transacao.size(); i++) {

				if (transacao.get(i).getHistorico() == "Dep�sito") {

					System.out.printf("%s �s %s - %s em %s: + R$ %.2f\n", transacao.get(i).getDataHoraOperacao().substring(0, 10),
							transacao.get(i).getDataHoraOperacao().substring(11, 19),
							transacao.get(i).getHistorico(),
							transacao.get(i).getTipoConta(), 
							transacao.get(i).getValor());
				}
				else {
					System.out.printf("%s �s %s - %s em %s: - R$ %.2f\n", transacao.get(i).getDataHoraOperacao().substring(0, 10),
							transacao.get(i).getDataHoraOperacao().substring(11, 19),
							transacao.get(i).getHistorico(),
							transacao.get(i).getTipoConta(), 
							transacao.get(i).getValor());
					
				}
			}
		}
		
		if (tipoTransacao == 2 && !transacao.isEmpty()) {
			Conta.limparTela();
			
			System.out.println("\n=== Extrato entradas ===");
			for (int i = 0; i < transacao.size(); i++) {
				if (transacao.get(i).getHistorico() == "Dep�sito")
					
					System.out.printf("%s �s %s - %s em %s: + R$ %.2f\n", transacao.get(i).getDataHoraOperacao().substring(0, 10),
							transacao.get(i).getDataHoraOperacao().substring(11, 19),
							transacao.get(i).getHistorico(),
							transacao.get(i).getTipoConta(), 
							transacao.get(i).getValor());
			}
		}
		
		if (tipoTransacao == 3 && !transacao.isEmpty()) {
			Conta.limparTela();
			System.out.println("\n=== Extrato sa�das ===");
			for (int i = 0; i < transacao.size(); i++) {
				if (transacao.get(i).getHistorico() != "Dep�sito")
					System.out.printf("%s �s %s - %s com %s: - R$ %.2f\n", transacao.get(i).getDataHoraOperacao().substring(0, 10),
							transacao.get(i).getDataHoraOperacao().substring(11, 19),
							transacao.get(i).getHistorico(),
							transacao.get(i).getTipoConta(), 
							transacao.get(i).getValor());
			}
		}
	}
}
