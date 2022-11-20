package com.ecocarbonltd.AEDParcer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AedParcerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AedParcerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner lookup(ParcerService service){
//		return args -> {
//			service.getCourse("2022-11-17");
//		};
//	}

}
