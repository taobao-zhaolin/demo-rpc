package com.taobao.danchen.rpc;

import java.lang.reflect.Proxy;

import com.taobao.danchen.rpc.mina.TMinaServer;
import com.taobao.danchen.rpc.mina.handler.ServerHandler;
import com.taobao.danchen.rpc.object.LocalServiceRepository;
import com.taobao.danchen.rpc.object.RPCConsumerMetaData;
import com.taobao.danchen.rpc.object.RPCProviderMetaData;

public class TRegistry {
	/**
	 * ����ҪԶ�̵��õķ���
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
		 * �������񷽷����ô����handle
		 */
		RPCInvocationHandler handler = new RPCInvocationHandler(rpcMetaData);
		/**
		 * ����һ���������
		 */
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
				new Class[] { serviceInterface }, handler);
	}

	/**
	 * ��������
	 * @param serviceInstance
	 */
	public void register(RPCProviderMetaData rpcMetaData) {
		// �ǼǷ���
		String key = rpcMetaData.getServiceName()+rpcMetaData.getServiceVersion();
		LocalServiceRepository.providerHashMap.put(key, rpcMetaData);
		
		//���������Է�����һ��ȫ�ֵ����÷�����
		
		TMinaServer tMinaServer = new TMinaServer();
		tMinaServer.setHandler(new ServerHandler());
		try {
			tMinaServer.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
