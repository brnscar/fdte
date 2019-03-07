package br.com.springmvc.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfig {

		
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory () {	
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean ();


		JpaVendorAdapter VendoAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(VendoAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root"); // modifique para a senha do seu banco
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvc");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		factoryBean.setDataSource(dataSource);
		
		java.util.Properties props = new java.util.Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        factoryBean.setJpaProperties(props);

        factoryBean.setPackagesToScan("br.com.springmvc.models");

 
		return factoryBean;
		
	}
	
		@Bean
		public JpaTransactionManager TransactionManager(EntityManagerFactory emf){
			return new JpaTransactionManager(emf);
		}
		
	
	
}
