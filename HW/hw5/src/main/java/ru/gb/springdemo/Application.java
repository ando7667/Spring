package ru.gb.springdemo;
/* **********************************************************************

 * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями) добавить следующие рерурсы,
 * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
 * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе
 * 1.2 /ui/reader - аналогично 1.1
 * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
 * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках у этого читателя
 *
 * Чтобы шаблонизатор thymeleaf заработал, то нужно добавить зависимость в pom.xml:
 *        <dependency>
 *            <groupId>org.springframework.boot</groupId>
 *            <artifactId>spring-boot-starter-thymeleaf</artifactId>
 *        </dependency>
 ********************************************************************** */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.gb.springdemo.service.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application {

	/*
	 * Домашнее задание:
	 * 1. Подключить базу данных к проекту "библиотека", который разрабатывали на прошлых уроках.
	 * 1.1 Подключить spring-boot-starter-data-jpa (и необходимый драйвер) и указать параметры соединения в application.yml
	 * 1.2 Для книги, читателя и факта выдачи описать JPA-сущности
	 * 1.3 Заменить самописные репозитории на JPA-репозитории
	 *
	 * Замечание: базу данных можно использовать любую (h2, mysql, postgres).
	 *
	 */


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println("Сервер запущен и слушает порт 8190");

	}

}
