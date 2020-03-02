package com.wowsanta.scim;

public class ScimException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8262185234345758114L;
	private ScimState state;
	public ScimException(String msg, Exception e) {
		super(msg,e);
	}

	public ScimException(String msg) {
		super(msg);
	}

	public ScimException(String msg, ScimState state) {
		super(msg);
		this.setState(state);
	}

	public ScimException(String msg, ScimState state, Exception e) {
		super(msg,e);
		this.setState(state);
	}

	public ScimState getState() {
		return state;
	}

	public void setState(ScimState state) {
		this.state = state;
	}

}
