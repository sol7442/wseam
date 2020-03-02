package com.wowsanta.scim.provider.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.provider.model.WsScimOrg;
import com.wowsanta.scim.provider.model.WsScimUser;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.USER,
		type   = SCIM_JSON_TYPE.PARSER
		)
public class WsScimUserJsonParser extends ScimResourceJsonParser {
	Logger logger = LoggerFactory.getLogger(WsScimUserJsonParser.class);

	@Override
	public WsScimUser fromJson(String json) throws ScimJsonException {
		WsScimUser org = null;
		try {
			JsonReader reader = newJsonReader(json);
			org = fromJson(reader);
			reader.close();
		} catch (IOException e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		
		return org;
	}

	@Override
	public WsScimUser fromJson(JsonReader reader) throws ScimJsonException {
		WsScimUser user = null; 
		try {
			
			user = new WsScimUser();
			
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "id":
	      				user.setId(reader.nextString());
	      			break;
	      			case "name":
	      				user.setName(reader.nextString());
	      			break;
	      			case "meta":
	      				user.setMeta(WsScimMetaJsonParser.fromJson(reader));
	  				break;
	      			case "org":
	      				WsScimOrg org = new WsScimOrg();
	      				org.setId(reader.nextString());
	      				user.setOrg(org);
      				break;
	      		}
		      } 
		      reader.endObject(); 
		}catch (Exception e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return user;
	}
	

}
