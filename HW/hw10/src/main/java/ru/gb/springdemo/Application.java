package ru.gb.springdemo;
/* **********************************************************************
 * Домашнее задание:
 * В проекте библиотека написать полноценные API-тесты (с поднятием БД в h2 и WebTestClient) на все ресурсы проекта, т.е.
 * Получение книги, читателя, выдачи, создание книги, читателя, ресурса, ...
 *
 * Чтобы не париться с безопасностью, рекомендую ее выключить в тестах.
 *
 *
 ********************************************************************** */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gb.springdemo.service.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println("Сервер запущен и слушает порт 8190");

	}
//
//  Внедряем кодирование пароля
//	@Autowired
//	public void configureAUthManagerBuilder(AuthenticationManagerBuilder builder, UserDetailsService service) throws Exception {
//		builder.userDetailsService(service);
//	}

}
