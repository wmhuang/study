package wmhuang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class CreateTable {
	@Test
	public void createTable() {
		
//		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		 ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("Activiti.cfg.xml").buildProcessEngine(); 
		
		System.out.println("---processEngin:" + processEngine);
	}

}
