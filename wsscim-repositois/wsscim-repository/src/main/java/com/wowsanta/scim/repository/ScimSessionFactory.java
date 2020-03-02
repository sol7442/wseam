
package com.wowsanta.scim.repository;


public abstract class ScimSessionFactory {
	private static ScimSessionFactory instance = null;
	public static ScimSessionFactory getInstance() {
		return instance;
	}
	public static void setInstance(ScimSessionFactory factory) {
		instance = factory;
	}
	
	public abstract ScimSession openSession();
	public abstract void close();
}
