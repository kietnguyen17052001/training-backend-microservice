-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: configuration
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `my_properties`
--

DROP TABLE IF EXISTS `my_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_properties` (
  `id` int NOT NULL AUTO_INCREMENT,
  `application` varchar(255) NOT NULL,
  `profile` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  `my_key` varchar(255) NOT NULL,
  `my_value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_properties`
--

LOCK TABLES `my_properties` WRITE;
/*!40000 ALTER TABLE `my_properties` DISABLE KEYS */;
INSERT INTO `my_properties` VALUES (1,'api-gateway','dev','master','server.port','6969'),(2,'api-gateway','dev','master','spring.cloud.gateway.routes[0].id','product-service'),(3,'api-gateway','dev','master','spring.cloud.gateway.routes[0].uri','http://localhost:8082'),(4,'api-gateway','dev','master','spring.cloud.gateway.routes[0].predicates[0]','Path=/api/v1/products/**'),(5,'api-gateway','dev','master','spring.cloud.gateway.routes[1].id','customer-service'),(6,'api-gateway','dev','master','spring.cloud.gateway.routes[1].uri','http://localhost:8081'),(7,'api-gateway','dev','master','spring.cloud.gateway.routes[1].predicates[0]','Path=/api/v1/customers/**'),(8,'customer-service','dev','master','server.port','8081'),(9,'customer-service','dev','master','spring.datasource.url','jdbc:postgresql://localhost:5432/CustomerManagement'),(10,'customer-service','dev','master','spring.datasource.username','postgres'),(11,'customer-service','dev','master','spring.datasource.password','5071'),(12,'customer-service','dev','master','spring.jpa.properties.hibernate.dialect','org.hibernate.dialect.PostgreSQLDialect'),(13,'customer-service','dev','master','spring.jpa.properties.hibernate.format_sql','true'),(14,'customer-service','dev','master','spring.jpa.hibernate.ddl-auto','update'),(15,'customer-service','dev','master','spring.jpa.show-sql','true'),(16,'product-service','dev','master','server.port','8082'),(17,'product-service','dev','master','spring.datasource.url','jdbc:postgresql://localhost:5432/ProductManagement'),(18,'product-service','dev','master','spring.datasource.username','postgres'),(19,'product-service','dev','master','spring.datasource.password','5071'),(20,'product-service','dev','master','spring.jpa.properties.hibernate.dialect','org.hibernate.dialect.PostgreSQLDialect'),(21,'product-service','dev','master','spring.jpa.properties.hibernate.format_sql','true'),(22,'product-service','dev','master','spring.jpa.hibernate.ddl-auto','update'),(23,'product-service','dev','master','spring.jpa.show-sql','true'),(24,'product-service','dev','master','spring.datasource.driver-class-name','org.postgresql.Driver'),(25,'customer-service','dev','master','spring.datasource.driver-class-name','org.postgresql.Driver'),(26,'api-gateway','dev','master','spring.cloud.gateway.routes[2].id','account-service'),(27,'api-gateway','dev','master','spring.cloud.gateway.routes[2].uri','http://localhost:8083'),(28,'api-gateway','dev','master','spring.cloud.gateway.routes[2].predicates[0]','Path=/api/v1/accounts/**'),(30,'account-service','dev','master','server.port','8083'),(31,'account-service','dev','master','spring.datasource.driver-class-name','org.postgresql.Driver'),(32,'account-service','dev','master','spring.datasource.url','jdbc:postgresql://localhost:5432/AccountManagement'),(33,'account-service','dev','master','spring.datasource.username','postgres'),(34,'account-service','dev','master','spring.datasource.password','5071'),(35,'account-service','dev','master','spring.jpa.properties.hibernate.dialect','org.hibernate.dialect.PostgreSQLDialect'),(36,'account-service','dev','master','spring.jpa.properties.hibernate.format_sql','true'),(37,'account-service','dev','master','spring.jpa.hibernate.ddl-auto','update'),(38,'account-service','dev','master','spring.jpa.show-sql','true');
/*!40000 ALTER TABLE `my_properties` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-25 17:38:21
