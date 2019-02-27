
public class ContaCorrente extends Conta implements Tributavel {

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

	@Override
	public double getValorImposto() {
		// TODO Auto-generated method stub
		return super.getSaldo() - getSaldo() * 0.05;
	}

	@Override
	public boolean tributa(int investimento, double preco) {
		// TODO Auto-generated method stub
		return false;
	}

}
