package org.example.filterexam;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
//필터 자체를 요청 할 수 는 없다.
//필터는 어떤 요청을 처리하기 전,  후 에 해야 할 일들을 처리 할 수 잇다.
@Slf4j
@Component
@WebFilter(urlPatterns = "/hi")
public class FirstFilter implements Filter {
    public FirstFilter() {
        log.info("FirstFilter 생성자 () 실행!!");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilter init () 실행!!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("FirstFilter doFilter () 실행 전 실행!!");

        filterChain.doFilter(servletRequest,servletResponse);

        log.info("FirstFilter doFilter () 실행 후 실행!!");
    }

    @Override
    public void destroy() {
        log.info("FirstFilter destroy () 실행!!");
    }
}
