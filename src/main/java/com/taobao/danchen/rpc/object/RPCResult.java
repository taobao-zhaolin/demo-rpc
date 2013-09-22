package com.taobao.danchen.rpc.object;
/**
 * 
 * @author danchen
 *
 */
public class RPCResult {
	/**
	 * �Ƿ�ɹ�
	 */
	private boolean success;
	
	/**
	 * ���ص����ݶ���
	 */
	private Object data;
	/**
	 * �쳣��Ϣ
	 */
	private Exception exception;
	/**
	 * -1��ʾ��Ч
	 * 0��ʾ�����Ч
	 */
	private short valid;
	
	public RPCResult(){
		this.valid = -1;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public synchronized short getValid() {
		if(this.valid == -1){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return valid;
	}
	public synchronized void setValid(short valid) {
		this.valid = valid;
		this.notify();
	}
}
