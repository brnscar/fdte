
public class ContaPoupanca extends Conta {

	public ContaPoupanca(int agencia, int numero) {
		super(agencia, numero);
		// TODO Auto-generated constructor stub
	}
		@Override
		public void deposita(double valor) {
			// TODO Auto-generated method stub
			super.saldo += valor;
		}
}
