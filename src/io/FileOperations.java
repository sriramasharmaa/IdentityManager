package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static byte[] read(File a) throws Exception{
		BufferedInputStream fp = new BufferedInputStream(new FileInputStream(a));
		byte[] bf = new byte[1024] ;
		byte[] result = new byte[(int)a.length()];
		int bytesRead=0;
		int totalBytesRead = 0;
		int bytesRemaining =0;
		while(totalBytesRead<a.length()){
			bytesRemaining = (int)a.length()-totalBytesRead;
			bytesRead=fp.read(result,totalBytesRead, bytesRemaining);
			if(bytesRead>0){
				totalBytesRead+= bytesRead;
			}
		}
		fp.close();
		return result;
		
	}
	
	public static int write(File a,byte[] str) throws Exception{
		OutputStream out = new BufferedOutputStream(new FileOutputStream(a));
		out.write(str);
		out.close();
		return 1;
	}

}
