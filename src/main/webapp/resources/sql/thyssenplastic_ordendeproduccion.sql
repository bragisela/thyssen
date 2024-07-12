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
-- Table structure for table `ordendeproduccion`
--

DROP TABLE IF EXISTS `ordendeproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordendeproduccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAlta` date DEFAULT NULL,
  `fechaCierre` date DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `idArticulo` int(11) DEFAULT NULL,
  `fechaPedido` date DEFAULT NULL,
  `idUnidadVenta` int(11) DEFAULT NULL,
  `cantidadAProducir` int(11) DEFAULT NULL,
  `idFichaTecnica` int(11) DEFAULT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  `idUsuarioCierre` int(11) DEFAULT NULL,
  `fechaAltaImpresion` datetime DEFAULT NULL,
  `fechaAbierta` datetime DEFAULT NULL,
  `idUsuarioAbierta` int(11) DEFAULT NULL,
  `fechaFabricacion` datetime DEFAULT NULL,
  `idUsuarioFabricacion` int(11) DEFAULT NULL,
  `fechaEmpaque` datetime DEFAULT NULL,
  `idUsuarioEmpaque` int(11) DEFAULT NULL,
  `fechaPallet` datetime DEFAULT NULL,
  `idUsuarioPallet` int(11) DEFAULT NULL,
  `stockActual` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordendeproduccion`
--

LOCK TABLES `ordendeproduccion` WRITE;
/*!40000 ALTER TABLE `ordendeproduccion` DISABLE KEYS */;
INSERT INTO `ordendeproduccion` VALUES (2,'2023-03-02','2023-03-29','Completado',12,2,'2023-03-01',55,100,10,NULL,1,3,'2023-03-02 17:00:00','2023-03-20 20:05:13',1,'2023-03-20 20:06:01',3,'2023-03-20 20:06:10',3,'2023-03-20 20:06:19',3,75),(3,'2023-03-18',NULL,'Completado',12,3,'2023-03-18',54,200,13,NULL,1,NULL,'2023-03-18 10:00:00','2023-03-20 20:05:23',1,NULL,NULL,NULL,NULL,NULL,NULL,10),(5,'2023-04-21',NULL,'Empaque',12,2,'2023-04-21',54,10,12,NULL,1,NULL,'2023-04-21 08:55:50','2023-04-21 09:06:43',1,'2023-05-08 19:36:14',3,'2023-05-08 19:37:53',3,NULL,NULL,0),(7,'2023-04-24',NULL,'Pallet',12,2,'2023-04-24',54,120,12,NULL,1,NULL,'2023-04-24 19:55:51','2023-06-01 16:14:49',1,'2023-06-01 16:15:07',3,'2023-06-01 16:15:24',3,'2023-06-01 16:15:44',3,0),(8,'2023-06-06',NULL,'Pallet',12,2,'2023-06-06',54,50,12,NULL,1,NULL,'2023-06-06 19:39:13','2023-06-06 19:39:22',1,'2023-06-06 19:39:54',3,'2023-06-06 19:40:19',3,'2023-06-06 19:40:48',3,0);
/*!40000 ALTER TABLE `ordendeproduccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:35
