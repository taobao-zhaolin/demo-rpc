package com.taobao.danchen.rpc.object;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class LocalRPCWaitCache {
	public static ConcurrentHashMap<Long, RPCResult> hashMap=new ConcurrentHashMap<Long, RPCResult>();
	
	private static AtomicLong uuid = new AtomicLong(0);
	
	public static long getUuid() {
		return uuid.getAndIncrement();
	}
}
