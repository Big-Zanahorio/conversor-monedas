package carlos.conversormonedas.modelos.apis.coingecko;

public record MonedaCoingecko(
        String name,
        String unit,
        double value,
        String type) {
}
