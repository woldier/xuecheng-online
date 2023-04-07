package com.xuecheng.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;
/**
*
* description 解决post请求被csrf拦截
*
* @author: woldier
* @date: 2023/4/7 8:34
*/
@Component
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private static final Pattern allowedMethods = Pattern
            .compile("^(GET|HEAD|TRACE|OPTIONS)$");

    /**
    *
    * description match方法
    *
    * @param request
    * @return boolean
    * @author: woldier
    * @date: 2023/4/7 8:40
    */
    @Override
    public boolean matches(HttpServletRequest request) {

        if (execludeUrls != null && execludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            for (String url : execludeUrls) {
                if (servletPath.contains(url)) {
                    return false;
                }
            }
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    /**
     * 需要排除的url列表
     */
    @Value("${spring-csrf}")
    private List<String> execludeUrls;

    public List<String> getExecludeUrls() {
        return execludeUrls;
    }
}