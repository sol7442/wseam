package com.wowsanta.com.scim.control;

import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.json.ScimStateJsonBinder;

import spark.Request;
import spark.Response;
import spark.Route;

public class WsScimEntityReplaceService implements Route {
	final String entity_type;
	public WsScimEntityReplaceService(String type,ScimEntityHandler service_info) {
		this.entity_type = type;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		return ScimStateJsonBinder.toJson(ScimState.NotImplemented);
	}

}
