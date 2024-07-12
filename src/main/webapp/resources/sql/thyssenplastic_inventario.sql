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
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `idArticulo` int(11) DEFAULT NULL,
  `idMateriaPrima` int(11) DEFAULT NULL,
  `idInsumo` int(11) DEFAULT NULL,
  `lote` varchar(45) DEFAULT NULL,
  `idDeposito` int(11) DEFAULT NULL,
  `existentePeso` decimal(10,2) DEFAULT NULL,
  `existenteCantidad` decimal(10,2) DEFAULT NULL,
  `ajustePeso` decimal(10,2) DEFAULT NULL,
  `ajusteCantidad` decimal(10,2) DEFAULT NULL,
  `resultadoPeso` decimal(10,2) DEFAULT NULL,
  `resultadoCantidad` decimal(10,2) DEFAULT NULL,
  `motivo` varchar(4000) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (2,'insumos',NULL,NULL,1,NULL,NULL,10.00,20.00,30.00,40.00,50.00,60.00,'nada',1,'2023-01-10'),(4,'materiaPrima',NULL,2,NULL,NULL,NULL,5.00,60.00,4.00,5.00,9.00,65.00,'ajuste por prueba',1,'2023-02-07'),(5,'articulos',2,NULL,NULL,NULL,NULL,0.00,0.00,10.00,50.00,10.00,50.00,'ajuste por prueba',1,'2023-02-07'),(6,'materiaPrima',NULL,2,NULL,NULL,NULL,9.00,65.00,-2.00,-2.00,7.00,63.00,'ajuste por prueba',1,'2023-02-08'),(7,'materiaPrima',NULL,4,NULL,NULL,NULL,0.00,0.00,10.00,100.00,10.00,100.00,'ajuste por prueba',1,'2023-02-08'),(8,'insumos',NULL,NULL,1,NULL,NULL,0.00,150.00,100.00,30.00,100.00,180.00,'ajuste por prueba',1,'2023-02-09'),(9,'articulos',3,NULL,NULL,NULL,NULL,0.00,0.00,200.00,200.00,200.00,200.00,'test',1,'2023-03-28');
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:36
