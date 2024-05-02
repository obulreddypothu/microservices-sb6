package com.obulreddy.departmentservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.obulreddy.departmentservice.client.EmployeeClient;

@Configuration
public class WebClientConfig {

	@Autowired
	private LoadBalancedExchangeFilterFunction filerFunction;

	@Bean
	public WebClient employeeWebCLient() {
		return WebClient.builder().baseUrl("http://employee-service").filter(filerFunction).build();
	}
	@Bean
	public EmployeeClient employeeClient() {
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(employeeWebCLient())).build();
		return httpServiceProxyFactory.createClient(EmployeeClient.class);
	}

}
