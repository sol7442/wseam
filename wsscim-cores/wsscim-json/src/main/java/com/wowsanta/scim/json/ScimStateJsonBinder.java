package com.wowsanta.scim.json;

import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.json.ScimJsonException;

public class ScimStateJsonBinder {
	static Logger logger = LoggerFactory.getLogger(ScimStateJsonBinder.class);

	public static String toJson(ScimState state) throws ScimJsonException {
		StringWriter string_writer = new StringWriter();
		
		try {
			JsonWriter writer = newWriter(string_writer);
			writer.beginObject();
	        
			writer.name("status").value(state.getStatus());
			if(state.getScimType() != null) {
				writer.name("scimType").value(state.getScimType());
			}
			if(state.getDetail() != null) {
				writer.name("detail").value(state.getDetail());
			}
	        
	       
	        writer.endObject();
	        
		}catch (Exception e) {
			logger.error("Binding Error : ",  e);
			throw new ScimJsonException("Bind Error : ",e) ;
		}
		
		return string_writer.toString();
	}
	
	private static JsonWriter newWriter(Writer writer) {
		JsonWriter json_writer = new JsonWriter(writer);
		json_writer.setHtmlSafe(true);
		json_writer.setIndent("  ");
		json_writer.setSerializeNulls(true);
		json_writer.setLenient(true);
		return json_writer;
	}

}
