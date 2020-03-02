package com.wowsanta.scim.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.annotation.SCIM_REPOSITORY;

public class ScimRepositoryManager {

	transient static Logger logger = LoggerFactory.getLogger(ScimRepositoryManager.class);
	
	transient private static ScimRepositoryManager instance = null;
	transient private  ScimRepositoryFactory repositoryFactory;

	private String repositoryConfig;
	private String repositoryFactoryClass;
	private List<String> entites;
	
	private Map<String,Map<String,ScimRepositoryClass>> repositoris = new HashMap<String, Map<String,ScimRepositoryClass>>();
	
	public static ScimRepositoryManager getInstance() {
		if(instance == null) {
			instance = new ScimRepositoryManager();
		}
		return instance;
	}
	
	public static ScimRepositoryManager load(String file_name) {
		try {
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new FileReader(file_name));
			instance =  gson.fromJson(reader,ScimRepositoryManager.class);
		}catch (Exception e) {
			logger.error("ScimRepositoryManager Save Error : ",e);
			instance = new ScimRepositoryManager();
		}
		return instance;
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
			logger.error("ScimRepositoryManager Save Error : ",e);
		}
	}

	public void setRepositoryConfig(String repositoryConfig) {
		this.repositoryConfig = repositoryConfig;
	}

	public void setRepositoryFactoryClass(String repositoryFactoryClass) {
		this.repositoryFactoryClass = repositoryFactoryClass;
	}
	
	public ScimRepositoryFactory getRepositoryFactory(){
		return this.repositoryFactory;
	}

	public List<String> getEntites() {
		return entites;
	}
	public void addEntity(String file) {
		if(this.entites == null) {
			this.entites = new ArrayList<String>();
		}
		this.entites.add(file);
	}
	public void setEntites(List<String> entites) {
		this.entites = entites;
	}

	public String toString(boolean pretty) {
		String json = "";
		try {
			GsonBuilder builder = new GsonBuilder();
			if(pretty) {
				builder = builder.setPrettyPrinting();
			}
			Gson gson = builder.create();
			json = gson.toJson(this.repositoris);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public void build() {
		try {
			repositoryFactory = (ScimRepositoryFactory) Class.forName(this.repositoryFactoryClass).newInstance();
			repositoryFactory.build(this.repositoryConfig);
			
		} catch (Exception e) {
			logger.error("{} build error",this.repositoryFactoryClass, e);
		}
	}

	public Map<String,ScimRepositoryClass> getRepositoryClass(String type) {
		return repositoris.get(type);
	}
}
