package com.wowsanta.scim.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class ScimRepositoryConfiguration {
	
	transient static Logger logger = LoggerFactory.getLogger(ScimRepositoryConfiguration.class);
	
	private String factoryClassName;
	private String factoryConfigFileName;
	
	public ScimRepositoryConfiguration load(String file_name) {
		try {
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new FileReader(file_name));
			return gson.fromJson(reader,ScimRepositoryConfiguration.class);
		}catch (Exception e) {
			logger.error("Configruation Save Error : ",e);
		}
		return null;
	}
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
	
	public String getFactoryConfigFileName() {
		return factoryConfigFileName;
	}
	public void setFactoryConfigFileName(String configFileName) {
		this.factoryConfigFileName = configFileName;
	}
	public String getFactoryClassName() {
		return factoryClassName;
	}
	public void setFactoryClassName(String factoryClassName) {
		this.factoryClassName = factoryClassName;
	}
	
}
