package com.wowsanta.scim.provider.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.provider.model.WsScimPos;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.POS,
		type   = SCIM_JSON_TYPE.PARSER
		)
public class WsScimPosJsonParser extends ScimResourceJsonParser {
	Logger logger = LoggerFactory.getLogger(WsScimPosJsonParser.class);

	@Override
	public WsScimPos fromJson(String json) throws ScimJsonException {
		WsScimPos pos = null;
		try {
			JsonReader reader = newJsonReader(json);
			pos = fromJson(reader);
			reader.close();
		} catch (IOException e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		
		return pos;
	}

	@Override
	public WsScimPos fromJson(JsonReader reader) throws ScimJsonException {
		WsScimPos pos = null; 
		try {
			
			pos = new WsScimPos();
			
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "id":
	      				pos.setId(reader.nextString());
	      			break;
	      			case "name":
	      				pos.setName(reader.nextString());
	      			break;
	      			case "meta":
	      				pos.setMeta(WsScimMetaJsonParser.fromJson(reader));
	  				break;
	      		}
		      } 
		      reader.endObject(); 
		}catch (Exception e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return pos;
	}
	

}
