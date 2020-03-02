package com.wowsanta.scim.provider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wowsanta.scim.entity.ScimMeta;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.hibernate.HibernateEntity;

@MappedSuperclass
public abstract class WsScimResource implements ScimResource, HibernateEntity{

	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(64)")
	protected String id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(64)")
	protected String name;
	
	@Column(name="createTime")
	protected Date created;

	@Column(name="modifyTime")
	protected Date lastModified;
	
	@Column(name="expireTime")
	protected Date expire;

	@Column(name="active")
	protected boolean active;
	
	@Override
	public String getId() {
		return this.id;
	}


	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	
	@Override
	public void setMeta(ScimMeta meta) {
		if(meta != null) {
			this.created      = meta.getCreated();
			this.lastModified = meta.getLastModified();
			this.active       = meta.isActive();
			this.expire	      = meta.getExpired();
		}
	}

}
