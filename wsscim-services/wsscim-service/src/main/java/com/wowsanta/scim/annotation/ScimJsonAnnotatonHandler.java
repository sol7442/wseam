package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.scim.annotation.SCIM_JSON;
import com.wowsanta.scim.annotation.ScimAnnotationHandler;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.json.ScimJsonInfo;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.type.SCIM_JSON_TYPE;

@SuppressWarnings({"rawtypes","unchecked"})
public class ScimJsonAnnotatonHandler implements ScimAnnotationHandler{
	@Override
	public boolean visit(File file, String class_name) {
		try {
			Class clazz = Class.forName(class_name);
			SCIM_JSON json_annotation = (SCIM_JSON) clazz.getAnnotation(SCIM_JSON.class);
			if(json_annotation != null) {
				String entity_name = json_annotation.entity();
				String json_type   = json_annotation.type();
				String json_target   = json_annotation.target();
				
				
				ScimEntityHandler handler = ScimServiceManager.getInstance().getHandler(entity_name);
				if(handler == null) {
					handler = new ScimEntityHandler();
					ScimServiceManager.getInstance().addHandler(entity_name, handler);
				}
				
				ScimJsonInfo json_info = handler.getJson(json_target) ;
				if(json_info == null) {
					json_info = new ScimJsonInfo();
					handler.setJson(json_target, json_info);
				}
				
				Class json_class = Class.forName(class_name);
				switch(json_type) {
				case SCIM_JSON_TYPE.BINDER:
					json_info.setBinderFile(file.getCanonicalPath());
					json_info.setBinderName(class_name);
					json_info.setBinderClass(json_class);
					
					break;
				case SCIM_JSON_TYPE.PARSER:
					json_info.setParserFile(file.getCanonicalPath());
					json_info.setParserName(class_name);
					json_info.setParserClass(json_class);
					break;
				}
			}
			
			return true;
		}catch (Exception e) {
			System.out.println(class_name + " : " + e.getMessage());
		}
		return false;
	}

}
