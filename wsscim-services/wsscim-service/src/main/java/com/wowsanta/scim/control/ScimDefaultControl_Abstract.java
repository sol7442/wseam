package com.wowsanta.scim.control;

import com.wowsanta.scim.entity.ScimEntityHandler;

public class ScimDefaultControl_Abstract {
	public static final String NAME = "SCIM_DEFAULT_CONTROL";
	protected ScimEntityHandler entityInfo;
	public void setEntityInfo(ScimEntityHandler info) {
		this.entityInfo = info;
	}
}
