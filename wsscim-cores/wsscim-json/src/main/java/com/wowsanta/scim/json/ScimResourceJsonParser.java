package com.wowsanta.scim.json;

import java.io.StringReader;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.entity.ScimResource;

public abstract class ScimResourceJsonParser  {
	protected JsonReader newJsonReader(String json) {
		return new JsonReader(new StringReader(json));		
	}
	public abstract ScimResource fromJson(String json) throws ScimJsonException;
	public abstract ScimResource fromJson(JsonReader reader) throws ScimJsonException;
}
