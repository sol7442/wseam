package com.wowsanta.scim.annotation;


import org.junit.Test;
import com.wowsanta.scim.service.ScimServiceManager;

public class AnnotrationTest {

	private String target_directory = "C:\\Work\\wsscim.git\\wsscim\\wsscim-samples\\wsscim-sample-provider-spark-hibernate\\bin\\main";
	//@Test
	public void find_class_path() {
		String classpath = System.getProperty("java.class.path");
        String[] paths = classpath.split(System.getProperty("path.separator"));
        for (String path : paths) {
			System.out.println("path : " +  path);
		}
        
        
	}
	@Test
	public void find_class_test() {
		ScimAnnotationManager mgr = ScimAnnotationManager.getInstance();
		mgr.setClassDirectory(target_directory);
		
		mgr.addDistLibFile("../../dist/wsscim-common-service-1.0.0.jar");
		mgr.addDistLibFile("../../dist/wsscim-provider-service-1.0.0.jar");
		mgr.addDistLibFile("../../dist/wsscim-scim-service-1.0.0.jar");
		//mgr.addHandler(new ScimJsonAnnotatonHandler());
		mgr.addHandler(new ScimEntityAnnotatonHandler());
		//mgr.addHandler(new ScimRepositoryAnnotatonHandler());
		mgr.addHandler(new ScimControlAnnotationHandler());
		//mgr.addHandler(new ScimServiceAnnotatonHandler());
		
		mgr.run();
		
		System.out.println(ScimServiceManager.getInstance().toString(true));
		
//		ScimHandler info = ScimServiceManager.getInstance().getService("ScimUser");
//		System.out.println(info.getJson("localhost").getParerFile());
//		System.out.println(ScimJsonManager.getInstance().toString(true));
//		System.out.println(ScimEntityManager.getInstance().toString(true));
//		System.out.println(ScimRepositoryManager.getInstance().toString(true));
//		System.out.println(ScimEntityServiceManager.getInstance().toString(true));
	}
	
	
	
	
	
	
//	
//	
//	//@Test
//	public void find_class() {
//        String classpath = System.getProperty("java.class.path");
//        String[] paths = classpath.split(System.getProperty("path.separator"));
//        for (String path : paths) {
//        	File file = new File(path);
//            if (file.exists()) {
//            	if(file.isDirectory()) {
//            		try {
//            			System.out.println(file.getCanonicalPath());
//            			findClass(file,file);
//    				} catch (IOException e) {
//    					e.printStackTrace();
//    				}
//            	}
//            	
//            }
//        }
//	}
//	private boolean findClass(File root ,File file) {
//		if (file.isDirectory()) {
//            for (File child : file.listFiles()) {
//                if (!findClass(root,child)) {
//                    return false;
//                }
//            }
//        }else {
//        	if(file.getName().endsWith(".class")) {
//        		String class_name = createClassName(root,file);
//        		System.out.println(" - : " +  class_name);
//        	}
//        }
//		return true;
//	}
//	private String createClassName(File root, File file) {
//		StringBuffer sb = new StringBuffer();
//        String fileName = file.getName();
//        sb.append(fileName.substring(0, fileName.lastIndexOf(".class")));
//        file = file.getParentFile();
//        while (file != null && !file.equals(root)) {
//            sb.insert(0, '.').insert(0, file.getName());
//            file = file.getParentFile();
//        }
//        return sb.toString();
//		
//	}
//	public void ceate_config() throws IOException {
//		
////	       Field f;
////	        try {
////	            f = ClassLoader.class.getDeclaredField("classes");
////	            f.setAccessible(true);
////	            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
////	            Vector<Class> classes =  (Vector<Class>) f.get(classLoader);
////
////	            for(Class cls : classes){
////	            	System.out.println(cls.getName());
////					SCIM_JSON annotation = (SCIM_JSON) cls.getAnnotation(SCIM_JSON.class);
////					if(annotation != null) {
////						System.out.println(annotation.type());
////						System.out.println(annotation.entity());
////						System.out.println(cls.getName());
////					}
////	            	
//////	                java.net.URL location = cls.getResource('/' + cls.getName().replace('.', '/') + ".class");
//////	                System.out.println("<p>"+location +"<p/>");
////	            }
////	        } catch (Exception e) {
////	            e.printStackTrace();
////	        }
//	        
////		System.out.println(System.getProperty("system.classpath"));;
////		System.out.println(System.getProperty("cp"));;
//		Properties sys_prop = System.getProperties();
////		System.out.println(sys_prop);
////		
//		Set<Entry<Object,Object>> props = sys_prop.entrySet();
//		for (Entry<Object, Object> entry : props) {
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
//		
//		
//		
//		PathMatcher bin_matcher = FileSystems.getDefault().getPathMatcher("glob:**/bin/main/**");
//	    Files.walkFileTree(Paths.get("../../"), new SimpleFileVisitor<Path>() {
//	        @Override
//	        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
//	        	if(bin_matcher.matches(path)) {
//	        		int start_index = path.toString().lastIndexOf("\\bin\\main\\");
//	        		int end_index = path.toString().lastIndexOf(".class");
//	        		String class_file = path.toString().substring(start_index + 10,end_index);
//	        		String class_name = class_file.replace("\\",".");
//	        		try {
//						Class clazz = Class.forName(class_name);
//						SCIM_JSON annotation = (SCIM_JSON) clazz.getAnnotation(SCIM_JSON.class);
//						if(annotation != null) {
//							System.out.println(annotation.type());
//							System.out.println(annotation.entity());
//							System.out.println(clazz.getName());
//						}
//						
//					} catch (ClassNotFoundException e) {
//						e.printStackTrace();
//					}
//	        	}
//	            return FileVisitResult.CONTINUE;
//	        }
//	    });
//	    
//	}
}
