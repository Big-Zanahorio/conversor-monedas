package carlos.conversormonedas.modelos;

public interface ApisDeDivisas {
    boolean conversionValida(String base, String target); // ¿Puede manejar este par?
    double obtenerTazaDeCambio();
}
