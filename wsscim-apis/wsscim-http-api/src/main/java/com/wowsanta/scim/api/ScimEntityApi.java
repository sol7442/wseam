package com.wowsanta.scim.api;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimEntityInfo;
import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimOrg;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimSearchRequest;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimJsonInfo;
import com.wowsanta.scim.json.ScimResourceJsonBinder;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.service.ScimService;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.service.ScimServiceName;
import com.wowsanta.scim.service.ScimServiceUrl;

public class ScimEntityApi extends AbstractScimApi implements ScimService<ScimResource> {
	Logger logger = LoggerFactory.getLogger(ScimEntityApi.class);
	
	final ScimTargetInfo target;
	final String entity;
	final ScimEntityHandler entityHandler; 
	CloseableHttpClient client;
	public ScimEntityApi(ScimTargetInfo info, String entity) {
		this.target = info;
		this.entity = entity;
		this.entityHandler = ScimServiceManager.getInstance().getHandler(this.entity);
		
		this.client = HttpConnectionManager.getInstance().get();
	}

	public void ExceptionHandler(Throwable e) throws ScimException {
		if(e instanceof ScimException) {
			throw (ScimException)e;
		}else {
			throw new ScimException(e.getMessage(),ScimState.InternalServerError);
		}
	}
	@Override
	public ScimResource get(String id) throws ScimException {
		ScimResource resource = null;
		String call_url = getUrl(id);
		String result = "";
		
		HttpGet get = new HttpGet(call_url);
		requestHeader(get);
		try {
			result = responseResult(client.execute(get));
			resource = fromJson(result);
		}
		catch (Exception e) {
			ExceptionHandler(e);
		}
		finally {
			logger.debug("read : {} : {}",call_url,result);
		}
		return resource;
	}

	@Override
	public void create(ScimResource resource) throws ScimException {
		String call_url = getUrl(null);
		String json   = "";
		String result = "";
		try {
			json = toJson(resource);
			
			HttpPost post = new HttpPost(call_url);
			requestHeader(post);
			
			post.setEntity(new StringEntity(json));
			result = responseResult(client.execute(post));	
			
		}catch (Exception e) {
			ExceptionHandler(e);
		}
		finally {
			logger.debug("create : {} : {}",call_url,result);
		}
		
	}


	@Override
	public void update(ScimResource resource) throws ScimException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) throws ScimException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScimListResponse search(ScimSearchRequest request) throws ScimException {
		// TODO Auto-generated method stub
		return null;
	}
	private String toJson(ScimResource resource) throws ScimJsonException, InstantiationException, IllegalAccessException {
		ScimJsonInfo json_info = this.entityHandler.getJson(target.getName());
		ScimResourceJsonBinder binder = (ScimResourceJsonBinder) json_info.getBinderClass().newInstance();
		return binder.toJson(resource);
	}
	private ScimResource fromJson(String result) throws InstantiationException, IllegalAccessException, ScimJsonException {
		ScimJsonInfo json_info = this.entityHandler.getJson(target.getName());
		ScimResourceJsonParser parser = (ScimResourceJsonParser) json_info.getParserClass().newInstance();
		return parser.fromJson(result);
	}

	private String getUrl(String id) {
		ScimEntityInfo entity_info = this.entityHandler.getEntity();
		return target.getAddress() + ScimServiceUrl.getUrl(entity_info.getEntityType()  ,id);
	}

}
