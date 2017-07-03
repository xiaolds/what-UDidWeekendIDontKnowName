package com.lids;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncodeClass {
	
	private static final String DEFAULT_SRC_PATH = "/Users/weiqiuhan/Documents/lids/eclipse-work/ClassLoaderDemo/bin/";
	private static final String DEFAULT_DES_PATH = "/Users/weiqiuhan/Documents/lids/eclipse-work/ClassLoaderDemo/encode-bin/";


	/********Added By Lids at Home */
	
	public static void main(String[] args) throws IOException {
		EncodeClass e = new EncodeClass();
		e.encode(DEFAULT_SRC_PATH, DEFAULT_DES_PATH);
	}

	/********Added By Lids at Home */
	
	/**
	 * encode Class file.
	 * @param srcPath
	 * @param desPath
	 * @throws IOException 
	 */
	public void encode(String srcPath, String desPath) throws IOException {
		// search the source path
		File srcPathFile = new File(srcPath);	// /src
		File desPathFile = new File(desPath);	// /bin
		
		if(!srcPathFile.exists() || !srcPathFile.isDirectory()) {
			throw new IOException("Source path is not exist.");
		}
		
		if(!desPathFile.exists()) {
			desPathFile.mkdirs();
		}
		
		File[] files = srcPathFile.listFiles();
		if(null == files) {
			return;
		}
		
		for(File subFile: files) {
			if(subFile.isDirectory()) {
				// directory
				encode(subFile.getAbsolutePath(), desPath + "/" + subFile.getName());
			} else {
				if(subFile.getName().endsWith(".class")) {
					encodeFile(subFile, new File(desPath , subFile.getName().replaceAll(".class", ".enclass")));
				}
			}
		}
		
	}
	
	
	private void encodeFile(File srcFile, File desFile) {
		
		try (
				FileInputStream in = new FileInputStream(srcFile);
				FileOutputStream out = new FileOutputStream(desFile);
				){
			
			int count = 0;
			byte[] b = new byte[1024];
			
			while((count = in.read(b)) != -1) {
				encodeByte(b);
				out.write(b, 0, count);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private void encodeByte(byte[] buf) {
		
		for(int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (buf[i] ^ 2);
		}
		
	}
	

}
