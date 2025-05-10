package carlos.conversormonedas.modelos.apis.coingecko;

public record TazaDeCambioCoingecko(
        String monedaBase,
        String monedaObjetivo,
        double tazaDeCambio
) {
    public double getTazaDeCambio() {
        return this.tazaDeCambio;
    }
}
