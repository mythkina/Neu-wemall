package com.neusoft.core.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/** 
* @ClassName: BaseVO 
* @Description: 框架核心类用于前后台数据传递，所有业务的VO对象必须继承这个类 
* @author neusoft
* @date 2015年8月17日 下午3:58:54 
*  
*/
public class BaseVO extends BaseObject {
	
	/** 
	* @Title: copyProperties 
	* @Description: 将PO对象的属性值拷贝到当前的VO中，拷贝时VO的属性名必须与PO相同才能被拷贝
	* @param @param source
	* @return void
	* @throws 
	*/
	public void copyProperties(BasePO source){
		try {
			BeanUtils.copyProperties(this, source);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	/** 
	* @Title: toMap 
	* @Description: 把当前VO转换为Map<String,Object>对象，只能转换简单类型的属性
	* @param @return
	* @return Map<String,Object>
	* @throws 
	*/
	public Map<String,Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(this);
                    if(value!=null)
                    	map.put(key, value);
                    else
                    	log.warn("Value of "+key+" property is null object,so ignore!");
                }
            }  
        } catch (Exception e) {  
            log.error(e);
        }  
  
        return map;  
	}
	
	/** 
	* @Title toMapString 
	* @Description 把当前VO转换为Map<String,String>对象，只能转换简单类型的属性,使用此方法时要求VO中的所有属性必须为String类型 
	* @return 
	* Map<String,String>
	*/
	public Map<String,String> toMapString(){
		Map<String, String> map = new HashMap<String, String>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(this);
                    if(value==null)
                    	log.warn("Value of "+key+" property is null object,so ignore!");
                    else if(value instanceof String)
                    	map.put(key, (String)value);
                    else
                    	log.warn("Type of "+key+" property is not String,so ignore!");
                }
            }  
        } catch (Exception e) {  
            log.error(e);
        }  
  
        return map;  
	}
}
