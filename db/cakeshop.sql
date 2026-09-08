-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: myshop
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brandname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'DPW'),(2,'MDesserts'),(3,'caravan fresh');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Chocolate Cake'),(2,'Cheese Cake'),(3,'Fruit Cake'),(4,'Vanilla  Cake'),(5,'White Chocolate Cake'),(6,'Coffee Cake'),(7,'Eggless Cake'),(8,'Mousse Cake'),(9,'Christmas Cake'),(10,'Ribbon Cake'),(11,'Desserts'),(12,'Cup Cakes'),(13,'Bento Cake'),(14,'Cakes');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Colombo');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `hotline` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Malindu cake Store','0112729729'),(2,'Sweet Delights','0112723678'),(3,'CBL Foods International (Pvt) Ltd','0117878602'),(4,'SwitzGroup','0552271670'),(5,'The English Cake Company','0112501345'),(6,'CakeHome','0552289762'),(12,'Wishque Cakes','0112222222');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `mobile` varchar(10) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `point` double NOT NULL,
  PRIMARY KEY (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('0723322123','Anoma','Priyadarshani','anoma@gmail.com',9.2),('0756677654','Kaveesha','Kalhara','kavee@gmail.com',14.92),('0756677890','Yohara','Kavishka','yoharaa45@gmail.com',2.5),('0767777777','Dineshika','Harshani','dine@gmail.com',0),('0776666890','Rushira','Manikya','rushi@gmail.com',3.74),('0789911234','Charitha','Athalge','charitha45@gmail.com',1.24);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `nic` varchar(45) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `date_registered` varchar(45) NOT NULL,
  `employee_type_id` int NOT NULL,
  `gender_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`email`),
  KEY `fk_employee_gender1_idx` (`gender_id`),
  KEY `fk_employee_employee_type1_idx` (`employee_type_id`),
  KEY `fk_employee_status1_idx` (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('arul@gmail.com','arul08asayafyc','Arul','Kaveesha','200278997766','0776543211','2024-11-24',1,1,1),('dinendra@gmail.com','dine312j','Dinendra','Lakshan','200278998877','0764433210','2024-12-20',2,1,1),('dinithi@gmail.com','dini123','Dini','Pabasara','200378210022','0787717624','2024-8-31',1,2,1),('githma@gmail.com','GithGo12','Githma','Bandara','200456544455','0765516524','2024-12-25',2,2,2),('kamani@gmail.com','KAmani12','Kamani','Kumari','200012321100','0758654321','2024-12-22',2,2,1),('kaushi@gmail.com','076kAUshi','Kaushi','Kavihari','199567555555','0766666666','2024-12-23',2,2,2),('ksu@gmail.com','passQ1211','Kasun','Kalhara','207787654422','0765543211','2024-09-14',2,1,2),('manaru12@gmail.com','manaruM23','Manaru','Suwahas','200378654433','0767755663','2024-9-4',2,1,1),('nadini@gmail.com','Nadu1234','Naduni','Dilhara','200088990066','0755221111','2025-05-22',2,2,1),('pavi@gmail.com','pavi123S','Pavindi','Ayodya','200678776633','0786655678','2024-09-18',2,2,2),('ruu@gmail.com','123Password','Rushani','Rashmila','200876543322','0786655443','2024-09-14',2,2,1),('yohani@gmail.com','Y123ssword','Yohani','Gamage','200056564312','0712222223','2025-01-01',1,2,2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_address`
--

DROP TABLE IF EXISTS `employee_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `line1` varchar(45) NOT NULL,
  `line2` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_address_city1_idx` (`city_id`),
  CONSTRAINT `fk_employee_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_address`
--

LOCK TABLES `employee_address` WRITE;
/*!40000 ALTER TABLE `employee_address` DISABLE KEYS */;
INSERT INTO `employee_address` VALUES (1,'No 230','Kotikawatta\r\n',1);
/*!40000 ALTER TABLE `employee_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type`
--

DROP TABLE IF EXISTS `employee_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type`
--

LOCK TABLES `employee_type` WRITE;
/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
INSERT INTO `employee_type` VALUES (1,'Admin'),(2,'Cashier');
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'Male'),(2,'Female');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn`
--

DROP TABLE IF EXISTS `grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grn` (
  `id` bigint NOT NULL,
  `date` date NOT NULL,
  `paid_amount` double NOT NULL,
  `supplier_mobile` varchar(10) NOT NULL,
  `employee_email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_supplier1_idx` (`supplier_mobile`),
  KEY `fk_grn_employee1_idx` (`employee_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
INSERT INTO `grn` VALUES (1726763242777,'2024-09-19',2189,'0767788765','dinithi@gmail.com'),(1726763407032,'2024-09-19',13453,'0765544554','dinithi@gmail.com'),(1726765859173,'2024-09-19',246880,'0765544554','dinithi@gmail.com'),(1726767142732,'2024-09-19',135685,'0765577678','dinithi@gmail.com'),(1726767680119,'2024-09-19',1488,'0765577678','dinithi@gmail.com'),(1726768043062,'2024-09-19',2246,'0765544554','dinithi@gmail.com'),(1726768557989,'2024-09-19',565,'0765577678','dinithi@gmail.com'),(1726768849293,'2024-09-19',14688,'0767788765','dinithi@gmail.com'),(1726769956883,'2024-09-19',234,'0765544554','dinithi@gmail.com'),(1726770169339,'2024-09-19',1500,'0765577678','dinithi@gmail.com'),(1726770311396,'2024-09-19',135,'0767788765','dinithi@gmail.com'),(1726777888999,'2024-09-14',600,'0765544554','ksu@gmail.com'),(1732289428747,'2024-11-22',500,'0767788765','dinithi@gmail.com'),(1734093523454,'2024-12-13',600,'0755544321','dinithi@gmail.com'),(1734267078753,'2024-12-15',480,'0758888999','null'),(1734630235680,'2024-12-19',1200,'0758888999','dinithi@gmail.com'),(1734699921240,'2024-12-20',2700,'0755544321','dinithi@gmail.com'),(1735788162258,'2025-01-02',9000,'0787766666','dinithi@gmail.com'),(1735801854762,'2025-01-02',5000,'0787766666','dinithi@gmail.com'),(1735802045470,'2025-01-02',7500,'0787766666','dinithi@gmail.com'),(1735803989022,'2025-01-02',600,'0787766666','dinithi@gmail.com'),(1735805016822,'2025-01-02',4500,'0787766666','dinithi@gmail.com'),(1735806724832,'2025-01-02',1300,'0787766666','dinithi@gmail.com'),(1735930236617,'2025-01-04',24000,'','dinithi@gmail.com'),(1747457640680,'2025-05-17',4900,'','dinithi@gmail.com'),(1747908119706,'2025-05-22',2900,'','dinithi@gmail.com');
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn_item`
--

DROP TABLE IF EXISTS `grn_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grn_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` double NOT NULL,
  `price` double NOT NULL,
  `grn_id` bigint NOT NULL,
  `stock_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_item_grn1_idx` (`grn_id`),
  KEY `fk_grn_item_stock1_idx` (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn_item`
--

LOCK TABLES `grn_item` WRITE;
/*!40000 ALTER TABLE `grn_item` DISABLE KEYS */;
INSERT INTO `grn_item` VALUES (1,3,500,1,1),(2,1,135,1726770311396,3),(3,1,500,1732289428747,4),(4,9,300,1734699921240,5),(5,20,450,1735788162258,6),(6,20,250,1735801854762,7),(7,30,250,1735802045470,8),(8,4,100,1735803989022,9),(9,2,100,1735803989022,10),(10,6,500,1735805016822,11),(11,6,250,1735805016822,12),(12,10,120,1735806724832,13),(13,80,300,1735930236617,14),(14,20,245,1747457640680,15),(15,20,145,1747908119706,16);
/*!40000 ALTER TABLE `grn_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` bigint NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  `discount` double NOT NULL,
  `paid_amount` double NOT NULL,
  `employee_email` varchar(45) NOT NULL,
  `customer_mobile` varchar(10) NOT NULL,
  `payment_method_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_employee1_idx` (`employee_email`),
  KEY `fk_invoice_customer1_idx` (`customer_mobile`),
  KEY `fk_invoice_payment_method1_idx` (`payment_method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1734398118346,'2024-12-17 06:45:39',0,499,'ruu@gmail.com','0756677890',1),(1734399076056,'2024-12-17 07:01:39',10,485,'ruu@gmail.com','0756677890',1),(1734436309818,'2024-12-17 17:22:04',0,1000,'ruu@gmail.com','0756677890',1),(1734436596353,'2024-12-17 17:26:58',0,500,'ruu@gmail.com','',1),(1734446782303,'2024-12-17 20:16:33',0,134,'ruu@gmail.com','0789911234',1),(1734446881005,'2024-12-17 20:18:12',0,1000,'ruu@gmail.com','0756677890',1),(1735550621898,'2024-12-30 14:54:44',0,124,'ruu@gmail.com','0756677890',2),(1735714554103,'2025-01-01 12:26:26',0,124,'ruu@gmail.com','',1),(1735714835782,'2025-01-01 12:30:50',0,124,'ruu@gmail.com','',1),(1735716420192,'2025-01-01 12:57:45',0,124,'ruu@gmail.com','',2),(1735719831513,'2025-01-01 13:56:23',0,124,'ruu@gmail.com','0723322123',2),(1735723004673,'2025-01-01 14:51:17',0,124,'ruu@gmail.com','',1),(1735723278145,'2025-01-01 14:51:38',0,0,'ruu@gmail.com','',2),(1735723301103,'2025-01-01 14:51:43',0,0,'ruu@gmail.com','',2),(1735724898050,'2025-01-01 15:18:32',0,124,'ruu@gmail.com','',1),(1735724989142,'2025-01-01 15:20:02',0,124,'ruu@gmail.com','',1),(1735726457714,'2025-01-01 15:44:29',0,124,'ruu@gmail.com','',1),(1735726469649,'2025-01-01 15:44:49',0,124,'ruu@gmail.com','',1),(1735737737479,'2025-01-01 18:53:24',0,500,'ruu@gmail.com','',1),(1735805941861,'2025-01-02 13:49:43',0,624,'ruu@gmail.com','0776666890',1),(1735814234496,'2025-01-02 16:08:01',0,620,'ruu@gmail.com','0723322123',1),(1735814491184,'2025-01-02 16:12:06',0,120,'ruu@gmail.com','',1),(1735815266211,'2025-01-02 16:24:48',0,223.76,'ruu@gmail.com','0756677890',1),(1735819235285,'2025-01-02 17:32:16',0,420,'ruu@gmail.com','0723322123',1),(1735819391045,'2025-01-02 17:34:07',10,500,'ruu@gmail.com','0789911234',1),(1735896125514,'2025-01-03 14:53:33',0,624,'ruu@gmail.com','',1),(1735898291958,'2025-01-03 15:29:29',0,634,'ruu@gmail.com','',1),(1747398424434,'2025-05-16 17:59:43',10,124,'ruu@gmail.com','0789911234',1),(1747978646896,'2025-05-23 11:11:51',11.76,400,'ruu@gmail.com','0776666890',1),(1747980927422,'2025-05-23 11:47:19',6,400,'ruu@gmail.com','0776666890',1),(1763820485948,'2025-11-22 19:43:27',10,250,'dinendra@gmail.com','0776666890',1),(1771760408856,'2026-02-22 18:22:07',20,120,'ruu@gmail.com','0776666890',1),(1788902993904,'2026-09-09 03:00:46',0,500,'manaru12@gmail.com','0723322123',1);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_item`
--

DROP TABLE IF EXISTS `invoice_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qty` varchar(45) NOT NULL,
  `stock_id` int NOT NULL,
  `invoice_id` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_invoice_item_stock1_idx` (`stock_id`),
  KEY `fk_invoice_item_invoice1_idx` (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_item`
--

LOCK TABLES `invoice_item` WRITE;
/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;
INSERT INTO `invoice_item` VALUES (1,'1',1,1734398118346),(2,'1',1,1734399076056),(3,'2',1,1734436309818),(4,'1',1,1734436596353),(5,'1',3,1734446782303),(6,'1',4,1734446881005),(7,'1',2,1735550621898),(8,'1',2,1735714554103),(9,'1',2,1735714835782),(10,'1',2,1735716420192),(11,'1',2,1735719831513),(12,'1',2,1735723004673),(13,'1',2,1735723278145),(14,'1',2,1735723301103),(15,'1',2,1735724898050),(16,'1',2,1735724989142),(17,'1',2,1735726457714),(18,'1',2,1735726469649),(19,'1',1,1735737737479),(20,'1',1,1735805941861),(21,'1',2,1735805941861),(22,'1',13,1735814234496),(23,'2',8,1735814234496),(24,'1',13,1735814491184),(25,'1',12,1735815266211),(26,'1',13,1735819235285),(27,'1',5,1735819235285),(28,'1',13,1735819391045),(29,'1',1,1735896125514),(30,'1',2,1735896125514),(31,'1',3,1735898291958),(32,'1',6,1735898291958),(33,'1',2,1747398424434),(34,'1',15,1747978646896),(35,'1',16,1747978646896),(36,'1',15,1747980927422),(37,'1',16,1747980927422),(38,'1',15,1763820485948),(39,'1',2,1771760408856),(40,'1',1,1788902993904);
/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Cash '),(2,'Card');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(20) NOT NULL,
  `pname` varchar(45) NOT NULL,
  `brand_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_brand1_idx` (`brand_id`),
  KEY `fk_product_category1_idx` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','White Chocolate Swiss Roll With Nuts',1,5),('10','Chocolate Cookie Cake 250g',1,1),('11','Eggless Chocolate Cake 500g',1,7),('12','Avacado Mousse Layer Cake 500g',1,8),('13','Chocolate Forest Cake 1kg',1,1),('14','Chunky Choco Chip Cake 1kg',1,1),('15','Eggless Choc Cake 500g',1,1),('2','Strawberry Cheese Cake 250g',1,2),('3','Strawberry Cheese Cake  500g',1,2),('4','Blueberry Cheesecake 1Kg',1,2),('5','Coffee Chocolate Crumble Cake 500g',2,6),('6','Coffee Snow Cake 1kg',1,6),('7','Chocolate Strawberry Cake 250g',1,1),('8','Cashew Chocolate Cake 500g',1,1),('9','Chocolate Macaroon Cake 250g',1,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Active'),(2,'Inactive');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `qty` double NOT NULL,
  `mfd` date NOT NULL,
  `exp` date NOT NULL,
  `product_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_product1_idx` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,500,36,'2025-02-14','2025-04-14','1'),(2,124,26,'2025-03-21','2025-09-12','1'),(3,134,19,'2025-03-20','2025-09-17','1'),(4,1000,0,'2025-04-23','2025-11-06','1'),(15,250,17,'2025-05-18','2025-05-27','11'),(16,150,18,'2025-05-19','2025-05-26','5');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `mobile` varchar(10) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `company_id` int NOT NULL,
  PRIMARY KEY (`mobile`),
  KEY `fk_supplier_company_idx` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('0744444444','Nilanthi','Kumari','nila@gmail.com',9),('0755544321','Arula','Tharinda','arultharu@gmail.com',5),('0758888999','Anurada','Disanayaka','anudisa@gmail.com',1),('0765544554','Amal','Perera','amal@gmail.com',1),('0767788765','Amara','Sirisena','amara@gmail.com',2),('0787766666','Jaliya','Akash','jaliya@gmail.com',6);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-09  3:48:34
