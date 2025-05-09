package carlos.conversormonedas.modelos;

import java.util.Map;

public record CodigosDeMonedaActuales(String result,
                                      String documentation,
                                      String terms_of_use,
                                      Map<String, String> supported_codes){
}
