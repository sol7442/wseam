package com.wowsanta.scim.service;

public class ScimServiceClass {
	private String method;
	private String path;
	private String className;
	private String classFile;
	private double version;
	transient private Class serviceClass;
	

	public void setClassFile(String file_name) {
		this.classFile = file_name;
	}
	public void setClassName(String name) {
		this.className = name;
	}
	
	public void setVersion(double ver) {
		this.version = ver;
	}
	public double getVersion() {
		return this.version;
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

	public Class getServiceClass() {
		return this.serviceClass;
	}
	public void setServiceClass(Class clazz) {
		this.serviceClass = clazz;
	}
}
