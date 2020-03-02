package com.wowsanta.scim.json;

public class ScimJsonClass {
	private String parserName;
	private String binderName;
	private String parserFile;
	private String binderFile;
	private double version;
	transient private Class  parserClass;
	transient private Class  binderClass;
	
	public String getParserName() {
		return parserName;
	}
	public void setParserName(String parserName) {
		this.parserName = parserName;
	}
	public String getBinderName() {
		return binderName;
	}
	public void setBinderName(String binderName) {
		this.binderName = binderName;
	}
	public Class getParserClass() {
		return parserClass;
	}
	public void setParserClass(Class parserClass) {
		this.parserClass = parserClass;
	}
	public Class getBinderClass() {
		return binderClass;
	}
	public void setBinderClass(Class binderClass) {
		this.binderClass = binderClass;
	}
	public void setBinderFile(String file_name) {
		this.binderFile = file_name;
	}
	public void setParserFile(String file_name) {
		this.parserFile = file_name;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double ver) {
		this.version = ver;
	}
}
