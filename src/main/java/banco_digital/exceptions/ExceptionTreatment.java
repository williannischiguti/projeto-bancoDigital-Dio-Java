package banco_digital.exceptions;

public class ExceptionTreatment extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExceptionTreatment (String mensagemErro) {
		super(mensagemErro);
	}
}
