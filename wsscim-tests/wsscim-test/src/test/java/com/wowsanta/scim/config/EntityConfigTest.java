package com.wowsanta.scim.config;

import org.junit.Test;

import com.wowsanta.hibernate.scim.repository.WsScimOrgRepository;
import com.wowsanta.scim.provider.model.WsScimOrg;
import com.wowsanta.scim.repository.ScimEntityConfiguration;
import com.wowsanta.scim.repository.ScimEntityInfo;
import com.wowsanta.scim.repository.ScimEntityType;

public class EntityConfigTest {
	String fileName = "../../config/provider-entity-scim.json";
	
	@Test
	public void load_modify_save_test() {
		ScimEntityConfiguration config = ScimEntityConfiguration.load(fileName);//();
		
		create(config);
		System.out.println(config.toString());
		
		config.save(fileName);
	}

	private void create(ScimEntityConfiguration config) {
		config.setName("provider-scim-entity");
		
		config.putEntity(ScimEntityType.SCIM_ORG, create_org_entity());
		
	}

	private ScimEntityInfo create_org_entity() {
		ScimEntityInfo entity_info = new ScimEntityInfo();
		entity_info.setEntityClass(WsScimOrg.class.getName());
		entity_info.setRepositoryClass(WsScimOrgRepository.class.getName());
		return entity_info;
	}
}
