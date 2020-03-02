package com.wowsanta.scim.control;

public class ScimControlKey {
	public String path   = "";
	public String method = "";
	
	public String getKey() {
		return path + method;
	}
	public String toString() {
		return path + ":"+ method;
	}
}
