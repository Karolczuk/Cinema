package com.app.cinema.model;

public enum CardType {

    CardType_FAMILY("CardType_FAMILY"), CardType_LOYALTY("CardType_LOYALTY");

    private String fullName;

    CardType(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }



}
