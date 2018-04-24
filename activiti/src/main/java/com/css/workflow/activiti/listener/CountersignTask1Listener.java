package com.css.workflow.activiti.listener;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.css.workflow.activiti.config.Evn;

public class CountersignTask1Listener implements TaskListener {

	private static final long serialVersionUID = -5909236295837884141L;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("任务1");
		// String assginee = delegateTask.getAssignee();
		String laeveUserName = (String) delegateTask.getVariable("leaveUserName");

		// 通过请假人名称获取上级领导
		String leader = Evn.MAN_AND_LEADER.get(laeveUserName);

		System.out.println("请假人名称" + laeveUserName);
		
		delegateTask.setVariable("leaderName", leader);

	}

}