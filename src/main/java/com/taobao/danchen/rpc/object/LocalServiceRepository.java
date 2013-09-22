package com.taobao.danchen.rpc.object;

import java.util.HashMap;

public class LocalServiceRepository {
	/**
	 * 本机提供哪些对外服务
	 */
	public static HashMap<String,RPCProviderMetaData> providerHashMap = new HashMap<String,RPCProviderMetaData>();
}
