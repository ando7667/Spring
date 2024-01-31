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

@SpringBootApplication
public class Application {

	/*
	 * слои spring-приложения
	 *
	 * 1. controllers (api)
	 * 2. сервисный слой (services)
	 * 3. репозитории (repositories, dao (data access objects), ...)
	 * 4. jpa-сущности (entity, model, domain)
	 *
	 *
	 * Сервер, отвечающий за выдачу книг в библиотеке.
	 * Нужно напрограммировать ручку, которая либо выдает книгу читателями, либо отказывает в выдаче.
	 *
	 * /book/** - книга
	 * GET /book/25 - получить книгу с идентификатором 25
	 *
	 * /reader/** - читатель
	 * /issue/** - информация о выдаче
	 * POST /issue {"readerId": 25, "bookId": 57} - выдать читателю с идентификатором 25 книгу с идентификатором 57
	 *
	 */


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println("Сервер запущен и слушает порт 8190");

	}

}
