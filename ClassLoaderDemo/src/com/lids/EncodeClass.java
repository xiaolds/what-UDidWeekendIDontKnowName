package com.lids;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncodeClass {
	
	private static final String DEFAULT_SRC_PATH = "D:/WorkStation/me16/websure61/target/ROOT/";
	private static final String DEFAULT_DES_PATH = "D:/WorkStation/me16/websure61/target/en-ROOT/";
/*	private static final String DEFAULT_SRC_PATH = "D:/WorkStation/me16/Test/WebRoot/WEB-INF/classes/";
	private static final String DEFAULT_DES_PATH = "D:/WorkStation/me16/Test/WebRoot/WEB-INF/classes/";
*/

	
	public static void main(String[] args) throws IOException {
		EncodeClass e = new EncodeClass();
		e.encode(DEFAULT_SRC_PATH, DEFAULT_DES_PATH);
	}

	
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
				else {
					Files.copy(Paths.get(subFile.getAbsolutePath()), Paths.get(desPath, subFile.getName()));
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
			return ;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		srcFile.delete();
		
	}
	
	
	private void encodeByte(byte[] buf) {
		
		for(int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (buf[i] ^ 2);
		}
		
	}
	

}
