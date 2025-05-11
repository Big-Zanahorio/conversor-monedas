package carlos.conversormonedas.modelos.apis.openexchangerates;

import carlos.conversormonedas.modelos.apis.ApisDeDivisas;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdaptadorOpenExchangeRates implements ApisDeDivisas {
    private String nombre = "Open Exchange Rates";
    @Override
    public boolean conversionValida(String monedaBase, String monedaObjetivo) {
        return monedaBase.equals("USD"); // Solo maneja USD como monedaBase
    }

    @Override
    public double obtenerTazaDeCambio(String monedaBase, String monedaObjetivo) {
        URI direccion = URI.create("https://openexchangerates.org/api/latest.json?app_id=308cfea71ae24654a18c65b6eb4e3676");

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
            RespuestaOpenExchangeRates respuesta = new Gson().fromJson(response.body(), RespuestaOpenExchangeRates.class);
            return respuesta.rates().get(monedaObjetivo);
        } catch (Exception e) {
            throw new RuntimeException("No encontre la monedaBase: " + monedaBase + " en la segunda API");
        }
    }

    @Override
    public void muestraConversionesValidas() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║          Conversión de USD a:         ║");
        System.out.println("╠═══════════════════════════════════════╣");

        URI direccion = URI.create("https://openexchangerates.org/api/latest.json?app_id=308cfea71ae24654a18c65b6eb4e3676");

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
            RespuestaOpenExchangeRates respuesta = new Gson().fromJson(response.body(), RespuestaOpenExchangeRates.class);
            int contador = 0;
            for (String moneda : respuesta.rates().keySet()) {
                System.out.printf("║   %-5s ", moneda);  // Formatea la monedaBase con un espacio de 8 caracteres
                contador++;
                if (contador % 4 == 0) {  // Cada 4 monedas imprimimos un salto de línea
                    System.out.println("║");
                }
            }

            if (contador % 4 != 0) {
                System.out.println("║");  // Asegura que la última línea se cierre correctamente
            }

            System.out.println("╚═══════════════════════════════════════╝");
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException("Error en la API Open Exchange Rates");
        }
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
