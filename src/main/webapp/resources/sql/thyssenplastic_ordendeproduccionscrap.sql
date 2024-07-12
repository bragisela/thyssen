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
-- Table structure for table `ordendeproduccionscrap`
--

DROP TABLE IF EXISTS `ordendeproduccionscrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordendeproduccionscrap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenDeProduccion` int(11) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `fechaAlta` datetime DEFAULT NULL,
  `idOrigen` int(11) DEFAULT NULL,
  `idTipoMaterial` int(11) DEFAULT NULL,
  `idMotivo` int(11) DEFAULT NULL,
  `idFormato` int(11) DEFAULT NULL,
  `esRecuperable` tinyint(4) DEFAULT '1',
  `materialImpreso` tinyint(4) DEFAULT '1',
  `pesoTotal` decimal(10,2) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordendeproduccionscrap`
--

LOCK TABLES `ordendeproduccionscrap` WRITE;
/*!40000 ALTER TABLE `ordendeproduccionscrap` DISABLE KEYS */;
INSERT INTO `ordendeproduccionscrap` VALUES (1,8,'S1','2023-09-28 19:43:23',99,100,102,101,0,0,10.00,'Nuevo','nada',1),(2,8,'S2','2023-09-28 20:15:43',99,100,102,101,1,1,14.00,'Nuevo','test 2',1),(4,8,'S4','2023-09-29 09:55:00',99,100,102,103,1,0,16.00,'Nuevo','test 2',1);
/*!40000 ALTER TABLE `ordendeproduccionscrap` ENABLE KEYS */;
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
