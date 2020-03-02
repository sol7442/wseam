package com.wowsanta.com.scim.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.com.scim.message.WsScimSearchRequest;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.entity.ScimSearchRequest;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimMessageJsonParser;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_MESSAGE_TYPE;

@SCIM_JSON(
		entity = SCIM_MESSAGE_TYPE.SEARCH_REQUEST,
		type   = SCIM_JSON_TYPE.PARSER
		)
public class WsScimSearchReqeustJsonParser extends ScimMessageJsonParser {
	Logger logger = LoggerFactory.getLogger(WsScimSearchReqeustJsonParser.class);

	@Override
	public ScimSearchRequest fromJson(String json) throws ScimJsonException {
		ScimSearchRequest request = null; 
		
		try {
			JsonReader reader = newJsonReader(json);
			request = fromJson(reader);
			reader.close();
		} catch (IOException e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return request;
		
	}

	@Override
	public ScimSearchRequest fromJson(JsonReader reader) throws ScimJsonException {
		ScimSearchRequest request = null; 
		try {
			
			request = new WsScimSearchRequest();
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "startIndex":
	      				request.setStartIndex(reader.nextInt());
	      			break;
	      			case "count":
	      				request.setCount(reader.nextInt());
	      			break;
	      			case "filter":
	      				request.setFilter(reader.nextString());
	      			break;
	      		}
		      } 
		      reader.endObject(); 
		}catch (Exception e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return request;
	}
	

}
