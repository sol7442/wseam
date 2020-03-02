package com.wowsanta.scim.repository;
@SuppressWarnings("rawtypes")
public class ScimRepositoryInfo {
	private String repositoryFile;
	private String repositoryName;
	transient private Class  repositoryClass;
	public String getRepositoryFile() {
		return repositoryFile;
	}
	public void setRepositoryFile(String repositoryFile) {
		this.repositoryFile = repositoryFile;
	}
	public String getRepositoryName() {
		return repositoryName;
	}
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}
	public Class getRepositoryClass() {
		return repositoryClass;
	}
	public void setRepositoryClass(Class repositoryClass) {
		this.repositoryClass = repositoryClass;
	}
}
