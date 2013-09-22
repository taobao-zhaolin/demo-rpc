package com.taobao.danchen.rpc.command;

import java.nio.ByteBuffer;

/**
 * 服务端返回的命令
 * @author danchen
 *
 */
public class ServiceResponseCommand implements IMessageCommand{
	/**
	 * -1表示失败,0表示成功
	 */
	private short success;
	/**
	 * 序列化类型
	 */
	private short serialType;
	/**
	 * 请求的ID
	 */
	private long messageId;
	/**
	 * 返回的数据长度
	 */
	private int contentLength;
	/**
	 * 返回的数据内容
	 */
	private byte[] content;
	/**
	 * 返回的对象类型
	 */
	private Class<?> returnType;
	
	public short getSuccess() {
		return success;
	}

	public void setSuccess(short success) {
		this.success = success;
	}

	public short getSerialType() {
		return serialType;
	}

	public void setSerialType(short serialType) {
		this.serialType = serialType;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}


	
	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public byte[] encoder() {
		ByteBuffer bytebuffer = ByteBuffer.allocate(16+content.length);
		bytebuffer.putShort(success);
		bytebuffer.putShort(serialType);
		bytebuffer.putLong(messageId);
		bytebuffer.putInt(contentLength);
		bytebuffer.put(content);
		bytebuffer.flip();
		return bytebuffer.array();
	}

	public void decoder(byte[] data) throws Exception {
		ByteBuffer bytebuffer = ByteBuffer.allocate(data.length);
		bytebuffer.put(data);
		bytebuffer.flip();
		this.success = bytebuffer.getShort();
		this.serialType = bytebuffer.getShort();
		this.messageId = bytebuffer.getLong();
		this.contentLength = bytebuffer.getInt();
		this.content = new byte[contentLength];
		bytebuffer.get(content);
	}

}
