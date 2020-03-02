package com.wowsanta.scim.entity;
import java.util.Date;

public interface ScimMeta {
	public void setResourceType(String type);
	public String getResourceType();
	public void setCreated(Date date);
	public Date getCreated();
	public void setLastModified(Date date);
	public Date getLastModified();
	public boolean isActive();
	public Date getExpired();
}
