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
-- Table structure for table `ordendeproduccionbulto`
--

DROP TABLE IF EXISTS `ordendeproduccionbulto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordendeproduccionbulto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenDeProduccion` int(11) DEFAULT NULL,
  `idOrdenDeProduccionBobina` int(11) DEFAULT NULL,
  `idPlegadora` int(11) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `fechaAlta` datetime DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  `estaEnPallet` tinyint(4) DEFAULT '0',
  `estaDisponibleParaRemito` tinyint(4) DEFAULT '1',
  `idDeposito` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordendeproduccionbulto`
--

LOCK TABLES `ordendeproduccionbulto` WRITE;
/*!40000 ALTER TABLE `ordendeproduccionbulto` DISABLE KEYS */;
INSERT INTO `ordendeproduccionbulto` VALUES (5,2,2,57,'R2','2023-03-13 09:28:20','observado','sds',3,1,1,63),(6,2,1,57,'R1','2023-03-14 10:43:13','ok',NULL,3,1,0,61),(7,2,3,57,'R3','2023-03-14 20:09:42',NULL,NULL,3,0,1,NULL),(9,5,5,56,'R5','2023-04-26 14:25:15','ok',NULL,3,0,1,NULL),(11,7,7,56,'R7','2023-06-01 16:15:35','ok',NULL,3,1,1,61),(13,8,8,56,'R8','2023-08-17 07:44:19','ok',NULL,3,1,1,NULL),(14,5,6,56,'R6','2023-08-17 10:17:50','ok','nada',3,0,1,NULL),(15,5,4,56,'R4','2023-11-01 19:11:58','ok','nada',3,0,1,NULL);
/*!40000 ALTER TABLE `ordendeproduccionbulto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:33
