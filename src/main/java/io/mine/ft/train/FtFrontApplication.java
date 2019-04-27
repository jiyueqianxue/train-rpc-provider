package io.mine.ft.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * 信贷系统前置
 */
@ComponentScan
@ImportResource("classpath:beanRefContext.xml")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
// @EnableEurekaClient
@EnableHystrix
// @EnableDiscoveryClient
@EnableFeignClients(basePackages = { "io.mine.ft.train" })
public class FtFrontApplication {

	/**
	 * @param args12
	 */
	public static void main(String[] args) {
		System.setProperty("DEPLOY_ENV", "test");// 本地启动时需要给定此值为test
													// 用容器启动时容器给出此变量
		SpringApplication.run(FtFrontApplication.class, args);
	}
}
