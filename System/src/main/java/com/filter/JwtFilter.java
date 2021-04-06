package com.filter;

import com.constant.Constants;
import com.entity.LoginUser;
import com.exception.JwtException;
import com.frame.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Value("${potic.whiteList}")
    private String whiteList;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(Constants.TOKEN);
        if(token != null){
            ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(httpServletRequest.getServletContext());
            TokenService tokenService = (TokenService) ac2.getBean("tokenService");
            LoginUser loginUser = tokenService.getLoginUser(token);
            if(loginUser == null || !tokenService.verifyToken(loginUser)){
                httpServletRequest.setAttribute("exception", new JwtException("token授权过期,请重新登录!"));
                httpServletRequest.getRequestDispatcher("/JwtError").forward(httpServletRequest, httpServletResponse);
                return;
            }

            PathMatcher matcher = new AntPathMatcher();
            for(String url : loginUser.getPermission()){
                if(matcher.match(url,httpServletRequest.getRequestURI())){
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                    return;
                }
            }
            httpServletRequest.setAttribute("exception", new JwtException("权限不足,请联系管理员!"));
            httpServletRequest.getRequestDispatcher("/JwtError").forward(httpServletRequest, httpServletResponse);
        }else if(isWhiteList(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else {
            httpServletRequest.setAttribute("exception", new JwtException("未登录,请先登录!"));
            httpServletRequest.getRequestDispatcher("/JwtError").forward(httpServletRequest, httpServletResponse);
        }
    }

    private boolean isWhiteList(String url){
        String[] listWhite = whiteList.split(";");
        PathMatcher matcher = new AntPathMatcher();
        for(String whiteUrl : listWhite){
            if(matcher.match(url,whiteUrl)){
                return true;
            }
        }
        return false;
    }
}
