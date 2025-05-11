package carlos.conversormonedas.modelos.apis.exchangerate;

import carlos.conversormonedas.modelos.apis.ApisDeDivisas;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdaptadorExchangeRate implements ApisDeDivisas {
    String nombre = "Exchange Rate";
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
            RespuestaExchangeRate respuesta = new Gson().fromJson(response.body(), RespuestaExchangeRate.class);
            tazaDeCambio = respuesta.conversion_rates().get(monedaObjetivo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public double obtenerTazaDeCambio(String monedaBase, String monedaObjetivo) {
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
            MonedasExchangeRate monedas =  new Gson().fromJson(response.body(), MonedasExchangeRate.class);
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║                  Lista de Monedas                    ║");
            System.out.println("╠═════════════════════════════════════════╦════════════╣");
            System.out.println("║             Código Nombre               ║ Código ISO ║");
            System.out.println("╠═════════════════════════════════════════╬════════════╣");

            for (String codigo : monedas.supported_codes().keySet()) {
                String nombre = monedas.supported_codes().get(codigo);
                System.out.printf("║ %-39s ║    %-7s ║%n", nombre, codigo);
            }

            System.out.println("╚═════════════════════════════════════════╩════════════╝");
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException("Incapaz de obtener las monedas actualizadas");
        }
    }

    @Override
    public String getNombre() {
        return nombre;
    }

}
