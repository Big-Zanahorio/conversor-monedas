package carlos.conversormonedas.modelos;

public interface CambiaAPI {
    boolean soporta(String base, String target); // ¿Puede manejar este par?
    double obtenerTazaDeCambio(String base, String target) throws Exception;
}
