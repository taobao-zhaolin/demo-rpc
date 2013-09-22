package com.taobao.danchen.rpc.test;

import com.taobao.danchen.rpc.TRegistry;

public class Client {
	public static void main(String[] args) {
		TRegistry tRegistry = new TRegistry();
		HelloService stub = tRegistry.lookup(HelloService.class);
		String str;
		for(int i=0;i<100;i++){
			str = stub.helloWorld("hi,rpc world.");
			System.out.println(str);
		}
		
	}

}
