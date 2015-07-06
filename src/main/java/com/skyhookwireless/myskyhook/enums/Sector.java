package com.skyhookwireless.myskyhook.enums;

public enum Sector {
	AD_SSP("SSP"), 
	AD_DSP("DSP"), 
	AD_DMP("DMP"),
	AD_NETWORK("Network"),
	AD_AGENCY("Agency"), 
	AD_ADVERTISER("Advertiser"), 
	AD_OTHER("Other AdTech"),
	
	APP_RETAIL("Retail"),
	APP_ETAIL("eTail"), 
	APP_FOOD("Food & Drink"), 
	APP_TRAVEL("Travel & Hospitality"),
	APP_LIFESTYLE("Lifestyle & Productivity"), 
	APP_ENTERTAINMENT("Entertainment"), 
	APP_GAMES("Games"),
	APP_PAYMENTS("Payment"), 
	APP_SOCIAL("Social"), 
	APP_HEALTH("Health & Fitness"), 
	APP_BRAND("Brand"),
	APP_DEVELOPMENT("Development Firm"),  
	APP_INSIGHTS("Insights"),
	APP_ENTERPRISE("Enterprise / SaaS"),
	APP_OTHER("Other Application"),
	
	DEVICE_MOBILE("Mobile Phones & Tablets"),
	DEVICE_WEARABLES("Wearables"),
	DEVICE_IOT("Internet of Things"),
	DEVICE_MPERS("mPERS"),
	DEVICE_EMONITORING("Electronic Monitoring"),
	DEVICE_ASSET("Asset Tracking"),
	DEVICE_LWS("Lone Worker Safety"),
	DEVICE_OTHER("Other Device");
	
	

    private final String sector;       

    private Sector(String s) {
	sector = s;
    }

    public String toString(){
       return sector;
    }

}