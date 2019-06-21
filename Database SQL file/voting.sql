/*
SQLyog Community v8.55 
MySQL - 5.0.96-community-nt : Database - voting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`voting` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `voting`;

/*Table structure for table `candidates` */

DROP TABLE IF EXISTS `candidates`;

CREATE TABLE `candidates` (
  `id` int(11) NOT NULL auto_increment,
  `dept` text,
  `post` text,
  `opt1` text,
  `opt2` text,
  `opt3` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `candidates` */

insert  into `candidates`(`id`,`dept`,`post`,`opt1`,`opt2`,`opt3`) values (1,'ME','President','A','B','C'),(2,'MECH','HOD','X','Y','Z'),(3,'ME','HOD','W','E','R'),(4,'MECH','President','F','G','H');

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` int(11) NOT NULL auto_increment,
  `dept` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `dept` */

insert  into `dept`(`id`,`dept`) values (2,'ME'),(4,'MECH');

/*Table structure for table `reg` */

DROP TABLE IF EXISTS `reg`;

CREATE TABLE `reg` (
  `id` int(11) NOT NULL auto_increment,
  `name` text,
  `uname` text,
  `email` text,
  `fathername` text,
  `mothername` text,
  `aadhar` text,
  `votid` text,
  `address` text,
  `dob` text,
  `pass` text,
  `type` text,
  `status` text,
  `macadd` text,
  `question` text,
  `answer` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `reg` */

insert  into `reg`(`id`,`name`,`uname`,`email`,`fathername`,`mothername`,`aadhar`,`votid`,`address`,`dob`,`pass`,`type`,`status`,`macadd`,`question`,`answer`) values (1,'Sachin','sachin@123','sachin@gmail.com','p','s','646464646464','jaj7382','Nagpur','1-1-1997 ','oooooooo','user','online','3c:97:0e:f2:52:d3','What was your childhood friend name?','s');

/*Table structure for table `vote` */

DROP TABLE IF EXISTS `vote`;

CREATE TABLE `vote` (
  `id` int(11) NOT NULL auto_increment,
  `uname` text,
  `dept` text,
  `post` text,
  `opt` text,
  `dnt` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `vote` */

insert  into `vote`(`id`,`uname`,`dept`,`post`,`opt`,`dnt`) values (1,'s','ME','President','N','2016-04-15 13:27:42'),(2,'s','ME','HOD','J','2016-04-15 13:29:14'),(3,'s','MECH','HOD','K','2016-04-15 13:29:20'),(4,'s','MECH','President','S','2016-04-15 13:29:29'),(5,'s','ME','HOD','J','2016-04-15 13:30:51'),(6,'s','ME','HOD','J','2016-04-15 13:30:56'),(7,'s','ME','HOD','J','2016-04-15 13:30:59'),(8,'s','ME','HOD','J','2016-04-15 13:31:02'),(9,'s','ME','HOD','J','2016-04-15 13:31:05'),(10,'sachin@123','ME','HOD','J','2016-04-15 13:31:08'),(11,'sachin@123','ME','President','N','2016-04-15 13:36:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
