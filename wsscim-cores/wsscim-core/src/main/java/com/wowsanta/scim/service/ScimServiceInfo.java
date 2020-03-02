package com.wowsanta.scim.service;

@SuppressWarnings("rawtypes")
public class ScimServiceInfo {
	private String serviceFile;
	private String serviceName;
	
	transient private Class serviceClass;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceFile() {
		return serviceFile;
	}
	public void setServiceFile(String serviceFile) {
		this.serviceFile = serviceFile;
	}
	public Class getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(Class serviceClass) {
		this.serviceClass = serviceClass;
	}

}
