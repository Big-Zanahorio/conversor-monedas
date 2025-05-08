package carlos.conversormonedas.modelos;

public record Consulta(
        double valorMoneda,
        String moneda,
        double valorMonedaConvertida,
        String monedaConvertida) {
}
