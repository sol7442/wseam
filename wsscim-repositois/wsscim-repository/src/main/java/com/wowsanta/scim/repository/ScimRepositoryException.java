package com.wowsanta.scim.repository;

public class ScimRepositoryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScimRepositoryException(String msg) {
		super(msg);
	}
	public ScimRepositoryException(String msg, Throwable e) {
		super(msg,e);
	}
}
