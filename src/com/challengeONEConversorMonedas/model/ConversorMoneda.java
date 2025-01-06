package com.challengeONEConversorMonedas.model;

public class ConversorMoneda {
    private String base_code;
    private String target_code;
    private double conversion_result;

    public ConversorMoneda(String base_code, String target_code, double conversion_result) {
        this.base_code = base_code;
        this.target_code = target_code;
        this.conversion_result = conversion_result;
    }
    public ConversorMoneda(Monedas monedas){
        this.base_code = monedas.base_code();
        this.target_code = monedas.target_code();
        this.conversion_result = monedas.conversion_result();
    }

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(double conversion_result) {
        this.conversion_result = conversion_result;
    }

    @Override
    public String toString() {
        return "La converción de: " +
                "base_code='" + base_code + '\'' +
                ", target_code='" + target_code + '\'' +
                ", conversion_result=" + conversion_result;
    }
}
