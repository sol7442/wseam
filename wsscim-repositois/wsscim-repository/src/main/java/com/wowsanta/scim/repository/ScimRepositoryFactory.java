package com.wowsanta.scim.repository;


@SuppressWarnings("rawtypes")
public interface ScimRepositoryFactory  {
	public ScimSession openSession() throws ScimRepositoryException;
	public void close()throws ScimRepositoryException;
	public ScimRepository getRepository(String type, ScimSession session)throws ScimRepositoryException;
	public ScimRepository newRepository(Class repository_class)throws ScimRepositoryException;
	public void build(String repositoryConfig);
	//public void addEntitis(Map<String, ScimEntityInfo> entitis);
}
