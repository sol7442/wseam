package com.wowsanta.scim.api;


import org.junit.Before;
import org.junit.Test;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.annotation.ScimAnnotationManager;
import com.wowsanta.scim.annotation.ScimControlAnnotationHandler;
import com.wowsanta.scim.annotation.ScimEntityAnnotatonHandler;
import com.wowsanta.scim.annotation.ScimJsonAnnotatonHandler;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimStateJsonBinder;
import com.wowsanta.scim.json.ScimStateJsonParser;
import com.wowsanta.scim.provider.json.WsScimOrgJsonParser;
import com.wowsanta.scim.provider.model.WsScimOrg;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;
import com.wowsanta.scim.type.SCIM_TARGET_TYPE;

public class ScimApiTest {

	private String fileName = "../../config/scim-spark-provider-rest.json";
	private boolean load = false;
	private String angetName = SCIM_TARGET_TYPE.LOCAL_TARGET;//.DEFAULT_HOST;//"provider";
	
	private String org_sample  = "{\"id\":\"org3\",\"name\":\"org3N\",\"meta\":{\"resourceType\":\"ScimOrg\",\"created\":\"2019-12-16T20:23:45Z\",\"lastModified\":\"2019-12-16T20:23:47Z\",\"expired\":\"2019-12-16T20:23:46Z\",\"active\":true},\"parent\":\"org1\"}";
	private String user_sample = "{\"id\":\"user3\",\"name\":\"user3N\",\"meta\":{\"resourceType\":\"ScimUser\",\"created\":\"2019-12-05T04:49:23Z\",\"lastModified\":\"2019-12-05T04:49:24Z\"},\"org\":\"org1\"}";

	String target_directory = "C:\\Work\\wsscim.git\\wsscim\\wsscim-samples\\wsscim-sample-provider-spark-hibernate\\bin\\main";

	@Before
	public void load() {
		if(!load) {
			ScimAnnotationManager mgr = ScimAnnotationManager.getInstance();
			mgr.setClassDirectory(target_directory);
			mgr.addDistLibFile("../../dist/wsscim-common-service-1.0.0.jar");
			mgr.addDistLibFile("../../dist/wsscim-scim-service-1.0.0.jar");
			mgr.addDistLibFile("../../dist/wsscim-provider-service-1.0.0.jar");
			
			mgr.addHandler(new ScimJsonAnnotatonHandler());
			mgr.addHandler(new ScimEntityAnnotatonHandler());
			mgr.run();
			
			System.out.println("-[SERVICE CLASS INFO] --------------------------");
			System.out.println(ScimServiceManager.getInstance().toString(true));

//			ScimTargetInfo target_info = new ScimTargetInfo();
//			target_info.setName(angetName);
//			target_info.setAddress("http://localhost:5000");
			
//			ScimApiFactory api_mgr = ScimApiFactory.getInstance();
//			api_mgr.addTargetInfo(angetName, target_info);
			
			load = true;
		}
	}
	
	@Test
	public void entity_api_test() {
		ScimTargetInfo target_info = new ScimTargetInfo();
		target_info.setName(angetName);
		target_info.setAddress("http://localhost:5000");
		
		ScimEntityApi entity_api = new ScimEntityApi(target_info,SCIM_ENTITY_NAME.ORG);
		try {
//			ScimResource resource = entity_api.get("org2");
//			System.out.println(resource.toString(false));
			
			WsScimOrg sample_org = new WsScimOrgJsonParser().fromJson(org_sample);
			entity_api.create(sample_org);
			
		} catch (Exception e) {
			if(e instanceof ScimException) {
				try {
					System.out.println(ScimStateJsonBinder.toJson(((ScimException) e).getState()));
				} catch (ScimJsonException e1) {
					e1.printStackTrace();
				}
			}else {
				System.out.println("e : " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	//@Test
	public void user_api_test() {
		
		ScimUserApi user_api = (ScimUserApi) ScimApiFactory.getInstance().getApi(angetName,SCIM_ENTITY_NAME.ORG);
		try {
			
//			WsScimUser sample_user = new WsScimUserJsonParser().fromJson(user_sample);
//			System.out.println(sample_user.toString(false));
//			user_api.create(sample_user);
//			
//			WsScimUser get_user = (WsScimUser) user_api.get(sample_user.getId());
//			System.out.println(get_user.toString(false));
//			
//			sample_user.setName("user3U");
//			user_api.update(sample_user);
			
//			WsScimSearchReqeust request = new WsScimSearchReqeust();
//			request.setCount(10);
//			request.setStartIndex(0);
//			
//			System.out.println(request.toString(true));
//			WsScimListResponse response = (WsScimListResponse) user_api.search(request);
//			System.out.println(response.toString(true, new WsScimUserJsonBinder()));
//			
//			user_api.delete(get_user.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void org_api_test() {
		
		ScimOrgApi org_api = (ScimOrgApi) ScimApiFactory.getInstance().getApi(angetName,"SCIM_ORG");
		try {
			
//			WsScimOrg sample_org = new WsScimOrgJsonParser().fromJson(org_sample);
//			System.out.println(sample_org.toString(false));
//			
//			org_api.create(sample_org);
//			
//			WsScimOrg get_org = (WsScimOrg) org_api.get(sample_org.getId());
//			System.out.println(get_org.toString(false));
//			
//			sample_org.setName("org3U");
//			org_api.update(sample_org);
			
//			WsScimSearchReqeust request = new WsScimSearchReqeust();
//			request.setCount(10);
//			request.setStartIndex(0);
//			
//			System.out.println(request.toString(true));
//			WsScimListResponse response = (WsScimListResponse) org_api.search(request);
//			System.out.println(response.toString(true, new WsScimOrgJsonBinder()));
			
//			org_api.delete(get_org.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	
	
	//@Test
	public void get_test() {
		try {
			
//			ScimOrgApi org_api = (ScimOrgApi) ScimApiFactory.getInstance().getApi(angetName,"SCIM_ORG");
//			org_api.setUrl("http://localhost:5000");
//			WsScimOrg org =  (WsScimOrg) org_api.get("org1");
//		
//			System.out.println(org.toString(false));
//			
//			WsScimOrg sample_org = new WsScimOrgJsonParser().fromJson(org_sample);
//			System.out.println(sample_org.toString(false));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void delete_test() {
//		try {
//			
//			ScimOrgApi org_api = (ScimOrgApi) ScimApiFactory.getInstance().getApi("SCIM_ORG");
//			org_api.setUrl("http://localhost:5000");
//			org_api.delete("org2");
//		
//		}catch (ScimException e) {
//			e.printStackTrace();
//		}
	}
	
	//@Test
	public void update_test() {
//		try {
//			
//			ScimOrgApi org_api = (ScimOrgApi) ScimApiFactory.getInstance().getApi("SCIM_ORG");
//			org_api.setUrl("http://localhost:5000");
//			WsScimOrg org = new WsScimOrg();
//			org.setId("org2");
//			org.setName("org2U");
//			org.setCreated(new Date());
//			org.setLastModified(new Date());
//			
//			org_api.update(org);
//		
//		}catch (ScimException e) {
//			e.printStackTrace();
//		}
	}
	
	//@Test
	public void create_test() {
//		try {
//			
//			ScimOrgApi org_api = (ScimOrgApi) ScimApiFactory.getInstance().getApi("SCIM_ORG");
//			org_api.setUrl("http://localhost:5000");
//			WsScimOrg org = new WsScimOrg();
//			org.setId("org2");
//			org.setName("org2N");
//			org.setCreated(new Date());
//			org.setLastModified(new Date());
//			
//			org_api.create(org);
//		
//		}catch (ScimException e) {
//			e.printStackTrace();
//		}
	}
	

}
