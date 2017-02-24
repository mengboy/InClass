package com.swjtu.cn.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.swjtu.cn.common.Status;


public class ActionUtil {

	public static final String SESSION_USER = "USER";
	public static final String SESSION_ADMIN = "ADMIN";
	public static final String SESSION_USER_LOGOUT = "USER_LOGOUT";// 用户登出
	public static final String SESSION_USER_ACTIVATION_KEY = "USER_ACTIVATION_KEY";
	public static final String SESSION_USER_RESETPASSWORD_KEY = "USER_RESETPASSWORD_KEY";

	public static BasicUser getCurrentUser(HttpServletRequest request) {
		BasicUser user = (BasicUser) request.getSession().getAttribute(
				SESSION_USER);
		return user;
	}

	public static void setCurrentUser(HttpServletRequest request, BasicUser user) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_USER, user);
		session.setAttribute(SESSION_USER_LOGOUT, false);
	}
	
	public static BasicUser getCurrentAdmin(HttpServletRequest request) {
		BasicUser user = (BasicUser) request.getSession().getAttribute(
				SESSION_ADMIN);
		return user;
	}
	
	public static void setCurrentAdmin(HttpServletRequest request, BasicUser user) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_ADMIN, user);
		session.setAttribute(SESSION_USER_LOGOUT, false);
	}

	public static void UserLogout(HttpServletRequest request){
		request.getSession().removeAttribute(SESSION_USER);
	}
	
	public static void AdminLogout(HttpServletRequest request){
		request.getSession().removeAttribute(SESSION_ADMIN);
	}

	public static boolean getLogout(HttpServletRequest request) {
		Object r = request.getSession().getAttribute(SESSION_USER_LOGOUT);
		return r != null && (Boolean) r;
	}

	public static void setActivationKey(HttpServletRequest request, String key) {
		request.getSession().setAttribute(SESSION_USER_ACTIVATION_KEY, key);
	}

	public static String getActivationKey(HttpServletRequest request) {
		String key = (String) request.getSession().getAttribute(
				SESSION_USER_ACTIVATION_KEY);
		return key;
	}

	public static void removeActivationKey(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_USER_ACTIVATION_KEY);
	}

	public static void setResetPasswordKey(HttpServletRequest request,
			String key) {
		request.getSession().setAttribute(SESSION_USER_RESETPASSWORD_KEY, key);
	}

	public static String getResetPasswordKey(HttpServletRequest request) {
		String key = (String) request.getSession().getAttribute(
				SESSION_USER_RESETPASSWORD_KEY);
		return key;
	}

	public static void removeResetPasswordKey(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_USER_RESETPASSWORD_KEY);
	}

	public static void setPhoneCode(HttpServletRequest request, String msg,
			String phone) {
		HttpSession session = request.getSession();
		session.setAttribute("PhoneCode", new PhoneCode(msg, phone));
	}

	public static int validPhoneCode(HttpServletRequest request, String msg) {
		HttpSession session = request.getSession();
		PhoneCode pc = (PhoneCode) session.getAttribute("PhoneCode");
		long difTime = (new Date()).getTime() - pc.getTime().getTime();
		System.out.println("蛤蛤：："+difTime);
		if (difTime < (5 * 60 * 1000)) {
			if (msg.equals(pc.getCode())) {
				System.out.println("蛤蛤：："+"success");
				return Status.SUCCESS;
			}
			System.out.println("蛤蛤：："+"error");
			return Status.ERROR;
		} else {
			return Status.TIMEOUT;
		}

	} 

	public static void main(String str[]){
		System.out.println(5 * 60 * 1000);
	}
	public static String getPhone(HttpServletRequest request) {
		HttpSession session = request.getSession();
		PhoneCode pc = (PhoneCode) session.getAttribute("PhoneCode");
		return pc.getPhone();
	}

}

class PhoneCode {
	private String code;
	private String phone;
	private Date time;

	public PhoneCode(String msg, String phone) {
		this.code = msg;
		this.setPhone(phone);
		this.time = new Date();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}