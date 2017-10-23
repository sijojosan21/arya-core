package com.sijojosan.arya.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
	

@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AryaPostService {
	
	@AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value();
}
