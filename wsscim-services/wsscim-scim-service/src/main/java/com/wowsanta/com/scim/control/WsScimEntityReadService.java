package com.wowsanta.com.scim.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonInfo;
import com.wowsanta.scim.json.ScimResourceJsonBinder;
import com.wowsanta.scim.json.ScimStateJsonBinder;
import com.wowsanta.scim.repository.ScimRepository;
import com.wowsanta.scim.repository.ScimRepositoryFactory;
import com.wowsanta.scim.repository.ScimRepositoryManager;
import com.wowsanta.scim.repository.ScimSession;
import com.wowsanta.scim.type.SCIM_TARGET_TYPE;

import spark.Request;
import spark.Response;
import spark.Route;

public class WsScimEntityReadService implements Route {
	Logger logger = LoggerFactory.getLogger(WsScimEntityReadService.class);
	
	final String entity_type;
	
	private ScimJsonInfo json_info;
	private ScimRepositoryFactory repository_factory ;
	
	public WsScimEntityReadService(String type,ScimEntityHandler service_info) {
		this.entity_type = type;
		json_info = service_info.getJson(SCIM_TARGET_TYPE.LOCAL_TARGET);
		repository_factory = ScimRepositoryManager.getInstance().getRepositoryFactory();
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		
		String id  		= "";
		String result 	= "";
		ScimState state = null;
		ScimSession session = null;
		
		try {
			id = request.params("id");
			
			session = repository_factory.openSession();
			ScimRepository repository = repository_factory.getRepository(entity_type, session);
			ScimResource resource = repository.get(id);
			if(resource == null) {
				throw new ScimException("Not Found", ScimState.NotFound);
			}
			
			
			ScimResourceJsonBinder binder = (ScimResourceJsonBinder) json_info.getBinderClass().newInstance();
			result = binder.toJson(resource);
			
			state  = ScimState.Ok;
		}catch (ScimException e) {
			logger.error("",e);
			state = e.getState();
			result = ScimStateJsonBinder.toJson(state);
		}catch (Exception e) {
			logger.error("",e);
			state = ScimState.InternalServerError;
			result = ScimStateJsonBinder.toJson(state);
		}
		
		finally {
			logger.debug("{} ({})=> {}",id,state,result);
			session.close();
			response.status(state.getStatus());
		}
		return result;
	}

}
