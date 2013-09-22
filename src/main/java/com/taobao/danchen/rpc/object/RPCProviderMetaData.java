package com.taobao.danchen.rpc.object;
/**
 * 
 * @author danchen
 *
 */
public class RPCProviderMetaData {
	/**
	 * 服务接口名字
	 */
	private String serviceName;
	/**
	 * 服务的实现类名字
	 */
	private String impServiceName;
	/**
	 * 服务版本号
	 */
	private String serviceVersion;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getImpServiceName() {
		return impServiceName;
	}
	public void setImpServiceName(String impServiceName) {
		this.impServiceName = impServiceName;
	}
	public String getServiceVersion() {
		return serviceVersion;
	}
	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
	
	
}
