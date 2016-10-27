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
* @ClassName: BasePO 
* @Description: 框架核心类与数据库实体一一对应，建议一个表一个PO，所有业务的PO对象必须继承这个类 
* @author neusoft
* @date 2015年8月17日 下午3:59:51 
*  
*/
/** 
* @ClassName: BasePO 
* @Description: TODO 
* @author neusoft
* @date 2015年8月19日 下午1:21:37 
*  
*/
public class BasePO extends BaseObject{
	
	/** 
	* @Title: copyProperties 
	* @Description: 将VO对象的属性值拷贝到当前的PO中，拷贝时VO的属性名必须与PO相同才能被拷贝
	* @param 被拷贝的VO对象
	* @return void
	* @throws 
	*/
	public void copyProperties(BaseVO source){
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
	* @Description: 把当前PO转换为Map<String,Object>对象，只能转换简单类型的属性
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
                    map.put(key, value);  
                }
            }  
        } catch (Exception e) {  
            log.error(e);
        }  
  
        return map;  
	}
}
