start transaction;

create user 'del-user'@'%' identified by password '*EE89F5183D614BA3B739D1872158684DDB27BBA7';

create user 'del-manager'@'%' identified by password '*6AEE22A9E5CB25E3944BFAB0C57E5D4D834EF1A1';


# Privilegios para `deliberations-manager`@`%`

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, TRIGGER ON *.* TO 'del-manager'@'%' IDENTIFIED BY PASSWORD '*6AEE22A9E5CB25E3944BFAB0C57E5D4D834EF1A1' WITH GRANT OPTION;

# Privilegios para `deliberations-user`@`%`

GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'del-user'@'%' IDENTIFIED BY PASSWORD '*EE89F5183D614BA3B739D1872158684DDB27BBA7' WITH GRANT OPTION;


-- Base de datos: `deliberations`
--
CREATE DATABASE IF NOT EXISTS `deliberations` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `deliberations`;

SET NAMES utf8;

-- --------------------------------------------------------


-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: deliberations
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_moment` datetime DEFAULT NULL,
  `erase` bit(1) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `thread` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_3ydpbc2gdp8jl5ld83rlo6arv` (`creation_moment`,`erase`),
  KEY `UK_2d2el8kro83u0g712ea9o0pnu` (`thread`,`creation_moment`),
  KEY `FK_4ivt42gn164dv18bc7kd6ofkv` (`user`),
  CONSTRAINT `FK_4ivt42gn164dv18bc7kd6ofkv` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_nlndnfhb4alu32b6y7h80rpv9` FOREIGN KEY (`thread`) REFERENCES `hilo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (9,0,'2004-11-11 12:15:00',NULL,'tex1t',7,4),(10,0,'2004-11-11 12:16:00',NULL,'text2',7,4),(11,0,'2005-01-11 12:18:00',NULL,'text3',8,5);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hilo`
--

DROP TABLE IF EXISTS `hilo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hilo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `closed` bit(1) NOT NULL,
  `creation_moment` datetime DEFAULT NULL,
  `decription` varchar(255) DEFAULT NULL,
  `erase` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_3d6fmstiobx85nk7u22h3lxii` (`title`,`erase`),
  KEY `FK_3mhae1ga8dlg02eyw2gy6rulh` (`user`),
  CONSTRAINT `FK_3mhae1ga8dlg02eyw2gy6rulh` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hilo`
--

LOCK TABLES `hilo` WRITE;
/*!40000 ALTER TABLE `hilo` DISABLE KEYS */;
INSERT INTO `hilo` VALUES (7,0,'\0','2004-11-11 12:12:00','text',NULL,'titulo',4),(8,0,'\0','2004-12-11 14:18:00','text2',NULL,'Hilando',5);
/*!40000 ALTER TABLE `hilo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `recipient` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hn9roqyj131hnul5fuwgwlv9e` (`recipient`),
  KEY `FK_a3km2kv42i1xu571ta911f9dc` (`sender`),
  CONSTRAINT `FK_a3km2kv42i1xu571ta911f9dc` FOREIGN KEY (`sender`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_hn9roqyj131hnul5fuwgwlv9e` FOREIGN KEY (`recipient`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rank`
--

DROP TABLE IF EXISTS `rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rank` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description_en` longblob,
  `description_es` longblob,
  `icon` varchar(255) DEFAULT NULL,
  `min_comments` int(11) DEFAULT NULL,
  `min_ratings` int(11) DEFAULT NULL,
  `min_threads` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_b6uotm0f90eofl9rmmnl6pqbf` (`min_threads`,`min_comments`,`min_ratings`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rank`
--

LOCK TABLES `rank` WRITE;
/*!40000 ALTER TABLE `rank` DISABLE KEYS */;
INSERT INTO `rank` VALUES (13,0,'You are the closest thing to a stone, you should interact at least once with the forum.','Eres lo m\ás parecido a una piedra, deber\ías de interactuar al menos 1 vez con el foro.','images/rank0.png',0,0,0,0,'Stone / Piedra'),(14,0,'You\'ve already taken your first step in the community but you still have a lot to talk about, maybe it\'s time to create your own thread and keep commenting and punctuation.','Ya has dado tu primer paso en la comunidad pero aun te queda mucho que hablar, quiz\ás sea hora de crear tu propio hilo y seguir comentando y puntuando.','images/rank1.png',0,0,0,1,'Noob / Novato'),(15,0,'This has only been the beginning, you already know all the basic interactions of the forum, continue and you will arrive far.','Este ha sido solo el comienzo, ya conoces todas las interacciones b\ásicas del foro, sigue as\í y llegar\ás lejos.','images/rank2.png',5,2,1,2,'Beginner / Aprendiz'),(16,0,'You are good at creating, commenting and punctuating. This is the first of the 3 epic ranks.','Se te da bien tanto crear, comentar y puntuar. Este es el primero de los 3 rangos epicos.','images/rank3.png',15,10,5,3,'Advanced / Avanzado'),(17,0,'Your passion for the community is great, you already have at least 15 own threads, 25 comments and 20 scores, that\'s why you wear this badge that more than one dreams have.','Tu pasión por la comunidad es grande, ya tienes al menos 15 hilos propios, 25 comentarios y 20 puntuaciones, por eso eso luces esta insignia que m\ás de uno sueña tener.','images/rank4.png',25,20,15,4,'Expert / Experto'),(18,0,'You are a promoter par excellence, your acts in the forum have led you to achieve the maximum possible distinction. Congratulations!','Eres un divulgador por excelencia, tus actos en el foro te han llevado a conseguir la m\áxima distinción posible. Enhorabuena!','images/rank5.png',50,30,25,5,'Discloser / Divulgador');
/*!40000 ALTER TABLE `rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  `thread` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_nohdq0ng826i95h69bry6i641` (`rate`),
  KEY `FK_eviip7m03pduhsquougt08a0b` (`thread`),
  KEY `FK_mljpjh5vu18fmshuuni30uhlp` (`user`),
  CONSTRAINT `FK_eviip7m03pduhsquougt08a0b` FOREIGN KEY (`thread`) REFERENCES `hilo` (`id`),
  CONSTRAINT `FK_mljpjh5vu18fmshuuni30uhlp` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (12,0,4,7,4);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `autonomous_community` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cjn1wn3ecn1kacgqxryr6a5c6` (`user_account`),
  CONSTRAINT `FK_cjn1wn3ecn1kacgqxryr6a5c6` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,0,'AndalucÃ­a','caballeroalba@gmail.com','Hombre','nombre','surname',1),(5,0,'Madrid','frandeveloper@gmail.com','Hombre','Fran','Developer',2),(6,0,'Madrid','user3@gmail.com','Hombre','user3','user3',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(2,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(3,0,'92877af70a45fd6a2ed7fe81e1236b78','user3');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (1,'USER'),(2,'USER'),(3,'USER');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-25  0:14:45

commit;
