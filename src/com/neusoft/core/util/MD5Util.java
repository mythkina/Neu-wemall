package com.neusoft.core.util;

import static com.neusoft.core.util.Util.bytesToHex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 * @author vincefan
 *
 */
public class MD5Util {
	/**
	 * MD5加密
	 * @param datas
	 * @return
	 */
	public static String gen(byte[] datas) {
		if (datas == null)
			return "md5 generate error!";
		return bytesToHex(encode(datas));
	}
	/**
	 * 加密执行方法
	 * @param datas
	 * @return
	 */
	private static byte[] encode(byte[] datas) {
		byte[] r = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			r = md.digest(datas);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return r;
	}
}
