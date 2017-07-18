/**
 * ponycar.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */

package cn.letcode.demo.netty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @description 实现url到handle的映射
 * @create 2017年7月18日
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RequestMapping {
	public String value();
}
