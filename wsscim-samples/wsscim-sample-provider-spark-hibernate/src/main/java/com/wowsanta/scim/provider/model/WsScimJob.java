package com.wowsanta.scim.provider.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.entity.ScimGroup;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.entity.ScimUser;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.provider.json.WsScimJobJsonBinder;
import com.wowsanta.scim.provider.model.WsScimMeta;
import com.wowsanta.scim.provider.model.WsScimResource;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;



@Entity
@Table(name = "WS_SCIM_JOB")
@SCIM_ENTITY(
		type = "Jobs",
		name = SCIM_ENTITY_NAME.JOB,
		version = 1.0)
@SuppressWarnings("unchecked")
public class WsScimJob extends WsScimResource implements ScimGroup{

	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WsScimUser> users;
	
	@Override
	public WsScimMeta getMeta() {
		return new WsScimMeta("JOB",this.created,this.lastModified, this.expire, this.active);
	}

	@Override
	public void copy(ScimResource resource) {
		if (resource instanceof WsScimJob) {
			WsScimJob new_job = (WsScimJob) resource;
			this.id				= new_job.id;
			this.name    		= new_job.name;
			this.created 		= new_job.created;
			this.lastModified 	= new_job.lastModified;
			this.expire         = new_job.expire;
			this.active			= new_job.active;
		}
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
	
	public String toString(boolean pretty) {
		String json = "";
		try {
			WsScimJobJsonBinder binder = new WsScimJobJsonBinder();
			binder.setPretty(pretty);
			json =  binder.toJson(this);
		} catch (ScimJsonException e) {
			e.printStackTrace();
		}
		return json;
	}
}
