package carlos.conversormonedas.modelos.apis.exchangerate;

import java.util.Map;

public record MonedasExchangeRate(String result,
                                  String documentation,
                                  String terms_of_use,
                                  Map<String, String> supported_codes){
}
