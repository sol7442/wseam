package com.wowsanta.com.scim.json;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimSearchRequest;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimMessageJsonBinder;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_MESSAGE_TYPE;

@SCIM_JSON(
		entity = SCIM_MESSAGE_TYPE.SEARCH_REQUEST,
		type   = SCIM_JSON_TYPE.BINDER
		)
public class WsScimSearchReqeustJsonBinder extends ScimMessageJsonBinder{
	Logger logger = LoggerFactory.getLogger(WsScimSearchReqeustJsonBinder.class);

	@Override
	public String toJson(Object request) throws ScimJsonException {
		StringWriter string_writer = new StringWriter();
		if (request instanceof ScimSearchRequest) {
			ScimSearchRequest search_request = (ScimSearchRequest) request;
			
			JsonWriter writer = newWriter(string_writer);
			toJson(writer,search_request);
		}
		
		
		return string_writer.toString();
	}

	@Override
	public void toJson(JsonWriter writer, Object request) throws ScimJsonException {
		try {
			ScimSearchRequest search_request = null;
			if (request instanceof ScimSearchRequest) {
				search_request = (ScimSearchRequest) request;
			}
			
			writer.beginObject();
			
			writer.name("startIndex").value(search_request.getStartIndex());
	        writer.name("count").value(search_request.getCount());
	        
	        if(search_request.getFilter() != null) {
	        	writer.name("filter").value(search_request.getFilter());
	        }
	        
	        writer.endObject();
	        
		}catch (Exception e) {
			logger.error("{} : ",e.getMessage(), e);
			throw new ScimJsonException("Bind Error : ",e) ;
		}
		
	}

}
