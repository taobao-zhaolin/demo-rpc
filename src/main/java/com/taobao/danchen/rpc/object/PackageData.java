package com.taobao.danchen.rpc.object;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * 
 * @author danchen
 *
 */
public class PackageData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6065777164357349070L;

	/**
	 * ����Ԫ��Ϣ
	 */
	private RPCConsumerMetaData rpcMetaData;
	/**
	 * ������
	 */
	private String methodName;
	
	/**
	 * ��������
	 */
	private LinkedList<String> listParameters;
	
	/**
	 * ����ֵ
	 */
	private Object[] listValues;

	public RPCConsumerMetaData getRpcMetaData() {
		return rpcMetaData;
	}

	public void setRpcMetaData(RPCConsumerMetaData rpcMetaData) {
		this.rpcMetaData = rpcMetaData;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public LinkedList<String> getListParameters() {
		return listParameters;
	}

	public void setListParameters(LinkedList<String> listParameters) {
		this.listParameters = listParameters;
	}

	public Object[] getListValues() {
		return listValues;
	}

	public void setListValues(Object[] listValues) {
		this.listValues = listValues;
	}
}
