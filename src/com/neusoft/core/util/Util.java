package com.neusoft.core.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Util {

	/**
	 * Web环境下判断对象是否为空
	 * 字符串长度为零被定义为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null)
			return true;
		if(obj instanceof String)
			return ((String)obj).trim().length()==0;
		return false;
	} 
	
	/**
	 * 对象是否不为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	/**
	 * 按yyyy-MM-dd格式返回日期
	 * @return
	 */
	public static String getLocalDate(){
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 按指定格式返回日期串
	 * @param format
	 * @return
	 */
	public static String getDate(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static String jsonp(String json){
		return jsonp("jsoncallback",json);
	}
	
	/**
	 * "jsoncallback({'success':'true','a':'1'})";
	 * @param prefix
	 * @param json
	 * @return
	 */
	public static String jsonp(String prefix,String json){
		return prefix+"("+json+")";
	}
	
	/**
	 * 根据给定的类的全路径返回一个新的对象
	 * @param clazz
	 * @return
	 */
	public static Object createObject(String clazz){
		Object obj=null;
		try {
			obj=Class.forName(clazz).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 将JavaBean=>Map
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,Object> convBeanToMap(Object bean,boolean ignoreNull)   {  
        Class type = bean.getClass();  
        Map<String,Object> r = new HashMap<String,Object>();  
        try{
        	 BeanInfo beanInfo = Introspector.getBeanInfo(type);  
        	  
             PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
             for (int i = 0; i< propertyDescriptors.length; i++) {  
                 PropertyDescriptor descriptor = propertyDescriptors[i];  
                 String propertyName = descriptor.getName();  
                 if (!propertyName.equals("class")) {  
                     Method readMethod = descriptor.getReadMethod();  
                     Object result = readMethod.invoke(bean, new Object[0]);  
                     if (result != null) {  
                         r.put(propertyName, result);  
                     } else
                    	 if(!ignoreNull)
                    		 r.put(propertyName, null);  
                 }  
             }  
        }catch(Exception e){
        	e.printStackTrace();
        }
        return r;  
    }  
	
	/**
	 * 默认忽略空值
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> convBeanToMap(Object bean)   { 
		return convBeanToMap(bean,true);
	}

	
	public static String bytesToHex(byte[] bArray,boolean upper) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toLowerCase());
		}
		return upper?sb.toString().toUpperCase():sb.toString();
	}
	
	public static String bytesToHex(byte[] bArray) {
		return bytesToHex(bArray,false);
	}
	
	
	
	/**
	 * 将String类型转化为int类型，转化失败则为0。
	 * 
	 * @param  
	 * @return int 
	 * @throws
	 * @author chechangying
	 */
	public static int parseInt(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (Exception ex) {
		}
		return result;
	}
	
	/**
	 * 将String以非null形式返回，如果String为null，则返回一个空字符串""。
	 * 
	 * @param  
	 * @return String 
	 * @throws
	 * @author chechangying
	 */
	public static String notNull(String str) {
		return str == null || "null".equalsIgnoreCase(str) ? "" : str;
	}
	
}
