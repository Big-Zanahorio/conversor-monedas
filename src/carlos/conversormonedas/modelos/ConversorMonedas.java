package carlos.conversormonedas.modelos;

import carlos.conversormonedas.modelos.apis.ApisDeDivisas;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConversorMonedas {
    private List<ApisDeDivisas> apis;
    private List<Consulta> consultas = new ArrayList<>();

    public ConversorMonedas(List<ApisDeDivisas> apis) {
        this.apis = apis;
    }

    public void mostrarConsultas() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  Historial de Consultas                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        for (Consulta consulta : consultas) {
            System.out.println(consulta);
        }
    }
    public  void convertir() {
        String monedaBase;
        String monedaObjetivo;
        Scanner teclado = new Scanner(System.in);
        double cantidadBase;
        String cantidadObjetivo;

        try {
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("║        Conversión de Moneda       ║");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.println("🔤 Ingresa el código de la monedaBase a convertir");
            System.out.println("   Ejemplo: MXN");
            System.out.print("👉 Código: ");
            monedaBase = teclado.nextLine();

            System.out.println();
            System.out.println("🔁 Ingresa el código de la monedaBase objetivo");
            System.out.println("   Ejemplo: USD");
            System.out.print("👉 Código: ");
            monedaObjetivo = teclado.nextLine();

            System.out.println();
            System.out.println("💰 Ingresa la cantidad a convertir");
            System.out.print("👉 Cantidad: ");
            cantidadBase = teclado.nextDouble();
            for (ApisDeDivisas api : apis) {
                if (api.conversionValida(monedaBase, monedaObjetivo)) {
                    double tazaDeCambio = api.obtenerTazaDeCambio(monedaBase, monedaObjetivo);
                    cantidadObjetivo = String.format("%.2f", (cantidadBase * tazaDeCambio));
                    System.out.println("\n╔══════════════════════════════════════════════╗");
                    System.out.println("║              Resultado de Conversión         ║");
                    System.out.println("╚══════════════════════════════════════════════╝");
                    System.out.println("🔢 " + cantidadBase + " " + monedaBase +
                            " equivale a 💱 " + cantidadObjetivo + " " + monedaObjetivo);                  DateTimeFormatter formatoBonito = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy - HH:mm zzzz");
                    Consulta consulta = new Consulta(
                            cantidadBase,
                            monedaBase,
                            cantidadObjetivo,
                            monedaObjetivo,
                            ZonedDateTime.now().format(formatoBonito).toString());
                    consultas.add(consulta);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }

    public void mostrarMenu() {
        int seleccion = 0;
        boolean salir = false;

        Scanner teclado = new Scanner(System.in);
        try {
            while (!salir) {
                System.out.println("╔═════════════════════════════════════════════╗");
                System.out.println("║      Bienvenido al Conversor de Moneda      ║");
                System.out.println("╚═════════════════════════════════════════════╝");
                System.out.println();
                System.out.println("  1) 💱 Convertir monedaBase");
                System.out.println("  2) 📋 Mostrar consultas anteriores");
                System.out.println("  3) 🗝️ Ver claves de las monedas");
                System.out.println("  9) ❌ Salir");
                System.out.println();
                System.out.print("👉 Escoja una opción válida: ");
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
        } catch (InputMismatchException e) {
            System.out.println("Opcion invalida");
        } catch (Exception e) {
            System.out.println("Hubo un error");
        }
    }

    private void mostrarClaves() {
        for (ApisDeDivisas api : apis) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.printf("║ API: %-30s  ║%n", api.getNombre());
            System.out.println("╚══════════════════════════════════════╝");

            api.muestraConversionesValidas();

            System.out.println("────────────────────────────────────────");
        }
    }
}
