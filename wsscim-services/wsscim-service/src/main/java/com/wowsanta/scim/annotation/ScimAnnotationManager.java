package com.wowsanta.scim.annotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScimAnnotationManager {
	Logger logger = LoggerFactory.getLogger(ScimAnnotationManager.class);
	
	private static ScimAnnotationManager instance = null;
	
	private String classDirectory;
	private List<String> distLibFiles;
	
	private List<ScimAnnotationHandler> handlers = new ArrayList<ScimAnnotationHandler>();
	public static ScimAnnotationManager getInstance() {
		if(instance  == null) {
			instance = new ScimAnnotationManager();
		}
		return instance;
	}
	public void addHandler(ScimAnnotationHandler handler) {
		this.handlers.add(handler);
	}
	
	public void run() {
        File file = new File(this.classDirectory);
        findClassFileFromDir(file,file);
        
        for (String jar_file : this.distLibFiles) {
        	File jar = new File(jar_file);
        	findClassFileFromJar(jar);
		}
	}

	private void findClassFileFromJar(File file) {
		logger.info("jar file : {} " ,  file);
		JarFile jar = null;
        try {
        	jar = new JarFile(file);
        	Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                
                if(name.endsWith("class")) {
                	String class_name = name.substring(0, name.length() - 6).replaceAll("/|\\\\", "\\.");
                    for (ScimAnnotationHandler handler : handlers) {
    					if(handler.visit(file,class_name)) {
    						
    					}
            		}
                }
            }
        	
        } catch (Exception e) {
        	logger.error("{}",file,e);
        }finally {
			if(jar != null) {
				try {
					jar.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	private boolean findClassFileFromDir(File root ,File file) {
		if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                if (!findClassFileFromDir(root,child)) {
                    return false;
                }
            }
        }else {
        	if(file.getName().endsWith(".class")) {
        		String class_name = createClassName(root,file);
        		for (ScimAnnotationHandler handler : handlers) {
        			if(handler.visit(file,class_name)) {
						
					}
        		}
        	}else if(file.getName().endsWith(".jar")) {
        		findClassFileFromJar(file);
        	}
        }
		return true;
	}
	private String createClassName(File root, File file) {
		StringBuffer sb = new StringBuffer();
        String fileName = file.getName();
        sb.append(fileName.substring(0, fileName.lastIndexOf(".class")));
        file = file.getParentFile();
        while (file != null && !file.equals(root)) {
            sb.insert(0, '.').insert(0, file.getName());
            file = file.getParentFile();
        }
        return sb.toString();
	}
	
	private List<String> findClassPath() {
		String classpath = System.getProperty("java.class.path");
        String[] paths = classpath.split(System.getProperty("path.separator"));
		return Arrays.asList(paths);
	}
	public String getClassDirectory() {
		return classDirectory;
	}
	public void setClassDirectory(String classDirectory) {
		this.classDirectory = classDirectory;
	}
	public void addDistLibFile(String file) {
		if(this.distLibFiles == null) {
			this.distLibFiles = new ArrayList<String>();
		}
		this.distLibFiles.add(file);
	}
	public List<String> getDistLibFiles() {
		return distLibFiles;
	}
}
