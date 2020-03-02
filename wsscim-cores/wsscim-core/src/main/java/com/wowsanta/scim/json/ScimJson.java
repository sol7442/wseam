package com.wowsanta.scim.json;

public interface ScimJson {
	public void fromJson(String json) throws ScimJsonException;
	public String toJson(boolean pretty) throws ScimJsonException;
}
