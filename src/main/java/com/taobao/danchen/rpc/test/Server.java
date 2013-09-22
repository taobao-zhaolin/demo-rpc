package com.taobao.danchen.rpc.test;

import com.taobao.danchen.rpc.TRegistry;
import com.taobao.danchen.rpc.object.RPCProviderMetaData;

public class Server {

	public static void main(String[] args) {
		TRegistry registry = new TRegistry();
		RPCProviderMetaData rpcMetaData = new RPCProviderMetaData();
		rpcMetaData.setServiceName(HelloService.class.getName());
		rpcMetaData.setServiceVersion("1.0.0");
		rpcMetaData.setImpServiceName(HelloServiceImpl.class.getName());
		registry.register(rpcMetaData);
	}

}
