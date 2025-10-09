-- MySQL dump 10.13  Distrib 8.4.6, for Linux (x86_64)
--
-- Host: localhost    Database: librarydb
-- ------------------------------------------------------
-- Server version	8.4.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7796896 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1052064,'Mark Twain','1835-11-30','American'),(2663176,'Franz Kafka','1883-07-03','Austrian'),(4280274,'Gabriel García Márquez','1927-03-06','Colombian'),(5452825,'Jane Austen','1775-12-16','British'),(5850790,'Fyodor Dostoevsky','1821-11-11','Russian'),(6604239,'Ernest Hemingway','1899-07-21','American'),(7796895,'Haruki Murakami','1949-01-12','Japanese');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `isbn` varchar(13) NOT NULL,
  `author_id` bigint DEFAULT NULL,
  `published_date` date DEFAULT NULL,
  `available_copies` int DEFAULT '0',
  `total_copies` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `fk_books_authors` (`author_id`),
  CONSTRAINT `fk_books_authors` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9901899 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (2596550,'The Metamorphosis','9780140184780',2663176,'1915-10-15',8,8),(3284128,'Kafka on the Shore','9781400079278',7796895,'2002-09-12',10,10),(3402428,'For Whom the Bell Tolls','9780684803357',6604239,'1940-10-21',9,9),(4201035,'Norwegian Wood','9780375704024',7796895,'1987-09-04',11,11),(4814376,'Crime and Punishment','9780143058144',5850790,'1866-01-15',12,13),(5021215,'The Adventures of Tom Sawyer','9780143039563',1052064,'1876-06-01',12,12),(6087416,'The Old Man and the Sea','9780684801223',6604239,'1952-09-01',10,10),(6294617,'Love in the Time of Cholera','9780307389732',4280274,'1985-09-05',2,2),(6315802,'The Trial','9780805209990',2663176,'1925-04-26',7,7),(7580863,'Pride and Prejudice','9780141439518',5452825,'1813-01-28',14,14),(7930992,'One Hundred Years of Solitude','9780060883287',4280274,'1967-05-30',15,15),(9578087,'Sense and Sensibility','9780141439662',5452825,'1811-10-30',11,11),(9880680,'The Brothers Karamazov','9780374528379',5850790,'1880-11-01',12,12),(9901898,'Adventures of Huckleberry Finn','9780143107323',1052064,'1884-12-10',10,10);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `loan_date` date DEFAULT (curdate()),
  `return_date` date DEFAULT NULL,
  `status` enum('BORROWED','RETURNED','OVERDUE') DEFAULT 'BORROWED',
  PRIMARY KEY (`id`),
  KEY `fk_loans_books` (`book_id`),
  KEY `fk_loans_members` (`member_id`),
  CONSTRAINT `fk_loans_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_loans_members` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9147053 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1248004,9901898,7444114,'2025-08-25','2025-10-09','RETURNED'),(1248009,9880680,7444114,'2025-08-25','2025-10-09','RETURNED'),(1248593,2596550,8629972,'2025-09-25',NULL,'BORROWED'),(1275849,9880680,1111111,'2025-09-15','2025-09-25','RETURNED'),(1836240,3402428,5353509,'2025-09-18',NULL,'OVERDUE'),(2064981,7580863,7444114,'2025-09-20','2025-09-28','RETURNED'),(2956043,9880680,5514533,'2025-09-28',NULL,'BORROWED'),(3149586,6087416,9812711,'2025-09-05',NULL,'OVERDUE'),(3633505,4814376,7444114,'2025-10-09',NULL,'BORROWED'),(4004778,2596550,7444114,'2025-10-09','2025-10-09','RETURNED'),(4082361,6294617,5514533,'2025-09-05',NULL,'OVERDUE'),(4589630,4814376,1111111,'2025-10-03',NULL,'BORROWED'),(4928715,9901898,8629972,'2025-10-01',NULL,'BORROWED'),(5367405,9578087,7444114,'2025-09-12',NULL,'OVERDUE'),(5682397,3284128,5353509,'2025-10-05',NULL,'BORROWED'),(6374209,7930992,8629972,'2025-09-15','2025-09-25','RETURNED'),(6920417,5021215,9812711,'2025-09-10','2025-09-18','RETURNED'),(7214982,6087416,5353509,'2025-09-10','2025-09-20','RETURNED'),(8371594,4814376,5514533,'2025-09-15','2025-09-23','RETURNED'),(8471253,6315802,9812711,'2025-09-25',NULL,'BORROWED'),(9056132,7930992,1111111,'2025-09-30',NULL,'BORROWED'),(9147052,4201035,7444114,'2025-10-02',NULL,'BORROWED');
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `joined_date` date DEFAULT (curdate()),
  `active` tinyint(1) DEFAULT '1',
  `role` enum('ADMIN','CUSTOMER') DEFAULT 'CUSTOMER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9812712 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1111111,'Admin User','admin@text.com','1234','2025-10-07',1,'ADMIN'),(5353509,'Daniel Jose','daniel.kim@example.com','1234','2025-10-07',1,'CUSTOMER'),(5514533,'Benjamin Wright','benjamin.wright@example.com','1234','2025-10-07',1,'CUSTOMER'),(7444114,'Clara Hughes','clara.hughes@example.com','1234','2025-10-07',1,'CUSTOMER'),(8629972,'Alice Carter','alice.carter@example.com','1234','2025-10-07',1,'CUSTOMER'),(9812711,'Ella Rodriguez','ella.rodriguez@example.com','1234','2025-10-07',1,'CUSTOMER');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `joined_date` date DEFAULT (curdate()),
  `active` tinyint(1) DEFAULT '1',
  `role` enum('ADMIN','CUSTOMER') DEFAULT 'CUSTOMER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4471699 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1111111,'Admin User','admin@text.com','1234','2025-10-07',1,'ADMIN'),(1122024,'Alice Carter','alice.carter@example.com','1234',NULL,1,'CUSTOMER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-09 11:39:58
