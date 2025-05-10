package carlos.conversormonedas.modelos;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consultor {
    public CodigosDeMonedaActuales obtenerMonedasActuales() {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/d82034ea31a337eb2a125d08/codes");
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return  new Gson().fromJson(response.body(), CodigosDeMonedaActuales.class);
        } catch (Exception e) {
            throw new RuntimeException("Incapaz de obtener las monedas actualizadas");
        }
    }
    public RespuestaEchangeRate obtenerTazaDeCambio(String moneda) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/d82034ea31a337eb2a125d08/latest/" + moneda);

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return  new Gson().fromJson(response.body(), RespuestaEchangeRate.class);

        } catch (Exception e) {
            throw new RuntimeException("No encontre la moneda: " + moneda);
        }
    }
}
