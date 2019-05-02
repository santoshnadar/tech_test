package com.test.basic;

public enum Currency {
    SGP("Monday", "Friday"), AED("Sunday", "Thursday"), SAR("Sunday", "Thursday");
    private String startDay;
    private String endDay;

    private Currency(String startDay, String endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getEndDay() {
        return endDay;
    }

}
