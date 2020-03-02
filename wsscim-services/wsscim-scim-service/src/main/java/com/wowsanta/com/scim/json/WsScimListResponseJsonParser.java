package com.wowsanta.com.scim.json;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.com.scim.message.WsScimListResponse;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimMessageJsonParser;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_MESSAGE_TYPE;

@SCIM_JSON(
		entity = SCIM_MESSAGE_TYPE.LIST_RESPONSE,
		type   = SCIM_JSON_TYPE.PARSER
		)
public class WsScimListResponseJsonParser extends ScimMessageJsonParser{
	Logger logger = LoggerFactory.getLogger(WsScimListResponseJsonParser.class);

	
	@Override
	public ScimListResponse fromJson(String json) throws ScimJsonException {
		
		ScimListResponse response = null; 
		
		try {
			JsonReader reader = newJsonReader(json);
			response = fromJson(reader);
			reader.close();
			
		}catch (Exception e) {
			logger.error("{} : ",json,e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return response;
	}

	@Override
	public ScimListResponse fromJson(JsonReader reader) throws ScimJsonException {
		ScimListResponse response = null; 
		try {
			response = new WsScimListResponse();
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "startIndex":
	      				response.setStartIndex(reader.nextInt());
	      			break;
	      			case "totalResults":
	      				response.setTotalResults(reader.nextInt());
	      			break;
	      			case "itemsPerPage":
	      				response.setItemsPerPage(reader.nextInt());
	      			break;
	      			case "resources":
	      				reader.beginArray();
	      				while (reader.hasNext()) {
	      					response.addResource((ScimResource) this.resourceParser.fromJson(reader));
	      				}
	      				reader.endArray();
	      			break;
	      		}
		      } 
		      reader.endObject(); 
		      reader.close();
		}catch (Exception e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return response;
	}
	

}
