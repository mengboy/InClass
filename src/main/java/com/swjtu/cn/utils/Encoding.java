package com.swjtu.cn.utils;


public class Encoding {
	
	public static String getStr(String str) throws Exception{
		
		if(str.equals(new String(str.getBytes("iso8859-1"), "iso8859-1"))){
			str=new String(str.getBytes("iso8859-1"),"utf-8");
			return str;
		}else if(str.equals(new String(str.getBytes("GB2312"), "GB2312"))){
			str=new String(str.getBytes("GB2312"),"utf-8");
			return str;
		}else if(str.equals(new String(str.getBytes("GBK"), "GBK"))){
			str=new String(str.getBytes("GBK"),"utf-8");
			return str;
		}else if(str.equals(new String(str.getBytes("utf-8"), "utf-8"))){
			str=new String(str.getBytes("utf-8"),"utf-8");
			return str;
		}else
			return str;
		
	}
}
