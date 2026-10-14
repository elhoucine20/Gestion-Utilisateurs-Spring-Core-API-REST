package config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

   // WebInitailizer cree le DispatcherServlet
    public class WebInitailizer extends AbstractAnnotationConfigDispatcherServletInitializer{ // configurer servlet dans depart de l'app (root et web)
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() { // avec beans
        return new Class[] {RootConfig.class} ;
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() { // ave web (controlles)
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() { // requits http
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext)throws ServletException{
        super.onStartup(servletContext);
        ServletRegistration.Dynamic h2 = servletContext.addServlet("h2-console","org.h2.server.web.JakartaWebServlet"); //
        h2.addMapping("/h2-console/*"); //
        h2.setInitParameter("-ifNotExists", "");
        h2.setLoadOnStartup(2); //
    }
}
