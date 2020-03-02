package com.wowsanta.scim.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wowsanta.scim.control.ScimControlInfo;
import com.wowsanta.scim.json.ScimJsonInfo;
import com.wowsanta.scim.repository.ScimRepositoryInfo;
import com.wowsanta.scim.service.ScimServiceInfo;

public class ScimEntityHandler {
	
	private Map<String,ScimControlInfo>	 control;
	private ScimServiceInfo     		 service;
	private ScimRepositoryInfo 			 repository;
	
	private ScimEntityInfo 				 entity;
	private Map<String,ScimJsonInfo> 	 json;

	public ScimEntityInfo getEntity() {
		return entity;
	}
	public void setEntity(ScimEntityInfo entity) {
		this.entity = entity;
	}
	public ScimJsonInfo getJson(String target) {
		if(this.json == null) {
			this.json = new HashMap<String, ScimJsonInfo>();
		}
		return this.json.get(target);
	}
	public void setJson(String target, ScimJsonInfo info) {
		this.json.put(target, info);
	}
	
	public ScimRepositoryInfo getRepository() {
		return repository;
	}
	public void setRepository(ScimRepositoryInfo repository) {
		this.repository = repository;
	}
	public ScimServiceInfo getService() {
		return service;
	}
	public void setService(ScimServiceInfo service) {
		this.service = service;
	}
	public Set<Entry<String, ScimControlInfo>> getControlSet() {
		if(this.control != null) {
			return this.control.entrySet();
		}
		return null;
	}
	public void putControl(String name, ScimControlInfo info) {
		if(this.control == null) {
			this.control = new HashMap<String, ScimControlInfo>();
		}
		this.control.put(name,info);
	}
}
