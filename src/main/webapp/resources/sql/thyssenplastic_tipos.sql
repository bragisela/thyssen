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
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `indiceDeFluidez` decimal(10,2) DEFAULT NULL,
  `densidad` decimal(10,2) DEFAULT NULL,
  `idPais` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'rubro Cliente 1','rubroCliente',NULL,NULL,NULL),(2,'rubro Cliente 2','rubroCliente',NULL,NULL,NULL),(3,'rubro Cliente 3','rubroCliente',NULL,NULL,NULL),(4,'Chofer','rol',NULL,NULL,NULL),(5,'Vendedor','rol',NULL,NULL,NULL),(6,'Logistica','rol',NULL,NULL,NULL),(7,'Legal','tipodomicilio',NULL,NULL,NULL),(8,'Real','tipodomicilio',NULL,NULL,NULL),(9,'Entrega','tipodomicilio',NULL,NULL,NULL),(10,'Tipo 1','tipomateriaprima',4.00,2.00,NULL),(11,'Tipo 2','tipomateriaprima',NULL,NULL,NULL),(12,'Denominacion 1','denominacion',NULL,NULL,NULL),(13,'Denominacion 2','denominacion',NULL,NULL,NULL),(14,'Petroquimica 1','petroquimica',NULL,NULL,NULL),(15,'Petroquimica 2','petroquimica',NULL,NULL,NULL),(16,'rubro articulo 1','rubroArticulo',NULL,NULL,NULL),(17,'rubro articulo 2','rubroArticulo',NULL,NULL,NULL),(18,'unidad Venta Articulo 1','unidadVentaArticulo',NULL,NULL,NULL),(19,'unidad Venta Articulo 2','unidadVentaArticulo',NULL,NULL,NULL),(20,'tipo Materiales Articulo 1','tipoMaterialesArticulo',NULL,NULL,NULL),(21,'tipo Materiales Articulo 2','tipoMaterialesArticulo',NULL,NULL,NULL),(22,'rubro Proveedor 1','rubroProveedor',NULL,NULL,NULL),(23,'rubro Proveedor 2','rubroProveedor',NULL,NULL,NULL),(24,'rubro Proveedor 3','rubroProveedor',NULL,NULL,NULL),(31,'Maquina 1','maquinaMantenimiento',NULL,NULL,NULL),(32,'Maquina 2','maquinaMantenimiento',NULL,NULL,NULL),(33,'tipo Reparacion 1','tipoReparacionMantenimiento',NULL,NULL,NULL),(34,'tipo Reparacion 2','tipoReparacionMantenimiento',NULL,NULL,NULL),(35,'Buenos Aires','provincia',NULL,NULL,60),(36,'Cordoba','provincia',NULL,NULL,60),(37,'rubro articulo 3','rubroArticulo',NULL,NULL,NULL),(38,'PEBD','articuloMaterial',NULL,NULL,NULL),(39,'LLDPE','articuloMaterial',NULL,NULL,NULL),(40,'MDPE','articuloMaterial',NULL,NULL,NULL),(41,'Transparente','articuloColor',NULL,NULL,NULL),(42,'Blanco','articuloColor',NULL,NULL,NULL),(43,'Negro','articuloColor',NULL,NULL,NULL),(44,'tubo de carton','tipoBobina',NULL,NULL,NULL),(45,'tubo de plastico','tipoBobina',NULL,NULL,NULL),(46,'Linea Tinta 1','lineaTintas',NULL,NULL,NULL),(47,'Linea Tinta 2','lineaTintas',NULL,NULL,NULL),(48,'Linea Solvente 1','lineaSolventes',NULL,NULL,NULL),(49,'Linea Solvente 2','lineaSolventes',NULL,NULL,NULL),(50,'Cajas','bultosEn',NULL,NULL,NULL),(51,'Bolsas','bultosEn',NULL,NULL,NULL),(52,'Palet 1','palets',NULL,NULL,NULL),(53,'Palet 2','palets',NULL,NULL,NULL),(54,'Unidad Venta 1','ordenDeProduccionUnidadVenta',NULL,NULL,NULL),(55,'Unidad Venta 2','ordenDeProduccionUnidadVenta',NULL,NULL,NULL),(56,'Plegadora 1','plegadoraBobina',NULL,NULL,NULL),(57,'Plegadora 2','plegadoraBobina',NULL,NULL,NULL),(60,'Argentina','pais',NULL,NULL,NULL),(61,'Principal','deposito',NULL,NULL,NULL),(62,'Fuera de standard','deposito',NULL,NULL,NULL),(63,'Campo','deposito',NULL,NULL,NULL),(64,'Scrap','deposito',NULL,NULL,NULL),(68,'Transporte','rubroProveedor',NULL,NULL,NULL),(69,'Brasil','pais',NULL,NULL,NULL),(70,'Uruguay','pais',NULL,NULL,NULL),(71,'Paraguay','pais',NULL,NULL,NULL),(72,'Australia','pais',NULL,NULL,NULL),(73,'Otro','provincia',NULL,NULL,69),(74,'Otro','provincia',NULL,NULL,70),(75,'Otro','provincia',NULL,NULL,71),(76,'Otro','provincia',NULL,NULL,72),(77,'Catamarca','provincia',NULL,NULL,60),(78,'Chaco','provincia',NULL,NULL,60),(79,'Chubut','provincia',NULL,NULL,60),(80,'Corrientes','provincia',NULL,NULL,60),(81,'Entre Rios','provincia',NULL,NULL,60),(82,'Formosa','provincia',NULL,NULL,60),(83,'Jujuy','provincia',NULL,NULL,60),(84,'La Pampa','provincia',NULL,NULL,60),(85,'La Rioja','provincia',NULL,NULL,60),(86,'Mendoza','provincia',NULL,NULL,60),(87,'Misiones','provincia',NULL,NULL,60),(88,'Neuquen','provincia',NULL,NULL,60),(89,'Rio Negro','provincia',NULL,NULL,60),(90,'Salta','provincia',NULL,NULL,60),(91,'San Juan','provincia',NULL,NULL,60),(92,'San Luis','provincia',NULL,NULL,60),(93,'Santa Cruz','provincia',NULL,NULL,60),(94,'Santa Fe','provincia',NULL,NULL,60),(95,'Santiago del Estero','provincia',NULL,NULL,60),(96,'Tierra del Fuego','provincia',NULL,NULL,60),(97,'Tucuman','provincia',NULL,NULL,60),(98,'Ciudad Autónoma de Bs As','provincia',NULL,NULL,60),(99,'Exclusión','origenScrap',NULL,NULL,NULL),(100,'PEBD','tipoMaterialScrap',NULL,NULL,NULL),(101,'Formato 1','formatoScrap',NULL,NULL,NULL),(102,'Arranque de Máquina','motivoScrap',NULL,NULL,NULL),(103,'Formato 2','formatoScrap',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07  9:32:29
