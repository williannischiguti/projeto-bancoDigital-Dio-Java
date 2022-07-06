package banco_digital.comparator;

import java.util.Comparator;

import banco_digital.contas.Conta;

public class ComparatorContatosIDCrescente implements Comparator <Conta> {

	public int compare(Conta idConta, Conta idConta2) {
		
		if (idConta.getIdConta() > idConta2.getIdConta()) {
			return 1;
		}
		return -1;
	}

}
