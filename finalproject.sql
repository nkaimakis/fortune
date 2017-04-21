-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: finalproject
-- ------------------------------------------------------
-- Server version	5.7.17-log

DROP DATABASE IF EXISTS finalproject;
CREATE DATABASE finalproject;

USE finalproject;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follows` (
  `follower_id` varchar(50) NOT NULL,
  `following_id` varchar(50) NOT NULL,
  `ghost_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ghost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
INSERT INTO `follows` VALUES ('whitneysit','cjin',1),('whitneysit','jessicaFu',2),('whitneysit','jessicaOh',3),('whitneysit','jessicaFu',4),('whitneysit','jessicaOh',5),('whitneysit','jessicaFu',6),('whitneysit','jessicaOh',7),('whitneysit','jessicaFu',8),('whitneysit','jessicaOh',9),('whitneysit','jessicaFu',10),('whitney','jessicaOh',11);
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_symbol` varchar(45) NOT NULL,
  `stock_price` decimal(10,0) NOT NULL,
  `stock_amount` int(11) NOT NULL,
  `isSell` tinyint(1) NOT NULL,
  `timestamp` datetime NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `isSuccess` tinyint(1) NOT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (4, 'fb', 120, 2,0,'2017-04-09 00:15:23','test',1),(5, 'nflx', 100, 4,0,'2017-04-09 00:15:23','test',1),(6,'aapl',1200,1000,1,'2017-04-09 00:15:23','fwada',1),(7,'aapl',500,1000,0,'2017-04-09 00:15:23','whitneysit',1),(8,'aapl',250,1000,1,'2017-04-09 00:15:23','whitneysit',1),(9,'aapl',1500,1000,0,'2017-04-10 21:45:01','jessicaFu',1),(10,'aapl',1000,1000,1,'2017-04-10 21:47:48','jessicaFu',1),(11,'amzn',1500,1000,0,'2017-04-10 21:49:11','jessicaFu',1),(12,'amzn',1000,500,1,'2017-04-10 21:49:58','jessicaFu',1),(13,'aapl',1500,1000,1,'2017-04-10 21:50:25','jessicaFu',1),(14,'aapl',1000,1000,0,'2017-04-10 23:11:00','jessicaFu',1),(15,'aapl',1500,1000,1,'2017-04-10 23:17:44','jessicaFu',1),(16,'aapl',1000,1000,0,'2017-04-10 23:26:47','jessicaFu',1),(17,'aapl',1500,1000,1,'2017-04-10 23:26:55','jessicaFu',1),(18,'aapl',1000,1000,0,'2017-04-10 23:27:03','jessicaFu',1),(19,'aapl',1000,1000,1,'2017-04-10 23:27:48','jessicaFu',1),(20,'aapl',1500,1000,1,'2017-04-10 23:28:00','jessicaFu',1),(21,'aapl',1000,1000,1,'2017-04-11 01:24:34','jessicaFu',1),(22,'aapl',1000,1000,1,'2017-04-13 17:30:06','jessicaFu',1),(23,'aapl',1000,1000,1,'2017-04-13 17:30:37','jessicaFu',1),(24,'aapl',1000,1000,1,'2017-04-13 17:30:39','jessicaFu',1),(25,'aapl',1000,1000,0,'2017-04-13 17:30:52','jessicaFu',1),(26,'aapl',1000,1000,0,'2017-04-13 17:30:53','jessicaFu',1),(27,'aapl',1000,1000,0,'2017-04-13 17:30:55','jessicaFu',1),(28,'aapl',1000,1000,0,'2017-04-13 17:30:56','jessicaFu',1),(29,'aapl',1000,1000,0,'2017-04-13 17:30:57','jessicaFu',1),(34,'aapl',1000,1000,0,'2017-04-13 17:34:13','jessicaFu',1),(51,'fb',139,1000,0,'2017-04-14 13:04:34','whitneysit',1),(52,'fb',149,500,1,'2017-04-14 13:05:44','whitneysit',1),(53,'fb',139,1000,0,'2017-04-14 13:10:00','whitneysit',1),(54,'fb',149,500,1,'2017-04-14 13:10:00','whitneysit',1),(55,'fb',139,1000,0,'2017-04-14 13:10:00','jessicaoh',1),(56,'fb',149,500,1,'2017-04-14 13:10:00','jessicaoh',1),(59,'pypl',43,1000,0,'2017-04-14 13:10:00','jessicaoh',1),(60,'pypl',53,500,1,'2017-04-14 13:10:00','jessicaoh',1),(63,'nke',55,1000,0,'2017-04-14 16:34:45','jessicafu',1),(64,'nke',55,1000,0,'2017-04-14 16:38:32','jessicafu',1),(65,'nke',55,1000,0,'2017-04-14 17:00:18','jessicafu',1),(66,'nke',55,1000,0,'2017-04-14 17:00:47','jessicafu',1),(67,'nke',55,1000,0,'2017-04-15 10:38:27','jessicafu',1),(68,'nke',55,1000,0,'2017-04-15 10:40:15','jessicafu',1),(69,'nke',55,1000,0,'2017-04-15 10:42:38','jessicafu',1),(70,'nke',55,1000,0,'2017-04-15 10:48:30','jessicafu',1),(71,'nke',55,1000,0,'2017-04-15 10:50:00','jessicafu',1),(72,'nke',55,1000,0,'2017-04-15 10:58:12','jessicafu',1),(73,'nke',55,1000,0,'2017-04-15 13:43:35','jessicafu',1),(74,'nke',55,1000,0,'2017-04-15 13:44:21','jessicafu',1),(75,'nke',55,1000,0,'2017-04-15 13:45:34','jessicafu',1),(76,'nke',55,1000,0,'2017-04-15 13:46:38','jessicafu',1);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinvestments`
--

DROP TABLE IF EXISTS `userinvestments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinvestments` (
  `investment_id` int(11) NOT NULL AUTO_INCREMENT,
  `investor_username` varchar(45) NOT NULL,
  `investee_username` varchar(45) NOT NULL,
  `amount_invested` decimal(10,0) NOT NULL,
  `isSell` tinyint(1) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `investee_value` decimal(10,0) NOT NULL,
  PRIMARY KEY (`investment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinvestments`
--

LOCK TABLES `userinvestments` WRITE;
/*!40000 ALTER TABLE `userinvestments` DISABLE KEYS */;
INSERT INTO `userinvestments` VALUES (55,'jessicaFu','cjin',100000,0,'2017-04-15 21:05:40',2000000),(56,'jessicaFu','cjin',100000,1,'2017-04-15 21:07:49',1000000),(57,'jessicaFu','cjin',100000,0,'2017-04-15 21:16:08',2000000),(58,'jessicaFu','cjin',100000,1,'2017-04-15 21:16:31',1000000);
/*!40000 ALTER TABLE `userinvestments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `balance` decimal(64,2) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO users (username, fname, lname, email, password, balance)
			VALUES ('test','first','last','email','test', 5000);

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('cjin','christina','jin','cjin@usc.edu','cjin',952380.95),('fwada','faith1','wada','fwada@usc.edu','fwada',1000000.00),('fwada1','faith2','wada','fwada@usc.edu','fwada',900000.00),('fwada2','faith3','wada','fwada@usc.edu','fwada',800000.00),('fwada3','faith4','wada','fwada@usc.edu','fwada',700000.00),('jessicaFu','jess','iskandar','j.iskandar@gmail.com','jessFU',1047619.05),('jessicaOh','jessica','oh','jeesooh@usc.edu','jOh',4000.00),('jessieDing','jessie','ding','jessie.ding@gpmail.org','jding',30000.00),('whitneysit','whitney','sit','wsit@usc.edu','wsit',999996.48);
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

-- Dump completed on 2017-04-15 15:07:13
