package com.sprphnx.filter;

import javax.servlet.Filter;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//reference:https://forum.camunda.org/t/turn-on-basic-http-authentication-for-rest-api-in-spring-boot/3431

@Configuration
public class CamundaSecurityFilter {

	@Bean
	public FilterRegistrationBean<Filter> processEngineAuthenticationFilter() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		registration.setName("camunda-auth");
		registration.setFilter(getProcessEngineAuthenticationFilter());
		registration.addInitParameter("authentication-provider",
				"org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public Filter getProcessEngineAuthenticationFilter() {
		return new ProcessEngineAuthenticationFilter();
	}
}