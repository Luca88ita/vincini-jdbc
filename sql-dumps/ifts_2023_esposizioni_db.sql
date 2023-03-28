CREATE DATABASE  IF NOT EXISTS `ifts_2023_esposizioni` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ifts_2023_esposizioni`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ifts_2023_esposizioni
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `espone`
--

DROP TABLE IF EXISTS `espone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `espone` (
  `cm` varchar(10) NOT NULL,
  `cq` varchar(10) NOT NULL,
  `sala` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cm`,`cq`),
  KEY `FK2_idx` (`cq`),
  CONSTRAINT `FK1` FOREIGN KEY (`cm`) REFERENCES `mostra` (`cm`),
  CONSTRAINT `FK2` FOREIGN KEY (`cq`) REFERENCES `quadro` (`cq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espone`
--

LOCK TABLES `espone` WRITE;
/*!40000 ALTER TABLE `espone` DISABLE KEYS */;
INSERT INTO `espone` VALUES ('M1','Q1','Sala1'),('M1','Q2','Sala1'),('M1','Q3','Sala2'),('M1','Q4','Sala2'),('M1','Q5','Sala2'),('M1','Q6','Sala2'),('M1','Q8','Sala3'),('M2','Q10','Sala2'),('M2','Q2','Sala2'),('M2','Q4','Sala2'),('M3','Q1','Sala3'),('M3','Q4','Sala4'),('M4','Q2','Sala1'),('M4','Q5','Sala2'),('M5','Q3','Sale2'),('M5','Q5','Sala1'),('M5','Q6','Sala2'),('M5','Q7','Sala1'),('M5','Q9','Sala2');
/*!40000 ALTER TABLE `espone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mostra`
--

DROP TABLE IF EXISTS `mostra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mostra` (
  `cm` varchar(10) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `anno` int DEFAULT NULL,
  `organizzatore` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mostra`
--

LOCK TABLES `mostra` WRITE;
/*!40000 ALTER TABLE `mostra` DISABLE KEYS */;
INSERT INTO `mostra` VALUES ('M1','Mostra1',1997,'Organizzatore1'),('M2','Mostra2',1998,'Organizzatore1'),('M3','Mostra3',1997,'Organizzatore2'),('M4','Mostra4',1998,'Organizzatore1'),('M5','Mostra5',1997,'Organizzatore3');
/*!40000 ALTER TABLE `mostra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quadro`
--

DROP TABLE IF EXISTS `quadro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quadro` (
  `cq` varchar(10) NOT NULL,
  `autore` varchar(45) DEFAULT NULL,
  `periodo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quadro`
--

LOCK TABLES `quadro` WRITE;
/*!40000 ALTER TABLE `quadro` DISABLE KEYS */;
INSERT INTO `quadro` VALUES ('Q1','Picasso','Periodo1'),('Q10','Picasso','Periodo3'),('Q2','Picasso','Periodo2'),('Q3','Autore2','Periodo 3'),('Q4','Autore2','Periodo1'),('Q5','Autore3','Periodo3'),('Q6','Autore4','Periodo1'),('Q7','Autore5','Periodo1'),('Q8','Autore1','Periodo4'),('Q9','Autore6','Periodo4');
/*!40000 ALTER TABLE `quadro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-27  8:12:26
