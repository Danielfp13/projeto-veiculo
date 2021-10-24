package program;

import java.util.Locale;
import java.util.Scanner;

import entities.Fatorial;

public class aplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Digite um numero:");
			Integer numero = sc.nextInt();
			Fatorial fatorial = new Fatorial(numero);
			System.out.println("O Fatorial de " + numero + " é = " +  fatorial.calculaFatorial());
		}
		
	}
}
