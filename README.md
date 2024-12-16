# Batalhas - Testes de Software

# Autores
Anchel Vitor Varela da Silva
André Franklin de Oliveira Lima
João Victor da Silva Saturnino


# Introdução
Projeto da disciplina Testes de Software com o objetivo de aplicar na prática conhecimentos adquiridos sobre etapas do processo de testes em software, indo desde a identificação de partições, análise de valores limite, até a implementação de testes unitários e geração de relatório dos testes. O relatório de testes contém a informação da porcentagem de arestas cobertas no código. Além disso, temos tabelas adicionais para auxiliar na rastreabilidade dos casos de teste.

# Compilação e execução
Para compilar o projeto, é necessário ter instalado o Maven com versão 3.9.9 e Java 8. Utilizando o Eclipse, basta executar o projeto pela classe Main e a interface com o usuário tem início. O usuário deverá escolher uma das opções disponíveis no Prompt, sendo assim até o encerramento do programa.
Caso deseje executar os testes pelo eclipse, será necessário instalar o plugin EclEmma Java Code Coverage disponível na loja do eclipse. 
## Execução de Testes
Com o plugin EclEmma instalado, basta apertar com o botão direito do mouse sobre o projeto da batalha > Coverage As > JUnit Test. 
Se preferir a linha de comando, basta executar na pasta raíz do projeto o comando: mvn clean test.


## Gerar relatório de cobertura
Execute o primeiro passo do tópico anterior para ver o relatório de cobertura dentro do eclipse.
Para gerar um relatório de testes via linha de comando, digite: mvn clean test jacoco:report.

# Dependências
- Junit Platform Suite versão 1.11.0-M2
-- Disponível em: [Maven Repository
](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-suite/1.11.0-M2)
- JUnit Jupiter Migration Support versão 5.11.0-M2
-- Disponível em: [Maven Repository
](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-migrationsupport/5.11.0-M2)
- Junit Jupiter params versão 5.11.0-M2
-- Disponível em: [Maven Repository
](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params/5.11.0-M2)
- Jacoco Maven Plugin versão 0.8.7
-- Disponível em: [Maven Repository
](https://mvnrepository.com/artifact/org.jacoco/jacoco-maven-plugin/0.8.12)

## TODO
VERIFICAR A NECESSIDADE DE ADICIONAR ISSO NO POM: `<build>
    <plugins>
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.7</version>
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>prepare-package</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>`

# Uso do Programa
- Selecione se deseja criar um Guerreiro ou Assassino.
- Selecione se deseja distribuir os pontos ou deixar o computador gerar.
- Caso deseje distribuir os pontos, você preenche cada atributo obedecendo as regras dos personagens.
- Seleciona o rival sendo outra pessoa ou o próprio computador.
- Se outra pessoa, ela fará a escolha de personagem e distribuição de atributos.
- Ao final da batalha, você escolhe a opção de jogar novamente ou encerrar o jogo.
- Caso deseje jogar novamente, você pode escolher reiniciar com os mesmos personagens ou criar novos, voltando aos mesmos passos de criação de personagem
- O jogo se encerra quando ao final de uma batalha, o jogador decidir não reiniciar.