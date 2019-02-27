
public class TesteTributo {
	
	public static void main(String[] args) {
		
		
	System.out.println("\n" + "Cliente: ");
	ContaCorrente cc = new ContaCorrente(3, 111);
	cc.deposita(1000);
	System.out.println("voce depositou " + cc.getSaldo());
	System.out.println("seu saldo é R$" + cc.getValorImposto());

	System.out.println("\n" + "Ações: ");
	AcaoBolsa acoes = new AcaoBolsa();
	acoes.setInvestimento(200);
	System.out.println("seu investimento foi de " + acoes.getInvestimento());
	System.out.println("seu saldo é R$" + acoes.getValorImposto());

//	System.out.println("\n" + "Seguro: ");
//	SeguroVida seguro = new SeguroVida ();
//	System.out.println("o seu plano e R$" + seguro.getPreco());
//	System.out.println("Sua mensalidade é: R$" + seguro.getValorImposto());

	CalculadorImposto calculador = new CalculadorImposto();
	calculador.tributa(acoes);
	calculador.tributa(cc);
//	calculador.tributa(seguro);




	}


}
