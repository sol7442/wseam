package com.wowsanta.scim.provider.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.provider.model.WsScimOrg;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.ORG,
		type   = SCIM_JSON_TYPE.PARSER
		)
public class WsScimOrgJsonParser extends ScimResourceJsonParser {
	Logger logger = LoggerFactory.getLogger(WsScimOrgJsonParser.class);

	@Override
	public WsScimOrg fromJson(String json) throws ScimJsonException {
		WsScimOrg org = null;
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
	public WsScimOrg fromJson(JsonReader reader) throws ScimJsonException {
		WsScimOrg org = null; 
		try {
			
			org = new WsScimOrg();
			
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "id":
	      				org.setId(reader.nextString());
	      			break;
	      			case "name":
	      				org.setName(reader.nextString());
	      			break;
	      			case "parent":
	      				WsScimOrg parent = new WsScimOrg();
	      				parent.setId(reader.nextString());
	      				org.setParent(parent);
	      			break;
	      			case "meta":
	      				org.setMeta(WsScimMetaJsonParser.fromJson(reader));
	  				break;
	      		}
		      } 
		      reader.endObject(); 
		}catch (Exception e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return org;
	}
	

}
