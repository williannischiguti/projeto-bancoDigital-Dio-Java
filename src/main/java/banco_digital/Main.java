package banco_digital;

import org.apache.commons.lang3.RandomStringUtils;

//import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
    	
        Cliente cliente = new Cliente();
        cliente.setNome("Willian");

        Conta cc = new ContaCorrente(cliente);
        Conta poupanca = new ContaPoupanca(cliente);
        Conta pix = new Pix(cliente);
        

        cc.depositar(100);
        cc.transferir(100, poupanca);

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
        pix.gerarChavePixAleatoria();
        
    }
}

