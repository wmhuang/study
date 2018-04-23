package com.css.etax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.css.etax.service.PublicMethodService;
import com.css.etax.utils.Result;

@Controller
@RequestMapping("/public")
public class PublicMethodController {
	@Autowired
	private PublicMethodService publicMethodService;

	@RequestMapping(value = "/setRequestValue", method = { RequestMethod.POST })
	@ResponseBody
	public String setRequestValue(String key, String value) {
		Result result = publicMethodService.setRequestRedis(key, value);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/getRequestValue", method = { RequestMethod.POST })
	@ResponseBody
	public String getRequestValue(String key) {
		Result result = publicMethodService.getRequestRedis(key);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/setResponseValue", method = { RequestMethod.POST })
	@ResponseBody
	public String setResponseValue(String key, String value) {
		Result result = publicMethodService.setResponseRedis(key, value);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/getResponseValue", method = { RequestMethod.POST })
	@ResponseBody
	public String getResponseValue(String key) {
		Result result = publicMethodService.getResponseRedis(key);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/delRequestValue", method = { RequestMethod.POST })
	@ResponseBody
	public String delRequestValue(String key) {
		Result result = publicMethodService.delRequestRedis(key);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/delResponseValue", method = { RequestMethod.POST })
	@ResponseBody
	public String delResponseValue(String key) {
		Result result = publicMethodService.delResponseRedis(key);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/delAllValueByKey", method = { RequestMethod.POST })
	@ResponseBody
	public String delAllValueByKey(String key) {
		Result result = publicMethodService.delAllValueByKey(key);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/flushRequestDb", method = { RequestMethod.POST })
	@ResponseBody
	public String flushRequestDb(String validMsg) {
		Result result = publicMethodService.flushRequestDb(validMsg);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/flushResponseDb", method = { RequestMethod.POST })
	@ResponseBody
	public String flushResponseDb(String validMsg) {
		Result result = publicMethodService.flushResponseDb(validMsg);
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "/flushAll", method = { RequestMethod.POST })
	@ResponseBody
	public String flushAll(String validMsg) {
		Result result = publicMethodService.flushAll(validMsg);
		return JSON.toJSONString(result);
	}

}
