package com.shenchangxin.community.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shenchangxin.community.auth.MyUserDetailsService;
import com.shenchangxin.community.exception.ErrorDetails;
import com.shenchangxin.community.utils.JsonWebTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JsonWebTokenRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;
        ErrorDetails errorDetails = null;

        try{
            if( (authorizationHeader !=null) && StringUtils.startsWithIgnoreCase(authorizationHeader,"Bearer ") ){
                jwt = StringUtils.substring(authorizationHeader,7);
                username = jsonWebTokenUtil.getUsernameFromToken(jwt);
            }

            if( username!=null && SecurityContextHolder.getContext().getAuthentication() == null ){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jsonWebTokenUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }catch (ExpiredJwtException ex){
            errorDetails = new ErrorDetails("登录过期","请检查JWT是否过期");
            responseWithErrorDetails(httpServletResponse,errorDetails,HttpStatus.UNAUTHORIZED);
            return;
        }catch (Exception ex){
            errorDetails = new ErrorDetails("未知错误","登录出现未知错误");
            responseWithErrorDetails(httpServletResponse,errorDetails,HttpStatus.UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }


    private void responseWithErrorDetails(HttpServletResponse httpServletResponse, ErrorDetails errorDetails, HttpStatus httpStatus) throws IOException {
        String content = null;

        if (errorDetails == null) {
            content = objectMapper.writeValueAsString(new HashMap<>());
        }else{
            content = objectMapper.writeValueAsString(errorDetails);
        }

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setStatus(httpStatus.value());
        httpServletResponse.getWriter().write(content);
    }

}

