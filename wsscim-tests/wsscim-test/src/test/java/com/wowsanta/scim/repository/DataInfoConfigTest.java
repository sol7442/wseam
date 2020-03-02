package com.wowsanta.scim.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.wowsanta.hibernate.scim.extra.WsScimAttribute;
import com.wowsanta.hibernate.scim.extra.WsScimJob;
import com.wowsanta.hibernate.scim.extra.WsScimOrg;
import com.wowsanta.hibernate.scim.extra.WsScimPos;
import com.wowsanta.hibernate.scim.extra.WsScimUser;
import com.wowsanta.hibernate.scim.json.WsUserJsonAdapter;
import com.wowsanta.scim.hibernate.basic.WsAttributeJsonAdapter;
import com.wowsanta.scim.hibernate.basic.WsJobJsonAdapter;
import com.wowsanta.scim.hibernate.basic.WsPosJsonAdapter;
import com.wowsanta.scim.object.extra.ScimExtObjectType;
import com.wowsanta.scim.repository.jpa.HibernateRepository;

public class DataInfoConfigTest {
	String fileName  = "../../config/data.info.json";
	@Test
	public void load_modify_save_test() {
		try {
			Map<String, ScimEntityInfo> data_info_map = create();
			println(data_info_map);
			
			
			
			save(data_info_map);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Map<String, ScimEntityInfo> load() {
		Map<String, ScimEntityInfo> data_map = null;
		try {
			File file = new File(fileName);
			Gson gson = new GsonBuilder().create();
			JsonReader reader = new JsonReader(new FileReader(file));
			
			TypeToken<Map<String, ScimEntityInfo>> typeToken = new TypeToken<Map<String, ScimEntityInfo>>() {};
			data_map = gson.fromJson(reader,typeToken.getType());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return data_map;
	}
	private Map<String, ScimEntityInfo> create() {
		ScimEntityInfo user_info = new ScimEntityInfo();
		user_info.setEntityClassName(WsScimUser.class.getCanonicalName());
		user_info.setRepositoryClassName(HibernateRepository.class.getCanonicalName());
		user_info.setJsonClassName(WsUserJsonAdapter.class.getCanonicalName());
		user_info.addRefDataInfo(ScimExtObjectType.ATTRIBUTE);
		
		
		ScimEntityInfo attr_info = new ScimEntityInfo();
		attr_info.setEntityClassName(WsScimAttribute.class.getCanonicalName());
		attr_info.setRepositoryClassName(HibernateRepository.class.getCanonicalName());
		attr_info.setJsonClassName(WsAttributeJsonAdapter.class.getCanonicalName());
		attr_info.addRefDataInfo(ScimExtObjectType.ORG);
		attr_info.addRefDataInfo(ScimExtObjectType.POS);
		attr_info.addRefDataInfo(ScimExtObjectType.JOB);
		
		ScimEntityInfo org_info = new ScimEntityInfo();
		org_info.setEntityClassName(WsScimOrg.class.getCanonicalName());
		org_info.setRepositoryClassName(HibernateRepository.class.getCanonicalName());
		org_info.setJsonClassName(WsPosJsonAdapter.class.getCanonicalName());
		
		ScimEntityInfo pos_info = new ScimEntityInfo();
		pos_info.setEntityClassName(WsScimPos.class.getCanonicalName());
		pos_info.setRepositoryClassName(HibernateRepository.class.getCanonicalName());
		pos_info.setJsonClassName(WsPosJsonAdapter.class.getCanonicalName());
		
		ScimEntityInfo job_info = new ScimEntityInfo();
		job_info.setEntityClassName(WsScimJob.class.getCanonicalName());
		job_info.setRepositoryClassName(HibernateRepository.class.getCanonicalName());
		job_info.setJsonClassName(WsJobJsonAdapter.class.getCanonicalName());
		
		
		Map<String, ScimEntityInfo> data_info_map = new HashMap<String,ScimEntityInfo>();
		data_info_map.put(ScimExtObjectType.USER		,user_info);
		data_info_map.put(ScimExtObjectType.ATTRIBUTE	,attr_info);
		data_info_map.put(ScimExtObjectType.ORG		,org_info);
		data_info_map.put(ScimExtObjectType.POS		,attr_info);
		data_info_map.put(ScimExtObjectType.JOB		,attr_info);
		return data_info_map;
	}
	public void println(Map<String, ScimEntityInfo> info_map) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(info_map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void save(Map<String, ScimEntityInfo> info_map) {
		FileWriter writer;
		try {
			writer = new FileWriter(new File(fileName));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(info_map,writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
