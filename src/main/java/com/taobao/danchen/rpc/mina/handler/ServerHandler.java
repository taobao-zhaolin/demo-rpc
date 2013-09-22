package com.taobao.danchen.rpc.mina.handler;

import java.lang.reflect.Method;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.taobao.danchen.rpc.command.ServiceRequestCommand;
import com.taobao.danchen.rpc.command.ServiceResponseCommand;
import com.taobao.danchen.rpc.object.LocalServiceRepository;
import com.taobao.danchen.rpc.object.PackageData;
import com.taobao.danchen.rpc.object.RPCProviderMetaData;
import com.taobao.danchen.rpc.serialize.HessianSerialUtil;

public class ServerHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
		System.out.println(session.getRemoteAddress()+" is connecting.");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
		System.out.println("io session is opened.");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
		System.out.println("io session is closed.");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		super.messageReceived(session, message);
		if(message instanceof byte[]){
			
			ServiceRequestCommand serviceRequestCommand = new ServiceRequestCommand();
			serviceRequestCommand.decoder((byte[])message);
			if(serviceRequestCommand.getSerialType()==1){
				PackageData packageData =(PackageData) HessianSerialUtil.deserialize(serviceRequestCommand.getContent());
				String interfaceName = packageData.getRpcMetaData().getServiceName();
				String version = packageData.getRpcMetaData().getServiceVersion();
				//先在服务端查询,是否有此服务
				RPCProviderMetaData rpcProviderMetaData = LocalServiceRepository.providerHashMap.get(interfaceName+version);
				if(rpcProviderMetaData!=null){
					Class<?> obj= Class.forName(rpcProviderMetaData.getImpServiceName());
					Method method = null;
					try {
						method = getMethod(packageData,obj);
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
					//利用反射,进行方法调用
					Object result = method.invoke(obj.newInstance(), packageData.getListValues());
					//此方法返回的类型
					Class<?> rtype = method.getReturnType();
					//封装返回的结果
					ServiceResponseCommand serviceResponseCommand =  new ServiceResponseCommand();
					serviceResponseCommand.setMessageId(serviceRequestCommand.getMessageId());
					serviceResponseCommand.setSerialType(serviceRequestCommand.getSerialType());
					serviceResponseCommand.setSuccess((short)0);
					serviceResponseCommand.setReturnType(rtype);
					
					if(serviceResponseCommand.getSerialType()==1){
						byte[] resultByte = HessianSerialUtil.serialize(result);
						serviceResponseCommand.setContent(resultByte);
						serviceResponseCommand.setContentLength(resultByte.length);
					}
					
					//返回客户端
					session.write(serviceResponseCommand.encoder());
				}else{
					//不提供这个服务
				}
			}
		}
		
	}

	private Method getMethod(PackageData packageData,Class<?> obj) throws NoSuchMethodException, SecurityException {
		Class<?>[] parameterTypes = new Class[packageData.getListParameters().size()];
		for(int i=0;i<parameterTypes.length;i++){
			try {
				parameterTypes[i]=Class.forName(packageData.getListParameters().get(i));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return obj.getDeclaredMethod(packageData.getMethodName(), parameterTypes);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}

}
