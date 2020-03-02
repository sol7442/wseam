package com.wowsanta.scim.json;

import java.io.Writer;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.entity.ScimResource;


public abstract class ScimResourceJsonBinder {
	private boolean pretty = true;
	public void setPretty(boolean pretty) {
		this.pretty = pretty;
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
	public abstract String toJson(ScimResource resouce) throws ScimJsonException;
	public abstract void toJson(JsonWriter writer, ScimResource resouce) throws ScimJsonException;
}
