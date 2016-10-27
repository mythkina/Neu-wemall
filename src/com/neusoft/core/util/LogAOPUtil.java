package com.neusoft.core.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.neusoft.core.common.BaseObject;
import com.neusoft.core.common.BaseVO;

/** 执行时间日志输出切面 */
@Aspect
public class LogAOPUtil extends BaseObject {
	/**
	 * 切入点的名字为anyMethod(),
	 * */
	@Pointcut("execution(* com.neusoft.neumooc.controller..*.*(..))")
	private void controllerMethod() {
	}

	// @AfterThrowing(pointcut = "controllerMethod()", throwing = "e")
	// // 定义异常通知
	// public void myAfterThrowingAdvice(JoinPoint joinpoint, Exception e) {
	// String classAndMethod = joinpoint.getTarget().getClass().getName()
	// + "类的" + joinpoint.getSignature().getName();
	// log.info("异常:" + classAndMethod + "方法抛出异常：" + e.getMessage());
	// }

	// @After("controllerMethod()")
	// // 定义最终通知
	// public void myAfterAdvice(JoinPoint joinpoint) {
	// String classAndMethod = joinpoint.getTarget().getClass().getName()
	// + "类的" + joinpoint.getSignature().getName();
	// log.info("完成:" + classAndMethod + "方法执行结束！");
	// }

	@Around("controllerMethod()")
	// 定义环绕通知
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long begintime = System.currentTimeMillis();// 记下开始时间
		// 传递给连接点对象进行接力处理

		Object result = pjp.proceed();
		long endtime = System.currentTimeMillis();// 记下结束时间
		String classAndMethod = pjp.getTarget().getClass().getName() + "类的"
				+ pjp.getSignature().getName();
		log.info("提示:" + classAndMethod + "方法执行结束,耗时" + (endtime - begintime)
				+ "毫秒！");
		Object[] args = pjp.getArgs();
		log.info("提示:" + classAndMethod + "方法执行,传递的参数：");
		for (Object object : args) {
			if (object instanceof BaseVO) {
				log.info(((BaseVO) object).toMap());
			}
		}
		return result;
	}
}
