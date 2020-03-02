package com.wowsanta.scim.repository;


import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimSearchRequest;

public interface ScimRepository {
	public void setSession(ScimSession session);
	public void create(ScimResource resource) throws ScimRepositoryException;
	public void update(ScimResource resource) throws ScimRepositoryException;
	public void delete(ScimResource resource) throws ScimRepositoryException;
	public ScimResource get(String id) throws ScimRepositoryException;
	public ScimListResponse search(ScimSearchRequest request)throws ScimRepositoryException;
	
}
