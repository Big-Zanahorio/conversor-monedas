package carlos.conversormonedas.modelos;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdaptadorExchangeRate implements ApisDeDivisas {
    double tazaDeCambio;
    public boolean conversionValida(String monedaBase, String monedaObjetivo) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/d82034ea31a337eb2a125d08/latest/" + monedaBase);

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
            RespuestaEchangeRate respuesta = new Gson().fromJson(response.body(), RespuestaEchangeRate.class);
            tazaDeCambio = respuesta.conversion_rates().get(monedaObjetivo);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("No encontre la moneda: " + monedaBase + " en la primer API");
        }
    }

    @Override
    public double obtenerTazaDeCambio() {
        return tazaDeCambio;
    }
    public void muestraConversionesValidas() {
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
            CodigosDeMonedaActuales monedas =  new Gson().fromJson(response.body(), CodigosDeMonedaActuales.class);
            for (String moneda : monedas.supported_codes().keySet()) {
                System.out.println(monedas.supported_codes().get(moneda) + ": " + moneda);
            }
        } catch (Exception e) {
            throw new RuntimeException("Incapaz de obtener las monedas actualizadas");
        }
    }

}
