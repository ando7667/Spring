-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               10.4.32-MariaDB - mariadb.org binary distribution
-- Операционная система:         Win64
-- HeidiSQL Версия:              12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дамп структуры базы данных library
DROP DATABASE IF EXISTS `library`;
CREATE DATABASE IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `library`;

-- Дамп структуры для таблица library.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы library.book: ~20 rows (приблизительно)
DELETE FROM `book`;
INSERT INTO `book` (`id`, `name`, `author`, `year`) VALUES
	(1, 'Книга 0', 'Автор 0', 'год 0'),
	(2, 'Книга 1', 'Автор 1', 'год 1'),
	(3, 'Книга 2', 'Автор 2', 'год 2'),
	(4, 'Книга 3', 'Автор 3', 'год 3'),
	(5, 'Книга 4', 'Автор 4', 'год 4'),
	(6, 'Книга 5', 'Автор 5', 'год 5'),
	(7, 'Книга 6', 'Автор 6', 'год 6'),
	(8, 'Книга 7', 'Автор 7', 'год 7'),
	(9, 'Книга 8', 'Автор 8', 'год 8'),
	(10, 'Книга 9', 'Автор 9', 'год 9'),
	(11, 'Книга 10', 'Автор 0', 'год 0'),
	(12, 'Книга 11', 'Автор 1', 'год 1'),
	(13, 'Книга 12', 'Автор 2', 'год 2'),
	(14, 'Книга 13', 'Автор 3', 'год 3'),
	(15, 'Книга 14', 'Автор 4', 'год 4'),
	(16, 'Книга 15', 'Автор 5', 'год 5'),
	(17, 'Книга 16', 'Автор 6', 'год 6'),
	(18, 'Книга 17', 'Автор 7', 'год 7'),
	(19, 'Книга 18', 'Автор 8', 'год 8'),
	(20, 'Книга 19', 'Автор 9', 'год 9');

-- Дамп структуры для таблица library.book_seq
DROP TABLE IF EXISTS `book_seq`;
CREATE TABLE IF NOT EXISTS `book_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы library.book_seq: ~0 rows (приблизительно)
DELETE FROM `book_seq`;
INSERT INTO `book_seq` (`next_val`) VALUES
	(1);

-- Дамп структуры для таблица library.issues
DROP TABLE IF EXISTS `issues`;
CREATE TABLE IF NOT EXISTS `issues` (
  `id` bigint(20) NOT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `reader_id` bigint(20) DEFAULT NULL,
  `timeissue` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Выдачи';

-- Дамп данных таблицы library.issues: ~2 rows (приблизительно)
DELETE FROM `issues`;
INSERT INTO `issues` (`id`, `book_id`, `reader_id`, `timeissue`) VALUES
	(1, 16, 2, '2024-02-05 13:17:36'),
	(2, 10, 1, '2024-02-05 13:51:11');

-- Дамп структуры для таблица library.issues_seq
DROP TABLE IF EXISTS `issues_seq`;
CREATE TABLE IF NOT EXISTS `issues_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы library.issues_seq: ~0 rows (приблизительно)
DELETE FROM `issues_seq`;
INSERT INTO `issues_seq` (`next_val`) VALUES
	(1);

-- Дамп структуры для таблица library.readers
DROP TABLE IF EXISTS `readers`;
CREATE TABLE IF NOT EXISTS `readers` (
  `id` bigint(20) NOT NULL,
  `name` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Читатели';

-- Дамп данных таблицы library.readers: ~4 rows (приблизительно)
DELETE FROM `readers`;
INSERT INTO `readers` (`id`, `name`) VALUES
	(1, 'Игорь'),
	(2, 'Антон'),
	(3, 'Олег'),
	(4, 'Дмитрий');

-- Дамп структуры для таблица library.readers_seq
DROP TABLE IF EXISTS `readers_seq`;
CREATE TABLE IF NOT EXISTS `readers_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы library.readers_seq: ~0 rows (приблизительно)
DELETE FROM `readers_seq`;
INSERT INTO `readers_seq` (`next_val`) VALUES
	(1);

-- Дамп структуры для таблица library.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL,
  `role` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='роли';

-- Дамп данных таблицы library.roles: ~4 rows (приблизительно)
DELETE FROM `roles`;
INSERT INTO `roles` (`id`, `role`) VALUES
	(1, 'admin'),
	(2, 'user'),
	(3, 'auth'),
	(4, 'simple');

-- Дамп структуры для таблица library.roles_seq
DROP TABLE IF EXISTS `roles_seq`;
CREATE TABLE IF NOT EXISTS `roles_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы library.roles_seq: ~1 rows (приблизительно)
DELETE FROM `roles_seq`;
INSERT INTO `roles_seq` (`next_val`) VALUES
	(1);

-- Дамп структуры для таблица library.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL,
  `login` tinytext DEFAULT NULL,
  `password` tinytext DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='пользователи';

-- Дамп данных таблицы library.users: ~4 rows (приблизительно)
DELETE FROM `users`;
INSERT INTO `users` (`id`, `login`, `password`, `role`) VALUES
	(1, 'admin', 'admin', 'admin'),
	(2, 'user', 'user', 'user'),
	(3, 'auth', 'auth', 'auth'),
	(4, 'simple', 'simple', 'simple');

-- Дамп структуры для таблица library.users_seq
DROP TABLE IF EXISTS `users_seq`;
CREATE TABLE IF NOT EXISTS `users_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы library.users_seq: ~1 rows (приблизительно)
DELETE FROM `users_seq`;
INSERT INTO `users_seq` (`next_val`) VALUES
	(101);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
