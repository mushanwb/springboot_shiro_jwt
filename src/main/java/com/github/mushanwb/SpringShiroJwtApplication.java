package com.github.mushanwb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 因为没有数据库配置，所以需要禁用数据库连接
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringShiroJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringShiroJwtApplication.class, args);
	}

}
