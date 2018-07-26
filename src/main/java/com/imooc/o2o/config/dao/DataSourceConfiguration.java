package com.imooc.o2o.config.dao;

import com.imooc.o2o.util.DESUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * @Author:REX
 * @Date: Create in 23:45 2018/5/27
 */
@Configuration
// 配置mybatis mapper的扫描路径
@MapperScan("com.imooc.o2o.dao")
public class DataSourceConfiguration {
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;

	/*
	* 生成SpringMVC中spring-dao.xml 对应的bean（dataSource）
	* */
	@Bean(name = "dataSource")
	public ComboPooledDataSource createDataSource() throws PropertyVetoException {
		// CREATE ENTITY
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		// PROPERTIES
		dataSource.setDriverClass(jdbcDriver);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(DESUtil.getDcryptString(jdbcUsername));
		dataSource.setPassword(DESUtil.getDcryptString(jdbcPassword));
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setCheckoutTimeout(10000);
		dataSource.setAcquireRetryAttempts(2);
		return dataSource;
	}
}
