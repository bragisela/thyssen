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
-- Table structure for table `remitodetalle`
--

DROP TABLE IF EXISTS `remitodetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remitodetalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAlta` varchar(45) DEFAULT NULL,
  `idRemito` int(11) DEFAULT NULL,
  `idOrdenDeProduccion` int(11) DEFAULT NULL,
  `idArticulo` int(11) DEFAULT NULL,
  `idBobina` int(11) DEFAULT NULL,
  `idBulto` int(11) DEFAULT NULL,
  `idPallet` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  `ingresoDeposito` tinyint(4) DEFAULT '0',
  `tipo` varchar(45) DEFAULT NULL,
  `tieneBultoOPallet` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remitodetalle`
--

LOCK TABLES `remitodetalle` WRITE;
/*!40000 ALTER TABLE `remitodetalle` DISABLE KEYS */;
INSERT INTO `remitodetalle` VALUES (6,'2023-03-31 15:59:37.041',6,NULL,NULL,NULL,6,NULL,20,1,1,NULL,NULL),(7,'2023-05-08 19:52:29.868',7,NULL,NULL,NULL,5,NULL,10,1,0,NULL,NULL);
/*!40000 ALTER TABLE `remitodetalle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:26
