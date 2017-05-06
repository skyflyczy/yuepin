package com.yp.publicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动类
 * @author zhiya.chai
 * 2016年9月7日 下午2:57:17
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.yp.publicweb"})
@ImportResource({"classpath:/spring/applicationContext.xml", 
	"classpath:/spring/applicationContext.xml"})
public class Application extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
