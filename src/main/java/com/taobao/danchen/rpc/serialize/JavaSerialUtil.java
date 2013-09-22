package com.taobao.danchen.rpc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerialUtil {
	/**

	 * java���л�

	 * @param obj

	 * @return

	 * @throws Exception

	 */

	public static byte[] serialize(Object obj) throws Exception {

	if (obj == null)

	throw new NullPointerException();

	ByteArrayOutputStream os = new ByteArrayOutputStream();

	ObjectOutputStream out = new ObjectOutputStream(os);

	out.writeObject(obj);

	return os.toByteArray();

	}

	/**

	 * java�����л�

	 * @param by

	 * @return

	 * @throws Exception

	 */

	public static Object deserialize(byte[] by) throws Exception {

	if (by == null)

	throw new NullPointerException();

	ByteArrayInputStream is = new ByteArrayInputStream(by);

	ObjectInputStream in = new ObjectInputStream(is);

	return in.readObject();

	}
}
