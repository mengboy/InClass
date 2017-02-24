package com.swjtu.cn.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zhixin Zhang
 * create on 2015年9月26日
 * 
 */
public class TimeUtil {
	public static String gettime(){
		Date date=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=f.format(date);
		return time;
	}
	public static Date getdate(){
		Date date=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=f.format(date);
		try {
			return f.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Date();
		}
		
	}
	
	public static Timestamp gettimestamp(){
		Date date = new Date();
		Timestamp t = new java.sql.Timestamp(date.getTime());
		return t;
	}
	public static String getfromtimestatmp(){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = f.format(gettimestamp());
		return time;
	}
	public static void main(String[] args) {
		System.out.println(getfromtimestatmp());
	}
}
