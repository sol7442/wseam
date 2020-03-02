package com.wowsanta.scim.entity;


@SuppressWarnings("rawtypes")
public class ScimEntityInfo {
	private String entityType;
	private String entityFile;
	private String entityName;
	private boolean primitive = false;
	
	transient private Class  entityClass;

	public String getEntityFile() {
		return entityFile;
	}

	public void setEntityFile(String entityFile) {
		this.entityFile = entityFile;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public void setPrimitive(boolean primitive) {
		this.primitive = primitive;
	}
}
