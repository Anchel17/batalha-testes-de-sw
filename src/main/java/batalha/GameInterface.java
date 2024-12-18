package batalha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameInterface {

    public ArrayList<Personagem> gameStart(Scanner scanner) {
        int choice = -1;
        Personagem p1 = null;
        Personagem p2 = null;
        promptWellCome();
        while (true) {
            choice = promptChoosePersonagem(choice, scanner);
            p1 = mainMenu(scanner, choice);
            if(p1 != null) {
                break;
            }
        }
        choice = decideOpponent(scanner);

        while (true) {
            switch (choice) {
                case 1:
                    choice =promptChoosePersonagem(choice, scanner);
                    p2 = mainMenu(scanner, choice);
                    break;
                case 2:
                    p2 = p1 instanceof Guerreiro ? new PersonagemBuilder().umAssassinoValido() : new PersonagemBuilder().umGuerreiroValido();
                    break;
                case 0:
                    exitGame();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            if(p2 != null) {
                break;
            }
        }

        return new ArrayList<>(Arrays.asList(p1, p2));
    }

    private Personagem mainMenu(Scanner scanner, int choice) {        
        Personagem p = null;
        while(true) {
            switch (choice) {
                case 1:
                    p = promptGuerreiroCreation(scanner);
                    break;
                case 2:
                    p = promptAssassinoCreation(scanner);
                    break;
                case 3:
                    promptSkillsInfo();
                    break;
                case 0: {
                    exitGame();
                }
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            if(p != null || choice == 3) {
                break;
            }
        }

        return p;
    }

    private void promptWellCome() {
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println("* Bem-vindo ao Jogo de RPG - Bed Stuy: Do or Die                            *");
		System.out.println("* O jogo é para até 2 jogadores, que podem ser Guerreiro ⛨  ou Assassino ⚔  *");
		System.out.println("* Cada jogador tem até 20 pontos para serem distribuídos entre as           *");
		System.out.println("* habilidades do seu personagem:                                            *");
		System.out.println("* - Ataque                                                                  *\n" +
						   "* - Defesa                                                                  *\n" +
						   "* - Velocidade                                                              *\n" +
						   "* - Resistencia                                                             *");
		System.out.println("*                                                                           *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	}

    private int promptChoosePersonagem(int choice, Scanner scanner) {
        while (true) {
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            System.out.println("* Escolha um Personagem ou Saiba mais sobre as habilidades:                 *\n" +
                               "* 1 - Guerreiro                                                             *\n" +
                               "* 2 - Assassino                                                             *");
            System.out.println("* 3 - Sobre as habilidades                                                  *");
            System.out.println("*                                                                           *");
            System.out.println("* Escolha 0 sempre que quiser sair do programa                              *");
            System.out.println("*                                                                           *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 0 && choice <=3) {
                    break;
                } else {
                    System.out.println("Opção inválida recebida!");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida recebida!");
                scanner.nextLine();
            }
        }
        return choice;
    }

	private void promptSkillsInfo() {
		System.out.println(
				"* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println(
				"* - Resistência                                                                               *");
		System.out.println(
				"*     Descrição: Capacidade de suportar danos, definindo os Pontos de Vida (HP) máximos. O HP *");
		System.out.println(
				"*     inicial de cada personagem é cinco vezes o valor de sua Resistência.                    *");
		System.out.println(
				"*     Impacto no Jogo: Quanto maior a Resistência, maior o HP, permitindo que o personagem    *");
		System.out.println(
				"*     suporte mais dano.                                                                      *");
		System.out.println(
				"* - Ataque                                                                                    *");
		System.out.println(
				"*     Descrição: Habilidade técnica e proficiência em combate ofensivo.                       *");
		System.out.println(
				"*     Impacto no Jogo: Influencia diretamente o dano causado ao oponente.                     *");
		System.out.println(
				"* - Defesa                                                                                    *");
		System.out.println(
				"*     Descrição: Capacidade de reduzir o dano recebido dos ataques inimigos.                  *");
		System.out.println(
				"*     Impacto no Jogo: Quanto maior a Defesa, menor o dano sofrido.                           *");
		System.out.println(
				"* - Velocidade                                                                                *");
		System.out.println(
				"*     Descrição: Agilidade e rapidez em combate.                                              *");
		System.out.println(
				"*     Impacto no Jogo: Determina a ordem dos turnos e a chance de evasão de ataques.          *");
		System.out.println(
				"* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	}

	private Guerreiro promptGuerreiroCreation(Scanner scanner) {
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println("* Você escolheu: Guerreiro ⛨                                         *");
		System.out.println("* Você pode definir suas propriedades ou deixar que o sistema crie um *");
		System.out.println("* Guerreiro para você:                                                *");
		System.out.println("* 1 - Definir Pontos                                                  *");
		System.out.println("* 2 - Gerar pelo sistema                                              *");
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		while (true) {
			try {
				int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice != 1 && choice != 2) {
                    throw new Exception("invalid option");
                }

                if(choice == 2) {
                    return getDefaultGuerreiro();
                }

				return promptCreateGuerreiroManual(scanner);
			} catch (Exception e) {
				System.out.println("Opção inválida recebida!");
                System.out.println("Escolha uma opção válida:");
		        System.out.println("1 - Definir Pontos");
		        System.out.println("2 - Gerar pelo sistema");
			}
		}
	}

	private static Guerreiro promptCreateGuerreiroManual(Scanner scanner) {
        final int maxPoints = 20;
        final int minStats = 3;
    
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* Distribua seus pontos seguindo as regras abaixo:        *");
        System.out.println("* Restrições:                                             *");
        System.out.println("* - Resistência: Deve ser igual ou maior que o Ataque.    *");
        System.out.println("* - Defesa e Velocidade: Não podem ultrapassar Res ou Atk.*");
        System.out.println("* Mínimo: Cada atributo deve ser pelo menos 3 pontos.     *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println();
    
        PersonagemBuilder guerreiro = new PersonagemBuilder();
        int pontosRestantes = maxPoints;

        int ataque = setAttribute("Ataque", pontosRestantes, minStats, scanner);
        guerreiro.comAtaque(ataque);
        pontosRestantes -= ataque;
    
        int resistencia;
        while (true) {
            resistencia = setAttribute("Resistência", pontosRestantes, minStats, scanner);
            if (resistencia >= ataque) {
                guerreiro.comResistencia(resistencia);
                pontosRestantes -= resistencia;
                break;
            } else {
                System.out.println("Resistência deve ser maior ou igual ao Ataque (" + ataque + ").");
            }
        }
    
        int maxDefesa = Math.min(ataque, resistencia);
        int defesa = setAttribute("Defesa", pontosRestantes, minStats, scanner, maxDefesa);
        guerreiro.comDefesa(defesa);
        pontosRestantes -= defesa;
    
        int maxVelocidade = Math.min(ataque, resistencia);
        int velocidade = setAttribute("Velocidade", pontosRestantes, minStats, scanner, maxVelocidade);
        guerreiro.comVelocidade(velocidade);
        pontosRestantes -= velocidade;
    
        System.out.println("Pontos restantes: " + pontosRestantes);
        return guerreiro.doTipoGuerreiro();
    }

    private Assassino promptAssassinoCreation(Scanner scanner) {
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println("* Você escolheu: Assassino ⚔                                         *");
		System.out.println("* Você pode definir suas propriedades ou deixar que o sistema crie um *");
		System.out.println("* Assassino para você:                                                *");
		System.out.println("* 1 - Definir Pontos                                                  *");
		System.out.println("* 2 - Gerar pelo sistema                                              *");
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		while (true) {
			try {
				int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice != 1 && choice != 2) {
                    throw new Exception("invalid option");
                }

                if(choice == 2) {
                    return getDefaultAssassino();
                }
				
                return promptCreateAssassinoManual(scanner);
			} catch (Exception e) {
				System.out.println("Opção inválida recebida!");
                System.out.println("Escolha uma opção válida:");
                System.out.println("1 - Definir Pontos");
                System.out.println("2 - Gerar pelo sistema");
			}
		}
	}

	private static Assassino promptCreateAssassinoManual(Scanner scanner) {
        final int maxPoints = 20;
        final int minStats = 3;
    
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* Distribua seus pontos seguindo as regras abaixo:        *");
        System.out.println("* Restrições:                                             *");
        System.out.println("* - Velocidade: Deve ser igual ou maior que o Ataque.     *");
        System.out.println("* - Resistência e Defesa: Não podem ultrapassar Vel ou Atk*");
        System.out.println("* Mínimo: Cada atributo deve ser pelo menos 3 pontos.     *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println();
    
        PersonagemBuilder assassino = new PersonagemBuilder();
        int pontosRestantes = maxPoints;
    
        int velocidade = setAttribute("Velocidade", pontosRestantes, minStats, scanner);
        assassino.comVelocidade(velocidade);
        pontosRestantes -= velocidade;
    
        int ataque;
        while (true) {
            ataque = setAttribute("Ataque", pontosRestantes, minStats, scanner);
            if (ataque <= velocidade) {
                assassino.comAtaque(ataque);
                pontosRestantes -= ataque;
                break;
            } else {
                System.out.println("Ataque deve ser menor ou igual à Velocidade (" + velocidade + ").");
            }
        }
    
        int maxResistencia = Math.min(ataque, velocidade);
        int resistencia = setAttribute("Resistência", pontosRestantes, minStats, scanner, maxResistencia);
        assassino.comResistencia(resistencia);
        pontosRestantes -= resistencia;
    
        int maxDefesa = Math.min(ataque, velocidade);
        int defesa = setAttribute("Defesa", pontosRestantes, minStats, scanner, maxDefesa);
        assassino.comDefesa(defesa);
        pontosRestantes -= defesa;
    
        System.out.println("Pontos restantes: " + pontosRestantes);
        return assassino.doTipoAssassino();
    }  

    private static int setAttribute(String attributeName, int maxPoints, int minStats, Scanner scanner) {
        return setAttribute(attributeName, maxPoints, minStats, scanner, maxPoints);
    }
    
    private static int setAttribute(String attributeName, int maxPoints, int minStats, Scanner scanner, int limit) {
        int attribute;
        while (true) {
            try {
                int effectiveMax = Math.min(maxPoints, limit);
                System.out.println("Defina a " + attributeName + ": (min: " + minStats + ", máx: " + effectiveMax + ")");
                attribute = scanner.nextInt();
                scanner.nextLine();
                if (attribute < minStats || attribute > effectiveMax) {
                    throw new Exception("Valor fora dos limites.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Valor inválido! Tente novamente.");
            }
        }
        return attribute;
    }
    
	private static Guerreiro getDefaultGuerreiro() {
		Guerreiro guerreiro = new PersonagemBuilder().umGuerreiroValido();
		System.out.println("* * * * * * * * * * * * * * * * *");
		System.out.println("* Guerreiro criado com sucesso: *");
		System.out.println("* Ataque: " + guerreiro.getAtaque() + " *");
		System.out.println("* Defesa: " + guerreiro.getDefesa() + " *");
		System.out.println("* Resistência: " + guerreiro.getResistencia() + " *");
		System.out.println("* Velocidade: " + guerreiro.getVelocidade() + " *");
		System.out.println("* * * * * * * * * * * * * * * * *");
		return guerreiro;
	}

	private static Assassino getDefaultAssassino() {
		Assassino assassino = new PersonagemBuilder().umAssassinoValido();
		System.out.println("* * * * * * * * * * * * * * * * *");
		System.out.println("* Assassino criado com sucesso: *");
		System.out.println("* Ataque: " + assassino.getAtaque() + " *");
		System.out.println("* Defesa: " + assassino.getDefesa() + " *");
		System.out.println("* Resistência: " + assassino.getResistencia() + " *");
		System.out.println("* Velocidade: " + assassino.getVelocidade() + " *");
		System.out.println("* * * * * * * * * * * * * * * * *");
		return assassino;
	}

    private int decideOpponent(Scanner scanner) {
        int choice;
        while (true) {
            System.out.println("* * * * * * * * * * * * * * * * * * * *");
            System.out.println("* Deseja jogar contra outro jogador?  *");
            System.out.println("* 1: Segundo Jogador                  *");
            System.out.println("* 2: contra o PC                      *");
            System.out.println("* * * * * * * * * * * * * * * * * * * *");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    throw new Exception("Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida recebida!");
            }
        }
        return choice;
    }

    public void promptRestart(Scanner scanner) {
        System.out.println("* * * * * * * * * * * * * * * *");
        System.out.println("* Deseja reiniciar a batalha? *");
        System.out.println("* 1 - Sim                     *");
        System.out.println("* 2 - Não                     *");
        System.out.println("* * * * * * * * * * * * * * * *");
        while (true) {
			try {
				int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice != 1 && choice != 2) {
                    throw new Exception("invalid option");
                }

                if(choice == 2) {
                    exitGame();
                }

				promptUseSameCharacters(scanner);
				break;
			} catch (Exception e) {
				System.out.println("Opção inválida recebida!");
                System.out.println("Escolha uma opção válida:");
			}
		}
        
    }

    private void promptUseSameCharacters(Scanner scanner) {
        System.out.println("* * * * * * * * * * * * * * * * * * * *");
        System.out.println("* Utilizar os mesmos personagens?     *");
        System.out.println("* 1 - Sim                             *");
        System.out.println("* 2 - Não                             *");
        System.out.println("* * * * * * * * * * * * * * * * * * * *");
        while (true) {
			try {
				int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice != 1 && choice != 2) {
                    throw new Exception("invalid option");
                }

                if(choice == 2) {
                    gameStart(scanner);
                }
                else if(choice == 1) {
                    break;
                }
			} catch (Exception e) {
				System.out.println("Opção inválida recebida!");
                System.out.println("Escolha uma opção válida:");
			}
		}
    }

    private void exitGame() {
        System.out.println("Encerrando...");
        System.exit(0);
    }
}
