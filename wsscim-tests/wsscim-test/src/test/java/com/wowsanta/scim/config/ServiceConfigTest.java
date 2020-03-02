package com.wowsanta.scim.config;

import java.util.Map;

import org.junit.Test;

import com.wowsanta.scim.attribute.control.OrgDeleteRoute;
import com.wowsanta.scim.attribute.control.OrgReadRoute;
import com.wowsanta.scim.attribute.control.OrgReplaceRoute;
import com.wowsanta.scim.attribute.control.OrgCreateRoute;
import com.wowsanta.scim.attribute.control.OrgUpdateRoute;
import com.wowsanta.scim.attribute.control.OrgSearchGetRoute;
import com.wowsanta.scim.provider.json.WsScimListResponseJsonBinder;
import com.wowsanta.scim.provider.json.WsScimListResponseJsonParser;
import com.wowsanta.scim.provider.json.WsScimSearchReqeustJsonBinder;
import com.wowsanta.scim.provider.json.WsScimSearchReqeustJsonParser;
import com.wowsanta.scim.provider.json.WsScimUserJsonBinder;
import com.wowsanta.scim.provider.json.WsScimUserJsonParser;
import com.wowsanta.scim.service.ScimRestfulInfo;
import com.wowsanta.scim.service.ScimServiceConfiguration;
import com.wowsanta.scim.service.ScimServiceType;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;
import com.wowsanta.scim.type.SCIM_MESSAGE_TYPE;
import com.wowsanta.scim.user.control.UserCreateRoute;
import com.wowsanta.scim.user.control.UserDeleteRoute;
import com.wowsanta.scim.user.control.UserReadRoute;
import com.wowsanta.scim.user.control.UserReplaceRoute;
import com.wowsanta.scim.user.control.UserSearchPostRoute;
import com.wowsanta.scim.service.ScimServiceName;

import spark.route.HttpMethod;

public class ServiceConfigTest {

	String fileName = "../../config/provider-service-scim.json";
	@Test
	public void load_modify_save_test() {
		ScimServiceConfiguration config = ScimServiceConfiguration.load(fileName);//();
		

		//create_user(config);
		
		
		System.out.println(config.toString());
		
		config.save(fileName);
	}
	private void create_user(ScimServiceConfiguration config) {
		ScimRestfulInfo info = new ScimRestfulInfo();
		info.setPath("/scim/v2/Users");
		info.putBinder(SCIM_ENTITY_NAME.USER, WsScimUserJsonBinder.class.getName());
		info.putParser(SCIM_ENTITY_NAME.USER, WsScimUserJsonParser.class.getName());
		info.putBinder(SCIM_MESSAGE_TYPE.SEARCH_REQUEST, WsScimSearchReqeustJsonBinder.class.getName());
		info.putParser(SCIM_MESSAGE_TYPE.SEARCH_REQUEST, WsScimSearchReqeustJsonParser.class.getName());
		info.putBinder(SCIM_MESSAGE_TYPE.LIST_RESPONSE , WsScimListResponseJsonBinder.class.getName());
		info.putParser(SCIM_MESSAGE_TYPE.LIST_RESPONSE , WsScimListResponseJsonParser.class.getName());
		
		ScimRestfulInfo create_info = new ScimRestfulInfo();
		create_info.setMethod(HttpMethod.post.name());
		create_info.setControlClass(UserCreateRoute.class.getName());
		info.putService(ScimServiceName.CREATE, create_info);
		
		ScimRestfulInfo read_info = new ScimRestfulInfo();
		read_info.setPath("/:id");
		read_info.setMethod(HttpMethod.get.name());
		read_info.setControlClass(UserReadRoute.class.getName());
		info.putService(ScimServiceName.READ, read_info);
		
		ScimRestfulInfo update_info = new ScimRestfulInfo();
		update_info.setPath("/:id");
		update_info.setMethod(HttpMethod.put.name());
		update_info.setControlClass(UserReplaceRoute.class.getName());
		info.putService(ScimServiceName.REPLACE, update_info);
		
		ScimRestfulInfo delete_info = new ScimRestfulInfo();
		delete_info.setPath("/:id");
		delete_info.setMethod(HttpMethod.delete.name());
		delete_info.setControlClass(UserDeleteRoute.class.getName());
		info.putService(ScimServiceName.DELETE, delete_info);
		
		ScimRestfulInfo search_info = new ScimRestfulInfo();
		search_info.setMethod(HttpMethod.post.name());
		search_info.setControlClass(UserSearchPostRoute.class.getName());
		info.putService(ScimServiceName.SEARCH_POST, search_info);
		
		config.putService(ScimServiceType.SCIM_USER, info);
		
	}
	private void create(ScimServiceConfiguration config) {
		config.setName("provider-scim-service");
		
		ScimRestfulInfo info = createInfo();
		
		
//		ScimRestfulInfo read_info 	= create_read_info();
//		ScimRestfulInfo update_info = create_update_info();
//		ScimRestfulInfo create_info = create_create_info();
//		ScimRestfulInfo delete_info = create_delete_info();
//		ScimRestfulInfo search_info = create_search_info();
		
		
		info.putService(ScimServiceName.CREATE		, create_create_info());
		info.putService(ScimServiceName.READ		, create_read_info());
		info.putService(ScimServiceName.UPDATE		, create_update_info());
		info.putService(ScimServiceName.REPLACE		, create_replace_info());
		info.putService(ScimServiceName.DELETE		, create_delete_info());
		info.putService(ScimServiceName.SEARCH_GET	, create_search_get_info());
		info.putService(ScimServiceName.SEARCH_POST	, create_search_post_info());
		
		config.putService(ScimServiceType.SCIM_ORG, info);
	}
	private ScimRestfulInfo create_search_get_info() {
		ScimRestfulInfo search_info = new ScimRestfulInfo();
		search_info.setMethod(HttpMethod.get.name());
		search_info.setControlClass(OrgSearchGetRoute.class.getName());
		return search_info;
	}
	private ScimRestfulInfo create_search_post_info() {
		ScimRestfulInfo search_info = new ScimRestfulInfo();
		search_info.setMethod(HttpMethod.post.name());
		search_info.setControlClass(OrgSearchGetRoute.class.getName());
		return search_info;
	}
	private ScimRestfulInfo create_delete_info() {
		ScimRestfulInfo delete_info = new ScimRestfulInfo();
		delete_info.setPath("/:id");
		delete_info.setMethod(HttpMethod.delete.name());
		delete_info.setControlClass(OrgDeleteRoute.class.getName());
		return delete_info;
	}
	private ScimRestfulInfo create_create_info() {
		ScimRestfulInfo create_info = new ScimRestfulInfo();
		create_info.setMethod(HttpMethod.post.name());
		create_info.setControlClass(OrgCreateRoute.class.getName());
		return create_info;
	}
	private ScimRestfulInfo create_update_info() {
		ScimRestfulInfo update_info = new ScimRestfulInfo();
		update_info.setPath("/:id");
		update_info.setMethod(HttpMethod.patch.name());
		update_info.setControlClass(OrgUpdateRoute.class.getName());
		return update_info;
	}
	private ScimRestfulInfo create_replace_info() {
		ScimRestfulInfo update_info = new ScimRestfulInfo();
		update_info.setPath("/:id");
		update_info.setMethod(HttpMethod.put.name());
		update_info.setControlClass(OrgReplaceRoute.class.getName());
		return update_info;
	}
	private ScimRestfulInfo create_read_info() {
		ScimRestfulInfo read_info = new ScimRestfulInfo();
		read_info.setPath("/:id");
		read_info.setMethod(HttpMethod.get.name());
		read_info.setControlClass(OrgReadRoute.class.getName());
		return read_info;
	}
	private ScimRestfulInfo createInfo() {
		ScimRestfulInfo info = new ScimRestfulInfo();
		//info.setServiceClass(ScimOrgService.class.getName());
		info.setPath("/scim/v2/Orgs");
		return info;
	}
}
