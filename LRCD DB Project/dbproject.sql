CREATE DATABASE  IF NOT EXISTS `dbproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbproject`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: dbproject
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `aids`
--

DROP TABLE IF EXISTS `aids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aids` (
  `missionID` varchar(4) NOT NULL,
  `missionDate` varchar(100) NOT NULL,
  `patientID` varchar(4) NOT NULL,
  `aidTime` time NOT NULL,
  `arrivalTime` time NOT NULL,
  PRIMARY KEY (`missionID`,`missionDate`,`patientID`),
  KEY `patientIDA_idx` (`patientID`),
  CONSTRAINT `missionIDA` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`),
  CONSTRAINT `patientIDA` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aids`
--

LOCK TABLES `aids` WRITE;
/*!40000 ALTER TABLE `aids` DISABLE KEYS */;
/*!40000 ALTER TABLE `aids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ambulance`
--

DROP TABLE IF EXISTS `ambulance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ambulance` (
  `ambulanceID` varchar(4) NOT NULL,
  `type` varchar(20) NOT NULL,
  `condition` char(1) NOT NULL,
  `mileage` int NOT NULL,
  `aStatus` varchar(1) NOT NULL,
  PRIMARY KEY (`ambulanceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambulance`
--

LOCK TABLES `ambulance` WRITE;
/*!40000 ALTER TABLE `ambulance` DISABLE KEYS */;
INSERT INTO `ambulance` VALUES ('A001','4-wheel','F',10000,'A'),('A002','4-wheel','F',25000,'A'),('A003','4-wheel','F',1000,'A');
/*!40000 ALTER TABLE `ambulance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departmentID` varchar(4) NOT NULL,
  `address` varchar(100) NOT NULL,
  `volunteersNb` int NOT NULL,
  PRIMARY KEY (`departmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('D001','Beirut',10),('D002','Byblos',20),('D003','Jounieh',24);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispatches`
--

DROP TABLE IF EXISTS `dispatches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dispatches` (
  `missionID` varchar(4) NOT NULL,
  `missionDate` varchar(100) NOT NULL,
  `volunteerID` varchar(4) NOT NULL,
  `departmentID` varchar(4) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` varchar(100) NOT NULL,
  PRIMARY KEY (`missionID`,`missionDate`,`volunteerID`,`departmentID`),
  KEY `departmentIDDI_idx` (`departmentID`),
  KEY `volunteerIDDI` (`volunteerID`),
  CONSTRAINT `departmentIDDI` FOREIGN KEY (`departmentID`) REFERENCES `volunteer` (`departmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `missionIDDI` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `volunteerIDDI` FOREIGN KEY (`volunteerID`) REFERENCES `volunteer` (`volunteerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispatches`
--

LOCK TABLES `dispatches` WRITE;
/*!40000 ALTER TABLE `dispatches` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispatches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donation` (
  `donationID` varchar(4) NOT NULL,
  `departmentID` varchar(4) NOT NULL,
  `donorName` varchar(50) DEFAULT NULL,
  `donationDate` varchar(100) DEFAULT NULL,
  `donationType` varchar(50) NOT NULL,
  `donationAmount` varchar(10) NOT NULL,
  PRIMARY KEY (`donationID`,`departmentID`),
  KEY `departmentIDD_idx` (`departmentID`),
  CONSTRAINT `departmentIDD` FOREIGN KEY (`departmentID`) REFERENCES `department` (`departmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation`
--

LOCK TABLES `donation` WRITE;
/*!40000 ALTER TABLE `donation` DISABLE KEYS */;
/*!40000 ALTER TABLE `donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `equipmentID` varchar(4) NOT NULL,
  `eCDiD` varchar(4) NOT NULL,
  `equCategoryID` varchar(20) NOT NULL,
  `eCondition` varchar(1) NOT NULL,
  PRIMARY KEY (`equipmentID`,`equCategoryID`,`eCDiD`),
  KEY `equCategoryID_idx` (`equCategoryID`),
  KEY `equCDID_idx` (`eCDiD`),
  CONSTRAINT `equCategoryID` FOREIGN KEY (`equCategoryID`) REFERENCES `equipmentcategory` (`equCategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES ('E001','D001','Bandage','N'),('E001','D001','Breathing Circuit ','N'),('E002','D001','Bandage','N'),('E002','D001','Breathing Circuit ','N'),('E003','D001','Bandage','N'),('E003','D001','Breathing Circuit ','N');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipmentcategory`
--

DROP TABLE IF EXISTS `equipmentcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipmentcategory` (
  `equCategoryID` varchar(20) NOT NULL,
  `eCDepartmentID` varchar(4) NOT NULL,
  `quantity` int NOT NULL,
  `priority` char(1) NOT NULL,
  PRIMARY KEY (`equCategoryID`,`eCDepartmentID`),
  KEY `eCDepartmentID_idx` (`eCDepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipmentcategory`
--

LOCK TABLES `equipmentcategory` WRITE;
/*!40000 ALTER TABLE `equipmentcategory` DISABLE KEYS */;
INSERT INTO `equipmentcategory` VALUES ('Bandage','D001',3,'L'),('Bandage','D002',0,'L'),('Breathing Circuit ','D001',3,'H'),('Breathing Circuit ','D002',0,'H'),('Defibrillator','D001',0,'H'),('Defibrillator','D002',0,'H'),('Mask','D001',0,'H'),('Mask','D002',0,'H'),('Needles','D001',0,'H'),('Needles','D002',0,'H'),('Nerve Stimulator ','D001',0,'H'),('Nerve Stimulator ','D002',0,'H'),('Oximeter ','D001',0,'M'),('Oximeter ','D002',0,'M');
/*!40000 ALTER TABLE `equipmentcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `volunteerID` varchar(4) NOT NULL,
  `departmentID` varchar(4) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`volunteerID`,`departmentID`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('C001','D001','C1234'),('V001','D001','1234'),('V002','D001','1234'),('V002','D002','1234'),('V003','D001','1234');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission` (
  `missionID` varchar(4) NOT NULL,
  `missionDate` varchar(100) NOT NULL,
  `patientID` varchar(4) NOT NULL,
  `receivedTime` time NOT NULL,
  `missionType` varchar(10) NOT NULL,
  `missionDestination` varchar(50) NOT NULL,
  `support` varchar(50) NOT NULL,
  PRIMARY KEY (`missionID`,`missionDate`,`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission`
--

LOCK TABLES `mission` WRITE;
/*!40000 ALTER TABLE `mission` DISABLE KEYS */;
/*!40000 ALTER TABLE `mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patientID` varchar(4) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` char(1) NOT NULL,
  `case` varchar(100) NOT NULL,
  `condition` char(1) NOT NULL,
  `medicalHistory` varchar(300) NOT NULL,
  PRIMARY KEY (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirements`
--

DROP TABLE IF EXISTS `requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirements` (
  `missionID` varchar(4) NOT NULL,
  `missionDate` varchar(100) NOT NULL,
  `equipmentID` varchar(4) NOT NULL,
  `equCategoryID` varchar(4) NOT NULL,
  PRIMARY KEY (`missionID`,`missionDate`,`equipmentID`,`equCategoryID`),
  KEY `equipmentIDR_idx` (`equipmentID`),
  KEY `equCategoryIDR_idx` (`equCategoryID`),
  CONSTRAINT `equCategoryIDR` FOREIGN KEY (`equCategoryID`) REFERENCES `equipmentcategory` (`equCategoryID`),
  CONSTRAINT `equipmentIDR` FOREIGN KEY (`equipmentID`) REFERENCES `equipment` (`equipmentID`),
  CONSTRAINT `missionIDR` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements`
--

LOCK TABLES `requirements` WRITE;
/*!40000 ALTER TABLE `requirements` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transports`
--

DROP TABLE IF EXISTS `transports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transports` (
  `patientID` varchar(4) NOT NULL,
  `ambulanceID` varchar(4) NOT NULL,
  `locationFrom` varchar(50) NOT NULL,
  PRIMARY KEY (`patientID`,`ambulanceID`,`locationFrom`),
  KEY `ambulanceIDT_idx` (`ambulanceID`),
  CONSTRAINT `ambulanceIDT` FOREIGN KEY (`ambulanceID`) REFERENCES `ambulance` (`ambulanceID`),
  CONSTRAINT `patientIDT` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transports`
--

LOCK TABLES `transports` WRITE;
/*!40000 ALTER TABLE `transports` DISABLE KEYS */;
/*!40000 ALTER TABLE `transports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer`
--

DROP TABLE IF EXISTS `volunteer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volunteer` (
  `volunteerID` varchar(4) NOT NULL,
  `departmentID` varchar(4) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`volunteerID`,`departmentID`),
  KEY `departmentID_idx` (`departmentID`),
  CONSTRAINT `departmentID` FOREIGN KEY (`departmentID`) REFERENCES `department` (`departmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer`
--

LOCK TABLES `volunteer` WRITE;
/*!40000 ALTER TABLE `volunteer` DISABLE KEYS */;
INSERT INTO `volunteer` VALUES ('V001','D001','A'),('V002','D001','A'),('V002','D002','A'),('V003','D001','A');
/*!40000 ALTER TABLE `volunteer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-19 18:28:30
