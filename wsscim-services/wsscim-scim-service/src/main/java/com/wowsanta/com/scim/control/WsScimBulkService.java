package com.wowsanta.com.scim.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.annotation.SCIM_ENTITY_CONTROL;
import com.wowsanta.scim.service.ScimServiceMethod;
import com.wowsanta.scim.service.ScimServiceUrl;

import spark.Request;
import spark.Response;
import spark.Route;

@SCIM_ENTITY_CONTROL(method = ScimServiceMethod.POST,path = ScimServiceUrl.SCIM_BULK)
public class WsScimBulkService implements Route {

	Logger logger = LoggerFactory.getLogger(WsScimBulkService.class);
	@Override
	public Object handle(Request request, Response response) throws Exception {
		logger.debug(WsScimBulkService.class.getName());
		
		return WsScimBulkService.class.getName();
	}

}
