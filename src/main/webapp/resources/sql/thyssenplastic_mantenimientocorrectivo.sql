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
-- Table structure for table `mantenimientocorrectivo`
--

DROP TABLE IF EXISTS `mantenimientocorrectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mantenimientocorrectivo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAlta` date DEFAULT NULL,
  `idMaquina` int(11) DEFAULT NULL,
  `componente` varchar(255) DEFAULT NULL,
  `horaParada` varchar(45) DEFAULT NULL,
  `prioridad` varchar(45) DEFAULT NULL,
  `problema` varchar(4000) DEFAULT NULL,
  `horaArranque` varchar(45) DEFAULT NULL,
  `idRepuesto` int(11) DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `actividadRealizada` varchar(4000) DEFAULT NULL,
  `idTipoReparacion` int(11) DEFAULT NULL,
  `observacion` varchar(4000) DEFAULT NULL,
  `idUserAlta` int(11) DEFAULT NULL,
  `idUserFin` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `fechaDeReparacionDesde` varchar(100) DEFAULT NULL,
  `fechaDeReparacionHasta` varchar(100) DEFAULT NULL,
  `intervaloReparacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mantenimientocorrectivo`
--

LOCK TABLES `mantenimientocorrectivo` WRITE;
/*!40000 ALTER TABLE `mantenimientocorrectivo` DISABLE KEYS */;
INSERT INTO `mantenimientocorrectivo` VALUES (1,'2023-01-17',31,'nada','03:24','media','test','18:27',1,'2023-01-18','test 2',NULL,'nada 2',1,2,'Cerrado',NULL,NULL,NULL),(2,'2023-01-16',32,'nada 2','16:44','baja','probelema 2','19:52',1,'2023-01-19','qweqwe',NULL,'qwewqewqewqe wqe',1,2,'Cerrado',NULL,NULL,NULL),(3,'2023-01-17',32,'nada 3','04:57','alta','232323','16:56',1,'2023-01-18','qww',NULL,'qsadasd',1,2,'Cerrado',NULL,NULL,NULL),(4,'2023-01-18',32,'rwerewr','17:18','media','tertt','17:22',1,'2023-01-19','324324',NULL,'2344324',1,2,'Cerrado',NULL,NULL,NULL),(5,'2023-01-30',31,'nada','10:42','alta','tesrt','22:46',2,'2023-01-30','nadadda',NULL,'asdsad',1,2,'Cerrado',NULL,NULL,NULL),(6,'2023-01-31',32,'nada 2','14:21','alta','nadaa',NULL,1,'2023-01-31','nada',NULL,'adsad',1,2,'Cerrado','2023-01-31T15:05','2023-02-02T15:05',2),(7,'2023-01-31',32,'nada','15:10','alta','eer',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'Abierto',NULL,NULL,NULL);
/*!40000 ALTER TABLE `mantenimientocorrectivo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:28
