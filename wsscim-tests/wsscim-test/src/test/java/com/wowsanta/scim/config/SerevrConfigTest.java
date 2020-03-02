package com.wowsanta.scim.config;


import org.junit.Test;

import com.wowsanta.scim.spark.SparkServerConfiguration;


public class SerevrConfigTest {

	private String fileName = "../../config/scim-spark-provider-server.json";
	
	@Test
	public void load_modify_save_test() {
		SparkServerConfiguration config = SparkServerConfiguration.load(fileName);
		config.addServiceConfigFile("../../config/service-common.json");
		config.addServiceConfigFile("../../config/service-scim.json");
		//ScimServerConfiguration<SparkServer> config = SparkServerConfiguration.load("../../config/scim-spark-provider-server.json");
		//server = config.build();
		config.save(fileName);
	}
}
