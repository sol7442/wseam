package com.wowsanta.scim.service;

public class ScimServiceUrl {
	public static final String SCIM_URL     = "/scim/v2/";
	public static final String SCIM_BULK     = "/scim/v2/Bulk";
	public static final String SCIM_USER 	= "/scim/v2/Users";
	public static final String SCIM_GROUP 	= "/scim/v2/Users";
	public static final String SCIM_ORG  	= "/scim/v2/Orgs";
	public static final String SCIM_POS 	= "/scim/v2/Poss";
	public static final String SCIM_JOB  	= "/scim/v2/Jobs";
	
	public static String getUrl(String key, String value) {
		String url =  SCIM_URL + key;
		if(value != null) {
			url +="/" + value;
		}
		return url;
	}
}
