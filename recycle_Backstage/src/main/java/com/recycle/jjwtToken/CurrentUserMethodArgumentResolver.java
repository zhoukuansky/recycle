package com.recycle.jjwtToken;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * Demo class
 *
 * @author zhoukuan
 * @date 2019/2/12
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //System.out.println("000000000000000000000000000000000000000000000");
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String[] tokenData = (String[]) nativeWebRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
//        if (tokenData==null) {
//            user = "游客";
//        }
        return tokenData;
    }
}
