package com.swjtu.cn.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyRead {
	//static ResourceBundle rb = ResourceBundle.getBundle("config");
	static Properties pro = new Properties();
	static InputStream in = null;
	static String path = /*PropertyRead.class.getClassLoader().getResource("").getPath()*/"/config.properties";
	static {
		//in = PropertyRead.class.getClassLoader().getResourceAsStream("/config.properties");
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pro.load(in);
		} catch (IOException e) {
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
		OutputStream out=new FileOutputStream(path);
		pro.store(out, "gsd");
	}
	//包含ip和项目名
	public static String projecturl(){
		String ip = pro.getProperty("ip");
		String projectname = pro.getProperty("projectname");
		return ip + "/" + projectname;
	}
	
	public static String getrecommend(){
		return getproperty("recommend");
	}
	public static void main(String[] args) {
		System.out.println(getrecommend());
		try {
			setproperty("recommend", "20");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
