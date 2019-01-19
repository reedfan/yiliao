package com.reed.ustc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@MapperScan("com.reed.ustc.mapper")
@EnableSwagger2
public class YiliaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiliaoApplication.class, args);
	}

}

