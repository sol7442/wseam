package com.wowsanta.scim.service;

public interface ScimServiceType {
	public static final String SCIM_PROVIDER   = "SCIM_PROVIDER";
	public static final String SCIM_RESOURCE   = "SCIM_RESOURCE";
	public static final String SCIM_CONSUMER   = "SCIM_CONSUMER";
	
	public static final String COMMON_SERVICE 			= "COMMON_SERVICE";
	public static final String SCIM_PROVIDER_SERVICE	= "SCIM_PROVIDER_SERVICE";
	
	public static final String SCIM_ORG  = "SCIM_ORG";
	public static final String SCIM_USER = "SCIM_USER";
	

	
	public static final String CREATE 	= "ceate";
	public static final String READ 	= "read";
	public static final String UPDATE 	= "update";
	public static final String DELETE	= "delete";
	public static final String SEARCH 	= "search";
}
