package com.wowsanta.com.scim.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimSearchRequest;
import com.wowsanta.scim.json.ScimJsonInfo;
import com.wowsanta.scim.json.ScimMessageJsonBinder;
import com.wowsanta.scim.json.ScimMessageJsonParser;
import com.wowsanta.scim.json.ScimResourceJsonBinder;
import com.wowsanta.scim.json.ScimStateJsonBinder;
import com.wowsanta.scim.repository.ScimRepository;
import com.wowsanta.scim.repository.ScimRepositoryFactory;
import com.wowsanta.scim.repository.ScimRepositoryManager;
import com.wowsanta.scim.repository.ScimSession;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.type.SCIM_MESSAGE_TYPE;
import com.wowsanta.scim.type.SCIM_TARGET_TYPE;

import spark.Request;
import spark.Response;
import spark.Route;

public class WsScimEntitySearchService implements Route {
	Logger logger = LoggerFactory.getLogger(WsScimEntityReadService.class);
	
	final String entity_type;
	private ScimJsonInfo json_info;
	
	private ScimMessageJsonParser request_parser;
	private ScimMessageJsonBinder response_binder;
	private ScimRepositoryFactory repository_factory ;
	
	public WsScimEntitySearchService(String type, ScimEntityHandler service_info) {
		this.entity_type = type;
		json_info = service_info.getJson(SCIM_TARGET_TYPE.LOCAL_TARGET);
		repository_factory = ScimRepositoryManager.getInstance().getRepositoryFactory();
		
		ScimJsonInfo request_json = ScimServiceManager.getInstance().getHandler(SCIM_MESSAGE_TYPE.SEARCH_REQUEST).getJson(SCIM_TARGET_TYPE.LOCAL_TARGET);
		ScimJsonInfo resonse_json = ScimServiceManager.getInstance().getHandler(SCIM_MESSAGE_TYPE.LIST_RESPONSE).getJson(SCIM_TARGET_TYPE.LOCAL_TARGET);
		try {
			request_parser = (ScimMessageJsonParser) request_json.getParserClass().newInstance();
			response_binder = (ScimMessageJsonBinder) resonse_json.getBinderClass().newInstance();
			response_binder.setResourceBinder((ScimResourceJsonBinder) json_info.getBinderClass().newInstance());
			
		} catch (Exception e) {
			logger.error("",e);
		} 
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String json   = "";
		String result = "";
		
		ScimSession session = null;
		
		ScimState state = null;
		try {
			json  	= request.body();
			
			ScimSearchRequest search_request = (ScimSearchRequest) request_parser.fromJson(json);
			
			session = repository_factory.openSession();
			ScimRepository repository = repository_factory.getRepository(entity_type, session);
			ScimListResponse  search_reponse = repository.search(search_request);
			
			state = ScimState.Ok;
			result = response_binder.toJson(search_reponse);
		}
		catch (Exception e) {
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
