package com.skyhookwireless.myskyhook.enums;

public enum Severity {
	LOW("Low or No Functional Impact"), 
	MODERATE("Moderate Operational or Functional Impact"), 
	SIGNIFICANT("Significant Impact");

    private final String severity;       

    private Severity(String s) {
	severity = s;
    }

    public String toString(){
       return severity;
    }
}