package com.skyhookwireless.myskyhook.enums;

public enum JobFunction { 
	BD("Business Development"), 
	DESIGN("Design"),
	ENG("Engineering"),
	IT("Information Technology"),
	MKT("Marketing / Communications"),
	MKTINT("Marketing Research / Intelligence"),
	OTHER("Other"),
	PD("Product Development"),
	RAD("Research and Development"),
	SALES("Sales");


    private final String jobFunction;       

    private JobFunction(String s) {
	jobFunction = s;
    }

    public String toString(){
       return jobFunction;
    }

}