package banco_digital;

public class Pix extends Conta {
	public Pix(Cliente cliente) {
		super(cliente);
	}
    
    public void imprimirExtrato() {
        super.imprimirInfosComuns();
    }
    
}
