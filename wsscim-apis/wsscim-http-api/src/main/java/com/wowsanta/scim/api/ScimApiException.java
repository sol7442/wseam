package com.wowsanta.scim.api;

public class ScimApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4161347804501788346L;


	public ScimApiException(String msg, Exception e) {
		super(msg,e);
	}


}
