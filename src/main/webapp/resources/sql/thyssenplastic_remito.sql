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
-- Table structure for table `remito`
--

DROP TABLE IF EXISTS `remito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAlta` datetime DEFAULT NULL,
  `fechaRemito` date DEFAULT NULL,
  `tipoRemito` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `idDomicilio` int(11) DEFAULT NULL,
  `idTransporte` int(11) DEFAULT NULL,
  `referenciaAdministrativa` varchar(100) DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  `fechaCierre` datetime DEFAULT NULL,
  `idUsuarioCierre` int(11) DEFAULT NULL,
  `fechaAbierto` datetime DEFAULT NULL,
  `idUsuarioAbierto` int(11) DEFAULT NULL,
  `fechaCompletado` datetime DEFAULT NULL,
  `idUsuarioCompletado` int(11) DEFAULT NULL,
  `idContacto` int(11) DEFAULT NULL,
  `estaEnHojaDeRuta` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remito`
--

LOCK TABLES `remito` WRITE;
/*!40000 ALTER TABLE `remito` DISABLE KEYS */;
INSERT INTO `remito` VALUES (6,'2023-03-31 15:59:19','2023-03-31','Salida','Completado',12,12,16,'REF 1',1,NULL,NULL,'2023-03-31 19:51:10',1,'2023-03-31 19:51:56',2,12,1),(7,'2023-05-08 19:52:17','2023-05-08','Entrada','Abierto',12,12,17,NULL,1,NULL,NULL,'2023-05-08 19:52:44',1,NULL,NULL,10,0),(8,'2023-06-06 19:53:21','2023-06-06','Salida','Nuevo',12,12,17,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,10,0);
/*!40000 ALTER TABLE `remito` ENABLE KEYS */;
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
