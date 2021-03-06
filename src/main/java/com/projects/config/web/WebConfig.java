/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projects.config.web;

import cn.stylefeng.roses.core.xss.XssFilter;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.projects.config.properties.GunsProperties;
import com.projects.core.common.controller.GunsErrorView;
import com.projects.core.interceptor.AttributeSetInteceptor;
import com.projects.core.interceptor.RestApiInteceptor;
import com.projects.core.listener.ConfigListener;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

import static com.projects.core.common.constant.Const.NONE_PERMISSION_RES;

/**
 * web ?????????
 *
 * @author fengshuonan
 * @date 2016???11???12??? ??????5:03:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private GunsProperties gunsProperties;

	/**
	 * ??????????????????
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (gunsProperties.getSwaggerOpen()) {

			// swagger
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}

		// ?????????
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:" + gunsProperties.getFileUploadPath());
	}

	/**
	 * ?????????rest api?????????spring mvc?????????
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RestApiInteceptor()).addPathPatterns("/api/**");
		registry.addInterceptor(new AttributeSetInteceptor()).excludePathPatterns(NONE_PERMISSION_RES)
				.addPathPatterns("/**");
	}

	/**
	 * ???????????????????????????json
	 */
	@Bean("error")
	public GunsErrorView error() {
		return new GunsErrorView();
	}

	/**
	 * druidServlet??????
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet());
		registration.addUrlMappings("/druid/*");
		return registration;
	}

	/**
	 * druid?????? ??????URI????????????
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// ??????????????????.
		filterRegistrationBean.addUrlPatterns("/*");
		// ????????????????????????????????????.
		filterRegistrationBean.addInitParameter("exclusions",
				"/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*");
		// ??????session?????????????????????????????? ????????????????????????username?????????session???
		filterRegistrationBean.addInitParameter("principalSessionName", "username");
		return filterRegistrationBean;
	}

	/**
	 * druid????????????????????????
	 */
	@Bean
	public DruidStatInterceptor druidStatInterceptor() {
		return new DruidStatInterceptor();
	}

	@Bean
	public JdkRegexpMethodPointcut druidStatPointcut() {
		JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
		String patterns = "com.projects.modular.*.service.*";
		// ??????set??????
		druidStatPointcut.setPatterns(patterns);
		return druidStatPointcut;
	}

	/**
	 * druid????????????????????????
	 */
	@Bean
	public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator() {
		BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
		beanTypeAutoProxyCreator.setTargetBeanType(DruidDataSource.class);
		beanTypeAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
		return beanTypeAutoProxyCreator;
	}

	/**
	 * druid ???druidStatPointcut????????????
	 *
	 * @return
	 */
	@Bean
	public Advisor druidStatAdvisor() {
		return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
	}

	/**
	 * xssFilter??????
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		XssFilter xssFilter = new XssFilter();
		// ?????????????????????xss???????????????
		// xssFilter.setUrlExclusion(Arrays.asList("/notice/update", "/notice/add"));
		FilterRegistrationBean registration = new FilterRegistrationBean(xssFilter);
		registration.addUrlPatterns("/*");
		return registration;
	}

	/**
	 * RequestContextListener??????
	 */
	@Bean
	public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
		return new ServletListenerRegistrationBean<>(new RequestContextListener());
	}

	/**
	 * ConfigListener??????
	 */
	@Bean
	public ServletListenerRegistrationBean<ConfigListener> configListenerRegistration() {
		return new ServletListenerRegistrationBean<>(new ConfigListener());
	}

	/**
	 * ?????????????????????
	 */
	@Bean
	public DefaultKaptcha kaptcha() {
		Properties properties = new Properties();
		properties.put("kaptcha.border", "no");
		properties.put("kaptcha.border.color", "105,179,90");
		properties.put("kaptcha.textproducer.font.color", "blue");
		properties.put("kaptcha.image.width", "125");
		properties.put("kaptcha.image.height", "45");
		properties.put("kaptcha.textproducer.font.size", "45");
		properties.put("kaptcha.session.key", "code");
		properties.put("kaptcha.textproducer.char.length", "4");
		properties.put("kaptcha.textproducer.font.names", "??????,??????,????????????");
		Config config = new Config(properties);
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
