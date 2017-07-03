package com.lids;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Customer ClassLoader. Load class file from disk.
 * @author weiqiuhan
 *
 */
public class DiskClassLoader extends ClassLoader {
	
	public static final String CLASS_SUFFIX = ".enclass";
	/**Default Path**/
	private static final String DEFAULT_LOAD_PATH = "/Users/weiqiuhan/Documents/lids/eclipse-work/ClassLoaderDemo/encode-bin/";

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		// 1. get file name
		String className = getName(name);
		
		try(BufferedInputStream in = 
				new BufferedInputStream(
						new FileInputStream(DEFAULT_LOAD_PATH + className));
			ByteArrayOutputStream out = new ByteArrayOutputStream();	
				){
			
			// read class file and write to Byte
			int c = 0;
			while((c = in.read()) != -1) {
				out.write((char)c ^ 2);
			}
			
			byte[] classBytes = out.toByteArray();
			
			System.out.println("Load customer successful~");
			return defineClass(name, classBytes, 0, classBytes.length);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return super.findClass(name);
	}
	
	
	private String getName(String fullName) {
		
		if(null == fullName) {
			return null;
		}
		
		/*int dotIndex = fullName.lastIndexOf(".");
		if(-1 == dotIndex) {
			return fullName + CLASS_SUFFIX;
		} else {
			return fullName.substring(dotIndex + 1) + CLASS_SUFFIX;
		}*/
		return fullName.replace(".", "/") + CLASS_SUFFIX;
		
	}
	
	

}
