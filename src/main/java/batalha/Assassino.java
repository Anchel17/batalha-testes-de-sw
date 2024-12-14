package batalha;

public class Assassino extends Personagem {

	public Assassino(Integer ataque, Integer defesa, Integer velocidade, Integer resistencia) {
		super(ataque, defesa, velocidade, resistencia);
	}

	@Override
	final void checarRegraDeClasse() {
	    if(isAtaqueMenorQueVelocidade() || isVelocidadeMenorQueAtaque()
	    || isResistenciaMaiorQueAtaque() || isResistenciaMaiorQueVelocidade()
	    || isDefesaMaiorQueAtaque() || isDefesaMaiorQueVelocidade()) {
	        throw new IllegalArgumentException("Parâmetros inválidos para criar um Assassino");
	    }
	}
	
	private Boolean isAtaqueMenorQueVelocidade() {
	    return this.getAtaque() < this.getVelocidade();
	}
	
	private Boolean isVelocidadeMenorQueAtaque() {
	    return this.getVelocidade() < this.getAtaque();
	}
	
	private Boolean isResistenciaMaiorQueAtaque() {
	    return this.getResistencia() > this.getAtaque();
	}
	
	private Boolean isResistenciaMaiorQueVelocidade() {
	    return this.getResistencia() > this.getVelocidade();
	}
	
	private Boolean isDefesaMaiorQueAtaque() {
	    return this.getDefesa() > this.getAtaque();
	}
	
	private Boolean isDefesaMaiorQueVelocidade() {
	    return this.getDefesa() > this.getVelocidade();
	}
}
