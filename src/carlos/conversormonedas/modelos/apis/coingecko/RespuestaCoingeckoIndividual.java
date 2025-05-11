package carlos.conversormonedas.modelos.apis.coingecko;

import java.util.Map;

public record RespuestaCoingeckoIndividual(Map<String, Map <String, Double>> tazaDeCambio) {
    public String getMonedaOrigen() {
        return tazaDeCambio.keySet().iterator().next(); // Obtiene la monedaBase origen desde la respuesta
    }

    public String getMonedaObjetivo() {
        Map<String, Double> inner = tazaDeCambio.get(getMonedaOrigen());
        return inner.keySet().iterator().next(); // Se Obtiene la monedaBase objetivo desde la respuesta
    }
    public  Double getTazaDeCambio() {
        return tazaDeCambio.get(getMonedaOrigen()).get(getMonedaObjetivo());
    }
}
