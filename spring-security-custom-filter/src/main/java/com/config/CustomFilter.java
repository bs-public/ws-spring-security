package com.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Spring automatically detect this filter & create the bean as it implements the Servlet Filter
 */
@Component
public class CustomFilter implements Filter {

  private static Logger logger = LoggerFactory.getLogger(CustomFilter.class);

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    logger.info("init method invoked");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    logger.info("doFilter method invoked");
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    Principal userPrincipal = httpServletRequest.getUserPrincipal();
    logger.info("subject: {}", userPrincipal.getName());

    /*
     * Use can customize authorization based upon role here. Now if use want to allow user to hit
     * the rest end point then pass request to filter chain.
     */
    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    logger.info("destroy method invoked");
  }

}
