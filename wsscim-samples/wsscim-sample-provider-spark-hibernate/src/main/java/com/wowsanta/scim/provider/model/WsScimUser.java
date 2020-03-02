package com.wowsanta.scim.provider.model;




import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.ScimAttribute;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimUser;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.provider.json.WsScimUserJsonBinder;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;

@Entity
@Table(name = "WS_SCIM_USER")
@SCIM_ENTITY(
		type = "Users",
		name = SCIM_ENTITY_NAME.USER,
		version = 1.0)
public class WsScimUser extends WsScimResource implements ScimUser {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org", foreignKey = @ForeignKey(name="FK_USER_ORG"))
	private WsScimOrg org;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pos", foreignKey = @ForeignKey(name="FK_USER_POS"))
	private WsScimPos pos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job" , foreignKey = @ForeignKey(name="FK_USER_JOB"))
	private WsScimJob job;
	
	@JoinColumn(name = "email")
	private String email;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public WsScimMeta getMeta() {
		return new WsScimMeta(SCIM_ENTITY_NAME.USER,this.created,this.lastModified, this.expire, this.active);
	}

	@Override
	public ScimAttribute getAttribute() {
		WsScimAttribute attribute = new WsScimAttribute();
		attribute.addGroup(this.org);
		return attribute;
	}
	
	@Override
	public void copy(ScimResource resource) {
		if (resource instanceof WsScimUser) {
			WsScimUser new_user = (WsScimUser) resource;

			this.name    		= new_user.name;
			this.created 		= new_user.created;
			this.lastModified 	= new_user.lastModified;
			this.expire         = new_user.expire;
			this.active			= new_user.active;
			this.org			= new_user.org;
		}
	}
	
	public void setOrg(WsScimOrg org) {
		this.org = org;
	}
	public WsScimOrg getOrg() {
		return this.org;
	}
	
	public void setPos(WsScimPos pos) {
		this.pos = pos;
	}
	public WsScimPos getPos() {
		return this.pos;
	}
	
	public void setJob(WsScimJob job) {
		this.job = job;
	}
	public WsScimJob getJob() {
		return this.job;
	}

	public String toString(boolean pretty) {
		String json = "";
		try {
			WsScimUserJsonBinder binder = new WsScimUserJsonBinder();
			binder.setPretty(pretty);
			json =  binder.toJson(this);
		} catch (ScimJsonException e) {
			e.printStackTrace();
		}
		return json;
	}

}
