package com.taobao.danchen.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.LinkedList;

import com.taobao.danchen.rpc.command.ServiceRequestCommand;
import com.taobao.danchen.rpc.mina.TMinaClient;
import com.taobao.danchen.rpc.mina.handler.ClientHandler;
import com.taobao.danchen.rpc.object.CachedTMinaClient;
import com.taobao.danchen.rpc.object.LocalRPCWaitCache;
import com.taobao.danchen.rpc.object.PackageData;
import com.taobao.danchen.rpc.object.RPCConsumerMetaData;
import com.taobao.danchen.rpc.object.RPCResult;
import com.taobao.danchen.rpc.serialize.HessianSerialUtil;

/**
 * 
 * @author danchen
 *
 */
public class RPCInvocationHandler implements InvocationHandler{

	private RPCConsumerMetaData rpcMetaData;
	
	public RPCInvocationHandler(RPCConsumerMetaData rpcMetaData) {
		this.rpcMetaData = rpcMetaData;
	}

	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		//��װ����content
		PackageData packageData = new PackageData();
		packageData.setRpcMetaData(rpcMetaData);
		packageData.setMethodName(method.getName());
		LinkedList<String> listParameters = new LinkedList<String>();
		Class<?>[] parameterTypes = method.getParameterTypes();
		for(int i=0;i<parameterTypes.length;i++){
			listParameters.add(parameterTypes[i].getName());
		}
		packageData.setListParameters(listParameters);
		packageData.setListValues(params);
		
		//���
		long id = LocalRPCWaitCache.getUuid();
		RPCResult rpcResult = new RPCResult();
		LocalRPCWaitCache.hashMap.put(id, rpcResult);
		
		//��װ���ݴ�������
		ServiceRequestCommand serviceRequestCommand = new ServiceRequestCommand();
		serviceRequestCommand.setSerialType((short)1);
		serviceRequestCommand.setMessageId(id);
		serviceRequestCommand.setContent(HessianSerialUtil.serialize(packageData));
		
		//���ݴ���
		if(CachedTMinaClient.tMinaClientHashMap.get(rpcMetaData.getTargetHost())==null){
			TMinaClient tMinaClient = new TMinaClient(rpcMetaData.getTargetHost());
			tMinaClient.setHandler(new ClientHandler());
			tMinaClient.init();
			CachedTMinaClient.tMinaClientHashMap.put(rpcMetaData.getTargetHost(), tMinaClient);
			//���ﲻ��̫��,ǿ�Ƶȴ�1000ms,Ҫ��Ȼ����д���ݻᱨ��
			Thread.sleep(1000);
			tMinaClient.writeData(serviceRequestCommand.encoder());
		}else{
			CachedTMinaClient.tMinaClientHashMap.get(rpcMetaData.getTargetHost()).writeData(serviceRequestCommand.encoder());
		}
		
		
		//ͬ���ȴ����
		if(rpcResult.getValid()==0){
			LocalRPCWaitCache.hashMap.remove(id);
			if(rpcResult.isSuccess()){
				return rpcResult.getData();
			}else{
				return new Exception(rpcResult.getException());
			}
		}
		
		//�޷��ȴ���Զ�̵��õĽ������
		return null;
	}
}
