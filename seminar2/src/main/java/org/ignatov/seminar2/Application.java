package org.ignatov.seminar2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);

		System.out.println(userRepository.getAll());
		System.out.println(userRepository.getById(2));
	}

}
