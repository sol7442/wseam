package com.wowsanta.scim.control;


import spark.Request;
import spark.Response;
import spark.Route;

public class ScimDefaultControl_Search extends ScimDefaultControl_Abstract implements Route {
	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		return ScimDefaultControl_Search.class.getName();
	}
}
