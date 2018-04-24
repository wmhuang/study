package com.css.workflow.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/countersign")
public class CountersignController {
	@Autowired
	private ProcessEngine processEngine;

	@RequestMapping(value = "/start")
	@ResponseBody
	public String start(String userName) {

		Map<String, Object> variables = new HashMap<String, Object>();

		variables.put("leaveUserName", userName);

		processEngine.getRuntimeService().startProcessInstanceByKey("countersign", variables);

		return userName + "请假流程提交成功！";
	}

}
