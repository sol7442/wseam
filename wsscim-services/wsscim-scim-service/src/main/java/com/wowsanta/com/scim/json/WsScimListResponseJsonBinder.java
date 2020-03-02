package com.wowsanta.com.scim.json;

import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimMessageJsonBinder;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_MESSAGE_TYPE;


@SCIM_JSON(
		entity = SCIM_MESSAGE_TYPE.LIST_RESPONSE,
		type   = SCIM_JSON_TYPE.BINDER
		)
public class WsScimListResponseJsonBinder extends ScimMessageJsonBinder {
	Logger logger = LoggerFactory.getLogger(WsScimListResponseJsonBinder.class);

	@Override
	public String toJson(Object response) throws ScimJsonException {
		
		StringWriter string_writer = new StringWriter();
		if (response instanceof ScimListResponse) {
			
			ScimListResponse list_response = (ScimListResponse) response;
			JsonWriter  writer = newWriter(string_writer);
			toJson(writer,list_response);
			
		}

		return string_writer.toString();
	}

	
	@Override
	public void toJson(JsonWriter writer,  Object response) throws ScimJsonException {
		try {
			ScimListResponse list_response = null;
			if (response instanceof ScimListResponse) {
				list_response = (ScimListResponse) response;
			}
			writer.beginObject();
			
	        writer.name("startIndex").value(list_response.getStartIndex());
	        writer.name("totalResults").value(list_response.getTotalResults());
	        writer.name("itemsPerPage").value(list_response.getItemsPerPage());
	        
	        writer.name("resources");
            writer.beginArray();
	        List<ScimResource> resource_list = list_response.getResources();
	        for (ScimResource scimResource : resource_list) {
	        	resourceBinder.toJson(writer,scimResource);
			}
	        writer.endArray();
	        writer.endObject();
	        
		}catch (Exception e) {
			logger.error("{} : ",e.getMessage(), e);
			throw new ScimJsonException("Bind Error : ",e) ;
		}
	}
}
