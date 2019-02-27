

public  class AcaoBolsa implements Tributavel{
		public double investimento;
		
	@Override
	public double getValorImposto() {
	return this.getInvestimento() - getInvestimento() * 0.05;
	
	}

	public double getInvestimento() {
		return investimento;
	}

	public void setInvestimento(double investimento) {
		this.investimento = investimento;
	}
	@Override
	public boolean tributa (int investimento, double preco) {
	if(this.investimento == investimento) {
		return true;
	} else {
		return false;
	}
	}


	
}
