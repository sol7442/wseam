package com.wowsanta.scim.provider.model;


import com.wowsanta.scim.entity.ScimAttribute;
import com.wowsanta.scim.entity.ScimGroup;
import com.wowsanta.scim.type.SCIM_ENTITY_NAME;

public class WsScimAttribute implements ScimAttribute {
	
	private WsScimOrg org;
//	private WsScimPos pos;
//	private WsScimJob job;
	@Override
	public void addGroup(ScimGroup group) {
		if (group instanceof WsScimOrg) {
			this.org = (WsScimOrg) group;
		}
	}
	@Override
	public ScimGroup getGroup(String name) {
		switch (name) {
		case SCIM_ENTITY_NAME.ORG:
			return this.org;
		default:
			return null;
		}
	}
}
