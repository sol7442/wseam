package com.wowsanta.scim.provider.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.provider.model.WsScimJob;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;

@SCIM_JSON(
		entity = SCIM_ENTITY_NAME.JOB,
		type   = SCIM_JSON_TYPE.PARSER
		)
public class WsScimJobJsonParser extends ScimResourceJsonParser {
	Logger logger = LoggerFactory.getLogger(WsScimJobJsonParser.class);

	@Override
	public WsScimJob fromJson(String json) throws ScimJsonException {
		WsScimJob job = null;
		try {
			JsonReader reader = newJsonReader(json);
			job = fromJson(reader);
			reader.close();
		} catch (IOException e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		
		return job;
	}

	@Override
	public WsScimJob fromJson(JsonReader reader) throws ScimJsonException {
		WsScimJob job = null; 
		try {
			job = new WsScimJob();
			
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "id":
	      				job.setId(reader.nextString());
	      			break;
	      			case "name":
	      				job.setName(reader.nextString());
	      			break;
	      			case "meta":
	      				job.setMeta(WsScimMetaJsonParser.fromJson(reader));
	  				break;
	      		}
		      } 
		      reader.endObject(); 
		}catch (Exception e) {
			logger.error("{} : ",e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		return job;
	}
	

}
