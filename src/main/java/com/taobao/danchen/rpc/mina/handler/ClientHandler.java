package com.taobao.danchen.rpc.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.taobao.danchen.rpc.command.ServiceResponseCommand;
import com.taobao.danchen.rpc.object.LocalRPCWaitCache;
import com.taobao.danchen.rpc.object.RPCResult;
import com.taobao.danchen.rpc.serialize.HessianSerialUtil;
import com.taobao.danchen.rpc.serialize.JavaSerialUtil;

public class ClientHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
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
	public void messageReceived(IoSession session, Object data)
			throws Exception {
		super.messageReceived(session, data);
		if(data instanceof byte[]){
			ServiceResponseCommand serviceResonseCommand = new ServiceResponseCommand();
			serviceResonseCommand.decoder((byte[])data);
			long id = serviceResonseCommand.getMessageId();
			RPCResult rpcResult = LocalRPCWaitCache.hashMap.get(id);
			if(serviceResonseCommand.getSuccess()==0){
				rpcResult.setSuccess(true);
			}else{
				rpcResult.setSuccess(false);
			}
			if(serviceResonseCommand.getSerialType()==1){
				rpcResult.setData(HessianSerialUtil.deserialize(serviceResonseCommand.getContent()));
			}else if(serviceResonseCommand.getSerialType()==2){
				rpcResult.setData(JavaSerialUtil.deserialize(serviceResonseCommand.getContent()));
			}
			rpcResult.setValid((short)0);
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}

}
