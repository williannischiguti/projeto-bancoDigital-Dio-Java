<h1>Desafio de projeto da DIO - Banco Digital</h1>
Esse repositório possui a estrutura base do desafio de projeto da plataforma DIO.

* A estrutura base possui a superclasse Conta que possui os métodos depositar, sacar, transferir, imprimir extratos e classes filhas ContaCorrente e ContaPoupanca que implementam a Interface Iconta (que "por contrato", obriga a utilizar os métodos citados).

* O desafio proposto é dar mais robustez ao projeto base, utilizar lombok (no intuito de "enxugar" o código quando se trata de getters e setters), criar novas classes, "dar uma outra cara" ao projeto.

* Link do repositório do desafio: https://github.com/falvojr/lab-banco-digital-oo

<h1>Alterações realizadas na estrutura inicial do projeto</h1>

* Utilizado lombok conforme sugerido no desafio.
* Retirado o método "imprimirExtrato" da interface Iconta, pois foi atribuido um tratamento diferente para essa ação.
* Criadas as classes Pix, OperacaoConta, Contatos, Historico e Comparators para que cada classe tenha sua própria responsabilidade. Separadas as classes em pacotes com o intuito de deixar a estrutura mais organizada.
* Implementadas algumas validações de Strings relacionadas aos cadastros:
Criação da conta do banco, cadastro de contatos e cadastro das chaves Pix.
* Utilização de Comparators para ordenação da lista de contatos:
Ordem alfabética (A-Z), ordem decrescente (Z-A) e ordem por ID do contato (começa por 1 e incrementa 1 a cada contato cadastrado).
* Tratamento de exceções com a estrutura try catch.
* Futuramente o projeto poderá sofrer novas implementações e/ou melhorias.