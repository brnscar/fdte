
public class TesteContas {

	public static void main(String[] args) {

			ContaCorrente cc = new ContaCorrente(3, 111);
			cc.deposita(10);
		
			ContaPoupanca cp = new ContaPoupanca(2, 888);
			cp.deposita(5);
			
			cc.saca(20);
			cp.saca(50);
			
			cc.transfere(3, cp);
			System.out.println("CC: " + cc.getSaldo());
			System.out.println("CP: " + cp.getSaldo());
			
			
			cc.saca(20);
			cp.saca(20);
			System.out.println("Você retirou 20$" + ", seu saldo agora é de " + cc.getSaldo());
			System.out.println("Você retirou 20$" + ", seu saldo agora é de " + cp.getSaldo() + "\n");
			
			cp.saca(30.0);
			System.out.println("Seu saldo é: " + cp.getSaldo());
	}

}
