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
//    public String toString() {
//
//        System.out.println();
//        return this.valorMoneda() + " " + this.monedaBase()
//                + " equivale a " + this.valorMonedaConvertida() + " " + this.monedaConvertida()
//                + ", fecha de consulta: " + this.fechaYHora;
//    }

    @Override
    public double valorMoneda() {
        return valorMoneda;
    }

    public String monedaBase() {
        return monedaBase;
    }

    @Override
    public String valorMonedaConvertida() {
        return valorMonedaConvertida;
    }

    @Override
    public String monedaConvertida() {
        return monedaConvertida;
    }

    @Override
    public String fechaYHora() {
        return fechaYHora;
    }
}
