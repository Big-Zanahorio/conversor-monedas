package carlos.conversormonedas.modelos.apis;

public interface ApisDeDivisas {
    boolean conversionValida(String monedaBase, String monedaObjetivo); // ¿Puede manejar este par?
    double obtenerTazaDeCambio(String monedaBase, String monedaObjetivo);
    void muestraConversionesValidas();
    String getNombre();
}
