package entities;

public class BublbeSort {
	private int vet[];
	
	public BublbeSort(int[] vet) {
		this.vet = vet;
	}

	public void ordenar() {
		int chave;
		int aux;
		do {
			chave = 0;
			for (int i = 0; i < vet.length - 1; i++) {
				if (vet[i] > vet[i + 1]) {
					aux = vet[i];
					vet[i] = vet[i + 1];
					vet[i + 1] = aux;
					chave = 1;
				}
			}
		} while (chave == 1);

		System.out.print("[ ");
		for (int i = 0; i < vet.length; i++) {

			System.out.print(vet[i] + " ");

		}
		System.out.println("]");
	}

}
