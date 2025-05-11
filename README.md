## 📖 Descripción General

Este proyecto es un conversor de divisas de uso sencillo que obtiene datos actualizados de diferentes APIs para ofrecer conversiones de moneda precisas. El usuario puede elegir entre varias funciones, como convertir divisas, revisar el historial de conversiones y consultar las monedas disponibles soportadas por las APIs.

---

## ✨ Características Principales

- 📋 Muestra un menú interactivo al usuario.
- ✅ Permite seleccionar diferentes opciones para operar.
- ⚠️ Muestra mensajes de error amigables ante entradas incorrectas.
- 🔄 Selecciona automáticamente la mejor API para realizar la conversión solicitada.
- 🕓 Guarda y muestra el historial de conversiones realizadas.
- 🌍 Lista todas las conversiones disponibles por cada API integrada.

---

## 🛠️ Tecnologías Utilizadas

- [Java](https://www.oracle.com/java/)
- [Gson](https://github.com/google/gson)
- [JSON](https://www.json.org/)
- Java HTTP Client (HttpRequest / HttpResponse)

---

## 🌐 APIs Utilizadas

El conversor de monedas integra múltiples APIs para ofrecer tasas de cambio actualizadas y confiables. A continuación se detallan las APIs disponibles:

- **[ExchangeRateAPI](https://app.exchangerate-api.com/)**  
  Proporciona tasas de cambio en tiempo real para una amplia variedad de monedas. Requiere una clave de API gratuita que puedes obtener registrándote en su sitio web.

- **[Open Exchange Rates](https://openexchangerates.org/)**  
  Ofrece datos fiables sobre tipos de cambio para más de 200 monedas. Requiere una cuenta gratuita o de pago para acceder a diferentes niveles de funcionalidad.

- **[CoinGecko API](https://www.coingecko.com/en/api)**  
  Ideal para obtener datos de criptomonedas. No requiere autenticación para acceder a la mayoría de sus endpoints, aunque aplica límites de uso por IP.

---

## ⚙️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener lo siguiente instalado en tu sistema:

- Java JDK 17 o superior
- Un IDE como IntelliJ IDEA, Eclipse o VS Code
- Conexión a Internet (para consultar las APIs)
- Maven (opcional, si decides administrar dependencias)

---

## 🚀 Instalación y Ejecución

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/conversor-monedas.git
