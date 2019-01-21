package com.zhiyesoft.vote;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.zhiyesoft.vote.modules.topic.mapper")
public class MyVoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyVoteApplication.class, args);
	}
	
//	@Autowired
//	private Environment env;
//
//	@Bean
//	public DataSource dataSource() {
//		DruidDataSource dataSource = new DruidDataSource();
//		dataSource.setUrl(env.getProperty("spring.datasource.url"));
//		dataSource.setUsername(env.getProperty("spring.datasource.username"));// 鐢ㄦ埛鍚�
//		dataSource.setPassword(env.getProperty("spring.datasource.password"));// 瀵嗙爜
//		dataSource.setInitialSize(10);
//		dataSource.setMaxActive(50);
//		dataSource.setMinIdle(0);
//		dataSource.setMaxWait(60000);
//		dataSource.setValidationQuery("SELECT 1");
//		dataSource.setTestOnBorrow(false);
//		dataSource.setTestWhileIdle(true);
//		dataSource.setPoolPreparedStatements(false);
//		return dataSource;
//	}
}

