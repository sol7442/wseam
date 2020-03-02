package com.wowsanta.scim.control;


import spark.Request;
import spark.Response;
import spark.Route;

public class ScimDefaultControl_Greate extends ScimDefaultControl_Abstract implements Route {
	@Override
	public Object handle(Request request, Response response) throws Exception {
		this.entityInfo.getEntity();
		this.entityInfo.getJson("localhost");
		this.entityInfo.getService();
		return ScimDefaultControl_Greate.class.getName();
	}
}
