package batalha;

public class Guerreiro extends Personagem {

	public Guerreiro(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	@Override
	final void checarRegraDeClasse() {
		if(isResistenciaMenorQueAtaque() || isAtaqueMenorQueResistencia()
		|| isDefesaMaiorOuIgualQueAtaque() || isDefesaMaiorOuIgualQueResistencia()
		|| isVelocidadeMaiorOuIqualQueAtaque() || isVelocidadeMaiorOuIgualQueResistencia()) {
		    throw new IllegalArgumentException("Parâmetros inválidos para criar um Guerreiro");
		}
	}

	private Boolean isResistenciaMenorQueAtaque() {
	    return this.getResistencia() < this.getAtaque();
	}
	
	private Boolean isAtaqueMenorQueResistencia() {
	    return this.getAtaque() < this.getResistencia();
	}
	
	private Boolean isDefesaMaiorOuIgualQueAtaque() {
	    return this.getDefesa() >= this.getAtaque();
	}
	
	private Boolean isDefesaMaiorOuIgualQueResistencia() {
	    return this.getDefesa() >= this.getResistencia();
	}
	
	private Boolean isVelocidadeMaiorOuIqualQueAtaque() {
	    return this.getVelocidade() >= this.getAtaque();
	}
	
	private Boolean isVelocidadeMaiorOuIgualQueResistencia() {
	    return this.getVelocidade() >= this.getResistencia();
	}
}
