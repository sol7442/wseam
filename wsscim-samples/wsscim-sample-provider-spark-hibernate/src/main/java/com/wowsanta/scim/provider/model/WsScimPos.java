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
import com.wowsanta.scim.provider.json.WsScimPosJsonBinder;
import com.wowsanta.scim.provider.model.WsScimMeta;
import com.wowsanta.scim.provider.model.WsScimResource;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;



@Entity
@Table(name = "WS_SCIM_POS")
@SCIM_ENTITY(
		type = "Poss",
		name = SCIM_ENTITY_NAME.POS,
		system = "webhard",
		version = 1.0)
public class WsScimPos extends WsScimResource implements ScimGroup {

	@OneToMany(mappedBy = "pos",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WsScimUser> users;
	
	@SuppressWarnings("unchecked")
	@Override
	public WsScimMeta getMeta() {
		return new WsScimMeta("POS",this.created,this.lastModified, this.expire, this.active);
	}

	public String toString(boolean pretty) {
		String json = "";
		try {
			WsScimPosJsonBinder binder = new WsScimPosJsonBinder();
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
		if (resource instanceof WsScimPos) {
			WsScimPos new_pos = (WsScimPos) resource;
			this.id				= new_pos.id;
			this.name    		= new_pos.name;
			this.created 		= new_pos.created;
			this.lastModified 	= new_pos.lastModified;
			this.expire         = new_pos.expire;
			this.active			= new_pos.active;
		}
	}
}
