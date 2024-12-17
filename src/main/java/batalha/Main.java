package batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GameInterface game = new GameInterface();
		ArrayList personagens = game.gameStart(scanner);
		Personagem p1 = (Personagem) personagens.get(0);
		Personagem p2 = (Personagem) personagens.get(1);
		do {
			Batalha batalha = new Batalha(p1, p2);

			while (true) {
				batalha.realizarPrimeiroAtaque();

				if (batalha.temVencedor()) {
					break;
				}

				batalha.realizarSegundoAtaque();

				if (batalha.temVencedor()) {
					break;
				}

			}

			batalha.apresentarVencedor();

			game.promptRestart(scanner);
		} while (true);
	}
}