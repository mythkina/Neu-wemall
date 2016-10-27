package com.neusoft.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
	public static void main(String args[]){
		//测试list
		List<Object> yearList = new ArrayList<Object>();
		for(int i=0;i<5;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("num", i+10);
			map.put("year", i+1);
			yearList.add(map);
		}
		
	/*	for(int q=0;q<yearList.size();q++){
			Map<String,Object> map = new HashMap<String, Object>();
			map = (Map<String,Object>)yearList.get(q);
			System.out.println("___"+map.get("year")+"="+map.get("num"));
		}*/
		
		//封装
		List<Object> retList = new ArrayList<Object>();
		for(int n=1;n<13;n++){
			System.out.println("&&&&&&&&"+n);
			Map<String,Object> yearMap = new HashMap<String, Object>();
			for(int m=0;m<yearList.size();m++){
				Map<String,Object> map = (Map<String,Object>)yearList.get(m);
				System.out.println("*******当前对象"+map.get("year")+"---"+map.get("num"));
				if(map.get("year").equals(n)){
					System.out.println("*******相等");
					yearMap.put("year", map.get("year"));
					yearMap.put("num", map.get("num"));
					break;
				}else{
					System.out.println("^^^^^^^^"+m);
					if(m==yearList.size()-1){
						System.out.println("*******不相等");
						yearMap.put("year", n);
						yearMap.put("num", "0");
					}
				}
			}
			retList.add(yearMap);
		}
		
		//输出
		for(int l=0;l<retList.size();l++){
			Map<String,Object> map = new HashMap<String, Object>();
			map = (Map<String,Object>)retList.get(l);
			System.out.println("*****"+map.get("year")+"="+map.get("num"));
		}
	}
}
