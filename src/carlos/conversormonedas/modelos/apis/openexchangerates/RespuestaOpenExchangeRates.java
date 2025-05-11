package carlos.conversormonedas.modelos.apis.openexchangerates;

import java.util.Map;

public record RespuestaOpenExchangeRates(String disclaimer,
                                         String license,
                                         String timestamp,
                                         String base,
                                         Map<String, Double> rates) {
}
