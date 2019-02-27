
public abstract class Conta {
	
	protected double saldo;
	private int agencia;
	private int numero;
	private Cliente titular;
	private static int total=0;

	public Conta (int agencia, int numero) {
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = 100;
		System.out.println("Sua conta " + getNumero() + " foi criada");
		++total;
	}
	

	public abstract void deposita (double valor);
	
		 
	public boolean saca(double valor) {
		if (this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		} else
			System.out.println("Você não pode sacar um valor maior que seu saldo");
		
		return false;
		
		
	}
	
	public boolean transfere(double valor, Conta destino) {
		if(this.saldo >= valor ) {
			
			this.saldo -= valor;
			destino.deposita(valor);
			
		
		return true;
		}
		
		return false;
	}
	public double getSaldo(){
		return this.saldo;
	
}  
	public int getNumero(){
		return this.numero;
	
}  
	public void setNumero(int numero) {
		if ( numero<1000  || numero > 10000  ) {
			System.out.println("numero invalido");
		return;
	}
		this.numero = numero;
	}
	
	public int getAgencia() {
		
		return this.agencia;
	}
	public void setAgencia(int agencia) {
		if ( agencia <= 0 ) {
			System.out.println("agencia invalida");
			}
		this.agencia = agencia;
	}
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	public Cliente getTitular() {
		return this.titular;
	}
	
	public static int getTotal() {
		return Conta.total;
	}
}

