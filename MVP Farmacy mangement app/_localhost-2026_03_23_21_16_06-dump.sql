-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lant_farmacii
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `farmacie`
--

DROP TABLE IF EXISTS `farmacie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `farmacie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(100) NOT NULL,
  `adresa` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `farmacie`
--

LOCK TABLES `farmacie` WRITE;
/*!40000 ALTER TABLE `farmacie` DISABLE KEYS */;
INSERT INTO `farmacie` VALUES (6,'Catena','str Buna ziua nr123'),(8,'Refermed','str plopilor nr 12'),(11,'Napoca','str izazului nr 11');
/*!40000 ALTER TABLE `farmacie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicament` (
  `id` int NOT NULL AUTO_INCREMENT,
  `denumire` varchar(100) NOT NULL,
  `producator` varchar(100) NOT NULL,
  `imagine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicament`
--

LOCK TABLES `medicament` WRITE;
/*!40000 ALTER TABLE `medicament` DISABLE KEYS */;
INSERT INTO `medicament` VALUES (7,'Paracetamol','Terapia','D:\\PS\\Tema 1\\Poze\\paracetamol2.jpg'),(8,'Paracetamol','Health','D:\\PS\\Tema 1\\Poze\\paracetamol.jpg'),(9,'Deprox','Bayern','D:\\PS\\Tema 1\\Poze\\deprox.jpg'),(11,'Norofen','Remax','D:\\PS\\Tema 1\\Poze\\norofen.jpg'),(12,'Ibuprofen','Terapia','D:\\PS\\Tema 1\\Poze\\ipubrofen.jpg');
/*!40000 ALTER TABLE `medicament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stoc`
--

DROP TABLE IF EXISTS `stoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stoc` (
  `id_stoc` int NOT NULL AUTO_INCREMENT,
  `id_med` int NOT NULL,
  `id_farm` int NOT NULL,
  `cantitate` int NOT NULL,
  `disponibilitate` tinyint(1) NOT NULL,
  `valabilitate` date NOT NULL,
  PRIMARY KEY (`id_stoc`),
  KEY `id_med` (`id_med`),
  KEY `id_farm` (`id_farm`),
  CONSTRAINT `stoc_ibfk_1` FOREIGN KEY (`id_med`) REFERENCES `medicament` (`id`) ON DELETE CASCADE,
  CONSTRAINT `stoc_ibfk_2` FOREIGN KEY (`id_farm`) REFERENCES `farmacie` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stoc`
--

LOCK TABLES `stoc` WRITE;
/*!40000 ALTER TABLE `stoc` DISABLE KEYS */;
INSERT INTO `stoc` VALUES (14,9,6,20,1,'2030-03-20'),(15,7,6,390,1,'2032-02-29'),(16,12,6,21,0,'2032-03-20'),(17,9,8,90,1,'2031-03-29'),(18,11,6,21,0,'2030-03-20'),(19,9,11,12,1,'2021-03-21'),(20,11,11,12,1,'2031-03-21'),(21,7,11,12,1,'2031-03-21'),(22,12,11,12,1,'2031-03-21'),(23,12,11,12,0,'2031-03-21');
/*!40000 ALTER TABLE `stoc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-23 21:16:06
