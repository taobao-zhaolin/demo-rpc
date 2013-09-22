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
	 * 服务接口名字
	 */
	private String serviceName;
	/**
	 * 服务版本号
	 */
	private String serviceVersion;
	
	/**
	 * 服务的目标地址
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
