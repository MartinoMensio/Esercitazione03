package it.polito.ai.lab3;

import org.springframework.context.annotation.*;

import it.polito.ai.lab3.entities.*;
import it.polito.ai.lab3.services.routing.Path;
import it.polito.ai.lab3.services.routing.RoutingService;
import it.polito.ai.lab3.services.routing.RoutingServiceImpl;

@Configuration
@ComponentScan(basePackages={"it.polito.ai.lab3.entities"})
public class RootConfig {
	// TODO define beans
	@Bean
	public TestEntity testEntity() {
		return new TestEntity();
	}
	
	@Bean
	public RoutingService routingService(){
		return new RoutingServiceImpl();
	}
}
