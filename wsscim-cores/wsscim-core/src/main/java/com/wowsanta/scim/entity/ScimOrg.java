package com.wowsanta.scim.entity;


public interface ScimOrg extends ScimGroup{
	public ScimOrg getParent();
	public void setParent(ScimOrg org);
}
