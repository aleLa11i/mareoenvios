-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mareoenvios
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Sofia','Gonzalez','Pellegrini 560 3 B','Rosario'),(2,'Carla','Torres','Mendoza 1302 PB','Rosario'),(3,'Juan','Perez','Pellegrini 1234','Rosario'),(4,'Homero ','Simpson','Avenida Siempre Viva  742','Springfield');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Termo Stanly',23),(2,'Set de cubiertos',10),(3,'Producto 1',60),(4,'Celular',50),(5,'Vaso',50),(6,'Plato',30),(7,'Agujereadora',25),(8,'Auriculares',10),(9,'Jueguete 1',80),(10,'Jueguete 2',12),(11,'Joystick',100),(12,'Teclado',50),(13,'Mouse',27);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping` (
  `id` int NOT NULL,
  `customer_id` int NOT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `send_date` date DEFAULT NULL,
  `arrive_date` date DEFAULT NULL,
  `priority` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shipping_FK` (`customer_id`),
  CONSTRAINT `shipping_FK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` VALUES (1,1,'In travel','2023-03-01','2023-09-25',2),(2,1,'Initial','2023-03-01',NULL,3),(3,2,'Cancelled','2023-11-20',NULL,2),(4,2,'Delivered','2023-05-21','2023-09-26',1),(5,1,'Delivered','2022-03-01','2022-03-07',2),(6,3,'Send to mail','2023-10-03',NULL,2),(7,1,'In travel','2023-09-17',NULL,3),(8,3,'Initial','2022-03-01',NULL,1),(9,4,'Delivered','2010-04-05','2010-04-06',1),(10,4,'Send to mail','2017-01-15',NULL,2),(11,4,'Cancelled','2010-04-05',NULL,3);
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_item`
--

DROP TABLE IF EXISTS `shipping_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_item` (
  `id` int NOT NULL,
  `shipping_id` int NOT NULL,
  `product_id` int NOT NULL,
  `product_count` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `shipping_id` (`shipping_id`),
  KEY `shipping_item_FK` (`product_id`),
  CONSTRAINT `shipping_item_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `shipping_item_ibfk_1` FOREIGN KEY (`shipping_id`) REFERENCES `shipping` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_item`
--

LOCK TABLES `shipping_item` WRITE;
/*!40000 ALTER TABLE `shipping_item` DISABLE KEYS */;
INSERT INTO `shipping_item` VALUES (1,1,1,3),(2,2,2,2),(3,1,2,1),(4,4,1,3),(5,8,3,42),(6,3,4,3),(7,11,13,1),(8,9,12,2),(9,4,8,1),(10,6,5,3),(11,7,3,5),(12,8,7,1),(13,11,8,20),(14,5,3,1),(15,3,8,2),(16,5,10,10),(17,4,2,2),(18,10,4,1),(19,10,1,1),(20,4,8,1),(21,2,4,2),(22,1,12,1),(23,4,8,1),(24,1,10,2);
/*!40000 ALTER TABLE `shipping_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shipping_id` int NOT NULL,
  `state` varchar(255) NOT NULL,
  `error` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_FK` (`shipping_id`),
  CONSTRAINT `task_FK` FOREIGN KEY (`shipping_id`) REFERENCES `shipping` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,1,'In progress','',NULL),(2,1,'Success',NULL,NULL),(3,1,'Success',NULL,NULL),(4,3,'Failed','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.',NULL),(5,5,'Failed','El envío con ID=\'5\' ya llego.',NULL),(6,5,'Failed','El envío con ID=\'5\' ya llego.',NULL),(7,1,'Success',NULL,NULL),(8,1,'In progress',NULL,NULL),(9,2,'In progress',NULL,NULL),(10,3,'Failed','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.',NULL),(11,1,'In progress',NULL,NULL),(12,2,'In progress',NULL,NULL),(13,3,'Failed','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.',NULL),(14,1,'In progress',NULL,NULL),(15,2,'In progress',NULL,NULL),(16,4,'In progress',NULL,NULL),(17,1,'In progress',NULL,NULL),(18,2,'In progress',NULL,NULL),(19,4,'In progress',NULL,NULL),(20,1,'In progress',NULL,NULL),(21,2,'In progress',NULL,NULL),(22,4,'In progress',NULL,NULL),(23,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose',NULL),(24,2,'Failed','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL),(25,4,'Failed','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL),(26,1,'Success',NULL,NULL),(27,2,'In progress',NULL,NULL),(28,4,'In progress',NULL,NULL),(29,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose',NULL),(30,2,'Failed','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL),(31,4,'Failed','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL),(32,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose',NULL),(33,2,'Failed','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL),(34,4,'Failed','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL),(35,1,'Success',NULL,NULL),(36,2,'In progress',NULL,NULL),(37,4,'In progress',NULL,NULL),(38,1,'Success',NULL,NULL),(39,2,'Failed','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL),(40,4,'Failed','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL),(41,3,'Failed','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.','2023-09-24 22:12:44'),(42,3,'Failed','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.','2023-09-25 11:55:08'),(43,1,'Success',NULL,'2023-09-25 11:55:38'),(44,1,'Failed','El envío con ID=\'1\' ya llego.','2023-09-25 14:28:56'),(45,1,'Success',NULL,'2023-09-25 14:30:30'),(46,1,'In progress',NULL,NULL),(47,1,'In progress',NULL,NULL),(48,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 14:54:03'),(49,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 14:54:29'),(50,1,'In progress',NULL,NULL),(51,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 14:58:33'),(52,1,'In progress',NULL,NULL),(53,1,'Success',NULL,'2023-09-25 15:01:47'),(54,1,'Success',NULL,'2023-09-25 15:03:12'),(55,1,'Success',NULL,'2023-09-25 15:07:31'),(56,1,'Failed','El envío con ID=\'1\' ya llego.','2023-09-25 15:08:51'),(57,1,'Success',NULL,'2023-09-25 15:09:07'),(58,1,'Success',NULL,'2023-09-25 15:10:00'),(59,1,'Success',NULL,'2023-09-25 15:10:21'),(60,1,'Failed','El envío con ID=\'1\' ya llego.','2023-09-25 15:10:25'),(61,1,'Success',NULL,'2023-09-25 15:10:52'),(62,1,'Success',NULL,'2023-09-25 15:11:29'),(63,1,'In progress',NULL,NULL),(64,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 20:38:57'),(65,1,'In progress',NULL,NULL),(66,1,'In progress',NULL,NULL),(67,1,'Failed','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 20:42:47'),(68,1,'In progress',NULL,NULL),(69,1,'In progress',NULL,NULL),(70,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60000.','2023-09-25 20:44:05'),(71,1,'Success',NULL,'2023-09-25 20:44:11'),(72,1,'Failed','El envío con ID=\'1\' ya llego.','2023-09-25 20:45:08'),(73,1,'Failed','El envío con ID=\'1\' ya llego.','2023-09-25 20:45:08'),(74,1,'Failed','El envío con ID=\'1\' ya llego.','2023-09-25 20:45:08'),(75,1,'Success',NULL,'2023-09-25 20:46:52'),(76,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:45:49'),(77,1,'Success',NULL,'2023-09-25 20:45:51'),(78,1,'In progress',NULL,NULL),(79,2,'Failed','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:46:22'),(80,4,'Success',NULL,'2023-09-25 20:46:24'),(81,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:46:42'),(82,2,'Failed','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:46:42'),(83,4,'Success',NULL,'2023-09-25 20:46:46'),(84,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:49:48'),(85,2,'Failed','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:49:48'),(86,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:50:19'),(87,2,'Failed','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:50:19'),(88,4,'In progress',NULL,NULL),(89,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 23:45:11'),(90,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 23:47:33'),(91,1,'In progress',NULL,NULL),(92,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 23:50:17'),(93,1,'Failed','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-26 01:10:11'),(94,2,'Failed','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-26 01:10:18'),(95,4,'Success',NULL,'2023-09-26 01:10:41');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mareoenvios'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-26 10:26:53
