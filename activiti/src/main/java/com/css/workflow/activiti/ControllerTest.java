package com.css.workflow.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {
	@Autowired
	private ProcessEngine processEngine;

	@GetMapping(value = "/a")
	public void a() {

		RepositoryService repositoryService = processEngine.getRepositoryService();

		repositoryService.createDeployment().addClasspathResource("bpmn/leave.bpmn").deploy();

		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<String, Object>();

		variables.put("userid", "10010");
		variables.put("day", 2);

		runtimeService.startProcessInstanceByKey("leave_process", variables);

		TaskService taskService = processEngine.getTaskService();

		TaskQuery query = taskService.createTaskQuery();

		List<Task> tasks = query.list();

		for (Task task : tasks) {
			System.out.println(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}

		System.out.println("--------------------");

		tasks = query.list();

		for (Task task : tasks) {
			System.out.println(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}

		System.out.println("--------------------");

		tasks = query.list();

		for (Task task : tasks) {
			System.out.println(task.getId() + "," + task.getName());
			taskService.complete(task.getId(), variables);
		}
	}

	@GetMapping(value = "/deploy")
	public void b() {

		// 得到流程存储服务组件
		RepositoryService repositoryService = processEngine.getRepositoryService();

		// 部署流程定义文件
		repositoryService.createDeployment().addClasspathResource("bpmn/my_leave.bpmn").deploy();

		System.out.println("流程部署成功！");

	}

	@GetMapping(value = "/start")
	public String c(String leaveMan, Long leaveDayCount) {
		StringBuffer result = new StringBuffer();
		if (StringUtils.isBlank(leaveMan) || leaveDayCount ==null ||leaveDayCount <= 0) {
			result.append("参数非法");
		} else {
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("userid", leaveMan);
			variables.put("xmjl", "shihanjie");
			variables.put("zjl", "yushuangqing");
			variables.put("day", leaveDayCount);
			// 使用流程定义的key的最新版本启动流程
			ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("my_leave", variables);
			result.append("<br>流程实例ID：" + pi.getId());
			result.append("<br>流程定义的ID：" + pi.getProcessDefinitionId());
			result.append("<br>流程启动成功！");
			System.out.println("流程实例ID：" + pi.getId());
			System.out.println("流程定义的ID：" + pi.getProcessDefinitionId());
			System.out.println("流程启动成功！");
		}
		return result.toString();
	}

	@GetMapping(value = "/doTask")
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
