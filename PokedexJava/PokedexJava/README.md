# Pokédex Java

Projeto Java 21 com interface Swing, consumo da PokéAPI via HttpClient e leitura de JSON com Gson.

## Abrir

Abra a pasta `PokedexJava` no VS Code (File > Open Folder). Aguarde o Java carregar o `pom.xml` e baixar a dependência Gson. Execute `src/main/java/pokedex/App.java` usando **Run**.

Se o VS Code pedir para importar o projeto Maven, aceite. A conexão à internet é necessária para baixar o Gson e consultar os Pokémon.

## Estrutura

- `App.java`: inicia a interface.
- `PokedexGUI.java`: janela e busca sem travar a interface.
- `Pokemon.java`: modelo dos dados.
- `PokemonAPI.java`: chamada HTTP e leitura do JSON.
- `pom.xml`: configura Java 21 e Gson.
