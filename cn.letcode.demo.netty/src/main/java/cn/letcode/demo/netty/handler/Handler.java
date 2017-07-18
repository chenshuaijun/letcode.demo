/**
 * Wasu.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */

package cn.letcode.demo.netty.handler;

/**
 * @description 暴露给用户的接口，用户自定义类实现该接口即可被Server调用， 返回用户响应结果，注意该类的对象为单例
 */

public interface Handler {
	public String invoke();
}
