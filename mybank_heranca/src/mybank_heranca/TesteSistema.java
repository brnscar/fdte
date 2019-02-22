package mybank_heranca;

public class TesteSistema {
	public static void main(String[] args) {

		  Gerente bruno = new Gerente(); 
		  bruno.setSenha(8802); 
		  bruno.setNome("Bruno");
		  
		  Admin admin = new Admin();
		  admin.setSenha(8802);
		  admin.setNome("Norival");
		  
		  Cliente cliente = new Cliente();
		  cliente.setSenha(8802);
		  
		  Fiscal fiscal = new Fiscal();
		  fiscal.setSenha(8802);

		  SistemaInterno sys = new SistemaInterno(); 
		  System.out.println(" Parabéns " +
		  bruno.getNome()); sys.autentica(bruno);
	
		  System.out.println(" Parabéns " + admin.getNome());
		  sys.autentica(admin);

		  System.out.println(" Parabéns visitante");
		  sys.autentica(cliente);
		  
		  System.out.println(" Parabéns fiscal");
		  sys.autentica(fiscal);


	}
}
