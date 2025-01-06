package com.challengeONEConversorMonedas.model;

public record Monedas(
        String base_code,
        String target_code,
        Double conversion_result) {
}
