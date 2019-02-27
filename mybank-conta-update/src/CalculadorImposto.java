


public class CalculadorImposto {
		private double totalImposto; 
		
			
		
	public void tributa(Tributavel t) {
		double valor = t.getValorImposto();
		this.totalImposto = valor - totalImposto;
	}

	public double getTotalImposto() {
		return totalImposto;
	}

}
