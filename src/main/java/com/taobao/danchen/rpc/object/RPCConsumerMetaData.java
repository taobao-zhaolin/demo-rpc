package com.taobao.danchen.rpc.object;

import java.io.Serializable;

/**
 * 
 * @author danchen
 *
 */
public class RPCConsumerMetaData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ����ӿ�����
	 */
	private String serviceName;
	/**
	 * ����汾��
	 */
	private String serviceVersion;
	
	/**
	 * �����Ŀ���ַ
	 */
	private String targetHost;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getTargetHost() {
		return targetHost;
	}

	public void setTargetHost(String targetHost) {
		this.targetHost = targetHost;
	}	
}
