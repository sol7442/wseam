package com.wowsanta.scim.provider.json;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.json.ScimResourceJsonBinder;
import com.wowsanta.scim.provider.model.WsScimPos;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.POS,
		type   = SCIM_JSON_TYPE.BINDER
		)
public class WsScimPosJsonBinder extends ScimResourceJsonBinder {
	Logger logger = LoggerFactory.getLogger(WsScimPosJsonBinder.class);

	
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
			WsScimPos pos = null;
			if (resource instanceof WsScimPos) {
				pos = (WsScimPos) resource;
			}
			
			writer.beginObject();
	        writer.name("id").value(pos.getId());
	        writer.name("name").value(pos.getName());
	        if(pos.getMeta() != null) {
	        	WsScimMetaJsonBinder.toJson(writer,pos.getMeta());
	        }
	        
	        writer.endObject();
	        
		}catch (Exception e) {
			logger.error("{} : ",e.getMessage(), e);
			throw new ScimJsonException("Bind Error : ",e) ;
		}
	}

}
