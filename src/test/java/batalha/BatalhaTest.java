package batalha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

class BatalhaTest {

	@Test
	void testCalculoDanoBase() {
		Personagem atacante = new Guerreiro(7, 3, 3, 7);
		double modificadorAtaque = 1.0; // Modificador dentro do intervalo esperado

		int danoBase = atacante.calcularDanoBase(modificadorAtaque);

		assertEquals(7, danoBase); // Ataque * 1.0 = 7
	}

	@Test
	void testCalculoDanoBaseComModificadorMinimo() {
		Personagem atacante = new Assassino(6, 5, 6, 3);
		double modificadorAtaque = 0.8; // Modificador mínimo permitido

		int danoBase = atacante.calcularDanoBase(modificadorAtaque);

		assertEquals(5, danoBase); // Math.round(6 * 0.8) = 5
	}

	@Test
	void testCalculoDanoBaseComModificadorMaximo() {
		Personagem atacante = new Assassino(6, 5, 6, 3);
		double modificadorAtaque = 1.2; // Modificador máximo permitido

		int danoBase = atacante.calcularDanoBase(modificadorAtaque);

		assertEquals(7, danoBase); // Math.round(6 * 1.2) = 7
	}

	@Test
	void testCalculoDanoInfringidoSemCritico() {
		Personagem atacante = new Guerreiro(7, 3, 3, 7);
		Personagem defensor = new Assassino(6, 5, 6, 3);
		int danoBase = 8;

		int danoInfringido = atacante.calcularDanoInfringindo(danoBase, defensor.getDefesa(), false);

		assertEquals(10, danoInfringido); // 8 (danoBase) + 7 (ataque) - 5 (defesa)
	}

	@Test
	void testCalculoDanoInfringidoComCritico() {
		Personagem atacante = new Guerreiro(7, 3, 3, 7);
		Personagem defensor = new Assassino(6, 5, 6, 3);
		int danoBase = 8;

		int danoInfringido = atacante.calcularDanoInfringindo(danoBase, defensor.getDefesa(), true);

		assertEquals(15, danoInfringido); // (8 + 7 - 5) * 1.5 = 15
	}

	@Test
	void testChanceDeEvasaoComEvasaoOcorrida() {

		Personagem defensor = new Assassino(6, 5, 6, 3);
		Personagem atacante = new Assassino(5, 5, 5, 5);
		int chanceEvasao = Batalha.calcularChanceEvasao(atacante.getVelocidade(), defensor.getVelocidade());

		// Criando condição pra que o valor aleatório seja menor que o valor retornado pela chance de evasão
		int valorAleatorio = 1;

		boolean evasaoOcorrida = valorAleatorio <= chanceEvasao;

		assertTrue(evasaoOcorrida);
	}

	@Test
	void testChanceDeEvasaoSemEvasaoOcorrida() {

		Personagem defensor = new Assassino(6, 5, 6, 3);
		Personagem atacante = new Assassino(5, 5, 5, 5);
		int chanceEvasao = Batalha.calcularChanceEvasao(atacante.getVelocidade(), defensor.getVelocidade());

		// Criando condição pra que o valor aleatório seja maior que o valor retornado pela chance de evasão
		int valorAleatorio = 3;

		boolean evasaoOcorrida = valorAleatorio <= chanceEvasao;

		assertFalse(evasaoOcorrida);
	}

	@Test
	void testReceberDanoNormal() {
		Personagem defensor = new Guerreiro(7, 3, 3, 7);
		defensor.setVida(30);

		defensor.receberDano(10);

		assertEquals(20, defensor.getVida());
	}

	@Test
	void testReceberDanoMinimo() {
		Personagem defensor = new Guerreiro(7, 3, 3, 7);
		defensor.setVida(30);

		defensor.receberDano(0);

		assertEquals(29, defensor.getVida());
	}

	@Test
	void testOrdemDeTurnosBaseadaNaVelocidade() {
		Personagem personagem1 = new Assassino(6, 5, 6, 3);
		Personagem personagem2 = new Guerreiro(7, 3, 3, 7);

		// Simulação do primeiro turno
		boolean personagem1AtacaPrimeiro = personagem1.getVelocidade() > personagem2.getVelocidade();

		assertTrue(personagem1AtacaPrimeiro);
	}

	@Test
	void testAlternanciaDeTurnos() {
		Personagem personagem1 = new Assassino(6, 5, 6, 3);
		Personagem personagem2 = new Guerreiro(7, 3, 3, 7);

		// Primeiro turno: personagem1 ataca
		int vidaInicialPersonagem2 = personagem2.getVida();
		personagem1.atacar(personagem2, 1.0, false);
		assertTrue(personagem2.getVida() < vidaInicialPersonagem2); // Personagem2 sofreu dano

		// Segundo turno: personagem2 ataca
		int vidaInicialPersonagem1 = personagem1.getVida();
		personagem2.atacar(personagem1, 1.0, false);
		assertTrue(personagem1.getVida() < vidaInicialPersonagem1); // Personagem1 sofreu dano
	}

	@Test
    void testDesempateDeTurnosMesmaVelocidade() {
        boolean personagem1AtacaPrimeiro;
        boolean personagem2AtacaPrimeiro;

        Personagem personagem1 = new Guerreiro(7, 3, 3, 7); 
        Personagem personagem2 = new Guerreiro(7, 3, 3, 7);
    	
    	SecureRandom randomico = new SecureRandom();
    	int randomicoP1 = randomico.nextInt(10);
    	int randomicoP2 = randomico.nextInt(10);

    	if(personagem1.getVelocidade() == personagem2.getVelocidade()) {
			if(randomicoP1>randomicoP2) {
				personagem1AtacaPrimeiro = true;
				personagem2AtacaPrimeiro = false;
				assertTrue(personagem1AtacaPrimeiro);
				assertFalse(personagem2AtacaPrimeiro);
			} else {
				personagem1AtacaPrimeiro = false;
				personagem2AtacaPrimeiro = true;
				assertFalse(personagem1AtacaPrimeiro);
				assertTrue(personagem2AtacaPrimeiro);	
			}
    	}
    }
	
	
    @Test
    void testVitoriaDoPrimeiroAtacante() {
        Personagem personagem1 = new Guerreiro(7, 3, 3, 7);
		Personagem personagem2 = new Assassino(6, 5, 6, 3);
        Batalha batalha = new Batalha(personagem1, personagem2);

        // Simula dano letal no segundo atacante
        personagem2.setVida(0);

        assertTrue(batalha.temVencedor());
        //Personagem 2 é o primeiro atacante devido aos critérios definidos no construtor de Batalha
        assertEquals(personagem2, batalha.getPrimeiroAtacante());
    }

    @Test
    void testVitoriaDoSegundoAtacante() {
        Personagem personagem1 = new Guerreiro(7, 3, 3, 7);
		Personagem personagem2 = new Assassino(6, 5, 6, 3);
        Batalha batalha = new Batalha(personagem1, personagem2);

        // Simula dano letal no primeiro atacante
        personagem1.setVida(0);

        assertTrue(batalha.temVencedor());
        //Personagem 1 é o segundo atacante devido aos critérios definidos no construtor de Batalha
        assertEquals(personagem1, batalha.getSegundoAtacante());
    }

    @Test
    void testEmpateSemVencedor() {
        Personagem personagem1 = new Guerreiro(7, 3, 3, 7);
		Personagem personagem2 = new Assassino(6, 5, 6, 3);
        Batalha batalha = new Batalha(personagem1, personagem2);

        personagem1.setVida(0);
        personagem2.setVida(0);

        assertTrue(batalha.temVencedor());
        assertEquals(0, personagem1.getVida());
        assertEquals(0, personagem2.getVida());
    }

    @Test
    void testBatalhaEmAndamento() {
        Personagem personagem1 = new Guerreiro(7, 3, 3, 7);
		Personagem personagem2 = new Assassino(6, 5, 6, 3);
        Batalha batalha = new Batalha(personagem1, personagem2);

        personagem1.setVida(20);
        personagem2.setVida(15);

        assertFalse(batalha.temVencedor());
    }

    @Test
    void testApresentarVencedor() {
        Personagem personagem1 = new Guerreiro(7, 3, 3, 7);
		Personagem personagem2 = new Assassino(6, 5, 6, 3);
        Batalha batalha = new Batalha(personagem1, personagem2);

        // Simula dano letal no segundo atacante
        personagem1.setVida(0);

        batalha.apresentarVencedor();

        assertTrue(batalha.temVencedor());
        assertEquals(personagem1, batalha.getSegundoAtacante());
    }
}
