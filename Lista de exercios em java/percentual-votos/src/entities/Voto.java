package entities;

public class Voto {

	private Integer totalEleitores;
	private Integer votosValidos;
	private Integer votosBrancos;
	private Integer votosNulos;

	public Voto(Integer totalEleitores, Integer votosValidos, Integer votosBrancos, Integer votosNulos) {
		super();
		this.totalEleitores = totalEleitores;
		this.votosValidos = votosValidos;
		this.votosBrancos = votosBrancos;
		this.votosNulos = votosNulos;
		
	}

	public Double pencentualVotosValidos() {

		return (double) (this.votosValidos / (double) this.totalEleitores) * 100;
	}
	
	public Double pencentualVotosBranco() {
		return (double) (this.votosNulos / (double) this.totalEleitores) * 100;
	}
	
	public Double pencentualVotosNulos() {
		return (double) (this.votosBrancos / (double) this.totalEleitores) * 100;
	}

}
