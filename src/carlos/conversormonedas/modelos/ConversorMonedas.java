package carlos.conversormonedas.modelos;

import java.util.Scanner;

public class ConversorMonedas {
    public  void convertir(String moneda, String otraMoneda) {
        Consultor conversor = new Consultor();
        Moneda valoresActuales = conversor.obtenerValoresActuales(moneda);
        Scanner teclado = new Scanner(System.in);
        double cantidadAConvertir;
        double cantidadConvertida;
        System.out.println("Ingresa la cantidad a convertir: ");
        cantidadAConvertir = teclado.nextDouble();
        cantidadConvertida = valoresActuales.conversion_rates().get(otraMoneda) * cantidadAConvertir;
        System.out.println("Cantidad convertida: " + cantidadConvertida);
    }

    public void mostrarMenu() {
        int seleccion = 0;
        boolean salir = false;

        Scanner teclado = new Scanner(System.in);
        while (!salir) {
            System.out.println("""
                ****************************************************
                Bienvenido al Conversor de Moneda
                ****************************************************
                
                1) Dolar =>> Peso Mexicano
                2) Peso Mexicano =>> Dolar
                3) Dolar =>> Real brasileño
                4) Real brasileño =>> Dolar
                5) Dolar =>> Peso colombiano
                6) Peso colombiano =>> Dolar
                7) Salir
                
                Escoja una opcion valida:
                """);
            seleccion = teclado.nextInt();
            switch (seleccion) {
                case 1:
                    this.convertir("USD", "MXN");
                    break;
                case 2:
                    this.convertir("MXN", "USD");
                    break;
                case 3:
                    this.convertir("USD", "BRL");
                    break;
                case 4:
                    this.convertir("BRL", "USD");
                    break;
                case 5:
                    this.convertir("USD", "COP");
                    break;
                case 6:
                    this.convertir("COP", "USD");
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
}
