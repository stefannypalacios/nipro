package sv.com.nipro.interfaz;


import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.ServletContextAware;

import com.sun.faces.config.ConfigureListener;

@SpringBootApplication(scanBasePackages={"sv.com.nipro.interfaz.controller"})
@EnableJpaAuditing
public class InterfazApplication implements ServletContextAware{

	public static void main(String[] args) {
		SpringApplication.run(InterfazApplication.class, args);
	}
	
	@Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
            new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(
            new ConfigureListener());
    }

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		
	}

}
