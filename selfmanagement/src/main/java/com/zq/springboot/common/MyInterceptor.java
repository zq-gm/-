package com.zq.springboot.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle被调用");
        if(httpServletRequest.getRequestURI().equals("/login")) {
        	return true;
        }
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
        	try {
				String id = stringRedisTemplate.opsForValue().get(token)+ "";
				if(id.equals("null") || id.equals("")) {
					System.out.println("token验证失败");
				    returnJson(httpServletResponse);    
				    return false;
				}else { 
				    return true;    //如果false，停止流程，api被拦截
				}
			} catch (Exception e) {
				System.out.println("token验证失败");
	            returnJson(httpServletResponse);    
	            return false; 
			}
        } else {
        	System.out.println("token验证失败");
            returnJson(httpServletResponse);    
            return false; 
        }
        
    }
    private void returnJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            MessageResult<String> result = new MessageResult<String>();
            result.setMessage("没有token或token无效");
            result.setStatus(301);
            result.setSuccessed(false);
            JSONObject jsonObject = new JSONObject(result);
            writer.print(jsonObject);
        } catch (IOException e){
           e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
