-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mortgage_loan
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applications`
--
CREATE database IF NOT EXISTS mortgage_loan_db;
use mortgage_loan_db;
DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `application_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(120) NOT NULL,
  `driving_license` varchar(45) NOT NULL,
  `employer_name` varchar(45) NOT NULL,
  `employer_address` varchar(120) NOT NULL,
  `job_title` varchar(45) DEFAULT NULL,
  `annual_salary` varchar(45) NOT NULL,
  `monthly_inhand_salary` varchar(45) NOT NULL,
  `bank_name` varchar(45) NOT NULL,
  `account_number` varchar(45) NOT NULL,
  `accout_type` varchar(10) NOT NULL DEFAULT '1',
  `status` int(11) NOT NULL DEFAULT '0',
  `status_changed_by` int(11) DEFAULT NULL,
  `is_read` tinyint(4) DEFAULT NULL,
  `is_assigned` tinyint(4) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  `modified_on` datetime NOT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `assigned_to` int(11) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (1,'Noah','Olivia','1995-03-07','noah.olivia@gmail.com','4222578965','927 Kimberly Way, MI','BGL8976TY','HP','2518 Sunset Drive, Watson','Software Engineer','5346','290','DBS','5359832','Saving',3,2,1,1,'2019-03-02 21:52:29',1,0,'2019-03-02 21:52:29',NULL,2),(2,'James','Isabella','2000-03-20','jems.is@gmail.com','8945789555','2518 Sunset Drive, Watson','GHT4532TU','Passport Dept.','1547 Woodland Drive, Chicago','Manager','8768','2351','HDFC','534527282','Current',3,3,1,1,'2019-03-02 23:08:51',2,0,'2019-03-02 23:08:51',NULL,3),(3,'Jayden','Layla','2019-03-06','jayden@gmail.com','6456456234','1780  Rockford Mountain Lane, WY','VSFS234KK','Microsoft',' 508 Virginia Street Chicago, IL','Technical Lead','10045','2789','ICICI','2342245643','Saving',1,NULL,0,1,'2019-03-02 23:16:36',3,0,'2019-03-02 23:16:36',NULL,3),(4,'John','Lillian','1994-03-06','john@gmail.com','2442424647','Carol J. Stephens 1635 Franklin Street Montgomery, AL ','FSFD2423D','IBM','Michael I. Days 3756 Preston Street Wichita','HR Manager','35345','1233','DSP','23133534','Current',2,3,1,1,'2019-03-02 23:20:57',4,0,'2019-03-02 23:20:57',NULL,3),(5,'Luke','Mila','1997-03-05','luke@gmail.com','4442123987','Donald M. Palmer 2595 Pearlman Avenue Sudbury, MA','BGU8789Y','Govt. Teacher','Micheal R. Porterfield 508 Virginia Street Chicago','Principle','23412','3123','HDFC','93213','Current',1,NULL,0,1,'2019-03-02 23:24:24',5,0,'2019-03-02 23:24:24',NULL,2),(6,'Jack','Eleanor','1999-07-05','jack@gmail.com','8864342546','Nathan K. Flores 1516 Holt Street West Palm Beach, FL','DGF64646G','TCS','Avenue Sudbury, MA 01776 Phone:293-112-459	Micheal R. Porterfield 508 Virginia Street Chicago','Software Engineer','56454','3422','ICICI','242341321','Saving',4,2,1,1,'2019-03-02 23:27:32',6,0,'2019-03-02 23:27:32',NULL,2),(7,'Levi','Luna','2000-02-06','levi@gmail.com','5756756324','Kelley A. Fleming 196 Woodside Circle Mobile, FL ','SDGS4654G','DXC Technology','Michael I. Days 3756 Preston Street Wichita, KS','Senior Manager ','64545','3242','ICICI','112312','Saving',1,NULL,0,0,'2019-03-02 23:30:18',7,0,'2019-03-02 23:30:18',NULL,NULL),(8,'Ryan','Hazel','1995-11-06','ryan@gmail.com','5557573353','Donald M. Palmer 2595 Pearlman Avenue Sudbury','DSFS343F','IBM','Michael I. Days 3756 Preston Street Wichita','Software Engineer','3545','1233','HDFC','24324','Current',1,NULL,0,0,'2019-03-02 23:33:10',8,0,'2019-03-02 23:33:10',NULL,NULL),(9,'Ian','Quinn','1994-06-05','ian@gmail.com','4432394632','Micheal R. Porterfield 508 Virginia Street Chicago','SDF3424DF','wipro','athan K. Flores 1516 Holt Street West Palm Beach, FL','Software Engineer','46454','1234','DBS','13422341212312312','Saving',4,3,1,1,'2019-03-02 23:36:05',9,0,'2019-03-02 23:36:05',NULL,3);
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(200) NOT NULL,
  `application_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  `created_on` datetime NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Hi Elijah Amelia, please verify this application.',1,1,0,'2019-03-02 22:40:10'),(2,'Hi Mason Charlotte, We\'ll verify this application.',1,2,0,'2019-03-02 22:43:34'),(3,'thank you',1,1,0,'2019-03-02 22:46:21'),(4,'This application accepted',1,2,0,'2019-03-02 22:52:32'),(5,'Please verify this application',6,1,0,'2019-03-02 23:39:07'),(6,'Verify this application',2,1,0,'2019-03-02 23:39:41'),(7,'verify this application',5,1,0,'2019-03-02 23:40:08'),(8,'verify this application',4,1,0,'2019-03-02 23:40:44'),(9,'verify this application',9,1,0,'2019-03-02 23:41:05'),(10,'verify this application',3,1,0,'2019-03-02 23:41:20'),(11,'We\'ll verify this',4,3,0,'2019-03-02 23:42:28'),(12,'Details are not correct',9,3,0,'2019-03-02 23:43:02'),(13,'Approved',2,3,0,'2019-03-02 23:43:27'),(14,'Rejected, documents are not valid',6,2,0,'2019-03-02 23:44:42');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_on` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modifed_on` datetime NOT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Mason','Charlotte','Male','admin@stackaero.com','Xuq6t7Fr/Dg=','2019-03-02 22:20:40',NULL,'2019-03-02 22:20:40',NULL,0,1),
(2,'Suresh','Redi','Female','suresh.reddy@stackaero.com','Xuq6t7Fr/Dg=','2019-03-02 22:34:49',NULL,'2019-03-02 22:34:49',NULL,0,0),
(3,'Sunny','Glen','Male','skglen628@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:37:53',NULL,'2019-03-02 23:37:53',NULL,0,0),
(4,'Vivek','Jian','Male','vivek.jain@stackaero.com','Xuq6t7Fr/Dg=','2019-03-02 22:34:49',NULL,'2019-03-02 22:34:49',NULL,0,0),
(5,'Vaibhav','Bhokare','Male','vaibhav.bhokare@stackaero.com','Xuq6t7Fr/Dg=','2019-03-02 22:34:49',NULL,'2019-03-02 22:34:49',NULL,0,1),
(6,'Sai','Krishna','Male','sai.krishna@stackaero.com','Xuq6t7Fr/Dg=','2019-03-02 22:34:49',NULL,'2019-03-02 22:34:49',NULL,0,0);

UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Open'),(2,'In Progress'),(3,'Accepted'),(4,'Rejected');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_read` tinyint(4) DEFAULT '0',
  `assign_to` int(11) NOT NULL,
  `task_date` datetime NOT NULL,
  `task_description` varchar(450) DEFAULT NULL,
  `application_id` int(11) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `modifed_on` datetime DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,0,2,'2019-03-08 05:30:00','<ol>\n	<li>Verify Customer details</li>\n	<li>Change status</li>\n</ol>\n',NULL,'2019-03-02 22:36:05',1,'2019-03-02 22:36:05',1,0,'Completed'),(2,0,2,'2019-03-15 05:30:00','<ol>\n	<li>verify all the task</li>\n	<li>verify all applications</li>\n</ol>\n',NULL,'2019-03-02 23:45:52',1,'2019-03-02 23:45:52',1,0,'Completed'),(3,0,2,'2019-03-13 05:30:00','<p>Verify all application</p>\n',NULL,'2019-03-03 13:03:46',1,'2019-03-03 13:03:46',1,0,'Open');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_on` datetime NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modifed_on` datetime NOT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Noah','Olivia','Female','noah.olivia@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 21:48:29',NULL,'2019-03-02 21:48:29',NULL,0),(2,'James','Isabella','Female','jems.is@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:02:46',NULL,'2019-03-02 23:02:46',NULL,0),(3,'Jayden','Layla','Male','jayden@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:13:34',NULL,'2019-03-02 23:13:34',NULL,0),(4,'John','Lillian','Male','john@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:18:05',NULL,'2019-03-02 23:18:05',NULL,0),(5,'Luke','Mila','Male','luke@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:21:33',NULL,'2019-03-02 23:21:33',NULL,0),(6,'Jack','Eleanor','Male','jack@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:25:16',NULL,'2019-03-02 23:25:16',NULL,0),(7,'Levi','Luna','Female','levi@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:28:10',NULL,'2019-03-02 23:28:10',NULL,0),(8,'Ryan','Hazel','Female','ryan@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:31:05',NULL,'2019-03-02 23:31:05',NULL,0),(9,'Ian','Quinn','Female','ian@gmail.com','Xuq6t7Fr/Dg=','2019-03-02 23:34:05',NULL,'2019-03-02 23:34:05',NULL,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-03 13:12:24
