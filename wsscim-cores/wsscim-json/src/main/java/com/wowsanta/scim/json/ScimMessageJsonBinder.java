package com.wowsanta.scim.json;

import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.json.ScimJsonException;

public abstract class ScimMessageJsonBinder {
	static Logger logger = LoggerFactory.getLogger(ScimMessageJsonBinder.class);

	protected ScimResourceJsonBinder resourceBinder;
	
	private boolean pretty = true;
	public void setPretty(boolean pretty) {
		this.pretty = pretty;
	}
	
	public void setResourceBinder(ScimResourceJsonBinder resourceBinder) {
		this.resourceBinder = resourceBinder;
	}
	
	protected JsonWriter newWriter(Writer writer) {
		JsonWriter json_writer = new JsonWriter(writer);
		json_writer.setHtmlSafe(true);
		if(this.pretty) {
			json_writer.setIndent("  ");
		}
		json_writer.setSerializeNulls(true);
		json_writer.setLenient(true);
		return json_writer;
	}
	public abstract String toJson(Object obj) throws ScimJsonException;
	public abstract void toJson(JsonWriter writer, Object obj) throws ScimJsonException;;

}
