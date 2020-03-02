package com.wowsanta.com.scim.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimStateJsonBinder;
import com.wowsanta.scim.repository.ScimRepository;
import com.wowsanta.scim.repository.ScimRepositoryFactory;
import com.wowsanta.scim.repository.ScimRepositoryManager;
import com.wowsanta.scim.repository.ScimSession;

import spark.Request;
import spark.Response;
import spark.Route;

public class WsScimEntityDeleteService implements Route {
	Logger logger = LoggerFactory.getLogger(WsScimEntityDeleteService.class);
	
	final String entity_type;
	
	//private ScimJsonInfo json_info;
	private ScimRepositoryFactory repository_factory ;
	
	public WsScimEntityDeleteService(String type,ScimEntityHandler service_info) {
		this.entity_type = type;
		
		//json_info = service_info.getJson(ScimServiceManager.getInstance().getServiceType());
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
			
			ScimResource old_resource = repository.get(id);
			if(old_resource != null) {
				throw new ScimException("Not Found", ScimState.NotFound);
			}
			repository.delete(old_resource);
			
			state = ScimState.Ok;
			result = ScimStateJsonBinder.toJson(state);
			
			session.commit();
		}catch (ScimException e) {
			session.rollback();
			state = e.getState();
			result = ScimStateJsonBinder.toJson(state);
			
		}catch (Exception e) {
			session.rollback();
			state = ScimState.InternalServerError;
			result = ScimStateJsonBinder.toJson(state);
		}
		finally {
			session.close();
			logger.debug("{} ({})=> {}",id,state,result);
			response.status(state.getStatus());
		}
		
		return result;
	}

}
