package carlos.conversormonedas.modelos.apis.coingecko;

import java.util.Map;

public record RespuestaCoingeckoMultiples(Map<String, MonedaCoingecko> rates) {
}
