package com.taobao.danchen.rpc.command;

import java.nio.ByteBuffer;

public class ServiceRequestCommand implements IMessageCommand{
	
	private short serialType;
	
	private long messageId;
	
	private int contentLength;
	
	private byte[] content;
	
	
	public short getSerialType() {
		return serialType;
	}
	public void setSerialType(short serialType) {
		this.serialType = serialType;
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
		this.contentLength = content.length;
	}
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public byte[] encoder() {
		ByteBuffer bytebuffer = ByteBuffer.allocate(14+content.length);
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
		this.serialType = bytebuffer.getShort();
		this.messageId = bytebuffer.getLong();
		this.contentLength = bytebuffer.getInt();
		this.content = new byte[contentLength];
		bytebuffer.get(content);
	}
}
