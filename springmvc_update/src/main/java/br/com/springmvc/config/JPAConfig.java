package br.com.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.sun.xml.internal.fastinfoset.sax.Properties;

public class JPAConfig {

	@Bean
	public void entityManagerFactory () {	
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean ();


		JpaVendorAdapter VendoAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(VendoAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root"); // modifique para a senha do seu banco
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvc");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		factoryBean.setDataSource(dataSource);
		
		
	}
}
