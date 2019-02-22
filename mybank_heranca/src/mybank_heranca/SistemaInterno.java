package mybank_heranca;

public class SistemaInterno {
	private int senha = 8802;

	public  void autentica(Autenticavel fa) {
		boolean autentica = fa.autentica (this.senha);
		if(autentica) {
			System.out.println("Você logou");
		}
			else {
				System.err.println("Não foi possivel entrar no sistema!!!");
			}
			
			
		}
	}


