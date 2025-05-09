package carlos.conversormonedas.modelos;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorMonedas {
    List<Consulta> consultas = new ArrayList<>();
    Consultor consultor = new Consultor();

    public void mostrarConsultas() {
        System.out.println("Historial de consultas: ");
        for (Consulta consulta : consultas) {
            System.out.println(consulta);
        }
    }
    public  void convertir() {
        String moneda;
        String otraMoneda;
        Scanner teclado = new Scanner(System.in);
        double cantidadAConvertir;
        String cantidadConvertida;
        System.out.println("""
                Ingresa el codigo de la moneda a convertir.
                (Ejemplo MXN)
                """);
        moneda = teclado.nextLine();
        try {
            TazaDeCambio valoresActuales = consultor.obtenerValoresActuales(moneda);
            System.out.println("""
                Ingresa el codigo de la moneda a la que la quieres convertir.
                (Ejemplo USD)
                """);
            otraMoneda = teclado.nextLine();
            System.out.println("Ingresa la cantidad a convertir: ");
            cantidadAConvertir = teclado.nextDouble();
            cantidadConvertida = String.format("%.2f", valoresActuales.conversion_rates().get(otraMoneda) * cantidadAConvertir);
            System.out.println(cantidadAConvertir + " " + moneda + " equivale a " + cantidadConvertida + " " + otraMoneda);
            DateTimeFormatter formatoBonito = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy - HH:mm zzzz");
            Consulta consulta = new Consulta(
                    cantidadAConvertir,
                    moneda,
                    cantidadConvertida,
                    otraMoneda,
                    ZonedDateTime.now().format(formatoBonito).toString());
            consultas.add(consulta);
        } catch (Exception e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        int seleccion = 0;
        boolean salir = false;

        Scanner teclado = new Scanner(System.in);
        while (!salir) {
            System.out.println("""
                *********************************
                Bienvenido al Conversor de Moneda
                *********************************
                
                1) Convertir moneda
                2) Mostrar consultas
                3) Claves de las monedas
                9) Salir
                
                Escoja una opcion valida:
                """);
            seleccion = teclado.nextInt();
            switch (seleccion) {
                case 1:
                    this.convertir();
                    break;
                case 2:
                    this.mostrarConsultas();
                    break;
                case 3:
                    this.mostrarClaves();
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    private void mostrarClaves() {
        CodigosDeMonedaActuales monedas = consultor.obtenerMonedasActuales();
        System.out.println("Monedas: " + monedas);
        for (String moneda : monedas.supported_codes().keySet()) {
            System.out.println(monedas.supported_codes().get(moneda) + ": " + moneda);
        }
    }
}
