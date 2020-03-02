package com.wowsanta.scim.provider.model;

import java.util.Date;
import com.wowsanta.scim.entity.ScimMeta;


public class WsScimMeta implements ScimMeta {
	private String resourceType;
	private Date created;
	private Date lastModified;
	private Date expired;
	private boolean active;
	
	public WsScimMeta() {
		
	}
	public WsScimMeta(String type, Date create, Date modify, Date extired, boolean act) {
		this.resourceType = type;
		this.created = create;
		this.lastModified = modify;
		this.expired = extired;
		this.active = act;
	}

	@Override
	public void setResourceType(String type) {
		this.resourceType = type;
	}

	@Override
	public String getResourceType() {
		return this.resourceType;
	}

	@Override
	public void setCreated(Date date) {
		this.created = date;
	}

	@Override
	public Date getCreated() {
		return this.created;
	}

	@Override
	public void setLastModified(Date date) {
		this.lastModified = date;
	}

	@Override
	public Date getLastModified() {
		return this.lastModified;
	}
	@Override
	public boolean isActive() {
		return this.active;
	}
	@Override
	public Date getExpired() {
		return this.expired;
	}
	public void setExpired(Date date) {
		this.expired = date;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
