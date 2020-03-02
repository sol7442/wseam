package com.wowsanta.scim.server;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ScimServerConfiguration<S extends ScimServer> {
	transient static Logger logger = LoggerFactory.getLogger(ScimServerConfiguration.class);
	
	private String repository;
	private String restful;
	private String service;
	
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
	protected  String getServiceConfigFile() {
		return service;
	}
	public void setServiceConfigFile(String file) {
		this.service = file;
	}
	public String getRestfulConfigFile() {
		return restful;
	}
	public void setRestfulConfigFile(String restful) {
		this.restful = restful;
	}
}

