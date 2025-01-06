package com.challengeONEConversorMonedas.principal;

import com.challengeONEConversorMonedas.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        double AMOUNT = 3.5;
//        System.out.println("Ingrese la moneda que desea convertir");
//
//        ConsumoAPI consumoAPI = new ConsumoAPI();
//        var json = consumoAPI.obtenerDatos("https://v6.exchangerate-api.com/v6/" + "110fa16039664e81c44f0e66/latest/USD");
//        //System.out.println(json);
        Menu menu = new Menu();
        menu.muestraElMenu();


    }
}
