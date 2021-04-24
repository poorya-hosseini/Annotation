package com.optiscan.entity;

public enum Gender {
    Male("M"),
    Female("F");

    String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
