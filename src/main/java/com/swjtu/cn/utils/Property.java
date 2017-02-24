package com.swjtu.cn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Property {
	static String filename = "config.properties";
	static Properties pro = new Properties();
	static File f = null;
	static{
			try {
				f = new File(Property.class.getClassLoader().getResource(filename).getFile());
				InputStream in = new FileInputStream(f);
				pro.load(in);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static String getproperty(String name){
		String value = pro.getProperty(name);
		return value;
	}
	
	public static void setproperty(String name,String value) throws IOException{
		pro.setProperty(name, value);
		OutputStream out = new FileOutputStream(f);
		pro.store(out, "123");
	}
	
	public static void main(String[] args) {
		System.out.println(getproperty("recommend"));
		try {
			setproperty("recommend", "20");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
