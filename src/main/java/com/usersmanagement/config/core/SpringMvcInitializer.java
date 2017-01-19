package com.usersmanagement.config.core;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.usersmanagement.config.UserManagementConfig;
import javax.servlet.Filter;

/**
 * SpringMvcInitializer for org.springframework.web.WebApplicationInitializer implementations 
 * that register a DispatcherServlet configured with annotated classes, e.g. Spring's @Configuration classes. 
 * Concrete implementations are required to implement getRootConfigClasses() 
 * and getServletConfigClasses() as well as getServletMappings(). 
 * Further template and customization methods are provided by AbstractDispatcherServletInitializer.


 * 
 * @author Waqar
 */

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { UserManagementConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
	
}