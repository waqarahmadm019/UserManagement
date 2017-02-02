package com.usersmanagement.config;

	import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *  UserManagementConfig : 
 *  Spring MVC enabled via @EnableWebMvc. 
 *  @EnableWebMvc-annotated configuration classes may implement this interface to be called back and given a chance to customize the default configuration. 
 *  Set Data source, sessionFactory, Transaction manager and all beans.
 * 
 * @author Waqar
 */

	@EnableWebMvc
	@Configuration
	@EnableJpaRepositories
	@ComponentScan({ "com.usersmanagement.*", "com.usersmanagement.controller", "com.usersmanagement.dao", "com.usersmanagement.service"})
	@EnableTransactionManagement
	@EnableAspectJAutoProxy
	
	public class UserManagementConfig extends WebMvcConfigurerAdapter{

		private static final Logger logger = Logger.getLogger(UserManagementConfig.class);
//	        @Bean
//	        public SessionFactory sessionFactory() {
//	                LocalSessionFactoryBuilder builder = 
//				new LocalSessionFactoryBuilder(dataSource());
//	                builder.scanPackages("com.usersmanagement.model")
//	                      .addProperties(getHibernateProperties());
//
//	                return builder.buildSessionFactory();
//	        }
	        
	        /**FactoryBean that creates a JPA EntityManagerFactory according to JPA's standard container bootstrap contract. 
	         * This is the most powerful way to set up a shared JPA EntityManagerFactory in a Spring application context; 
	         * the EntityManagerFactory can then be passed to JPA-based DAOs via dependency injection. 
	         * Note that switching to a JNDI lookup or to a LocalEntityManagerFactoryBean definition is just a matter of configuration!
	         * JpaVendorAdapter interface :SPI interface that allows to plug in vendor-specific behavior 
	         * into Spring's EntityManagerFactory creators. 
	         * Serves as single configuration point for all vendor-specific properties.
	         * @return
	         */
	        @Bean
	        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	           LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	           emf.setDataSource(dataSource());
	           emf.setPackagesToScan(new String[] { "com.usersmanagement.model" });
	      
	           JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	           emf.setJpaVendorAdapter(vendorAdapter);
	           emf.setJpaProperties(getHibernateProperties());
	      
	           return emf;
	        }  
	        
	        @Bean
	        public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	           JpaTransactionManager transactionManager = new JpaTransactionManager();
	           transactionManager.setEntityManagerFactory(emf);
	      
	           return transactionManager;
	        } 
	        
	        @Bean
	        public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	           return new PersistenceExceptionTranslationPostProcessor();
	        } 

		private Properties getHibernateProperties() {
	                Properties prop = new Properties();
	                prop.put("hibernate.format_sql", "true");
	                prop.put("hibernate.show_sql", "true");
	                prop.put("hibernate.dialect", 
	                    "org.hibernate.dialect.PostgreSQLDialect");
	                return prop;
	        }
		
		@Bean(name = "dataSource")
		public DriverManagerDataSource dataSource() {
			logger.debug("UserManagementConfig::dataSource");
			DriverManagerDataSource ds = new DriverManagerDataSource();
		        ds.setDriverClassName("org.postgresql.Driver");
		        ds.setUrl("jdbc:postgresql://localhost:5432/usermanagement");
			
			ds.setUsername("postgres");
			ds.setPassword("admin");
			return ds;
		}
		
		//Create a transaction manager
//		@Bean
//	        public HibernateTransactionManager txManager() {
//	                return new HibernateTransactionManager(sessionFactory());
//	        }
		
//		@Override
//	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//	        configurer.enable();
//	    }
			
//		@Bean
//		public InternalResourceViewResolver viewResolver() {
//			InternalResourceViewResolver viewResolver 
//	                             = new InternalResourceViewResolver();
//			viewResolver.setViewClass(JstlView.class);
//			viewResolver.setPrefix("/WEB-INF/");
//			viewResolver.setSuffix(".jsp");
//			return viewResolver;
//		}
		
		@Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/pages/");
	        viewResolver.setSuffix(".jsp");
	        registry.viewResolver(viewResolver);
	    }
	 
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	    }
		
		
	}
