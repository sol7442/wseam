package com.wowsanta.scim.repository;


public interface ScimSession {
	public void close() throws ScimRepositoryException;
	public void commit() throws ScimRepositoryException;
	public void rollback() throws ScimRepositoryException;
}
