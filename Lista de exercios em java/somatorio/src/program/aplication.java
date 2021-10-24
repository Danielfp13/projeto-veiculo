package program;

import java.util.Scanner;

import entities.Somatorio;

public class aplication {
	public static void main(String[] args) {
		try (
			Scanner sc = new Scanner(System.in)) {
			System.out.println("Digite um numero:");
			int numero = sc.nextInt();
			Somatorio somatorio = new Somatorio();
			System.out.println("O somatório dos números multiplos de 3 ou 5 a baixo de de " + numero + " é = "  + somatorio.calcula(numero) + ".");
		}
	}
}
