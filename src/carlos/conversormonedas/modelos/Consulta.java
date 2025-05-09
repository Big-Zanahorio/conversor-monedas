package carlos.conversormonedas.modelos;

public record Consulta(
        double valorMoneda,
        String moneda,
        String valorMonedaConvertida,
        String monedaConvertida,
        String fechaYHora) {
    @Override
    public String toString() {

        System.out.println();
        return this.valorMoneda() + " " + this.moneda()
                + " equivale a " + this.valorMonedaConvertida() + " " + this.monedaConvertida()
                + ", fecha de consulta: " + this.fechaYHora;
    }
}
