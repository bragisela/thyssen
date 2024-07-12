-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: thyssenplastic
-- ------------------------------------------------------
-- Server version	5.7.37-log

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
-- Table structure for table `hojaderuta`
--

DROP TABLE IF EXISTS `hojaderuta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hojaderuta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAlta` datetime DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaCarga` date DEFAULT NULL,
  `horaCarga` varchar(45) DEFAULT NULL,
  `fechaSalida` date DEFAULT NULL,
  `horaSalida` varchar(45) DEFAULT NULL,
  `idChofer` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  `fechaEstadoAbierto` datetime DEFAULT NULL,
  `idUsuarioEstadoAbierto` int(11) DEFAULT NULL,
  `fechaEstadoCerrado` datetime DEFAULT NULL,
  `idUsuarioEstadoCerrado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaderuta`
--

LOCK TABLES `hojaderuta` WRITE;
/*!40000 ALTER TABLE `hojaderuta` DISABLE KEYS */;
INSERT INTO `hojaderuta` VALUES (4,'2023-03-31 17:04:22','2023-03-31','2023-03-31','17:04','2023-04-01','20:08',15,'Abierto','test',1,'2023-05-16 21:07:49',1,'2023-05-16 20:22:53',1),(5,'2023-05-08 19:51:04','2023-05-08','2023-05-09','19:50','2023-05-12','19:54',NULL,'Nuevo',NULL,1,NULL,NULL,NULL,NULL),(7,'2023-05-16 20:00:55','2023-05-16','2023-05-16',NULL,'2023-05-18',NULL,NULL,'Nuevo','nada',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hojaderuta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:32
