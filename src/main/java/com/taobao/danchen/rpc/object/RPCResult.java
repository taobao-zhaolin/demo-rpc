package com.taobao.danchen.rpc.object;
/**
 * 
 * @author danchen
 *
 */
public class RPCResult {
	/**
	 * 是否成功
	 */
	private boolean success;
	
	/**
	 * 返回的数据对象
	 */
	private Object data;
	/**
	 * 异常信息
	 */
	private Exception exception;
	/**
	 * -1表示无效
	 * 0表示结果生效
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
