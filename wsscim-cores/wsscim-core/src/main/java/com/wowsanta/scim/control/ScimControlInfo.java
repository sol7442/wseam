package com.wowsanta.scim.control;

@SuppressWarnings("rawtypes")
public class ScimControlInfo {
	private String controlFile;
	private String controlName;
	private String name;
	private String method;
	private String path;
	
	transient private Class  controlClass;

	public String getControlFile() {
		return controlFile;
	}

	public void setControlFile(String controlFile) {
		this.controlFile = controlFile;
	}

	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Class getControlClass() {
		return controlClass;
	}

	public void setControlClass(Class controlClass) {
		this.controlClass = controlClass;
	}

	public String getKey() {
		return this.path + " - " + this.method;
	}
	
}
