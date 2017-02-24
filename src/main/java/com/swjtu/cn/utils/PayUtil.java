package com.swjtu.cn.utils;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;

public class PayUtil {
	public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Pingpp.apiKey = "sk_test_yXjTKGO88mrHzfnrH4HG0C48";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", "123456789");
		chargeParams.put("amount", 10000);
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", "app_nXv9q9uzDu101uDO");
		chargeParams.put("app", app);
		chargeParams.put("channel", "alipay");
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", "127.0.0.1");
		chargeParams.put("subject", "Your Subject");
		chargeParams.put("body", "Your Body");
		Charge charge=Charge.create(chargeParams);
		System.out.println(charge);
		
	}
}
