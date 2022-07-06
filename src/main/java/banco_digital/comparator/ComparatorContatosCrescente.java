package banco_digital.comparator;

import java.util.Comparator;

import banco_digital.contas.Conta;

public class ComparatorContatosCrescente implements Comparator <Conta> {

	public int compare(Conta nome1, Conta nome2) {

		if (nome1.getNome().compareTo(nome2.getNome()) > 0) {
			return 1;
		}
		return -1;
	}

}
