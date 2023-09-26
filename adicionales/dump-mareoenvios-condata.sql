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
INSERT INTO `customer` VALUES (1,'Sofia','Gonzalez','Pellegrini 560 3 B','Rosario');
INSERT INTO `customer` VALUES (2,'Carla','Torres','Mendoza 1302 PB','Rosario');
INSERT INTO `customer` VALUES (3,'Juan','Perez','Pellegrini 1234','Rosario');
INSERT INTO `customer` VALUES (4,'Homero ','Simpson','Avenida Siempre Viva  742','Springfield');
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
INSERT INTO `product` VALUES (1,'Termo Stanly',23);
INSERT INTO `product` VALUES (2,'Set de cubiertos',10);
INSERT INTO `product` VALUES (3,'Producto 1',60);
INSERT INTO `product` VALUES (4,'Celular',50);
INSERT INTO `product` VALUES (5,'Vaso',50);
INSERT INTO `product` VALUES (6,'Plato',30);
INSERT INTO `product` VALUES (7,'Agujereadora',25);
INSERT INTO `product` VALUES (8,'Auriculares',10);
INSERT INTO `product` VALUES (9,'Jueguete 1',80);
INSERT INTO `product` VALUES (10,'Jueguete 2',12);
INSERT INTO `product` VALUES (11,'Joystick',100);
INSERT INTO `product` VALUES (12,'Teclado',50);
INSERT INTO `product` VALUES (13,'Mouse',27);
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
INSERT INTO `shipping` VALUES (1,1,'Entregado','2023-03-01','2023-09-25',2);
INSERT INTO `shipping` VALUES (2,1,'Cancelado','2023-03-01',NULL,3);
INSERT INTO `shipping` VALUES (3,2,'Cancelado','2023-11-20',NULL,2);
INSERT INTO `shipping` VALUES (4,2,'Entregado','2023-05-21','2023-09-26',1);
INSERT INTO `shipping` VALUES (5,1,'Entregado','2022-03-01','2022-03-07',2);
INSERT INTO `shipping` VALUES (6,3,'Entregado a correo','2023-10-03',NULL,2);
INSERT INTO `shipping` VALUES (7,1,'En camino','2023-09-17',NULL,3);
INSERT INTO `shipping` VALUES (8,3,'Inicial','2022-03-01',NULL,1);
INSERT INTO `shipping` VALUES (9,4,'Entregado','2010-04-05','2010-04-06',1);
INSERT INTO `shipping` VALUES (10,4,'Entregado','2017-01-15','2023-09-26',2);
INSERT INTO `shipping` VALUES (11,4,'Cancelado','2010-04-05',NULL,3);
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
INSERT INTO `shipping_item` VALUES (1,1,1,3);
INSERT INTO `shipping_item` VALUES (2,2,2,2);
INSERT INTO `shipping_item` VALUES (3,1,2,1);
INSERT INTO `shipping_item` VALUES (4,4,1,3);
INSERT INTO `shipping_item` VALUES (5,8,3,42);
INSERT INTO `shipping_item` VALUES (6,3,4,3);
INSERT INTO `shipping_item` VALUES (7,11,13,1);
INSERT INTO `shipping_item` VALUES (8,9,12,2);
INSERT INTO `shipping_item` VALUES (9,4,8,1);
INSERT INTO `shipping_item` VALUES (10,6,5,3);
INSERT INTO `shipping_item` VALUES (11,7,3,5);
INSERT INTO `shipping_item` VALUES (12,8,7,1);
INSERT INTO `shipping_item` VALUES (13,11,8,20);
INSERT INTO `shipping_item` VALUES (14,5,3,1);
INSERT INTO `shipping_item` VALUES (15,3,8,2);
INSERT INTO `shipping_item` VALUES (16,5,10,10);
INSERT INTO `shipping_item` VALUES (17,4,2,2);
INSERT INTO `shipping_item` VALUES (18,10,4,1);
INSERT INTO `shipping_item` VALUES (19,10,1,1);
INSERT INTO `shipping_item` VALUES (20,4,8,1);
INSERT INTO `shipping_item` VALUES (21,2,4,2);
INSERT INTO `shipping_item` VALUES (22,1,12,1);
INSERT INTO `shipping_item` VALUES (23,4,8,1);
INSERT INTO `shipping_item` VALUES (24,1,10,2);
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
  PRIMARY KEY (`id`),
  KEY `task_FK` (`shipping_id`),
  CONSTRAINT `task_FK` FOREIGN KEY (`shipping_id`) REFERENCES `shipping` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_task`
--

LOCK TABLES `shipping_task` WRITE;
/*!40000 ALTER TABLE `shipping_task` DISABLE KEYS */;
INSERT INTO `shipping_task` VALUES (1,1,'En progreso','',NULL,NULL);
INSERT INTO `shipping_task` VALUES (2,1,'Éxito',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (3,1,'Éxito',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (4,3,'Fallido','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.',NULL,NULL);
INSERT INTO `shipping_task` VALUES (5,5,'Fallido','El envío con ID=\'5\' ya llego.',NULL,NULL);
INSERT INTO `shipping_task` VALUES (6,5,'Fallido','El envío con ID=\'5\' ya llego.',NULL,NULL);
INSERT INTO `shipping_task` VALUES (7,1,'Éxito',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (8,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (9,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (10,3,'Fallido','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.',NULL,NULL);
INSERT INTO `shipping_task` VALUES (11,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (12,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (13,3,'Fallido','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.',NULL,NULL);
INSERT INTO `shipping_task` VALUES (14,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (15,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (16,4,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (17,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (18,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (19,4,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (20,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (21,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (22,4,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (23,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (24,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (25,4,'Fallido','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (26,1,'Éxito',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (27,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (28,4,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (29,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (30,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (31,4,'Fallido','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (32,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (33,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (34,4,'Fallido','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (35,1,'Éxito',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (36,2,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (37,4,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (38,1,'Éxito',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (39,2,'Fallido','El envío con ID=\'2\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (40,4,'Fallido','El envío con ID=\'4\' ya posee una tarea ejecutandose',NULL,NULL);
INSERT INTO `shipping_task` VALUES (41,3,'Fallido','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.','2023-09-24 22:12:44',NULL);
INSERT INTO `shipping_task` VALUES (42,3,'Fallido','El envío con ID=\'3\' se encuentra cancelado por lo que no se puede ejecutar una tarea.','2023-09-25 11:55:08',NULL);
INSERT INTO `shipping_task` VALUES (43,1,'Éxito',NULL,'2023-09-25 11:55:38',NULL);
INSERT INTO `shipping_task` VALUES (44,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-25 14:28:56',NULL);
INSERT INTO `shipping_task` VALUES (45,1,'Éxito',NULL,'2023-09-25 14:30:30',NULL);
INSERT INTO `shipping_task` VALUES (46,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (47,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (48,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 14:54:03',NULL);
INSERT INTO `shipping_task` VALUES (49,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 14:54:29',NULL);
INSERT INTO `shipping_task` VALUES (50,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (51,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 14:58:33',NULL);
INSERT INTO `shipping_task` VALUES (52,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (53,1,'Éxito',NULL,'2023-09-25 15:01:47',NULL);
INSERT INTO `shipping_task` VALUES (54,1,'Éxito',NULL,'2023-09-25 15:03:12',NULL);
INSERT INTO `shipping_task` VALUES (55,1,'Éxito',NULL,'2023-09-25 15:07:31',NULL);
INSERT INTO `shipping_task` VALUES (56,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-25 15:08:51',NULL);
INSERT INTO `shipping_task` VALUES (57,1,'Éxito',NULL,'2023-09-25 15:09:07',NULL);
INSERT INTO `shipping_task` VALUES (58,1,'Éxito',NULL,'2023-09-25 15:10:00',NULL);
INSERT INTO `shipping_task` VALUES (59,1,'Éxito',NULL,'2023-09-25 15:10:21',NULL);
INSERT INTO `shipping_task` VALUES (60,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-25 15:10:25',NULL);
INSERT INTO `shipping_task` VALUES (61,1,'Éxito',NULL,'2023-09-25 15:10:52',NULL);
INSERT INTO `shipping_task` VALUES (62,1,'Éxito',NULL,'2023-09-25 15:11:29',NULL);
INSERT INTO `shipping_task` VALUES (63,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (64,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 20:38:57',NULL);
INSERT INTO `shipping_task` VALUES (65,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (66,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (67,1,'Fallido','El envío con ID=\'1\' ya posee una tarea ejecutandose','2023-09-25 20:42:47',NULL);
INSERT INTO `shipping_task` VALUES (68,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (69,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (70,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60000.','2023-09-25 20:44:05',NULL);
INSERT INTO `shipping_task` VALUES (71,1,'Éxito',NULL,'2023-09-25 20:44:11',NULL);
INSERT INTO `shipping_task` VALUES (72,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-25 20:45:08',NULL);
INSERT INTO `shipping_task` VALUES (73,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-25 20:45:08',NULL);
INSERT INTO `shipping_task` VALUES (74,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-25 20:45:08',NULL);
INSERT INTO `shipping_task` VALUES (75,1,'Éxito',NULL,'2023-09-25 20:46:52',NULL);
INSERT INTO `shipping_task` VALUES (76,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:45:49',NULL);
INSERT INTO `shipping_task` VALUES (77,1,'Éxito',NULL,'2023-09-25 20:45:51',NULL);
INSERT INTO `shipping_task` VALUES (78,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (79,2,'Fallido','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:46:22',NULL);
INSERT INTO `shipping_task` VALUES (80,4,'Éxito',NULL,'2023-09-25 20:46:24',NULL);
INSERT INTO `shipping_task` VALUES (81,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:46:42',NULL);
INSERT INTO `shipping_task` VALUES (82,2,'Fallido','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:46:42',NULL);
INSERT INTO `shipping_task` VALUES (83,4,'Éxito',NULL,'2023-09-25 20:46:46',NULL);
INSERT INTO `shipping_task` VALUES (84,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:49:48',NULL);
INSERT INTO `shipping_task` VALUES (85,2,'Fallido','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:49:48',NULL);
INSERT INTO `shipping_task` VALUES (86,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:50:19',NULL);
INSERT INTO `shipping_task` VALUES (87,2,'Fallido','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-25 20:50:19',NULL);
INSERT INTO `shipping_task` VALUES (88,4,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (89,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 23:45:11',NULL);
INSERT INTO `shipping_task` VALUES (90,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 23:47:33',NULL);
INSERT INTO `shipping_task` VALUES (91,1,'En progreso',NULL,NULL,NULL);
INSERT INTO `shipping_task` VALUES (92,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-25 23:50:17',NULL);
INSERT INTO `shipping_task` VALUES (93,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-26 01:10:11',NULL);
INSERT INTO `shipping_task` VALUES (94,2,'Fallido','La tarea del envío con ID=\'2\' supera el tiempo maximo de 60 segundos.','2023-09-26 01:10:18',NULL);
INSERT INTO `shipping_task` VALUES (95,4,'Éxito',NULL,'2023-09-26 01:10:41',NULL);
INSERT INTO `shipping_task` VALUES (96,1,'Éxito',NULL,'2023-09-26 12:45:30','2023-09-26 12:45:18');
INSERT INTO `shipping_task` VALUES (97,1,'Éxito',NULL,'2023-09-26 12:46:27','2023-09-26 12:46:14');
INSERT INTO `shipping_task` VALUES (98,1,'Éxito',NULL,'2023-09-26 12:47:08','2023-09-26 12:46:55');
INSERT INTO `shipping_task` VALUES (99,1,'Fallido','El envío con ID=\'1\' se encuentra cancelado por lo que no se puede ejecutar una tarea.','2023-09-26 12:47:19','2023-09-26 12:47:19');
INSERT INTO `shipping_task` VALUES (100,1,'Fallido','El envío con ID=\'1\' ya llego.','2023-09-26 12:47:45','2023-09-26 12:47:45');
INSERT INTO `shipping_task` VALUES (101,1,'Fallido','La tarea del envío con ID=\'1\' supera el tiempo maximo de 60 segundos.','2023-09-26 12:48:22','2023-09-26 12:48:22');
INSERT INTO `shipping_task` VALUES (102,10,'Éxito',NULL,'2023-09-26 12:49:15','2023-09-26 12:48:57');
INSERT INTO `shipping_task` VALUES (103,2,'Éxito',NULL,'2023-09-26 12:50:54','2023-09-26 12:50:34');
INSERT INTO `shipping_task` VALUES (104,5,'Fallido','El envío con ID=\'5\' ya llego.','2023-09-26 12:50:34','2023-09-26 12:50:34');
INSERT INTO `shipping_task` VALUES (105,10,'Éxito',NULL,'2023-09-26 12:50:54','2023-09-26 12:50:34');
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

-- Dump completed on 2023-09-26 12:57:16
