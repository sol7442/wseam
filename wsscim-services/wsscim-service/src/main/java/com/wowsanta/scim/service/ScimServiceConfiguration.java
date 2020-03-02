package com.wowsanta.scim.service;

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

public class ScimServiceConfiguration {
	transient static Logger logger = LoggerFactory.getLogger(ScimServiceConfiguration.class);
	
	private String name;
	
	public String toString() {
		String json = "";
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
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
			logger.error("Configruation Save Error : {} ",file_name,e);
		}
	}
	public static ScimServiceConfiguration load(String file_name) {
		ScimServiceConfiguration config = null;
		try {
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new FileReader(file_name));
			config = gson.fromJson(reader,ScimServiceConfiguration.class);
			logger.debug("load : {} ",file_name);
		}catch (Exception e) {
			logger.error("Configruation Load Error : {} ",file_name,e);
		}
		return config;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
