package com.skyhookwireless.myskyhook.enums;

public enum ProjectType {
	ACC("Context Accelerator"), 
	CRT("Certified for Web"), 
	HYP("Hyperlocal IP"), 
	PRE("Precision Location");

    private final String projectName;       

    private ProjectType(String s) {
        projectName = s;
    }

    public String toString(){
       return projectName;
    }

}