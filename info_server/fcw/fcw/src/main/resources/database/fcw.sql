/*
SQLyog Community Edition- MySQL GUI v6.07
Host - 5.5.9 : Database - fcw
*********************************************************************
Server version : 5.5.9
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `fcw`;

USE `fcw`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `as_shard` */

DROP TABLE IF EXISTS `as_shard`;

CREATE TABLE `as_shard` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `ZONE_ID` int(11) NOT NULL,
  `START_NODE_ID` int(11) NOT NULL,
  `END_NODE_ID` int(11) NOT NULL,
  `DB_HOST` varchar(255) DEFAULT NULL,
  `DB_NAME` varchar(255) DEFAULT NULL,
  `DB_USERNAME` varchar(255) DEFAULT NULL,
  `DB_PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `as_shard` */

insert  into `as_shard`(`ID`,`CREATE_TIME`,`UPDATE_TIME`,`DELETE_TIME`,`IS_DELETED`,`ZONE_ID`,`START_NODE_ID`,`END_NODE_ID`,`DB_HOST`,`DB_NAME`,`DB_USERNAME`,`DB_PASSWORD`) values (1,NULL,NULL,NULL,'\0',0,0,1,'127.0.0.1','fcw','root','123456');

