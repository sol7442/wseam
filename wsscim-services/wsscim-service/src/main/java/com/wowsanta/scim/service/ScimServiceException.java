package com.wowsanta.scim.service;


public class ScimServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6458748308847553985L;
	
	public ScimServiceException(String msg) {
		super(msg);
	}

	public ScimServiceException(String msg, Throwable e) {
		super(msg,e);
	}

}
