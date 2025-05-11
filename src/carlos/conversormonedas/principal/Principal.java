package carlos.conversormonedas.principal;

import carlos.conversormonedas.modelos.apis.coingecko.AdaptadorCoingecko;
import carlos.conversormonedas.modelos.apis.exchangerate.AdaptadorExchangeRate;
import carlos.conversormonedas.modelos.ConversorMonedas;
import carlos.conversormonedas.modelos.apis.openexchangerates.AdaptadorOpenExchangeRates;

import java.util.List;


public class Principal {
    public static void main(String[] args) {
        ConversorMonedas conversor = new ConversorMonedas(List.of(
                new AdaptadorExchangeRate(),
                new AdaptadorOpenExchangeRates(),
                new AdaptadorCoingecko()
        ));
        conversor.mostrarMenu();
    }
}
