package batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    
    private static int ataqueP1;
    private static int defesaP1;
    private static int resistenciaP1;
    private static int velocidadeP1;
    private static int vidaP1;
    
    private static int ataqueP2;
    private static int defesaP2;
    private static int resistenciaP2;
    private static int velocidadeP2;
    private static int vidaP2;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GameInterface game = new GameInterface();
		ArrayList<Personagem> personagens = game.gameStart(scanner);
		
		setBackupAttributes(personagens);
		
		Personagem p1 = (Personagem) personagens.get(0);
		Personagem p2 = (Personagem) personagens.get(1);
		do {
		    restoreAttributes(p1, p2);
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
	
	private static void setBackupAttributes(List<Personagem> personagens) {
	    Personagem p1 = personagens.get(0);
	    Personagem p2 = personagens.get(1);
	    
	    ataqueP1 = p1.getAtaque();
	    defesaP1 = p1.getDefesa();
	    resistenciaP1 = p1.getResistencia();
	    velocidadeP1 = p1.getVelocidade();
	    vidaP1 = p1.getVida();
	    
	    ataqueP2 = p2.getAtaque();
        defesaP2 = p2.getDefesa();
        resistenciaP2 = p2.getResistencia();
        velocidadeP2 = p2.getVelocidade();
        vidaP2 = p2.getVida();
	}
	
	private static void restoreAttributes(Personagem p1, Personagem p2) {
	    p1.setAtaque(ataqueP1);
	    p1.setDefesa(defesaP1);
	    p1.setResistencia(resistenciaP1);
	    p1.setVelocidade(velocidadeP1);
	    p1.setVida(vidaP1);
	    
	    p2.setAtaque(ataqueP2);
        p2.setDefesa(defesaP2);
        p2.setResistencia(resistenciaP2);
        p2.setVelocidade(velocidadeP2);
        p2.setVida(vidaP2);
	}
}