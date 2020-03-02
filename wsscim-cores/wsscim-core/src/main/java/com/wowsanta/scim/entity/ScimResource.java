package com.wowsanta.scim.entity;

public interface ScimResource {
	public void setId(String id);
	public String getId();
	public <M extends ScimMeta> void setMeta(M meta);
	public <M extends ScimMeta> M getMeta();
	public String toString(boolean pretty);
}
