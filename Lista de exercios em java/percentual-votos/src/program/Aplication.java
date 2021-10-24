package program;

import entities.Voto;

public class Aplication {

	public static void main(String[] args) {
		
		Voto voto = new Voto(1000, 800, 150, 50);
		
		
		System.out.println("Percentual de votos validos = " + voto.pencentualVotosValidos() + "%");
		
		System.out.println("Percentual de votos em Branco = " + voto.pencentualVotosBranco() + "%");
		
		System.out.println("Percentual de votos nulos = " + voto.pencentualVotosNulos() + "%");
	}
}
