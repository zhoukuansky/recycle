package com.recycle.aop;

import com.recycle.dao.SystemLogMapper;
import com.recycle.model.SystemLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 切点类
 */
@Aspect
@Component
public class SystemLogAspect {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired
    private SystemLogMapper systemLogMapper;

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static SystemLog getControllerAndServiceMethodDescription(
            JoinPoint joinPoint, int i) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        SystemControllerLog log;
        SystemServiceLog log2;
        SystemLog logM = new SystemLog();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    //SystemControllerLog的切点获取方法描述
                    if (i == 1) {
                        log = method.getAnnotation(SystemControllerLog.class);
                        logM.setLogAction(log.logAction());
                        logM.setLogContent(log.logContent());
                        break;
                        //SystemServiceLog的切点获取方法描述
                    } else {
                        log2 = method.getAnnotation(SystemServiceLog.class);
                        logM.setLogAction(log2.logAction());
                        logM.setLogContent(log2.logContent());
                        break;
                    }
                }
            }
        }
        return logM;
    }

    // Controller层切点,针对在业务模块标注SystemControllerLog注解记录日志
    @Pointcut("@annotation( com.recycle.aop.SystemControllerLog )")
    public void controllerAspect() {
    }

    // Service层切点,针对在业务模块标注SystemControllerLog注解记录日志
    @Pointcut("@annotation( com.recycle.aop.SystemServiceLog )")
    public void serviceAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        try {
            // 请求的IP
            String logIP = request.getRemoteAddr();

            String[] userID = (String[]) request.getAttribute("currentUser");

            //String userName = request.getParameter("UserName");
//       if (StringUtils.isEmpty(userID) || StringUtils.isEmpty(userName)) {
//          logger.debug("操作日志-->日志添加:用户名或用户ID为空，返回不添加日志!");
//          return;
//       }

            SystemLog slm = getControllerAndServiceMethodDescription(joinPoint, 1);
            slm.setLogIP(logIP);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
//            TimeZone.setDefault(time);
            String date = dateFormat.format(new Date());

            slm.setLogTime(date);

            if (userID != null) {
                slm.setFlagID(userID[1]);
            }

            // *========控制台输出=========*//
            logger.debug("=====注解参数获取开始=====");
            logger.debug("请求方法:"
                    + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));
            logger.debug("操作方法:" + slm.getLogAction());
            logger.debug("操作内容:" + slm.getLogContent());
            logger.debug("请求IP:" + slm.getLogIP());
            logger.debug("用户ID:" + slm.getFlagID());
            // *========数据库日志=========*//
            int res = systemLogMapper.insert(slm);
            if (res > 0) {
                System.out.println("SystemLogAspect保存日志成功");
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>保存日志成功");
            }
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("前置通知异常,保存日志异常信息:{}", e.getMessage());
        }
    }


    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "serviceAspect()", returning = "ret")
    public void doAfter(JoinPoint joinPoint, Map ret) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        try {
            // 请求的IP
            SystemLog slm = getControllerAndServiceMethodDescription(joinPoint, 2);

            String logIP = request.getRemoteAddr();
            slm.setLogIP(logIP);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String date = dateFormat.format(new Date());
            slm.setLogTime(date);

            //System.out.println(ret);

            slm.setFlagID(String.valueOf(ret.get("id")));
            //System.out.println(ret.get("id"));
            int res = systemLogMapper.insert(slm);
            if (res > 0) {
                System.out.println("SystemLogAspect保存日志成功");
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>保存日志成功");
            }
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("后置通知异常,保存日志异常信息:{}", e.getMessage());
        }
    }

}