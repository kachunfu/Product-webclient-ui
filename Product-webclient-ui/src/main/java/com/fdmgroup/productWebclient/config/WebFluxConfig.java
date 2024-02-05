package com.fdmgroup.productWebclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements ApplicationContextAware, WebFluxConfigurer{
	
	Logger logger = LoggerFactory.getLogger(WebFluxConfig.class);
	
	private ApplicationContext ctx;
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
	}
	
	
	//Setup for UI
	
	 @Override
	    public void setApplicationContext(ApplicationContext context) {
	        this.ctx = context;
	    }
	 
	 @Bean
	    public SpringResourceTemplateResolver thymeleafTemplateResolver() {

	        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	        resolver.setApplicationContext(this.ctx);
	        resolver.setPrefix("classpath:/templates/");
	        resolver.setSuffix(".html");
	        resolver.setTemplateMode(TemplateMode.HTML);
	        resolver.setCacheable(false);
	        resolver.setCheckExistence(false);
	        return resolver;

	    }
	 

	 
	 @Bean
	    public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
	        // We override here the SpringTemplateEngine instance that would otherwise be
	        // instantiated by
	        // Spring Boot because we want to apply the SpringWebFlux-specific context
	        // factory, link builder...
	        final SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
	        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
	        return templateEngine;
	    }
	 
	 @Bean
	    public ThymeleafReactiveViewResolver thymeleafChunkedAndDataDrivenViewResolver() {
	        final ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
	        viewResolver.setTemplateEngine(thymeleafTemplateEngine());
//	        viewResolver.setOrder(1);
//	        viewResolver.setViewNames(new String[]{"home"});
	        viewResolver.setResponseMaxChunkSizeBytes(8192); // OUTPUT BUFFER size limit
	        return viewResolver;
	    }

	    @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        registry.viewResolver(thymeleafChunkedAndDataDrivenViewResolver());
	    }
	 
	 
}
