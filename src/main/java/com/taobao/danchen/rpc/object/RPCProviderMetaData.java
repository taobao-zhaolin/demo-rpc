package com.taobao.danchen.rpc.object;
/**
 * 
 * @author danchen
 *
 */
public class RPCProviderMetaData {
	/**
	 * ����ӿ�����
	 */
	private String serviceName;
	/**
	 * �����ʵ��������
	 */
	private String impServiceName;
	/**
	 * ����汾��
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
