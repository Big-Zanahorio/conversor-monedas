package carlos.conversormonedas.modelos;

public record Consulta(
        double valorMoneda,
        String monedaBase,
        String valorMonedaConvertida,
        String monedaConvertida,
        String fechaYHora) {
    @Override
    public String toString() {
        // Se formatea todo en una sola línea, con valores bien alineados y delimitados
        return String.format(" %.2f %s → %.2f %s | Fecha: %s ",
                valorMoneda, monedaBase,
                Double.parseDouble(valorMonedaConvertida), monedaConvertida,
                fechaYHora);
    }
}
