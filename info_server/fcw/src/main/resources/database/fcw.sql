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

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SYS_CHARACTER_ID` bigint(20) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `MAC` varchar(255) DEFAULT NULL,
  `ACTIVATE_TIME` datetime DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `LAST_LOGOUT_TIME` datetime DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(255) DEFAULT NULL,
  `LAST_LOGIN_DEVICE` varchar(255) DEFAULT NULL,
  `EXP` int(11) NOT NULL,
  `GOLD` int(11) NOT NULL,
  `DIAMOND` int(11) NOT NULL,
  `POWER` int(11) NOT NULL,
  `ARENA` int(11) NOT NULL,
  `AVATAR` varchar(255) DEFAULT NULL,
  `SKILL_POINT` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `DAMAGE` float NOT NULL,
  `VIP_LEVEL` int(11) NOT NULL,
  `BAN_START_TIME` datetime DEFAULT NULL,
  `BAN_END_TIME` datetime DEFAULT NULL,
  `BAN_REASON` varchar(255) DEFAULT NULL,
  `BAN_COUNT` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_ID_FK` (`USER_ID`,`IS_DELETED`),
  KEY `PLAYER_NAME` (`NAME`(8)),
  KEY `PLAYER_EXP` (`EXP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player` */

/*Table structure for table `player_item00000000` */

DROP TABLE IF EXISTS `player_item00000000`;

CREATE TABLE `player_item00000000` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000000` */

/*Table structure for table `player_item00000001` */

DROP TABLE IF EXISTS `player_item00000001`;

CREATE TABLE `player_item00000001` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000001` */

/*Table structure for table `player_item00000002` */

DROP TABLE IF EXISTS `player_item00000002`;

CREATE TABLE `player_item00000002` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=200000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000002` */

/*Table structure for table `player_item00000003` */

DROP TABLE IF EXISTS `player_item00000003`;

CREATE TABLE `player_item00000003` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=300000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000003` */

/*Table structure for table `player_item00000100` */

DROP TABLE IF EXISTS `player_item00000100`;

CREATE TABLE `player_item00000100` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000100` */

/*Table structure for table `player_item00000101` */

DROP TABLE IF EXISTS `player_item00000101`;

CREATE TABLE `player_item00000101` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=10100000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000101` */

/*Table structure for table `player_item00000102` */

DROP TABLE IF EXISTS `player_item00000102`;

CREATE TABLE `player_item00000102` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=10200000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000102` */

/*Table structure for table `player_item00000103` */

DROP TABLE IF EXISTS `player_item00000103`;

CREATE TABLE `player_item00000103` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `PLAYER_ID` bigint(20) NOT NULL,
  `SYS_ITEM_ID` bigint(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `ITEM_TYPE` int(11) NOT NULL,
  `IS_STORAGE` bit(1) NOT NULL,
  `UNIT_TYPE` int(11) NOT NULL,
  `UNIT` int(11) NOT NULL,
  `IS_DEFAULT` bit(1) NOT NULL,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `CAN_EQUIP` bit(1) NOT NULL,
  `CAN_USE` bit(1) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `STAR` int(11) NOT NULL,
  `GRADE` int(11) NOT NULL,
  `LIFE` int(11) NOT NULL,
  `USER_COUNT` int(11) NOT NULL,
  `CRITICAL` float NOT NULL,
  `CRITICAL_RESISTANCE` float NOT NULL,
  `PENETRATION` float NOT NULL,
  `DEFENSE` float NOT NULL,
  `BASE_DAMAGE` float NOT NULL,
  `DISRUPT` float NOT NULL,
  `ARMOUR` float NOT NULL,
  `SHIELD` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PLAYER_ID_FK` (`PLAYER_ID`,`IS_DELETED`)
) ENGINE=InnoDB AUTO_INCREMENT=10300000000001 DEFAULT CHARSET=utf8;

/*Data for the table `player_item00000103` */

/*Table structure for table `sys_item` */

DROP TABLE IF EXISTS `sys_item`;

CREATE TABLE `sys_item` (
  `ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `X_DAMAGE_RATE` float NOT NULL,
  `X_FIRE_SPEED` float NOT NULL,
  `X_BASE_DAMAGE` float NOT NULL,
  `X_LIFE` float NOT NULL,
  `X_CONTROL_TURN_SPEED` float NOT NULL,
  `X_AUTO_TURN_SPEED` float NOT NULL,
  `RESOURCE` varchar(255) DEFAULT NULL,
  `DISPLAY_NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `TYPE` int(11) NOT NULL,
  `SUB_TYPE` int(11) NOT NULL,
  `RANK` int(11) NOT NULL,
  `DECOMPOSE_ID` bigint(20) NOT NULL,
  `ICON` varchar(255) DEFAULT NULL,
  `MAX_QUANTITY` int(11) NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `LEVEL_LIMIT` int(11) NOT NULL,
  `BUFF` varchar(255) DEFAULT NULL,
  `SYS_BULLET_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_item` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` bit(1) NOT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(255) DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `ACTIVATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_NAME` (`USER_NAME`(8))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`ID`,`CREATE_TIME`,`UPDATE_TIME`,`DELETE_TIME`,`IS_DELETED`,`USER_NAME`,`PASSWORD`,`LAST_LOGIN_IP`,`LAST_LOGIN_TIME`,`ACTIVATE_TIME`) values (1,'2016-03-01 11:43:37',NULL,NULL,'\0','tomt1',NULL,'','2016-03-01 11:43:37','2016-03-01 11:43:36');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
