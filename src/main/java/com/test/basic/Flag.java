package com.test.basic;

public enum Flag {
    B("Buy"), S("Sell");

    private String type;

    private Flag(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
