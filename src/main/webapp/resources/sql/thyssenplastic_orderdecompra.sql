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
-- Table structure for table `orderdecompra`
--

DROP TABLE IF EXISTS `orderdecompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdecompra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAlta` date DEFAULT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `idProveedor` int(11) DEFAULT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `fechaCierreOrden` date DEFAULT NULL,
  `idUsuarioAlta` int(11) DEFAULT NULL,
  `idUsuarioCierre` int(11) DEFAULT NULL,
  `referenciaAdministrativa` varchar(255) DEFAULT NULL,
  `fechaAltaImpresion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdecompra`
--

LOCK TABLES `orderdecompra` WRITE;
/*!40000 ALTER TABLE `orderdecompra` DISABLE KEYS */;
INSERT INTO `orderdecompra` VALUES (1,'2023-02-01',NULL,'Cerrado',5,'test2 item supera cantidad pedida','2023-02-16',1,1,NULL,'2023-02-01 10:00:00'),(2,'2023-02-01',NULL,'Cerrado',5,'test 3','2023-02-04',1,1,NULL,'2023-02-01 10:00:00'),(3,'2023-02-07',NULL,'Completado',6,'nadad',NULL,1,NULL,NULL,'2023-02-07 10:00:00'),(4,'2023-02-08',NULL,'Cerrado',6,'nada','2023-02-09',1,1,NULL,'2023-02-08 10:00:00'),(5,'2023-02-16',NULL,'Completado',6,'nada e wew wqe wqe',NULL,1,NULL,NULL,'2023-02-16 10:00:00'),(7,'2023-02-16',NULL,'Completado',5,'test se completo porque el proveedor no tiene mas stock',NULL,1,NULL,NULL,'2023-02-16 10:00:00'),(9,'2023-02-16','2023-02-18','Completado',5,'asd jhjhj',NULL,1,NULL,NULL,'2023-02-16 10:00:00'),(10,'2023-02-16','2023-02-18','Completado',6,'qwe',NULL,1,NULL,NULL,'2023-02-16 10:00:00'),(11,'2023-03-08','2023-03-08','Abierto',5,'nada',NULL,1,NULL,'232','2023-03-08 10:00:00');
/*!40000 ALTER TABLE `orderdecompra` ENABLE KEYS */;
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
