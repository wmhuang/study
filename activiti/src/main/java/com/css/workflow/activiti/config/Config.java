package com.css.workflow.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	public static final String DB_SCHEMA_UPDATE_TRUE = "true";

	@Bean/*(name = "processEngineFactoryBean")*/
	public ProcessEngine getProcessEngineFactoryBean() {
		StandaloneProcessEngineConfiguration config = new StandaloneProcessEngineConfiguration();
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		config.setJdbcDriver("oracle.jdbc.driver.OracleDriver");
		config.setJdbcUsername("wmhuang");
		config.setJdbcPassword("wmhuang");
		config.setDatabaseSchema("WMHUANG");
		config.setDatabaseSchemaUpdate(Config.DB_SCHEMA_UPDATE_TRUE);
		config.setDatabaseSchemaUpdate("true");
		return config.buildProcessEngine();
	}
	

}
