package com.wowsanta.scim.json;

import java.io.StringReader;

import com.google.gson.stream.JsonReader;

public abstract class ScimMessageJsonParser {
	
	protected ScimResourceJsonParser resourceParser;
	
	protected JsonReader newJsonReader(String json) {
		return new JsonReader(new StringReader(json));		
	}
	public void setResourceParser(ScimResourceJsonParser parser) {
		this.resourceParser = parser;
	}
	public abstract Object fromJson(String json) throws ScimJsonException;
	public abstract Object fromJson(JsonReader reader) throws ScimJsonException;
}
