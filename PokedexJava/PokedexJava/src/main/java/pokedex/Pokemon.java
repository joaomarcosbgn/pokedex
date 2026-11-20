package pokedex;

public class Pokemon {
    private final int id;
    private final String nome;
    private final int altura;
    private final int peso;

    public Pokemon(int id, String nome, int altura, int peso) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getAltura() { return altura; }
    public int getPeso() { return peso; }
}
