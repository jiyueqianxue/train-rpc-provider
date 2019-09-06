/**   
 * Copyright © 2016 猪八戒网. All rights reserved.
 * 
 * @Title: LogAspect.java 
 * @Prject: mobile-wap-activity
 * @Package: com.zbj.mobile.activitywap.web.aspect 
 * @Description: 
 * @author: lidaming   
 * @date: 2016年7月30日 下午7:49:15 
 * @version: V1.0   
 */
package io.mine.ft.train.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @ClassName: LogAspect
 * @Description: 切面日志，对controller的切面
 * @author: lidaming
 * @date: 2016年7月30日 下午7:49:15
 */
@Aspect
@Order(1)
@Component
public class ReferenceAspect {

	//private static Logger logger = LoggerFactory.getLogger(ReferenceAspect.class);
	/**
	 * RequestMapping 方法切入
	 */
	@Pointcut("@annotation(io.mine.ft.train.conf.Reference)")
	private void aspect() {}
	@Before("aspect()")
	public Object before(JoinPoint joinPoint) throws Throwable {
	    
	    Class<? extends Object> clazz = joinPoint.getTarget().getClass();
	    Reference reference = clazz.getAnnotation(Reference.class);
	    if (reference.value() == true) {
	        
	    }
	    Object object = ((ProceedingJoinPoint) joinPoint).proceed();
        return object;
	}
}