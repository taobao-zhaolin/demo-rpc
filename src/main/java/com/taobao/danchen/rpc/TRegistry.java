package com.taobao.danchen.rpc;

import java.lang.reflect.Proxy;

import com.taobao.danchen.rpc.mina.TMinaServer;
import com.taobao.danchen.rpc.mina.handler.ServerHandler;
import com.taobao.danchen.rpc.object.LocalServiceRepository;
import com.taobao.danchen.rpc.object.RPCConsumerMetaData;
import com.taobao.danchen.rpc.object.RPCProviderMetaData;

public class TRegistry {
	/**
	 * 声明要远程调用的服务
	 * @param serviceInterface
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T lookup(Class<T> serviceInterface) {
		String serviceName = serviceInterface.getName();
		RPCConsumerMetaData rpcMetaData = new RPCConsumerMetaData();
		rpcMetaData.setServiceName(serviceName);
		rpcMetaData.setServiceVersion("1.0.0");
		rpcMetaData.setTargetHost("127.0.0.1");
		/**
		 * 后续服务方法调用处理的handle
		 */
		RPCInvocationHandler handler = new RPCInvocationHandler(rpcMetaData);
		/**
		 * 返回一个服务代理
		 */
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
				new Class[] { serviceInterface }, handler);
	}

	/**
	 * 发布服务
	 * @param serviceInstance
	 */
	public void register(RPCProviderMetaData rpcMetaData) {
		// 登记服务
		String key = rpcMetaData.getServiceName()+rpcMetaData.getServiceVersion();
		LocalServiceRepository.providerHashMap.put(key, rpcMetaData);
		
		//这里服务可以发布到一个全局的配置服务器
		
		TMinaServer tMinaServer = new TMinaServer();
		tMinaServer.setHandler(new ServerHandler());
		try {
			tMinaServer.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
