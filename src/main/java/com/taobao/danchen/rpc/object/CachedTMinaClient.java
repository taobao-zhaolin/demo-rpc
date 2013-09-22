package com.taobao.danchen.rpc.object;

import java.util.concurrent.ConcurrentHashMap;

import com.taobao.danchen.rpc.mina.TMinaClient;
/**
 * 
 * @author danchen
 *
 */
public class CachedTMinaClient {
     public static ConcurrentHashMap<String/*host ip address*/, TMinaClient> tMinaClientHashMap = new ConcurrentHashMap<String,TMinaClient>();
}
