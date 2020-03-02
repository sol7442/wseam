package com.wowsanta.com.scim.control;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonInfo;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.json.ScimStateJsonBinder;
import com.wowsanta.scim.repository.ScimRepository;
import com.wowsanta.scim.repository.ScimRepositoryFactory;
import com.wowsanta.scim.repository.ScimRepositoryManager;
import com.wowsanta.scim.repository.ScimSession;
import com.wowsanta.scim.service.ScimServiceManager;

import spark.Request;
import spark.Response;
import spark.Route;

public class WsScimEntityUpdateService implements Route {
	Logger logger = LoggerFactory.getLogger(WsScimEntityUpdateService.class);
	
	final String entity_type;
	private ScimJsonInfo json_info;
	private ScimRepositoryFactory repository_factory ;
	
	public WsScimEntityUpdateService(String type,ScimEntityHandler service_info) {
		this.entity_type = type;
		
		json_info = service_info.getJson(ScimServiceManager.getInstance().getServiceType());
		repository_factory = ScimRepositoryManager.getInstance().getRepositoryFactory();
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String json   = "";
		String result = "";
		ScimState state = null;
		
		ScimSession session = null;
		
		try {
			ScimResourceJsonParser parser = (ScimResourceJsonParser) json_info.getParserClass().newInstance();

			json  	= request.body();
			ScimResource new_resource = parser.fromJson(json);
			
			session = repository_factory.openSession();
			ScimRepository repository = repository_factory.getRepository(entity_type, session);
			ScimResource old_resource = repository.get(new_resource.getId());
			if(old_resource == null) {
				throw new ScimException("Not Found", ScimState.NotFound);
			}
					
			Date modify_date = new Date();
			new_resource.getMeta().setLastModified(modify_date);
			
			repository.update(new_resource);
			
			state = ScimState.Ok;
			result = ScimStateJsonBinder.toJson(state);
		
		}catch (ScimException e) {
			session.rollback();
			state = e.getState();
			result = ScimStateJsonBinder.toJson(state);
		}
		finally {
			session.close();
			logger.debug("{} ({})=> {}",json,state,result);
			response.status(state.getStatus());
		}
		
		return result;
	}

}
