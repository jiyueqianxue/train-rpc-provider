package io.mine.ft.train.conf.async;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 异步参数
 *
 * @author machao
 * @date 2018-08-23.
 */
@ConfigurationProperties(prefix = "spring.task")
public class AsyncProperties {

	/**
	 * 核心线程数
	 */
	private Integer corePoolSize = 5;

	/**
	 * 最大线程数
	 */
	private Integer maxPoolSize = 20;

	/**
	 * 队列大小
	 */
	private Integer queueCapacity = 10000;

	/**
	 * 线程保活时间，单位：秒
	 */
	private Integer keepAliveSeconds = 60;

	public Integer getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(Integer corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public Integer getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(Integer queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	public Integer getKeepAliveSeconds() {
		return keepAliveSeconds;
	}

	public void setKeepAliveSeconds(Integer keepAliveSeconds) {
		this.keepAliveSeconds = keepAliveSeconds;
	}
}
