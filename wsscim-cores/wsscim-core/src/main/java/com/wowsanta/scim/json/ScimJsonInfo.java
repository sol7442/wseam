package com.wowsanta.scim.json;

@SuppressWarnings("rawtypes")
public class ScimJsonInfo {
	private String parerFile;
	private String binderFile;
	private String parserName;
	private String binderName;
	
	transient private Class  parserClass;
	transient private Class  binderClass;

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
	public String getParerFile() {
		return parerFile;
	}
	public void setParserFile(String parerFile) {
		this.parerFile = parerFile;
	}
	public String getBinderFile() {
		return binderFile;
	}
	public void setBinderFile(String binderFile) {
		this.binderFile = binderFile;
	}
}
