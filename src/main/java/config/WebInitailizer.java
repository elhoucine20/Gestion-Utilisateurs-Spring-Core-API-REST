package config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
}
