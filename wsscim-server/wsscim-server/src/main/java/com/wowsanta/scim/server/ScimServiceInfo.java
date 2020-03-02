package com.wowsanta.scim.server;

import java.util.ArrayList;
import java.util.List;


public class ScimServiceInfo {
	private String name;
	private String parseClassName;
	private String bindClassName;

	private String method;
	private String path;
	private String serviceClassName;
	
	private List<ScimServiceInfo> subServices;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParseClassName() {
		return parseClassName;
	}
	public void setParseClassName(String parseClassName) {
		this.parseClassName = parseClassName;
	}
	public String getBindClassName() {
		return bindClassName;
	}
	public void setBindClassName(String bindClassName) {
		this.bindClassName = bindClassName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getServiceClassName() {
		return serviceClassName;
	}
	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}
	public void addSubService(ScimServiceInfo service) {
		if(this.subServices == null) {
			this.subServices = new ArrayList<ScimServiceInfo>();
		}
		subServices.add(service);
	}
	public List<ScimServiceInfo> getSubServices() {
		return subServices;
	}
	public void setSubServices(List<ScimServiceInfo> subServices) {
		this.subServices = subServices;
	}
	
}
