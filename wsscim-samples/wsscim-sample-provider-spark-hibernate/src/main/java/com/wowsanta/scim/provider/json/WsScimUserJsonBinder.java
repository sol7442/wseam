package com.wowsanta.scim.provider.json;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.json.ScimResourceJsonBinder;
import com.wowsanta.scim.provider.model.WsScimUser;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.USER,
		type   = SCIM_JSON_TYPE.BINDER
		)
public class WsScimUserJsonBinder extends ScimResourceJsonBinder {
	Logger logger = LoggerFactory.getLogger(WsScimUserJsonBinder.class);

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
			WsScimUser user = null;
			if (resource instanceof WsScimUser) {
				user = (WsScimUser) resource;
			}
			writer.beginObject();
	        writer.name("id").value(user.getId());
	        writer.name("name").value(user.getName());
	        if(user.getMeta() != null) {
	        	WsScimMetaJsonBinder.toJson(writer,user.getMeta());
	        }
	        
	        if(user.getOrg() != null) {
	        	writer.name("org").value(user.getOrg().getId());
	        }
	        
	        writer.endObject();
	        
		}catch (Exception e) {
			logger.error("{} : ",e.getMessage(), e);
			throw new ScimJsonException("Bind Error : ",e) ;
		}
	}

}
