package com.wowsanta.scim.repository;

public class ScimRepositoryClass {
	private String className;
	private String classFile;
	private double version;
	transient private Class repositoryClass;
	
	public Class getRepositoryClass() {
		return this.repositoryClass;
	}
	public void setRepositoryClass(Class clazz) {
		this.repositoryClass = clazz;
	}

	public void setClassFile(String file_name) {
		this.classFile = file_name;
	}
	public String getClassname() {
		return this.className;
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
}
