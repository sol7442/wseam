package com.wowsanta.scim.provider.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.ScimOrg;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimUser;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.provider.json.WsScimOrgJsonBinder;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;



@Entity
@Table(name = "WS_SCIM_ORG")
@SCIM_ENTITY(
		type = "Orgs",
		name = SCIM_ENTITY_NAME.ORG,
		version = 1.0)
public class WsScimOrg extends WsScimResource implements ScimOrg {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent", foreignKey = @ForeignKey(name="FK_ORG_PARENT"))
	private WsScimOrg parent;
	
	
	@OneToMany(mappedBy = "org", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WsScimUser> users;
	
	@SuppressWarnings("unchecked")
	@Override
	public WsScimMeta getMeta() {
		return new WsScimMeta(SCIM_ENTITY_NAME.ORG,this.created,this.lastModified, this.expire, this.active);
	}

	@Override
	public void setParent(ScimOrg parent) {
		this.parent = (WsScimOrg) parent;
	}

	@Override
	public ScimOrg getParent() {
		return this.parent;
	}
	
	public String toString(boolean pretty) {
		String json = "";
		try {
			WsScimOrgJsonBinder binder = new WsScimOrgJsonBinder();
			binder.setPretty(pretty);
			json =  binder.toJson(this);
		} catch (ScimJsonException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public List<ScimUser> getUsers() {
		List<ScimUser> user_list = null ;
		if(this.users != null) {
			user_list = new ArrayList<ScimUser>();
			for (WsScimUser user : this.users) {
				user_list.add(user);
			}
		}
		return user_list;
	}

	@Override
	public void copy(ScimResource resource) {
		if (resource instanceof WsScimOrg) {
			WsScimOrg new_org = (WsScimOrg) resource;
			this.id				= new_org.id;
			this.name    		= new_org.name;
			this.created 		= new_org.created;
			this.lastModified 	= new_org.lastModified;
			this.expire         = new_org.expire;
			this.active			= new_org.active;
			this.parent  		= new_org.parent;
		}
	}
}
