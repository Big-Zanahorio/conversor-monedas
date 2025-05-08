package carlos.conversormonedas.principal;

import carlos.conversormonedas.modelos.Consultor;
import carlos.conversormonedas.modelos.ConversorMonedas;

public class Principal {
    public static void main(String[] args) {
        ConversorMonedas conversor = new ConversorMonedas();
        conversor.mostrarMenu();
    }
}
