package com.css.workflow.activiti.util;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/common")
public class ActivitiUtils {
	@Autowired
	private ProcessEngine processEngine;

	/**
	 * 部署流程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deploy")
	@ResponseBody
	public String deploy(String bpmnName) {
		String result = "";
		try {
			// 得到流程存储服务组件
			RepositoryService repositoryService = processEngine.getRepositoryService();

			// 部署流程定义文件
			repositoryService.createDeployment().addClasspathResource("bpmn/" + bpmnName + ".bpmn").deploy();
			result = bpmnName + "部署成功";
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

}
