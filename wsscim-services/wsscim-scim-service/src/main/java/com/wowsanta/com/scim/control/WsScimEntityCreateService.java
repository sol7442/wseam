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
import com.wowsanta.scim.type.SCIM_TARGET_TYPE;

import spark.Request;
import spark.Response;
import spark.Route;

public class WsScimEntityCreateService implements Route {
	Logger logger = LoggerFactory.getLogger(WsScimEntityCreateService.class);
	
	final String entity_type;
	private ScimJsonInfo json_info;
	private ScimRepositoryFactory repository_factory ;
	
	public WsScimEntityCreateService(String type,ScimEntityHandler service_info) {
		this.entity_type = type;
		
		json_info = service_info.getJson(SCIM_TARGET_TYPE.LOCAL_TARGET);
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
			if(old_resource != null) {
				throw new ScimException("Duplicate entry", ScimState.mutability);
			}
					
			Date ceate_date = new Date();
			new_resource.getMeta().setCreated(ceate_date);
			new_resource.getMeta().setLastModified(ceate_date);
			
			repository.create(new_resource);
			
			state = ScimState.Created;
			result = ScimStateJsonBinder.toJson(state);
			
			session.commit();
		}
		catch (ScimException e) {
			session.rollback();
			logger.error("",e);
			state = e.getState();
			result = ScimStateJsonBinder.toJson(state);
		}catch (Exception e) {
			session.rollback();
			logger.error("",e);
			state = ScimState.InternalServerError;
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
