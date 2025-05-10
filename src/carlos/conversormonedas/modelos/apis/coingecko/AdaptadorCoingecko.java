package carlos.conversormonedas.modelos.apis.coingecko;

import carlos.conversormonedas.modelos.apis.ApisDeDivisas;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class AdaptadorCoingecko implements ApisDeDivisas {
    String nombre = "Coingecko";
    TazaDeCambioCoingecko tazaDeCambio;

    @Override
    public boolean conversionValida(String monedaBase, String monedaObjetivo) {
        String apiKey = "CG-wjCavp3eYrsXuoWG2N4zAtsb";
        URI direccion = URI.create("https://api.coingecko.com/api/v3/simple/price?ids=" +
                monedaBase + "&vs_currencies=" +
                monedaObjetivo + "&x_cg_demo_api_key=" +
                apiKey);
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Respuesta: " + response.body());
            RespuestaCoingeckoMultiples respuesta = new Gson().fromJson(response.body(), RespuestaCoingeckoMultiples.class);
            System.out.println("Monedas: ");
            for (Map.Entry<String, MonedaCoingecko> entry : respuesta.rates().entrySet()) {
                String codigo = entry.getKey(); // btc, eth, etc.
                MonedaCoingecko moneda = entry.getValue();

                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + moneda.name());
                System.out.println("Unidad: " + moneda.unit());
                System.out.println("Valor: " + moneda.value());
                System.out.println("Tipo: " + moneda.type());
                System.out.println();
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Incapaz de obtener las monedas actualizadas");
        }
    }

    @Override
    public double obtenerTazaDeCambio(String monedaBase, String monedaObjetivo) {
        return tazaDeCambio.getTazaDeCambio();
    }

    @Override
    public void muestraConversionesValidas() {
        String apiKey = "CG-wjCavp3eYrsXuoWG2N4zAtsb";
        URI direccion = URI.create("https://api.coingecko.com/api/v3/exchange_rates?x_cg_demo_api_key=" + apiKey);
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
            RespuestaCoingeckoMultiples datosCoingecko =  new Gson().fromJson(response.body(), RespuestaCoingeckoMultiples.class);
            for (Map.Entry<String, MonedaCoingecko> entry: datosCoingecko.rates().entrySet()) {
                String codigo = entry.getKey();
                MonedaCoingecko moneda = entry.getValue();

                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + moneda.name());
                System.out.println("Unidad: " + moneda.unit());
                System.out.println("Valor: " + moneda.value());
                System.out.println("Tipo: " + moneda.type());
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException("Incapaz de obtener las monedas actualizadas");
        }
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
