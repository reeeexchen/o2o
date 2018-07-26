package com.imooc.o2o.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author:REX
 * @Date: Create in 0:02 2018/5/28
 */
@Configuration
public class SessionFactoryConfiguration {
	// THE PATH OF MYBATIS-CONFIG.XIM
	private static String mybatisConfigFile;
	@Value("${mybatis_config_file}")
	public void setMybatisConfigFile(String mybatisConfigFile){
		SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
	}
	// THE PATH OF MYBATIS MAPPER FILE
	private static String mapperPath;
	@Value("${mapper_path}")
	public void setMapperPath(String mapperPath){
		SessionFactoryConfiguration.mapperPath = mapperPath;
	}
	// THE PACKAGE OF ALL ENTITY
	@Value("${type_alias_package}")
	private String typeAliasPackage;

	@Autowired
	private DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 设置mybatis configuration扫描路径
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
		// 添加mapper扫描路径
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
		sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
		// 设置DataSource
		sqlSessionFactoryBean.setDataSource(dataSource);
		// 设置typeAlias 包扫描路径
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
		return sqlSessionFactoryBean;

	}

}
