package entities;

public class Somatorio {

	private Integer somatorio;
	
	public Somatorio() {

	}

	public Integer calcula(Integer limite) {
		
		this.somatorio = 0;
		for (int i = 3; i < limite; i++) {
			
			if(i % 3 == 0 || i % 5 == 0) {
				
				somatorio += i;
			}
			
		}
		
		return this.somatorio;
	}

	
}
