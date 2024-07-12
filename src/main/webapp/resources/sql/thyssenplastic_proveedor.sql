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
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `cuit` varchar(45) NOT NULL,
  `razonSocial` varchar(256) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `horario` varchar(45) DEFAULT NULL,
  `idRubro` int(11) DEFAULT NULL,
  `observaciones` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `tipoDocumento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (3,NULL,'2022-09-16','20-12345678-2','Proveedor 2','115','gusfilippini@gmail.com','',3,'test',NULL),(5,NULL,'2022-09-23','20-12345678-3','Proveedor 3','118','mirtha@yahoo.com','de 10 a 12',1,'nada',NULL),(6,NULL,NULL,'20-12345678-4','Proveedor 4',NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,'20-12345678-5','Proveedor 5',NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,'20-12345678-6','Proveedor 6',NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,'20-12345678-7','Proveedor 7',NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,NULL,'20-12345678-1','Proveedor 8',NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'2022-09-19','20-12345678-9','Proveedor 9','01156545557','gusfilippini@yahoo.com','de 8 a 14',2,'test',NULL),(13,NULL,'2022-09-22','20-12345678-11','Proveedor 11','','','',22,'','cuit'),(14,NULL,'2022-09-22','20-12345678-12','Proveedor 12','','','',22,'','dni'),(15,NULL,'2022-09-26','20-12345678-10','Proveedor 13','01156545557','','',1,'test',NULL),(16,NULL,'2023-03-31','20-12345678-8','Transporte de Sur','01212323213','gusfilippini@gmail.com','de 8 a 14',68,'nada','cuit'),(17,NULL,'2023-04-01','20-12345678-9','Rubens','01212323213','gusfilippini@gmail.com','de 10 a 12',68,'test','cuit'),(18,NULL,'2023-04-21','20-12345678-8','Proveedor 7','01212323213','gusfilippini@gmail.com','de 8 a 14',22,'','cuit');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:34
