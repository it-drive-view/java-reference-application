package com.coussy.reference.statistic;

import java.math.BigDecimal;

public class StatisticDto {

    private String label;
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public StatisticDto(String label, BigDecimal value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return "StatisticDto{" +
                "label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
