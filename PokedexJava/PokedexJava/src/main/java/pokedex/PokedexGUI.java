package pokedex;

import javax.swing.*;
import java.awt.*;

public class PokedexGUI extends JFrame {
    public PokedexGUI() {
        super("Pokédex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 360);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Minha Pokédex", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        JTextField busca = new JTextField(18);
        JButton botao = new JButton("Buscar Pokémon");
        JTextArea resultado = new JTextArea("Digite o nome ou número de um Pokémon para começar.");
        resultado.setEditable(false);
        resultado.setLineWrap(true);
        resultado.setWrapStyleWord(true);
        JPanel topo = new JPanel();
        topo.add(busca);
        topo.add(botao);
        botao.addActionListener(e -> {
            String nome = busca.getText().trim();
            if (nome.isEmpty()) {
                resultado.setText("Digite um nome ou número.");
                return;
            }
            botao.setEnabled(false);
            resultado.setText("Buscando...");
            new SwingWorker<Pokemon, Void>() {
                @Override protected Pokemon doInBackground() throws Exception {
                    return PokemonAPI.buscar(nome);
                }
                @Override protected void done() {
                    botao.setEnabled(true);
                    try {
                        Pokemon p = get();
                        resultado.setText("Nome: " + p.getNome() + "\nNúmero: " + p.getId() +
                            "\nAltura: " + (p.getAltura() / 10.0) + " m\nPeso: " + (p.getPeso() / 10.0) + " kg");
                    } catch (Exception ex) {
                        resultado.setText("Não foi possível encontrar o Pokémon. Verifique o nome e a conexão.");
                    }
                }
            }.execute();
        });
        setLayout(new BorderLayout(10, 10));
        add(titulo, BorderLayout.NORTH);
        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.add(topo, BorderLayout.NORTH);
        centro.add(new JScrollPane(resultado), BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);
    }
}
