package batalha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PersonagemTest {
	
	@Test
    void testGuerreiroDistribuicaoValida() {
        Guerreiro guerreiro = new Guerreiro(7, 3, 3, 7);
        assertEquals(7, guerreiro.getAtaque());
        assertEquals(3, guerreiro.getDefesa());
        assertEquals(3, guerreiro.getVelocidade());
        assertEquals(7, guerreiro.getResistencia());
        assertEquals(35, guerreiro.getVida()); // 5 * resistencia
    }
	
	@Test
    void testSomatorioInvalido() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            Guerreiro guerreiro = new Guerreiro(7, 3, 3, 8); // Soma = 21
            guerreiro.checarTotal();
        });
        assertEquals("Somatório dos atributos deve ser igual a 20.", exception.getMessage());
    }
	
    @Test
    void testAtributoAbaixoMinimo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guerreiro(7, 3, 2, 8); // Velocidade < 3
        });
        assertEquals("Atributo não pode ter valor menor do que 3.", exception.getMessage());
    }
	
    //Testes sobre as regras de defini��o dos atributos do GUERREIRO
    @Test
    void testRestricoesGuerreiroResistenciaMenorQueAtaque() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guerreiro(8, 3, 3, 6); // Resist�ncia < Ataque
        });
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }
    
    @Test
    void testRestricoesGuerreiroAtaqueMenorQueResistencia() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guerreiro(6, 3, 3, 8); // Resist�ncia > Ataque
        });
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }

    @Test
    void testRestricoesGuerreiroDefesaMaiorOuIgualQueAtaque() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guerreiro(7, 7, 3, 7); // Defesa >= Ataque
        });
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }

    @Test
    void testRestricoesGuerreiroVelocidadeMaiorOuIgualQueResistencia() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guerreiro(7, 3, 8, 7); // Velocidade >= Resist�ncia
        });
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }
	
    //Testes sobre as regras de defini��o dos atributos do ASSASSINO
    @Test
    void testRestricoesAssassinoAtaqueMenorQueVelocidade() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Assassino(6, 3, 8, 3); // Ataque < Velocidade
        });
        assertEquals("Parâmetros inválidos para criar um Assassino", exception.getMessage());
    }

    @Test
    void testRestricoesAssassinoVelocidadeMenorQueAtaque() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Assassino(8, 3, 6, 3); // Velocidade < Ataque
        });
        assertEquals("Parâmetros inválidos para criar um Assassino", exception.getMessage());
    }

    @Test
    void testRestricoesAssassinoResistenciaMaiorQueAtaque() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Assassino(8, 3, 8, 9); // Resist�ncia > Ataque
        });
        assertEquals("Parâmetros inválidos para criar um Assassino", exception.getMessage());
    }

    @Test
    void testRestricoesAssassinoDefesaMaiorQueVelocidade() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Assassino(8, 9, 8, 3); // Defesa > Velocidade
        });
        assertEquals("Parâmetros inválidos para criar um Assassino", exception.getMessage());
    }

	//Testes para cobrir MC/DC
    //MCDC01
    @Test
    void testMcDcGuerreiroValido() {
        Guerreiro guerreiro = new Guerreiro(7, 3, 3, 7);
        assertEquals(7, guerreiro.getAtaque());
        assertEquals(3, guerreiro.getDefesa());
        assertEquals(3, guerreiro.getVelocidade());
        assertEquals(7, guerreiro.getResistencia());
        assertEquals(35, guerreiro.getVida());
    }
    
    //MCDC02
    @Test
    void testMcDcGuerreiroResistenciaMenorQueAtaque() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
           new Guerreiro(8, 3, 3, 6); 
        });
        
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }
    
    //MCDC03
    @Test
    void testMcDcGuerreiroAtaqueMenorQueResistencia() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           new Guerreiro(6, 3, 3, 8);
        });
        
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }
    
    //MCDC04
    //MCDC05
    @Test
    void testMcDcGuerreiroDefesaMaiorOuIgualAtaqueEResistencia() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            new Guerreiro(5, 6, 4, 5);
        });
        
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }
    
    //MCDC06
    //MCDC07
    @Test
    void testMcDcGuerreiroVelocidadeMaiorOuIgualAtaqueEResistencia() {
        //Integer ataque, Integer defesa, Integer velocidade, Integer resistencia;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            new Guerreiro(5, 4, 6, 5);
        });
        
        assertEquals("Parâmetros inválidos para criar um Guerreiro", exception.getMessage());
    }
}
