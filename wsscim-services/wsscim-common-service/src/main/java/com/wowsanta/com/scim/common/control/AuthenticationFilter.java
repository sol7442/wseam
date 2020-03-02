package com.wowsanta.com.scim.common.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.annotation.SCIM_ENTITY_CONTROL;
import com.wowsanta.scim.service.ScimServiceMethod;

import spark.Filter;
import spark.Request;
import spark.Response;

@SCIM_ENTITY_CONTROL(
		entity 	= "Authen", 
		path 	= "/*",
		method 	= ScimServiceMethod.BEFORE)
public class AuthenticationFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Override
	public void handle(Request request, Response response) throws Exception {
		logger.debug(AuthenticationFilter.class.getName());
		
	}

}