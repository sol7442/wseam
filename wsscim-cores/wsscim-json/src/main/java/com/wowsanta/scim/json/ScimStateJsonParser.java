package com.wowsanta.scim.json;


import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonParser;

public class ScimStateJsonParser  {
	static Logger logger = LoggerFactory.getLogger(ScimStateJsonParser.class);

	public static ScimState fromJson(String json) throws ScimJsonException {
		
		int status		= ScimState.Ok.getStatus();
		String type 	= null;
		String detail 	= null;
		
		try {
			JsonReader reader =  new JsonReader(new StringReader(json));
			reader.beginObject(); 
	      	while (reader.hasNext()) { 
	      		switch (reader.nextName()) {
	      			case "status":
	      				status = reader.nextInt();
	      			break;
	      			case "scimType":
	      				type = reader.nextString();
	      			break;
	      			case "detail":
	      				detail = reader.nextString();
	      			break;
	      		}
		      } 
		      reader.endObject(); 
		      reader.close();
		}catch (Exception e) {
			logger.error("Parser Error : ",  e);
			throw new ScimJsonException("Parse Error : ",e) ;
		}
		
		return ScimState.getValue(status,type,detail);
	}
}
