package aplication;

import entities.BublbeSort;

public class program {

	public static void main(String[] args) {
		int[] vet = { 5, 3, 2, 4, 7, 1, 0, 6 };
		BublbeSort bs = new BublbeSort(vet);
		bs.ordenar();
	}
}
