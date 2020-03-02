package com.wowsanta.scim.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wowsanta.scim.service.ScimRestFulServiceManager;

public class ScimServiceManagerTest {
	private String fileName = "../../config/scim-spark-provider-rest.json";
	
	private String commonFileName = "../../config/provider-service-common.json";
	private String scimFileName = "../../config/provider-service-scim.json";
	@Test
	public void load_modify_save_test() {
		List<String> config_files = new ArrayList<String>();
		config_files.add(commonFileName);
		config_files.add(scimFileName);
		
		
		ScimRestFulServiceManager manager = ScimRestFulServiceManager.getInstance();
		manager.load(config_files);

		System.out.println(manager.toString(true));
	}

}
