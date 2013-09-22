package com.taobao.danchen.rpc.test;

public class HelloServiceImpl implements HelloService {

	public String helloWorld(String info) {
		return new String("danchen:"+info);
	}

}
