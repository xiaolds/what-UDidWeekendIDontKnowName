package com.lids;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestDiskClassLoader {

	
	public static void main(String[] args) {
		
		// ins a customer class loader
		DiskClassLoader loader = new DiskClassLoader();
		String className = "com.lids.CustomerClass";
		
		try {
			Class<?> clazz = loader.loadClass(className);
			Method method = clazz.getMethod("sayHi");
			Object instance = clazz.newInstance();
			method.invoke(instance);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
}
