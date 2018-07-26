package com.imooc.o2o.config.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.imooc.o2o.interceptor.shopadmin.ShopLoginInterceptor;
import com.imooc.o2o.interceptor.shopadmin.ShopPermissionInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;

/**
 * @Author: REX
 * @Date: Create in 22:31 2018/5/31
 */
@Configuration
// 相当于<mvc:annotation-driven/>
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 添加拦截器配置
	 *
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		String interceptPath = "/shopadmin/**";
		// 注册拦截器
		InterceptorRegistration loginIR = registry.addInterceptor(new ShopLoginInterceptor());
		// 配置拦截的路径
		loginIR.addPathPatterns(interceptPath);
		loginIR.excludePathPatterns("/shopadmin/addshopauthmap");
		// 注册其他拦截器
		InterceptorRegistration permissionIR = registry.addInterceptor(new ShopPermissionInterceptor());
		// 配置拦截路径
		permissionIR.addPathPatterns(interceptPath);
		// 配置无须拦截路径
		permissionIR.excludePathPatterns("/shopadmin/shoplist");
		permissionIR.excludePathPatterns("/shopadmin/getshoplist");

		permissionIR.excludePathPatterns("/shopadmin/getshopinitinfo");
		permissionIR.excludePathPatterns("/shopadmin/registershop");
		permissionIR.excludePathPatterns("/shopadmin/shopoperation");

		permissionIR.excludePathPatterns("/shopadmin/shopmanagement");
		permissionIR.excludePathPatterns("/shopadmin/getshopmanagementinfo");

		permissionIR.excludePathPatterns("/shopadmin/addshopauthmap");
	}

	/**
	 * 静态资源配置
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		无法加载静态资源 故注释下文
//		registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/baidu/work/image/upload/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:D:\\JavaDemo\\images\\upload\\");
	}

	/**
	 * 定义默认的请求处理器
	 *
	 * @param configurer
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 视图解析器
	 *
	 * @return
	 */
	@Bean(name = "viewResolver")
	public ViewResolver createViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 设置Spring容器
		viewResolver.setApplicationContext(this.applicationContext);
		// CANCEL THE CACHE
		viewResolver.setCache(false);
		// SET VIEW-RESOLVER PREFIX
		viewResolver.setPrefix("/WEB-INF/html/");
		// SET VIEW-RESOLVER SUFFIX
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	/**
	 * 文件上传解析器
	 *
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(20971520);
		return multipartResolver;
	}

	@Value("${kaptcha.border}")
	private String border;

	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;

	@Value("${kaptcha.image.width}")
	private String width;

	@Value("${kaptcha.image.height}")
	private String height;

	@Value("${kaptcha.textproducer.char.string}")
	private String cString;

	@Value("${kaptcha.textproducer.font.size}")
	private String fSize;

	@Value("${kaptcha.noise.color}")
	private String nColor;

	@Value("${kaptcha.textproducer.char.length}")
	private String cLength;

	@Value("${kaptcha.textproducer.font.names}")
	private String fNames;


	/**
	 * KaptchaServlet
	 *
	 * @return
	 * @throws ServletException
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() throws ServletException {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/Kaptcha");
		servlet.addInitParameter("kaptcha.border", border);
		servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
		servlet.addInitParameter("kaptcha.image.width", width);
		servlet.addInitParameter("kaptcha.image.height", height);
		servlet.addInitParameter("kaptcha.textproducer.char.string", cString);
		servlet.addInitParameter("kaptcha.textproducer.font.size", fSize);
		servlet.addInitParameter("kaptcha.noise.color", nColor);
		servlet.addInitParameter("kaptcha.textproducer.char.length", cLength);
		servlet.addInitParameter("kaptcha.textproducer.font.names", fNames);
		return servlet;
	}
}
