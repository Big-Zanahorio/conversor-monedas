package carlos.conversormonedas.modelos;

public interface APIsDeDivisas {
    boolean conversionValida(String base, String target); // ¿Puede manejar este par?
    double obtenerTazaDeCambio() throws Exception;
}
