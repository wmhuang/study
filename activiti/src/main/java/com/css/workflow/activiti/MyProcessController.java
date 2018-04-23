package com.css.workflow.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myProcess")
public class MyProcessController {
	@Autowired
	private ProcessEngine processEngine;

	@RequestMapping(value = "/start")
	@ResponseBody
	public String start(int money) {
		String result = "";

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("money", money);
		try {
			processEngine.getRuntimeService().startProcessInstanceByKey("myProcess", variables);
			result = "流程启动成功";
		} catch (Exception e) {
			result = e.getMessage();
		}

		return result;
	}

	@RequestMapping(value = "/doTask")
	@ResponseBody
	public String d(String userName, Long taskId) {
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userName).list();
		StringBuffer result = new StringBuffer();
		if (tasks != null && tasks.size() > 0) {
			for (Task task : tasks) {
				if (taskId == null) {
					result.append("<br>任务ID：" + task.getId());
					result.append("<br>任务的办理人：" + task.getAssignee());
					result.append("<br>任务名称：" + task.getName());
					result.append("<br>任务的创建时间：" + task.getCreateTime());
					result.append("<br>流程实例ID：" + task.getProcessInstanceId());
					result.append("<br>#######################################");
					// 查询任务
					System.out.println("任务ID：" + task.getId());
					System.out.println("任务的办理人：" + task.getAssignee());
					System.out.println("任务名称：" + task.getName());
					System.out.println("任务的创建时间：" + task.getCreateTime());
					System.out.println("流程实例ID：" + task.getProcessInstanceId());
					System.out.println("#######################################");
				} else if (taskId.toString().equals(task.getId())) {
					taskService.complete(task.getId());
					result.append("任务执行完成");
				}
			}
		}
		return result.toString();
	}
}
