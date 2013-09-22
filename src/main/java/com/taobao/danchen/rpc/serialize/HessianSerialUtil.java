package com.taobao.danchen.rpc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;

public class HessianSerialUtil {
	/**

	 * ��hessian���л�
	 * @param object
	 * @return
	 * @throws Exception
	 */

	public static byte[] serialize(Object object) throws Exception{

		if (object == null) {
			throw new NullPointerException();
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianSerializerOutput hessianOutput = new HessianSerializerOutput(os);
		hessianOutput.writeObject(object);
		return os.toByteArray();

	}

	/**

	 * ��hessian�����л�
	 * @param bytes
	 * @return
	 * @throws Exception
	 */

	public static Object deserialize(byte[] bytes) throws Exception{

		if(bytes==null){
		throw new NullPointerException();
		}
	
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		HessianSerializerInput hessianInput=new HessianSerializerInput(is);
		Object object = hessianInput.readObject();
		return object;

	}
}
