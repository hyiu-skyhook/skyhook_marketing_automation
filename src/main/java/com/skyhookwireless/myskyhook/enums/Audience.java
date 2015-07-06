package com.skyhookwireless.myskyhook.enums;

public enum Audience {
	ADTECH("AdTech"), 
	APP("Applications"), 
	DEVICE("Devices"),
	OTHER("Other");
	

    private final String audience;       

    private Audience(String s) {
	audience = s;
    }

    public String toString(){
       return audience;
    }

}