package com.wowsanta.scim.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.json.ScimJson;
import com.wowsanta.scim.json.ScimJsonException;



public class UserEntityTest {
	String fileName = "../../config/sample.user.json";
	
	public class user_json implements ScimJson{
		private String[] schema =  {"test.wowsat.a.a.","test.aaaaw.www.ad"};
		private String id   = "id";
		private String name ;
		
		@Override
		public String toJson(boolean pretty) throws ScimJsonException {
			try {
				GsonBuilder builder = new GsonBuilder();
				builder.disableHtmlEscaping();
			    if (pretty) {
			    	builder.setPrettyPrinting();
			    }
				Gson gson = builder.create();
				return gson.toJson(this);
			}catch (Exception e) {
				throw new ScimJsonException("JSON ERROR : ",e);
			}
		}
		
		@Override
		public void fromJson(String json) throws ScimJsonException {
			
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
	@Test
	public void toJsonTest() {
		user_json user = new user_json();
		user.name = "esss";
		try {
			System.out.println(user.toJson(true));
		} catch (ScimJsonException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromJsonTest() {
		
	}
	
	
//	@Test
//	public void load_create_save_test() {
//		try {
//			File file = new File(fileName);
//			if(file.exists()) {
//				Gson gson = new GsonBuilder().create();
//				JsonReader reader = new JsonReader(new FileReader(file));
//				TypeToken<List<WsScimUser>> typeToken = new TypeToken<List<WsScimUser>>() {};
//				List<WsScimUser> user_list = gson.fromJson(reader,typeToken.getType());
//				if(user_list != null) {
//					for (WsScimUser WsScimUser : user_list) {
//						System.out.println(WsScimUser.toJson(false));
//					}
//				}
//				
//				
//				
//			}else {
//				createSample();
//			}
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void createStringDataSample() {
//		List<WsScimUser> user_list = createSample();
//		List<String> user_str_list = new ArrayList<String>();
//		for (WsScimUser user : user_list) {
//			user_str_list.add(user.toJson(false));
//		}
//		
//		FileWriter writer;
//		try {
//			writer = new FileWriter(new File("../../config/sample.user2.json"));
//			Gson gson = new GsonBuilder().registerTypeAdapter(WsScimUser.class, new WsUserJsonAdapter()).setPrettyPrinting().create();
//			gson.toJson(user_list,writer);
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	private List<WsScimUser> createSample() {
//		WsScimUser user1 = new WsScimUser();
//		WsScimUser user2 = new WsScimUser();
//		WsScimMeta meta = new WsScimMeta();
//		meta.setCreated(new Date());
//		meta.setLastModified(new Date());
//		meta.setResourceType(ScimExtObjectType.USER);
//		
//		user1.setId("sample1");
//		user1.setMeta(meta);
//		user2.setId("sample2");
//		user2.setMeta(meta);
//		
//		System.out.println("create sample 2");
//		System.out.println(user1.toJson(false));
//		System.out.println(user2.toJson(false));
//		
//		List<WsScimUser> user_list = new ArrayList<WsScimUser>();
//		user_list.add(user1);
//		user_list.add(user2);
//		
//		return user_list;
//		
////		FileWriter writer;
////		try {
////			writer = new FileWriter(new File(fileName));
////			Gson gson = new GsonBuilder().create();
////			gson.toJson(user_list,writer);
////			writer.flush();
////			writer.close();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//	}
}
