

public class SeguroVida implements Tributavel{
			public double preco;
			
	@Override
	public double getValorImposto() {
		// TODO Auto-generated method stub
		return this.preco + preco * 0.05;
	}

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = 500;
	}

	@Override
	public boolean tributa(int investimento, double preco) {
		if(this.preco == preco) {
			return true;
		} else {
			return false;
		}
		}


		
}
