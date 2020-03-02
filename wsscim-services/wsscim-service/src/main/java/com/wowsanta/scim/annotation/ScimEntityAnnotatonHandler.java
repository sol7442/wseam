package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.annotation.ScimAnnotationHandler;
import com.wowsanta.scim.control.ScimControlInfo;
import com.wowsanta.scim.control.ScimDefaultControl_Read;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimEntityInfo;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.service.ScimServiceUrl;

@SuppressWarnings({"rawtypes","unchecked"})
public class ScimEntityAnnotatonHandler implements ScimAnnotationHandler{
	
	@Override
	public boolean visit(File file, String class_name) {
		try {
			Class clazz = Class.forName(class_name);
			SCIM_ENTITY entity_annotation = (SCIM_ENTITY) clazz.getAnnotation(SCIM_ENTITY.class);
			if(entity_annotation != null) {
				String entity_type = entity_annotation.type();
				String entity_name = entity_annotation.name();
				String entity_system = entity_annotation.system();
				
				Class entity_class = Class.forName(class_name);
				
				ScimEntityInfo entity_info = new ScimEntityInfo();
				entity_info.setEntityType(entity_type);
				entity_info.setEntityName(class_name);
				entity_info.setEntityFile(file.getCanonicalPath());
				entity_info.setEntityClass(entity_class);

				if(entity_system.equals("")) {
					ScimControlInfo control_info = new ScimControlInfo();
					control_info.setPath(ScimServiceUrl.getUrl(entity_info.getEntityType()   ,":id"));
					control_info.setMethod("get");
					control_info.setName("read");
					control_info.setControlName(ScimDefaultControl_Read.class.getName());
					ScimServiceManager.getInstance().addHandler(control_info);
				}else {
					System.out.println(entity_system + " : ");
				}
				
//				control_info.setControlName(class_name);
//				control_info.setControlFile(file.getCanonicalPath());
//				control_info.setControlClass(control_class);
				
//				ScimEntityHandler handler = ScimServiceManager.getInstance().getHandler(entity_name);
//				if(handler == null) {
//					handler = new ScimEntityHandler();
//					ScimServiceManager.getInstance().addHandler(entity_name, handler);
//				}
//				handler.putControl(control_name, control_info);
				
				
				
//				ScimEntityHandler handler = ScimServiceManager.getInstance().getHandler(entity_name);
//				if(handler == null) {
//					handler = new ScimEntityHandler();
//					ScimServiceManager.getInstance().addHandler(entity_name, handler);
//				}
//				handler.setEntity(entity_info);
			}
			return true;
		}catch (Exception e) {
			System.out.println(class_name + " : " + e.getMessage());
		}
		return false;
	}

}
