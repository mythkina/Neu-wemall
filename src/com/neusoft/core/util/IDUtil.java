package com.neusoft.core.util;

import java.util.UUID;

/**
 * ID生成类，用于获取主键ID
 * @author vincefan
 *
 */
public class IDUtil {
	/**
	 * 生成新的ID，并返回
	 * @return
	 */
	public static String getID() {
		// 创建 GUID 对象
		UUID uuid = UUID.randomUUID();
		// 得到对象产生的ID
		String a = uuid.toString();
		// 转换为大写
		a = a.toUpperCase();
		// 替换 -
		a = a.replaceAll("-", "");
		return a;
	}

/*	public static void main(String[] args) {
		System.out.println(getID());
	}
*/
}
