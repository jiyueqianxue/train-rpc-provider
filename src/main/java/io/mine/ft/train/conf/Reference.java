package io.mine.ft.train.conf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.FIELD)
@Autowired
public @interface Reference {
	boolean value() default true;
}
