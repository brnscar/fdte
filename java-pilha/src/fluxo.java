public class fluxo {

	public static void main(String[] args) {
		System.out.println("inicio da main");
		metodo1();
		System.out.println("fim da main");
	}
	private static void metodo1() {
		// TODO Auto-generated method stub
		System.out.println("inicio do metodo1");
		metodo2();
		System.out.println("fim do metodo1");
	}
	public static void metodo2() {
		System.out.println("inicio do metodo2");
		for(int x = 0; x<=10; x++) {
			System.out.println(x);
			try {
			int a = x/0;
			} catch (ArithmeticException ex){
		}	
	}
		System.out.println("fim do metodo2");

}
}
