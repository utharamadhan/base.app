package id.base.app.site.thymeleaf;

import id.base.app.site.ParameterLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.code.kaptcha.servlet.KaptchaServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer{
	@Override
	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(ApplicationContextConfig.class);
		
		container.addListener(new ContextLoaderListener(appContext));
		container.addListener(new ParameterLoader(appContext));

		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"SpringDispatcher", new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(3);
		dispatcher.addMapping("/page/*");
		
		ServletRegistration.Dynamic kaptcha = container.addServlet("KaptchaServlet", new KaptchaServlet());
		kaptcha.setLoadOnStartup(4);
		kaptcha.addMapping("/kaptcha");
	}
}
