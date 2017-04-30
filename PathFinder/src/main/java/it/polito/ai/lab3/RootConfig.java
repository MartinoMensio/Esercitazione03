package it.polito.ai.lab3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import it.polito.ai.lab3.services.routing.RoutingService;
import it.polito.ai.lab3.services.routing.RoutingServiceImpl;

@Configuration
@EnableJpaRepositories
@ComponentScan(basePackages={"it.polito.ai.lab3.services"})
public class RootConfig {
	// TODO define beans
	@Bean
	public RoutingService routingService(){
		return new RoutingServiceImpl();
	}
	
	@Bean
	public DataSource dataSource() {
		String server = null;
		try {
			List<String> lines = Files.readAllLines(Paths.get(
					RootConfig.class.getClassLoader().getResource("db_ip.txt").toURI().toString().substring(6)));
			server = lines.get(0);
		} catch (Exception e) {
			server = "localhost";
		}
		
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://" + server + ":5432/trasporti");
		ds.setUsername("postgres");
		ds.setPassword("ai-user-password");
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform("org.hibernate.spatial.dialect.postgis.PostgisDialect");
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("it.polito.ai.lab3.repo.entities");
		factory.setDataSource(dataSource);
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(factory);
		return txManager;
	}
}
