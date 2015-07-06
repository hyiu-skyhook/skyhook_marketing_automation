package com.skyhookwireless.myskyhook.enums;

public enum JobLevel { 
	ANALYST("Analyst"), 
	BOD("Board of Directors"),
	CONSULTANT("Consultant"),
	COORDINATOR("Coordinator / Assistant"),
	EXECUTIVE("Executive (CEO, CFO, COO)"),
	DIRECTOR("Director"),
	ENGINEER("Engineer"),
	MANAGER("Manager"),
	OTHER("Other"),
	PRESIDENT("President"),
	REPORTER("Reporter / Editor"),
	STUDENT("Student"),
	VP("Vice President");


    private final String jobLevel;       

    private JobLevel(String s) {
	jobLevel = s;
    }

    public String toString(){
       return jobLevel;
    }

}