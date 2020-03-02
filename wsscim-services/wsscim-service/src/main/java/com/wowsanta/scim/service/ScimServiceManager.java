package com.wowsanta.scim.service;

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
import com.wowsanta.scim.control.ScimControlInfo;
import com.wowsanta.scim.control.ScimControlKey;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.repository.ScimRepositoryType;

public class ScimServiceManager {
	transient static Logger logger = LoggerFactory.getLogger(ScimServiceManager.class);
	transient private static ScimServiceManager instance = null;
	
	private String serviceType    = ScimServiceType.SCIM_PROVIDER;
	private String repositorytype = ScimRepositoryType.HIBERNATE;
	private Map<String,ScimControlInfo> handlers = new HashMap<String,ScimControlInfo>();
//	private List<ScimControlInfo> handlers = new ArrayList<ScimControlInfo>();//new HashMap<ScimControlKey,ScimControlInfo>();
	public static ScimServiceManager getInstance() {
		if(instance == null) {
			instance = new ScimServiceManager();
		}
		return instance;
	}

	public static ScimServiceManager load(String file_name) {
		try {
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new FileReader(file_name));
			instance =  gson.fromJson(reader,ScimServiceManager.class);
		}catch (Exception e) {
			logger.error("ScimServiceManager Load Error : ",e);
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
			logger.error("ScimServiceManager Save Error : ",e);
		}
	}

	public String toString(boolean pretty) {
		String json = "";
		try {
			GsonBuilder builder = new GsonBuilder();
			if(pretty) {
				builder = builder.setPrettyPrinting();
			}
			Gson gson = builder.create();
			json = gson.toJson(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public void addHandler(ScimControlInfo control) {
		this.handlers.put(control.getKey(),control);
		
	}

//	public void addHandler(String name, ScimEntityHandler handler) {
//		this.handlers.put(name,handler);
//	}
//	public ScimEntityHandler getHandler(String name) {
//		return this.handlers.get(name);
//	}
//	
//	public Set<Entry<String,ScimEntityHandler>> getHandlerSet() {
//		return handlers.entrySet();
//	}
	
	public String getRepositorytype() {
		return repositorytype;
	}
	public void setRepositorytype(String repositorytype) {
		this.repositorytype = repositorytype;
	}
	public String getServiceType() {
		return this.serviceType;
	}
	public void setServiceType(String type) {
		this.serviceType = type;
	}

	
}
