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
-- Table structure for table `articulofichatecnica`
--

DROP TABLE IF EXISTS `articulofichatecnica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulofichatecnica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idArticulo` int(11) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `version` decimal(10,1) DEFAULT '1.0',
  `observaciones` varchar(4000) DEFAULT NULL,
  `especificacion` varchar(4000) DEFAULT NULL,
  `urlEspecificacion` varchar(255) DEFAULT NULL,
  `idUsuarioCreacion` int(11) DEFAULT NULL,
  `idUsuarioModificacion` int(11) DEFAULT NULL,
  `fechaModificacion` date DEFAULT NULL,
  `idMaterial` int(11) DEFAULT NULL,
  `lamina` tinyint(4) DEFAULT NULL,
  `bobina` tinyint(4) DEFAULT NULL,
  `idColorA` int(11) DEFAULT NULL,
  `idColorB` int(11) DEFAULT NULL,
  `idColorC` int(11) DEFAULT NULL,
  `idColorD` int(11) DEFAULT NULL,
  `idColorE` int(11) DEFAULT NULL,
  `idColorF` int(11) DEFAULT NULL,
  `idColorG` int(11) DEFAULT NULL,
  `ancho` decimal(10,2) DEFAULT NULL,
  `fuelleIzquierdo` decimal(10,2) DEFAULT NULL,
  `fuelleDerecho` decimal(10,2) DEFAULT NULL,
  `espesorNominal` decimal(10,2) DEFAULT NULL,
  `espesorPromedio` decimal(10,2) DEFAULT NULL,
  `espesorArticulo` decimal(10,2) DEFAULT NULL,
  `cantidadCapas` int(11) DEFAULT NULL,
  `pesoEspecifico` decimal(10,2) DEFAULT NULL,
  `anchoBruto` decimal(10,2) DEFAULT NULL,
  `bobinadoBarras` decimal(10,2) DEFAULT NULL,
  `metros` decimal(10,2) DEFAULT NULL,
  `peso` decimal(10,2) DEFAULT NULL,
  `diametro` decimal(10,2) DEFAULT NULL,
  `empalmes` decimal(10,2) DEFAULT NULL,
  `idTipoBobina` int(11) DEFAULT NULL,
  `diametro2` decimal(10,2) DEFAULT NULL,
  `largo` decimal(10,2) DEFAULT NULL,
  `espesor` decimal(10,2) DEFAULT NULL,
  `peso2` decimal(10,2) DEFAULT NULL,
  `idLineaTintas` int(11) DEFAULT NULL,
  `idLineaSolventes` int(11) DEFAULT NULL,
  `idBultosEn` int(11) DEFAULT NULL,
  `bultosPorPalet` decimal(10,2) DEFAULT NULL,
  `idPalet` int(11) DEFAULT NULL,
  `posicionPalet` varchar(45) DEFAULT NULL,
  `idMateriaPrima1CapaA` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaA` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaA` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaA` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaA` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaA` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaA` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaA` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaA` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaA` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaA` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaA` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima1CapaB` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaB` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaB` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaB` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaB` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaB` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaB` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaB` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaB` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaB` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaB` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaB` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima1CapaC` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaC` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaC` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaC` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaC` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaC` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaC` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaC` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaC` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaC` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaC` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaC` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima1CapaD` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaD` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaD` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaD` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaD` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaD` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaD` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaD` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaD` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaD` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaD` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaD` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima1CapaE` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaE` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaE` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaE` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaE` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaE` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaE` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaE` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaE` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaE` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaE` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaE` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima1CapaF` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaF` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaF` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaF` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaF` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaF` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaF` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaF` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaF` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaF` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaF` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaF` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima1CapaG` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage1CapaG` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima2CapaG` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage2CapaG` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima3CapaG` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage3CapaG` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima4CapaG` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage4CapaG` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima5CapaG` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage5CapaG` decimal(10,2) DEFAULT NULL,
  `idMateriaPrima6CapaG` int(11) DEFAULT NULL,
  `materiaPrimaPorcentage6CapaG` decimal(10,2) DEFAULT NULL,
  `resumenCapaA` decimal(10,2) DEFAULT NULL,
  `resumenCapaB` decimal(10,2) DEFAULT NULL,
  `resumenCapaC` decimal(10,2) DEFAULT NULL,
  `resumenCapaD` decimal(10,2) DEFAULT NULL,
  `resumenCapaE` decimal(10,2) DEFAULT NULL,
  `resumenCapaF` decimal(10,2) DEFAULT NULL,
  `resumenCapaG` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulofichatecnica`
--

LOCK TABLES `articulofichatecnica` WRITE;
/*!40000 ALTER TABLE `articulofichatecnica` DISABLE KEYS */;
INSERT INTO `articulofichatecnica` VALUES (9,2,'2023-02-09',1.9,'we','we',NULL,1,1,'2023-02-09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,2,'2023-02-10',2.0,'wewe','wewe','D:/Temp/2023-02-09/3hrs.pptx',1,1,'2023-02-21',39,1,0,41,42,43,41,NULL,NULL,NULL,1.06,2.05,3.00,4.00,5.00,6.01,4,0.01,0.02,0.03,0.04,1.00,0.06,0.07,44,0.08,0.09,0.10,0.11,NULL,47,50,0.01,53,'vertical',1,10.00,NULL,NULL,NULL,NULL,3,90.00,NULL,NULL,NULL,NULL,NULL,NULL,2,100.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,100.00,NULL,NULL,NULL,NULL,NULL,NULL,1,60.00,NULL,NULL,NULL,NULL,4,40.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,34.00,16.00,20.00,30.00,NULL,NULL,NULL),(12,2,'2023-02-22',2.2,'wewe adasdd','wewe','D:/Temp/2023-02-09/3hrs.pptx',1,1,'2023-02-22',39,1,0,41,42,43,41,NULL,NULL,NULL,1.06,2.05,3.00,4.00,5.00,6.01,4,0.01,0.02,0.03,0.04,1.00,0.06,0.07,44,0.08,0.09,0.10,0.11,NULL,47,50,0.01,53,'vertical',1,10.00,NULL,NULL,NULL,NULL,3,90.00,NULL,NULL,NULL,NULL,NULL,NULL,2,100.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,100.00,NULL,NULL,NULL,NULL,NULL,NULL,1,60.00,NULL,NULL,NULL,NULL,4,40.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,34.00,16.00,20.00,30.00,NULL,NULL,NULL),(13,3,'2023-03-02',2.3,'erwr','ewrewrwer',NULL,1,1,'2023-03-02',38,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `articulofichatecnica` ENABLE KEYS */;
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
