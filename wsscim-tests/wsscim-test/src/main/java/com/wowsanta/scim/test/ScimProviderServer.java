package com.wowsanta.scim.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.annotation.ScimAnnotationManager;
import com.wowsanta.scim.annotation.ScimControlAnnotationHandler;
import com.wowsanta.scim.annotation.ScimEntityAnnotatonHandler;
import com.wowsanta.scim.annotation.ScimJsonAnnotatonHandler;
import com.wowsanta.scim.annotation.ScimRepositoryAnnotatonHandler;
import com.wowsanta.scim.annotation.ScimServiceAnnotatonHandler;
import com.wowsanta.scim.repository.ScimRepositoryManager;
import com.wowsanta.scim.server.ScimServerConfiguration;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.spark.SparkServer;
import com.wowsanta.scim.spark.SparkServerConfiguration;

public class ScimProviderServer {
	static Logger logger = LoggerFactory.getLogger(ScimProviderServer.class);
	private static SparkServer server;

	public static void main(String[] args) {
		try {
			ScimServerConfiguration<SparkServer> config = SparkServerConfiguration
					.load("../../config/scim-spark-provider-server.json");
			server = config.build();

			ScimRepositoryManager.load(config.getRepositoryConfigFile());
			
			String target_directory = "C:\\Work\\wsscim.git\\wsscim\\wsscim-samples\\wsscim-sample-provider-spark-hibernate\\bin\\main";
			
			ScimAnnotationManager mgr = ScimAnnotationManager.getInstance();
			mgr.setClassDirectory(target_directory);
			mgr.addDistLibFile("../../dist/wsscim-common-service-1.0.0.jar");
			mgr.addDistLibFile("../../dist/wsscim-scim-service-1.0.0.jar");
			mgr.addDistLibFile("../../dist/wsscim-provider-service-1.0.0.jar");
			
			mgr.addHandler(new ScimJsonAnnotatonHandler());
			mgr.addHandler(new ScimEntityAnnotatonHandler());
			mgr.addHandler(new ScimRepositoryAnnotatonHandler());
			mgr.addHandler(new ScimServiceAnnotatonHandler());
			mgr.addHandler(new ScimControlAnnotationHandler());
			mgr.run();
			
			logger.info("-[SERVICE CLASS INFO] --------------------------\n {}", ScimServiceManager.getInstance().toString(true));

			ScimRepositoryManager.getInstance().build();;

			server.initialize(config.getServices());
			server.start();

		} catch (Exception e) {
			logger.error("ScimProviderServer Error : ", e);
		}
	}

}
