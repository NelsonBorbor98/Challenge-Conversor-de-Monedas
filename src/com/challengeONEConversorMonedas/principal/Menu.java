package com.challengeONEConversorMonedas.principal;

import com.challengeONEConversorMonedas.model.ConversorMoneda;
import com.challengeONEConversorMonedas.model.Monedas;
import com.challengeONEConversorMonedas.service.ConsumoAPI;
import com.google.gson.Gson;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Gson gson = new Gson();

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private final String API_KEY = System.getenv("KEY_EXCHANGE_RATE");
    private String convercionPares = " "; ///pair/USD/EUR/
    private double AMOUNT;
    int opcion = -1;

    public void muestraElMenu() {
        //var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ***************************************************************
                    Sea bienvenido/a al Conversor de Moneda =]
                    1 - Dólar =>> Peso argentino
                    2 - Peso argentino =>> Dólar
                    3 - Dólar =>> Real brasileño
                    4 - Real brasileño =>>> Dólar
                    5 - Dólar =>> Peso colombiano
                    6 - Peso colombiano =>> Dólar
              
                    0 - Salir
                    
                    Elija una opción válida:
                    *****************************************************************
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 0) {
                System.out.println("Cerrando la aplicación...");
                break;
            }

            convercionPares = obtenerConversionPares(opcion);

            System.out.println("Ingrese el valor que desea convertir: ");
            AMOUNT = scanner.nextDouble();

            switch (opcion) {
                case 1:
                    convertirPares();
                    break;
                case 2:
                    convertirPares();
                    break;
                case 3:
                    convertirPares();
                    break;
                case 4:
                    convertirPares();
                    break;
                case 5:
                    convertirPares();
                    break;
                case 6:
                    convertirPares();
                    break;
                case 8:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void convertirPares() {
        //convercionPares = obtenerConversionPares(opcion);
        var jsonValor = consumoAPI.obtenerDatos(URL_BASE + API_KEY + convercionPares + AMOUNT);
        //System.out.println(jsonValor);
        ConversorMoneda monedas = gson.fromJson(jsonValor, ConversorMoneda.class);
        System.out.println("El valor de " + AMOUNT + monedas.getBase_code() +
                " corresponde al valor final de ==> " + monedas.getConversion_result() + monedas.getTarget_code());
    }

    private String obtenerConversionPares(int opcion) {
        String[] conversiones = {
                "/pair/USD/ARS/", // opción 1
                "/pair/ARS/USD/", // opción 2
                "/pair/USD/BRL/", // opción 3
                "/pair/BRL/USD/", // opción 4
                "/pair/USD/COP/", // opción 5
                "/pair/COP/USD/"  // opción 6
        };

        if (opcion < 1) {
            throw new IllegalArgumentException("Opción inválida");
        } else if (opcion > conversiones.length) {
            return " ";
        }

        return conversiones[opcion - 1];
    }
/*
    private void convercionMonedas(){
        String monedaBase;
        String monedaConvertir;
        var opcionesMoneda = """
                Elije la moneda base y la moneda de los siguientes codigos:
                ********************************************************************************************************************
                AED (United Arab Emirates) - AFN (Afghanistan) - ALL (Albania) - AMD (Armenia) - ANG (Netherlands Antilles) - 
                AOA (Angola) - ARS (Argentina) - AUD (Australia) - AWG (Aruba) - AZN (Azerbaijan) - BAM (Bosnia and Herzegovina) - 
                BBD (Barbados) - BDT (Bangladesh) - BGN (Bulgaria) - BHD (Bahrain) - BIF (Burundi) - BMD (Bermuda) - BND (Brunei) - 
                BOB (Bolivia) - BRL (Brazil) - BSD (Bahamas) - BTN (Bhutan) - BWP (Botswana) - BYN (Belarus) - BZD (Belize) - 
                CAD (Canada) - CDF (Democratic Republic of the Congo) - CHF (Switzerland) - CLP (Chile) - CNY (China) - 
                COP (Colombia) - CRC (Costa Rica) - CUP (Cuba) - CVE (Cape Verde) - CZK (Czech Republic) - DJF (Djibouti) - 
                DKK (Denmark) - DOP (Dominican Republic) - DZD (Algeria) - EGP (Egypt) - ERN (Eritrea) - ETB (Ethiopia) - 
                EUR (European Union) - FJD (Fiji) - FKP (Falkland Islands) - FOK (Faroe Islands) - GBP (United Kingdom) - 
                GEL (Georgia) - GGP (Guernsey) - GHS (Ghana) - GIP (Gibraltar) - GMD (The Gambia) - GNF (Guinea) - GTQ (Guatemala) - 
                GYD (Guyana) - HKD (Hong Kong) - HNL (Honduras) - HRK (Croatia) - HTG (Haiti) - HUF (Hungary) - IDR (Indonesia) - 
                ILS (Israel) - IMP (Isle of Man) - INR (India) - IQD (Iraq) - IRR (Iran) - ISK (Iceland) - JEP (Jersey) - 
                JMD (Jamaica) - JOD (Jordan) - JPY (Japan) - KES (Kenya) - KGS (Kyrgyzstan) - KHR (Cambodia) - KID (Kiribati) - 
                KMF (Comoros) - KRW (South Korea) - KWD (Kuwait) - KYD (Cayman Islands) - KZT (Kazakhstan) - LAK (Laos) - 
                LBP (Lebanon) - LKR (Sri Lanka) - LRD (Liberia) - LSL (Lesotho) - LYD (Libya) - MAD (Morocco) - MDL (Moldova) - 
                MGA (Madagascar) - MKD (North Macedonia) - MMK (Myanmar) - MNT (Mongolia) - MOP (Macau) - MRU (Mauritania) - 
                MUR (Mauritius) - MVR (Maldives) - MWK (Malawi) - MXN (Mexico) - MYR (Malaysia) - MZN (Mozambique) - NAD (Namibia) - 
                NGN (Nigeria) - NIO (Nicaragua) - NOK (Norway) - NPR (Nepal) - NZD (New Zealand) - OMR (Oman) - PAB (Panama) - 
                PEN (Peru) - PGK (Papua New Guinea) - PHP (Philippines) - PKR (Pakistan) - PLN (Poland) - PYG (Paraguay) - QAR (Qatar) - 
                RON (Romania) - RSD (Serbia) - RUB (Russia) - RWF (Rwanda) - SAR (Saudi Arabia) - SBD (Solomon Islands) - SCR (Seychelles) - 
                SDG (Sudan) - SEK (Sweden) - SGD (Singapore) - SHP (Saint Helena) - SLE (Sierra Leone) - SOS (Somalia) - SRD (Suriname) - 
                SSP (South Sudan) - STN (São Tomé and Príncipe) - SYP (Syria) - SZL (Eswatini) - THB (Thailand) - TJS (Tajikistan) - 
                TMT (Turkmenistan) - TND (Tunisia) - TOP (Tonga) - TRY (Turkey) - TTD (Trinidad and Tobago) - TVD (Tuvalu) - TWD (Taiwan) - 
                TZS (Tanzania) - UAH (Ukraine) - UGX (Uganda) - USD (United States) - UYU (Uruguay) - UZS (Uzbekistan) - VES (Venezuela) - 
                VND (Vietnam) - VUV (Vanuatu) - WST (Samoa) - XAF (CEMAC) - XCD (Organisation of Eastern Caribbean States) - 
                XDR (International Monetary Fund) - XOF (CFA) - XPF (Collectivités d'Outre-Mer) - YER (Yemen) - ZAR (South Africa) - 
                ZMW (Zambia) - ZWL (Zimbabwe)
                *******************************************************************************************************************************
                """;
        System.out.println(opcionesMoneda);
        System.out.println("Moneda base: ");
        monedaBase = scanner.nextLine().toUpperCase();
        System.out.println("Moneda a convertir: ");
        monedaConvertir = scanner.nextLine().toUpperCase();
        scanner.nextLine();
        System.out.println("Ingrese valor a convertir: ");
        AMOUNT = scanner.nextDouble();

        var json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + "/pair/"+ monedaBase + "/" + monedaConvertir + "/" + AMOUNT);

        ConversorMoneda monedas = gson.fromJson(json, ConversorMoneda.class);
        System.out.println("El valor de " + AMOUNT + monedas.getBase_code() +
                " corresponde al valor final de ==> " + monedas.getConversion_result() + monedas.getTarget_code());
    }
*/
}
