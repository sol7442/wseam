package com.wowsanta.scim.entity;


public interface ScimAttribute {
	public void addGroup(ScimGroup group);
	public ScimGroup getGroup(String name);
}
