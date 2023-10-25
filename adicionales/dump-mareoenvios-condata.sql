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
INSERT INTO `shipping` VALUES (1,1,'Cancelado','2023-03-01','2023-10-11',2),(2,1,'En camino','2023-03-01','2023-10-11',3),(3,2,'Entregado a correo','2023-11-20',NULL,2),(4,2,'Entregado','2023-05-21','2023-09-26',1),(5,1,'Entregado','2022-03-01','2022-03-07',2),(6,3,'Entregado a correo','2023-10-03',NULL,2),(7,1,'En camino','2023-09-17',NULL,3),(8,3,'Inicial','2022-03-01',NULL,1),(9,4,'Entregado','2010-04-05','2010-04-06',1),(10,4,'Entregado','2017-01-15','2023-09-26',2),(11,4,'Cancelado','2010-04-05',NULL,3);
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
-- Table structure for table `shipping_task`
--

DROP TABLE IF EXISTS `shipping_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shipping_id` int NOT NULL,
  `state` varchar(255) NOT NULL,
  `error` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `next_state` tinyint(1) DEFAULT NULL,
  `time_start` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_FK` (`shipping_id`),
  CONSTRAINT `task_FK` FOREIGN KEY (`shipping_id`) REFERENCES `shipping` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=665 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_task`
--

LOCK TABLES `shipping_task` WRITE;
/*!40000 ALTER TABLE `shipping_task` DISABLE KEYS */;
INSERT INTO `shipping_task` VALUES (544,1,'Éxito',NULL,'2023-10-10 14:46:23','2023-10-10 14:46:12',1,10),(545,2,'Éxito',NULL,'2023-10-10 14:46:22','2023-10-10 14:46:12',1,7),(546,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-10 14:46:12','2023-10-10 14:46:12',1,5),(547,1,'Éxito',NULL,'2023-10-10 14:46:31','2023-10-10 14:46:12',1,5),(548,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-10 14:46:12','2023-10-10 14:46:12',1,10),(549,1,'Éxito',NULL,'2023-10-10 14:46:46','2023-10-10 14:46:12',1,10),(550,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-10 14:46:12','2023-10-10 14:46:12',1,8),(551,2,'Éxito',NULL,'2023-10-10 14:46:35','2023-10-10 14:46:12',1,8),(552,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-10 14:46:12','2023-10-10 14:46:12',1,5),(553,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-10-10 14:46:51','2023-10-10 14:46:12',1,5),(554,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-10-11 11:36:26','2023-10-10 14:54:29',1,72),(555,1,'Éxito',NULL,'2023-10-11 11:38:07','2023-10-10 17:17:34',1,72),(556,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-10-10 17:18:00','2023-10-10 17:18:00',1,72),(557,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-10-10 17:18:05','2023-10-10 17:18:04',1,72),(558,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-10-11 11:35:14','2023-10-11 11:35:04',1,10),(559,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:35:04','2023-10-11 11:35:04',1,5),(560,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-10-11 12:25:44','2023-10-11 11:35:04',1,5),(561,2,'Fallido','El envío con ID=\'2\' ya llego.','2023-10-11 11:35:08','2023-10-11 11:35:04',1,4),(562,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:35:04','2023-10-11 11:35:04',0,8),(563,1,'Éxito',NULL,'2023-10-11 12:26:02','2023-10-11 11:35:04',0,8),(564,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:35:04','2023-10-11 11:35:04',1,12),(565,2,'Fallido','El envío con ID=\'2\' ya llego.','2023-10-11 11:35:20','2023-10-11 11:35:04',1,12),(566,1,'Éxito',NULL,'2023-10-11 11:36:52','2023-10-11 11:36:40',1,10),(567,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:36:41','2023-10-11 11:36:41',1,5),(568,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:26:22','2023-10-11 11:36:41',1,5),(569,2,'Éxito',NULL,'2023-10-11 11:36:48','2023-10-11 11:36:41',1,4),(570,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:36:41','2023-10-11 11:36:41',0,8),(571,1,'Éxito',NULL,'2023-10-11 12:26:45','2023-10-11 11:36:41',0,8),(572,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:36:41','2023-10-11 11:36:41',1,12),(573,2,'Éxito',NULL,'2023-10-11 11:37:05','2023-10-11 11:36:41',1,12),(574,1,'Éxito',NULL,'2023-10-11 11:51:42','2023-10-11 11:51:24',1,10),(575,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:51:24','2023-10-11 11:51:24',1,5),(576,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:26:56','2023-10-11 11:51:24',1,5),(577,2,'Éxito',NULL,'2023-10-11 11:51:31','2023-10-11 11:51:24',1,4),(578,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:51:24','2023-10-11 11:51:24',0,8),(579,1,'Éxito',NULL,'2023-10-11 12:27:20','2023-10-11 11:51:24',0,8),(580,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:51:24','2023-10-11 11:51:24',1,12),(581,2,'Éxito',NULL,'2023-10-11 11:52:10','2023-10-11 11:51:24',1,12),(582,1,'Éxito',NULL,'2023-10-11 11:53:45','2023-10-11 11:53:32',1,10),(583,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 11:53:32','2023-10-11 11:53:32',1,5),(584,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:28:01','2023-10-11 11:53:32',1,5),(585,1,'Éxito',NULL,'2023-10-11 12:25:29','2023-10-11 12:25:14',1,10),(586,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:25:14','2023-10-11 12:25:14',1,5),(587,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:28:09','2023-10-11 12:25:14',1,5),(588,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:32:36','2023-10-11 12:32:26',1,10),(589,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:32:26','2023-10-11 12:32:26',1,5),(590,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:33:02','2023-10-11 12:32:26',1,5),(591,1,'Éxito',NULL,'2023-10-11 12:34:46','2023-10-11 12:34:11',1,10),(592,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:34:11','2023-10-11 12:34:11',1,5),(593,1,'Éxito',NULL,'2023-10-11 12:34:54','2023-10-11 12:34:11',1,5),(594,2,'Éxito',NULL,'2023-10-11 12:34:17','2023-10-11 12:34:11',1,4),(595,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:34:11','2023-10-11 12:34:11',0,8),(596,1,'Éxito',NULL,'2023-10-11 12:35:05','2023-10-11 12:34:11',0,8),(597,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:34:11','2023-10-11 12:34:11',1,12),(598,2,'Éxito',NULL,'2023-10-11 12:35:00','2023-10-11 12:34:11',1,12),(599,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:40:56','2023-10-11 12:40:22',1,10),(600,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:40:22','2023-10-11 12:40:22',1,5),(601,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:41:14','2023-10-11 12:40:22',1,5),(602,2,'Éxito',NULL,'2023-10-11 12:40:31','2023-10-11 12:40:22',1,4),(603,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:40:22','2023-10-11 12:40:22',0,8),(604,1,'Éxito',NULL,'2023-10-11 12:41:25','2023-10-11 12:40:22',0,8),(605,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:40:22','2023-10-11 12:40:22',1,12),(625,1,'Éxito',NULL,'2023-10-11 12:45:35','2023-10-11 12:45:24',1,10),(626,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:45:24','2023-10-11 12:45:24',1,5),(627,1,'Éxito',NULL,'2023-10-11 12:45:43','2023-10-11 12:45:24',1,5),(628,2,'Éxito',NULL,'2023-10-11 12:45:29','2023-10-11 12:45:24',1,4),(629,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:45:24','2023-10-11 12:45:24',0,8),(630,1,'Éxito',NULL,'2023-10-11 12:45:56','2023-10-11 12:45:24',0,8),(631,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:45:24','2023-10-11 12:45:24',1,12),(632,2,'Éxito',NULL,'2023-10-11 12:45:44','2023-10-11 12:45:24',1,12),(633,1,'Éxito',NULL,'2023-10-11 12:48:21','2023-10-11 12:48:10',1,10),(634,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:48:10','2023-10-11 12:48:10',1,5),(635,1,'Éxito',NULL,'2023-10-11 12:48:29','2023-10-11 12:48:10',1,5),(636,2,'Éxito',NULL,'2023-10-11 12:48:15','2023-10-11 12:48:10',1,4),(637,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:48:10','2023-10-11 12:48:10',0,8),(638,1,'Éxito',NULL,'2023-10-11 12:48:40','2023-10-11 12:48:10',0,8),(639,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:48:10','2023-10-11 12:48:10',1,12),(640,2,'Éxito',NULL,'2023-10-11 12:48:31','2023-10-11 12:48:10',1,12),(641,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:52:10','2023-10-11 12:52:00',1,10),(642,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:52:00','2023-10-11 12:52:00',1,5),(643,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado.','2023-10-11 12:52:15','2023-10-11 12:52:00',1,5),(644,2,'Éxito',NULL,'2023-10-11 12:52:09','2023-10-11 12:52:00',1,4),(645,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:52:00','2023-10-11 12:52:00',0,8),(646,1,'Éxito',NULL,'2023-10-11 12:52:26','2023-10-11 12:52:00',0,8),(647,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:52:00','2023-10-11 12:52:00',1,12),(648,2,'Fallido','El envío con ID=\'2\' ya llego.','2023-10-11 12:52:21','2023-10-11 12:52:00',1,12),(649,1,'Éxito',NULL,'2023-10-11 12:52:57','2023-10-11 12:52:46',1,10),(650,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:52:46','2023-10-11 12:52:46',1,5),(651,1,'Éxito',NULL,'2023-10-11 12:53:05','2023-10-11 12:52:46',1,5),(652,2,'Éxito',NULL,'2023-10-11 12:52:51','2023-10-11 12:52:46',1,4),(653,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:52:46','2023-10-11 12:52:46',0,8),(654,1,'Éxito',NULL,'2023-10-11 12:53:16','2023-10-11 12:52:46',0,8),(655,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 12:52:46','2023-10-11 12:52:46',1,12),(656,2,'Éxito',NULL,'2023-10-11 12:53:06','2023-10-11 12:52:46',1,12),(657,1,'Éxito',NULL,'2023-10-11 13:03:41','2023-10-11 13:03:30',1,10),(658,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 13:03:30','2023-10-11 13:03:30',1,5),(659,1,'Éxito',NULL,'2023-10-11 13:03:49','2023-10-11 13:03:30',1,5),(660,2,'Éxito',NULL,'2023-10-11 13:03:35','2023-10-11 13:03:30',1,4),(661,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 13:03:30','2023-10-11 13:03:30',0,8),(662,1,'Éxito',NULL,'2023-10-11 13:04:00','2023-10-11 13:03:30',0,8),(663,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutándose. Se agrega la cola y comenzara a ejecutarse luego de la tarea previa.','2023-10-11 13:03:30','2023-10-11 13:03:30',1,12),(664,2,'Éxito',NULL,'2023-10-11 13:03:50','2023-10-11 13:03:30',1,12);
/*!40000 ALTER TABLE `shipping_task` ENABLE KEYS */;
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

-- Dump completed on 2023-10-25  9:44:49
