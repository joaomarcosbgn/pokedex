package pokedex;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class PokemonAPI {
    private static final HttpClient CLIENTE = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10)).build();

    public static Pokemon buscar(String nomeOuNumero) throws Exception {
        String termo = URLEncoder.encode(nomeOuNumero.trim().toLowerCase(), StandardCharsets.UTF_8);
        HttpRequest pedido = HttpRequest.newBuilder()
            .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + termo))
            .timeout(Duration.ofSeconds(15)).GET().build();
        HttpResponse<String> resposta = CLIENTE.send(pedido, HttpResponse.BodyHandlers.ofString());
        if (resposta.statusCode() != 200) {
            throw new IllegalArgumentException("Pokémon não encontrado. HTTP " + resposta.statusCode());
        }
        JsonObject json = JsonParser.parseString(resposta.body()).getAsJsonObject();
        return new Pokemon(json.get("id").getAsInt(), json.get("name").getAsString(),
            json.get("height").getAsInt(), json.get("weight").getAsInt());
    }
}
