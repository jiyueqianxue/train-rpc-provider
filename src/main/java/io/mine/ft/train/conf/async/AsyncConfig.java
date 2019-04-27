package io.mine.ft.train.conf.async;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 异步任务支持
 *
 * @author machao
 * @date 2018-08-21.
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties({ AsyncProperties.class })
public class AsyncConfig implements AsyncConfigurer {

	@Autowired
	private AsyncProperties asyncProperties;

	@Bean
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(asyncProperties.getCorePoolSize());
		taskExecutor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
		taskExecutor.setQueueCapacity(asyncProperties.getQueueCapacity());
		taskExecutor.setKeepAliveSeconds(asyncProperties.getKeepAliveSeconds());// 单位秒
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}
}
