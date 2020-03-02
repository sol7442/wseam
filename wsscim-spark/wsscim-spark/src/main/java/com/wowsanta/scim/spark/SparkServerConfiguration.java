package com.wowsanta.scim.spark;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.server.ScimServerConfiguration;

public class SparkServerConfiguration extends ScimServerConfiguration<SparkServer> {
	transient static Logger logger = LoggerFactory.getLogger(SparkServerConfiguration.class);
	
	private int port;
	private int maxThreads;
	private int minThreads;
	private int idleTimeoutMillis;
	private SparkSecurity security;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	public int getMinThreads() {
		return minThreads;
	}
	public void setMinThreads(int minThreads) {
		this.minThreads = minThreads;
	}
	public int getIdleTimeoutMillis() {
		return idleTimeoutMillis;
	}
	public void setIdleTimeoutMillis(int idleTimeoutMillis) {
		this.idleTimeoutMillis = idleTimeoutMillis;
	}
	
	public void setSecurity(SparkSecurity security) {
		this.security = security;
	}
	public SparkSecurity getSecurity() {
		return this.security;
	}
	
	public static SparkServerConfiguration load(String file_name) {
		try {
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new FileReader(file_name));
			return gson.fromJson(reader,SparkServerConfiguration.class);
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
	@Override
	public SparkServer build() {
		SparkServer server = new SparkServer();
		
		server.setPort(this.port);
		server.setThreadPool(this.maxThreads,this.minThreads,this.idleTimeoutMillis);
		server.setSecurity(this.security);
		
		return server;
	}
}
