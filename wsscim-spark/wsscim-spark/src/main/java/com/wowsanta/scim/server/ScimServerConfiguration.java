package com.wowsanta.scim.server;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ScimServerConfiguration<S extends ScimServer> {
	transient static Logger logger = LoggerFactory.getLogger(ScimServerConfiguration.class);
	
	private String repository;
	private List<String> services;
	
	public void save(String file_name) {
		FileWriter writer;
		try {
			writer = new FileWriter(new File(file_name));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(this,writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error("Configruation Save Error : ",e);
		}
	}
	public String getRepositoryConfigFile() {
		return repository;
	}
	public void setRepositoryConfigFile(String file) {
		this.repository = file;
	}
	
	public abstract S build() ;
	public  List<String> getServices() {
		return services;
	}
	public  void addServiceConfigFile(String file_name) {
		if(services == null) {
			services = new ArrayList<String>();
		}
		services.add(file_name);
	}
	
}

