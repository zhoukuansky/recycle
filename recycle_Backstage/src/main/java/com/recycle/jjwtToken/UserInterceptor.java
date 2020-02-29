package com.recycle.jjwtToken;

import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class UserInterceptor implements HandlerInterceptor {
    //    @Autowired
//    private UserService service;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
//        if (!(object instanceof HandlerMethod)) {
//            return true;
//        }

//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
        if (token.isEmpty()) {
            throw new DescribeException(ExceptionEnum.NEED_LOGIN);
        }
        // 执行认证
        Map<String, Object> claims = JwtToken.verifyToken(token);
        if (claims == null) {
            throw new DescribeException(ExceptionEnum.TOKEN_OUTTIME);
        }
        //System.out.println("1111111111111111111111111111111");//登录接口不经过这儿
        String tokenData[] = new String[2];
        tokenData[0] = claims.get("type").toString();
        tokenData[1] = claims.get("id").toString();
        httpServletRequest.setAttribute("currentUser", tokenData);
        //System.out.println(claims);//包括id,type,和过期时间
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}