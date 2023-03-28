CREATE DATABASE  IF NOT EXISTS `ifts_universita` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ifts_universita`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ifts_universita
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
-- Table structure for table `c`
--

DROP TABLE IF EXISTS `c`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c` (
  `cc` varchar(10) NOT NULL,
  `cnome` varchar(45) DEFAULT NULL,
  `cd` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cc`),
  KEY `ccd_idx` (`cd`),
  CONSTRAINT `ccd` FOREIGN KEY (`cd`) REFERENCES `d` (`cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c`
--

LOCK TABLES `c` WRITE;
/*!40000 ALTER TABLE `c` DISABLE KEYS */;
INSERT INTO `c` VALUES ('C1','Fisica 1','D1'),('C2','Analisi Matematica 1','D2'),('C3','Fisica 2','D1'),('C4','Analisi Matematica 2','D3');
/*!40000 ALTER TABLE `c` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d`
--

DROP TABLE IF EXISTS `d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `d` (
  `cd` varchar(10) NOT NULL,
  `dnome` varchar(45) DEFAULT NULL,
  `citta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d`
--

LOCK TABLES `d` WRITE;
/*!40000 ALTER TABLE `d` DISABLE KEYS */;
INSERT INTO `d` VALUES ('D1','Paolo Rossi','MO'),('D2','Maria Pastore','BO'),('D3','Paola Caboni','FI');
/*!40000 ALTER TABLE `d` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e`
--

DROP TABLE IF EXISTS `e`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `e` (
  `matr` varchar(10) NOT NULL,
  `cc` varchar(10) NOT NULL,
  `data` varchar(10) DEFAULT NULL,
  `voto` int DEFAULT NULL,
  PRIMARY KEY (`matr`,`cc`),
  KEY `ecc_idx` (`cc`),
  CONSTRAINT `ecc` FOREIGN KEY (`cc`) REFERENCES `c` (`cc`),
  CONSTRAINT `ematr` FOREIGN KEY (`matr`) REFERENCES `s` (`matr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e`
--

LOCK TABLES `e` WRITE;
/*!40000 ALTER TABLE `e` DISABLE KEYS */;
INSERT INTO `e` VALUES ('M1','C1','06-29-1995',24),('M1','C2','08-09-1996',33),('M1','C3','03-12-1996',30),('M2','C1','06-29-1995',28),('M2','C2','07-07-1996',24),('M3','C2','07-07-1996',27),('M3','C3','11-11-1996',25),('M4','C3','11-11-1996',33),('M6','C2','01-02-1996',28),('M7','C1','06-29-1995',24),('M7','C2','04-11-1996',26),('M7','C3','06-23-1996',27);
/*!40000 ALTER TABLE `e` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `s`
--

DROP TABLE IF EXISTS `s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `s` (
  `matr` varchar(10) NOT NULL,
  `snome` varchar(45) DEFAULT NULL,
  `citta` varchar(45) DEFAULT NULL,
  `acorso` int DEFAULT NULL,
  PRIMARY KEY (`matr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s`
--

LOCK TABLES `s` WRITE;
/*!40000 ALTER TABLE `s` DISABLE KEYS */;
INSERT INTO `s` VALUES ('M1','Lucia Quaranta','SA',1),('M2','Giacomo Tedesco','PA',2),('M3','Carla Longo','MO',1),('M4','Ugo Rossi','MO',1),('M5','Valeria Neri','MO',2),('M6','Giuseppe Verdi','BO',1),('M7','Maria Rossi',NULL,1);
/*!40000 ALTER TABLE `s` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-13  9:32:53
