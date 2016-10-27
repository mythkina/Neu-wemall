package com.neusoft.core.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

	
public class DynamicTimeFormatter extends BodyTagSupport{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -2427614208583345623L;
	private String param;
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	public DynamicTimeFormatter(){  
        System.out.println("MyTag构造方法：一个myTag类的对象被构建了....");  
    }  
	
	public  static String timeFormatter(String time){
		System.out.println("^%^&*()(*&^%$%^&*(*^%$$%^&"+time);
		time = "1419840284000";
		long timeF = Long.parseLong(time);
		StringBuffer sb = new StringBuffer();
		long l = new Date().getTime() - timeF;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		//sb.append("发表于：");
		if (day >=1){
			sb.append(day + "天");
		}else{
			if (hour >=1){
				sb.append(hour + "小时");
			}else{
				if (min > 0)
					sb.append(min + "分");
				sb.append(s + "秒 前");
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public int doStartTag() {  
        JspWriter out = this.pageContext.getOut();  
        try {  
            out.print(timeFormatter(param));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        System.out.println("对象正在处理开始标记.....");  
        return EVAL_BODY_INCLUDE;  
    }  
    public int doAfterBody() throws JspException{  
        System.out.println("处理标签体（after body）....");  
        return SKIP_BODY;  
    }  
    public int doEndTag() throws JspException{  
        System.out.println("对象正在处理结束标记.....");  
        return EVAL_PAGE;  
    }  
}
