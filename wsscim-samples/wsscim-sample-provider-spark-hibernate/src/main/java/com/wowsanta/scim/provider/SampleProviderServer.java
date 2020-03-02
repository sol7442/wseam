package com.wowsanta.scim.provider;

public class SampleProviderServer {

	public static void main(String[] args) {
		String classpath = System.getProperty("java.class.path");
        String[] paths = classpath.split(System.getProperty("path.separator"));
        for (String class_path : paths) {
			System.out.println(class_path);
			
			
		}
	}

}
