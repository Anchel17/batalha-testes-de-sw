package batalha;

import static java.lang.Math.min;

import java.security.SecureRandom;

public class Batalha {

	private final Personagem primeiroAtacante;

	private final Personagem segundoAtacante;
	
	private final SecureRandom geradorRandomico;

	Batalha(Personagem p1, Personagem p2, int randomico) {
		this.geradorRandomico = new SecureRandom();
		
		if (p1.getVelocidade() > p2.getVelocidade()) {
			this.primeiroAtacante = p1;
			this.segundoAtacante = p2;
		} else if (p1.getVelocidade() < p2.getVelocidade()) {
			this.primeiroAtacante = p2;
			this.segundoAtacante = p1;
		} else {
			if (randomico == 0) {
				this.primeiroAtacante = p1;
				this.segundoAtacante = p2;
			} else {
				this.primeiroAtacante = p2;
				this.segundoAtacante = p1;
			}
		}
	}

	public Batalha(Personagem p1, Personagem p2) {
		this(p1, p2, new SecureRandom().nextInt(2));
	}

	public void realizarPrimeiroAtaque() {
		realizarAtaque(primeiroAtacante, segundoAtacante);
	}
	
	public void realizarSegundoAtaque() {
		realizarAtaque(segundoAtacante, primeiroAtacante);
	}

	private void realizarAtaque(Personagem atacante, Personagem defensor) {
		int chanceEvasao = calcularChanceEvasao(atacante, defensor);
		int randomicoEvasao = geradorRandomico.nextInt(101);
		
		if(this.evadiu(chanceEvasao, randomicoEvasao)) {
			System.out.println(this.getTipoDePersonagem(defensor) + " se evadiu do ataque");
		}
		else {
			double modificadorAtaque = 0.8 + (geradorRandomico.nextDouble() * (0.4));
			int randomicoGolpeCritico = geradorRandomico.nextInt(101);
			if(randomicoGolpeCritico == 0) {
				randomicoGolpeCritico = 1;
			}
			boolean eGolpeCritico = randomicoGolpeCritico <= 10;
			
			atacante.atacar(defensor, modificadorAtaque, eGolpeCritico);
		}
	}

	boolean evadiu(int chanceEvasao, int randomico) {
		return randomico <= chanceEvasao;
	}
	

	int calcularChanceEvasao(Personagem atacante, Personagem defensor) {
		return Batalha.calcularChanceEvasao(atacante.getVelocidade(), defensor.getVelocidade());
	}

	static int calcularChanceEvasao(int velocidadeAtacante, int velocidadeDefensor) {
		int chanceEvasao = min(50, (velocidadeDefensor - velocidadeAtacante) * 2);
		return chanceEvasao < 0 ? 0 : chanceEvasao;
	}

	public boolean temVencedor() {
		return this.primeiroAtacante.getVida() <= 0 || this.segundoAtacante.getVida() <= 0;
	}

	public Personagem getPrimeiroAtacante() {
		return primeiroAtacante;
	}

	public Personagem getSegundoAtacante() {
		return segundoAtacante;
	}

	String getTipoDePersonagem(Personagem p) {
		return p instanceof Guerreiro ? "Guerreiro" : "Assassino";
	}

	public void apresentarVencedor() {
		if (this.primeiroAtacante.getVida() > 0) {
			System.out.println("O " + this.getTipoDePersonagem(primeiroAtacante) + " foi o vencedor desta batalha");
		} else {
			System.out.println("O " + this.getTipoDePersonagem(segundoAtacante) + " foi o vencedor desta batalha");
		}
	}
}
