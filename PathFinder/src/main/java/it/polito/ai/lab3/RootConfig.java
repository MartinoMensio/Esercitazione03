package it.polito.ai.lab3;

import org.springframework.context.annotation.*;

import it.polito.ai.lab3.entities.*;

@Configuration
@ComponentScan(basePackages={"it.polito.ai.lab3.entities"})
public class RootConfig {
	// TODO define beans
	@Bean
	public TestEntity testEntity() {
		return new TestEntity();
	}
}
