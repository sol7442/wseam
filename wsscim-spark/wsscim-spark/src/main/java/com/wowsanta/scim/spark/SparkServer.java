package com.wowsanta.scim.spark;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.com.scim.control.WsScimEntityCreateService;
import com.wowsanta.com.scim.control.WsScimEntityDeleteService;
import com.wowsanta.com.scim.control.WsScimEntityReadService;
import com.wowsanta.com.scim.control.WsScimEntityReplaceService;
import com.wowsanta.com.scim.control.WsScimEntitySearchService;
import com.wowsanta.com.scim.control.WsScimEntityUpdateService;
import com.wowsanta.scim.control.ScimControlInfo;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimEntityInfo;
import com.wowsanta.scim.server.ScimServer;
import com.wowsanta.scim.service.ScimServiceClass;
import com.wowsanta.scim.service.ScimServiceManager;
import com.wowsanta.scim.service.ScimServiceMethod;
import com.wowsanta.scim.service.ScimServiceUrl;

import spark.Filter;
import spark.Route;
import spark.RouteGroup;
import spark.Spark;
import spark.route.HttpMethod;
import static spark.Spark.*;

public class SparkServer implements ScimServer {
	Logger logger = LoggerFactory.getLogger(SparkServer.class);
	
	public void initialize(List<String> servies) {
		
		Set<Entry<String,ScimEntityHandler>> entry_sety = ScimServiceManager.getInstance().getHandlerSet();
		for (Entry<String, ScimEntityHandler> entry : entry_sety) {
			String entry_name = entry.getKey();
			ScimEntityHandler service_info = entry.getValue();
			
			ScimEntityInfo entity_info = service_info.getEntity();
			if(entity_info != null) {
				if(entity_info.isPrimitive()) {
					initSparkPrimititveService(entry_name,service_info);
				}
			}
			
			Set<Entry<String, ScimControlInfo>> control_entry_set = service_info.getControlSet();
			if(control_entry_set != null) {
				for (Entry<String, ScimControlInfo> control_entry : control_entry_set) {
					initSparkService(control_entry.getValue());
				}
			}
		}
		
		Spark.init();		
	}
	private void initSparkPrimititveService(String entry_name, ScimEntityHandler service_info) {
		try {
			
			ScimEntityInfo entity_info = service_info.getEntity();
			logger.info("ADD SCIM ENTITY SERVICE : {}",entry_name);
			//create
			post(ScimServiceUrl.getUrl(entity_info.getEntityType()  ,null)  ,new WsScimEntityCreateService(entry_name,service_info));
			//search
			post(ScimServiceUrl.getUrl(entity_info.getEntityType()  ,"")    ,new WsScimEntitySearchService(entry_name,service_info));
			//pacth 
			put(ScimServiceUrl.getUrl(entity_info.getEntityType()   ,":id")	,new WsScimEntityUpdateService(entry_name,service_info));
			//read
			get(ScimServiceUrl.getUrl(entity_info.getEntityType()   ,":id") ,new WsScimEntityReadService(entry_name,service_info));
			//pacth 
			patch(ScimServiceUrl.getUrl(entity_info.getEntityType() ,":id") ,new WsScimEntityReplaceService(entry_name,service_info));
			//delete
			delete(ScimServiceUrl.getUrl(entity_info.getEntityType(),":id")	,new WsScimEntityDeleteService(entry_name,service_info));
		}catch (Exception e) {
			logger.error("spark error : ",e);
		}
	}
	private void initSparkService(ScimControlInfo control_info) {
		try {
			logger.info("init- {}-{}", control_info.getName(),control_info.getControlName());
			
			switch(control_info.getMethod()) {
			case ScimServiceMethod.BEFORE:
				before(control_info.getPath(),newFilter(control_info.getControlClass()));
				break;
			case ScimServiceMethod.AFTER:
				after(control_info.getPath(),newFilter(control_info.getControlClass()));
				break;
			case ScimServiceMethod.POST:
				post(control_info.getPath(),newRouter(control_info.getControlClass()));
				break;
			case ScimServiceMethod.GET:
				get(control_info.getPath(),newRouter(control_info.getControlClass()));
				break;
			case ScimServiceMethod.PATCH:
				patch(control_info.getPath(),newRouter(control_info.getControlClass()));
				break;
			case ScimServiceMethod.PUT:
				put(control_info.getPath(),newRouter(control_info.getControlClass()));
				break;
			case ScimServiceMethod.DELETE:
				delete(control_info.getPath(),newRouter(control_info.getControlClass()));
				break;
			default:
				break;
			}
		}catch (Exception e) {
			logger.error("spark error : ",e);
		}
	}
//	private void initSparkService(Map<String, ScimRestfulInfo> services) {
//		Set<Entry<String, ScimRestfulInfo>> entry_set = services.entrySet();
//		for (Entry<String, ScimRestfulInfo> entry : entry_set) {
//			ScimRestfulInfo service_info = entry.getValue();
//			
//			if(service_info.isGroup()) {
//				logger.debug("{} : ========================= ", entry.getKey());
//				path(service_info.getPath(), new RouteGroup() {
//					@Override
//					public void addRoutes() {
//						initSparkService(service_info.getServices());
//					}
//				});
//			}else {
//				logger.debug("{} ---------------- ", entry.getKey());
//				logger.debug("{} ", service_info.toString(true));
//				String method = service_info.getMethod();
//				try {
//					switch(HttpMethod.valueOf(method)) {
//					case before:
//						before(service_info.getPath(),newFilterClass(service_info.getControlClass()));
//						break;
//					case after:
//						after(service_info.getPath(),newFilterClass(service_info.getControlClass()));
//						break;
//					case post:
//						post(service_info.getPath(), newImpleClass(service_info.getControlClass()));
//						break;
//					case get:
//						get(service_info.getPath(), newImpleClass(service_info.getControlClass()));
//						break;
//					case patch:
//						patch(service_info.getPath(), newImpleClass(service_info.getControlClass()));
//						break;
//					case put:
//						put(service_info.getPath(), newImpleClass(service_info.getControlClass()));
//						break;
//					case delete:
//						delete(service_info.getPath(), newImpleClass(service_info.getControlClass()));
//						break;
//					default:
//						break;
//					}
//				}catch (Exception e) {
//					logger.error("spark error : ",e);
//				}
//			}
//		}
//	}
	
	private Filter newFilter(Class clazz) throws InstantiationException, IllegalAccessException {
		return (Filter) clazz.newInstance();
	}
	private Route newRouter(Class clazz) throws InstantiationException, IllegalAccessException {
		return (Route) clazz.newInstance();
	}
	
	
	private Filter newFilterClass(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (Filter) Class.forName(className).newInstance();
	}

	private Route newImpleClass(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (Route) Class.forName(className).newInstance();
	}
	public void start() {
		Spark.awaitInitialization();
	}
	public void stop() {
		Spark.awaitStop();
	}
	public void setPort(int port) {
		Spark.port(port);
	}
	public void setThreadPool(int max,int min,int idleTime) {
		Spark.threadPool(max, min, idleTime);
	}
	public void setSecurity(SparkSecurity security) {
		
	}
}
