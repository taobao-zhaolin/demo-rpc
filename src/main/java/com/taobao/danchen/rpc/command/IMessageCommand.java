package com.taobao.danchen.rpc.command;

public interface IMessageCommand {
	/**
	 * ����
	 * @return
	 */
	public byte[] encoder();
	/**
	 * ����
	 * @throws Exception
	 */
	public void decoder(byte[] data) throws Exception;
}
