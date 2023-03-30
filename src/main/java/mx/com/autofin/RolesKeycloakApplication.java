package mx.com.autofin;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class RolesKeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolesKeycloakApplication.class, args);
	}
	
	@Bean
	 public GroupedOpenApi publicApi(){
		 return GroupedOpenApi.builder()
				 .group("springshop-public")
				 .packagesToScan("mx.com.autofin")
				 .build();
	 }

}
