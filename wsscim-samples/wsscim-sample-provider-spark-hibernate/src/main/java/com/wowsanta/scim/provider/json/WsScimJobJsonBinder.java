package com.wowsanta.scim.provider.json;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonBinder;
import com.wowsanta.scim.provider.model.WsScimJob;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.JOB,
		type   = SCIM_JSON_TYPE.BINDER
		)
public class WsScimJobJsonBinder extends ScimResourceJsonBinder {
	Logger logger = LoggerFactory.getLogger(WsScimJobJsonBinder.class);

	
	@Override
	public String toJson(ScimResource resource) throws ScimJsonException {
		
		StringWriter string_writer = new StringWriter();
		JsonWriter writer = newWriter(string_writer);
		
		toJson(writer,resource);
		
		return string_writer.toString();
	}

	@Override
	public void toJson(JsonWriter writer, ScimResource resource) throws ScimJsonException {
		try {
			WsScimJob job = null;
			if (resource instanceof WsScimJob) {
				job = (WsScimJob) resource;
			}
			
			
			writer.beginObject();
	        writer.name("id").value(job.getId());
	        writer.name("name").value(job.getName());
	        if(job.getMeta() != null) {
	        	WsScimMetaJsonBinder.toJson(writer,job.getMeta());
	        }
	        
	        writer.endObject();
	        
		}catch (Exception e) {
			logger.error("{} : ",e.getMessage(), e);
			throw new ScimJsonException("Bind Error : ",e) ;
		}
	}

}
