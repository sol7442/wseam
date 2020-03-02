package com.wowsanta.scim.hibernate;

import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.entity.ScimEntityInfo;
import com.wowsanta.scim.repository.ScimRepositoryException;
import com.wowsanta.scim.repository.ScimRepositoryType;
import com.wowsanta.scim.service.ScimServiceManager;

public class HibernateConfiguration{
	transient Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);
	
	private String driver;
	private String url;
	private String user;
	private String pass;
	private String dialect;
	private String showsql;
	private String formatsql;
	private String hbm2ddlauto;
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String isShowsql() {
		return showsql;
	}

	public void setShowsql(String showsql) {
		this.showsql = showsql;
	}

	public String isFormatsql() {
		return formatsql;
	}

	public void setFormatsql(String formatsql) {
		this.formatsql = formatsql;
	}

	public String getHbm2ddlauto() {
		return hbm2ddlauto;
	}

	public void setHbm2ddlauto(String hbm2ddlauto) {
		this.hbm2ddlauto = hbm2ddlauto;
	}

	public static HibernateConfiguration load(String config_file) throws ScimRepositoryException {
		try {
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
			JsonReader reader = new JsonReader(new FileReader(config_file));
			return gson.fromJson(reader, HibernateConfiguration.class);
		} catch (Exception e) {
			throw new ScimRepositoryException("HibernateConfiguration Load Error : ", e);
		}
	}


	public SessionFactory build() throws ScimRepositoryException {
		SessionFactory sessionFactory = null;
		try {
			Configuration config = new Configuration();
			Properties properties = new Properties();
			properties.put(Environment.DRIVER,this.driver);	
			properties.put(Environment.URL,this.url);
			properties.put(Environment.USER,this.user);
			properties.put(Environment.PASS,this.pass);
			properties.put(Environment.DIALECT,this.dialect);
			properties.put(Environment.SHOW_SQL,this.showsql);
			properties.put(Environment.FORMAT_SQL,this.formatsql);
			properties.put(Environment.HBM2DDL_AUTO,this.hbm2ddlauto);
			properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
			config.setProperties(properties);
			
			logger.info("REGIST REPOSITORY - [{}] ",ScimRepositoryType.HIBERNATE);
			
			Set<Entry<String,ScimEntityHandler>> entry_set = ScimServiceManager.getInstance().getHandlerSet();
			for (Entry<String,ScimEntityHandler> entry : entry_set) {
				ScimEntityInfo entry_info = entry.getValue().getEntity();
				if(entry_info != null) {
					entry_info.getEntityClass();
					logger.info("    ENTITY {} - {}", entry.getKey(), entry_info.getEntityName());
					config.addAnnotatedClass(entry_info.getEntityClass());
				}
			}
			sessionFactory = config.buildSessionFactory();
		}catch (Exception e) {
			throw new ScimRepositoryException("Repository Build Error ",e);
		}
		
		return sessionFactory;
	}
}
