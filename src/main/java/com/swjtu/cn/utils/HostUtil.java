package com.swjtu.cn.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhixin Zhang
 *2015年12月6日  下午2:12:05
 */
public class HostUtil {
	public static String geturl(HttpServletRequest request){
		try {
			InetAddress netAddress = InetAddress.getLocalHost();
//			String ip = netAddress.getHostAddress();//主机ip
			String ip = request.getServerName();//主机ip
			int port = request.getLocalPort();//端口号
			String project_name = request.getContextPath();//项目名
			String curUrl = request.getRequestURL().toString();
			String uri = request.getRequestURI();//返回请求行中的资源名称
			String server_url = curUrl.substring(0, (curUrl.length()-uri.length())); 
//			return "http://"+ip+":"+port+project_name+"/";
			return server_url+project_name+"/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "http://www.in-class.cn/";
		}
	}
}
