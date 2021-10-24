package entities;

public class Fatorial {

	private Integer numero;
	private Long fatorial;
	
	public Fatorial(Integer numero) {
		this.numero = numero;
		this.fatorial = 1L;
	}
	
	public Long calculaFatorial() {
		
		 for (int i = 1; i <= this.numero; i++) {
	 
			this.fatorial = this.fatorial *  i;
		}
		 return this.fatorial;
	}
	
}
