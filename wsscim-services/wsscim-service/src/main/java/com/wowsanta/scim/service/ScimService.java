package com.wowsanta.scim.service;


import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimSearchRequest;

public interface ScimService<R extends ScimResource> {
	public R get(String id) throws ScimException ;
	public void create(R obj) throws ScimException;
	public void update(R obj) throws ScimException;
	public void delete(String id) throws ScimException;
	public ScimListResponse search(ScimSearchRequest request) throws ScimException;
}
