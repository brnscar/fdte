
public class ContaCorrente extends Conta {

	public ContaCorrente(int agencia, int numero) {
		super (agencia, numero);

	}

	@Override
	public boolean saca(double valor) {
		// TODO Auto-generated method stub
		double DescontoSaca  = valor + 1;
		return super.saca(DescontoSaca);
	}

	@Override
	public void deposita(double valor) {
		super.saldo = super.saldo + valor;		
	}

}
