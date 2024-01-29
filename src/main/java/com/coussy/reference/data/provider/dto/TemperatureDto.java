package com.coussy.reference.data.provider.dto;

public class TemperatureDto {

    String town;

    double temperature;

    public TemperatureDto() {}

    public TemperatureDto(String town, double temperature) {
        this.town = town;
        this.temperature = temperature;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "town='" + town + '\'' +
                ", temperature=" + temperature +
                '}';
    }

}
