package com.wowsanta.scim.annotation;

import java.lang.annotation.Annotation;

@SuppressWarnings("rawtypes")
public class ScimAnnotaionInfo {

	private String classFile;
	private String className;
	private Class  targetClass;
	private Annotation annotation;
	
	public String getClassFile() {
		return classFile;
	}
	public void setClassFile(String classFile) {
		this.classFile = classFile;
	}
	
	public Class getTargetClass() {
		return this.targetClass;
	}
	public void setTargetClass(Class clazz) {
		this.targetClass = clazz;
		this.className = clazz.getName();
	}
	public String getClassName() {
		return this.className;
	}
	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}
	public Annotation getAnnotation() {
		return this.annotation;
	}
}
