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
-- Table structure for table `orderdecompraitem`
--

DROP TABLE IF EXISTS `orderdecompraitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdecompraitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenDeCompra` int(11) DEFAULT NULL,
  `idMateriaPrima` int(11) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fechaCompletado` date DEFAULT NULL,
  `estaCompletado` tinyint(4) DEFAULT '0',
  `idInsumo` int(11) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `superaCantidad` tinyint(4) DEFAULT '0',
  `faltaCantidad` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdecompraitem`
--

LOCK TABLES `orderdecompraitem` WRITE;
/*!40000 ALTER TABLE `orderdecompraitem` DISABLE KEYS */;
INSERT INTO `orderdecompraitem` VALUES (2,1,3,'2023-02-01',110,1,'2023-02-03',1,NULL,'materiaPrima',0,0),(3,1,2,'2023-02-01',200,1,'2023-02-16',1,NULL,'materiaPrima',1,0),(4,2,4,'2023-02-02',100,1,'2023-02-03',1,NULL,'materiaPrima',0,0),(6,3,NULL,'2023-02-07',100,1,'2023-02-07',1,1,'insumo',0,0),(7,3,NULL,'2023-02-07',60,1,'2023-02-07',1,3,'insumo',0,0),(8,4,NULL,'2023-02-09',50,1,'2023-02-09',1,1,'insumo',0,0),(9,4,NULL,'2023-02-09',30,1,'2023-02-09',1,3,'insumo',0,0),(10,5,NULL,'2023-02-16',100,1,'2023-02-16',1,1,'insumo',0,1),(11,7,4,'2023-02-16',50,1,'2023-02-16',1,NULL,'materiaPrima',NULL,1),(12,9,4,'2023-02-16',80,1,'2023-02-16',1,NULL,'materiaPrima',1,NULL),(13,10,NULL,'2023-02-16',100,1,'2023-03-18',1,1,'insumo',NULL,1),(14,11,4,'2023-03-08',100,1,NULL,0,NULL,'materiaPrima',NULL,NULL);
/*!40000 ALTER TABLE `orderdecompraitem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:30
