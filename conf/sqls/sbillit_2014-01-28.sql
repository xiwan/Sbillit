# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: sbillit
# Generation Time: 2014-01-28 10:01:36 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sbillit_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_account`;

CREATE TABLE `sbillit_account` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `balance` decimal(10,0) NOT NULL DEFAULT '0',
  `card1_no` varchar(255) DEFAULT NULL,
  `card2_no` varchar(255) DEFAULT NULL,
  `card3_no` varchar(255) DEFAULT NULL,
  `bank1_id` int(11) DEFAULT NULL,
  `bank2_id` int(11) DEFAULT NULL,
  `bank3_id` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_account_flow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_account_flow`;

CREATE TABLE `sbillit_account_flow` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `from_user_id` int(11) unsigned NOT NULL,
  `to_user_id` int(11) unsigned NOT NULL,
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_account_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_account_log`;

CREATE TABLE `sbillit_account_log` (
  `id` int(11) unsigned NOT NULL,
  `card_no` int(11) unsigned NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:deposit, 1:withdraw',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `created_at` int(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table sbillit_ads
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_ads`;

CREATE TABLE `sbillit_ads` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT '',
  `area_id` int(11) DEFAULT NULL,
  `combo_id` int(11) unsigned NOT NULL DEFAULT '0',
  `start_at` int(11) unsigned NOT NULL DEFAULT '0',
  `end_at` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sbillit_ads` WRITE;
/*!40000 ALTER TABLE `sbillit_ads` DISABLE KEYS */;

INSERT INTO `sbillit_ads` (`id`, `title`, `image_url`, `area_id`, `combo_id`, `start_at`, `end_at`)
VALUES
	(1,'mchotdog','http://t11.baidu.com/it/u=3793972620,1319905622&fm=90&gp=0.jpg',NULL,3,1390886092,1397886092),
	(2,'kfc','http://t12.baidu.com/it/u=3809591067,1451642802&fm=21&gp=0.jpg',NULL,1,1390886092,1397886092);

/*!40000 ALTER TABLE `sbillit_ads` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_alarm
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_alarm`;

CREATE TABLE `sbillit_alarm` (
  `id` int(11) unsigned NOT NULL,
  `alarm1_status` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '0:normal, 1:disabled',
  `alarm2_status` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '0:normal, 1:disabled',
  `alarm3_status` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '0:normal, 1:disabled',
  `alarm1_at` int(10) DEFAULT NULL,
  `alarm2_at` int(10) DEFAULT NULL,
  `alarm3_at` int(10) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_bank
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_bank`;

CREATE TABLE `sbillit_bank` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_bill`;

CREATE TABLE `sbillit_bill` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `payment_detail` text,
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_combo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_combo`;

CREATE TABLE `sbillit_combo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `item_name1` varchar(128) DEFAULT NULL,
  `item_number1` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price1` decimal(11,2) unsigned NOT NULL DEFAULT '0.00',
  `item_name2` varchar(128) DEFAULT NULL,
  `item_number2` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price2` decimal(11,2) unsigned NOT NULL DEFAULT '0.00',
  `item_name3` varchar(128) DEFAULT NULL,
  `item_number3` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price3` decimal(11,2) unsigned NOT NULL DEFAULT '0.00',
  `item_name4` varchar(128) DEFAULT NULL,
  `item_number4` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price4` decimal(11,2) unsigned NOT NULL DEFAULT '0.00',
  `item_name5` varchar(128) DEFAULT NULL,
  `item_number5` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price5` decimal(11,2) unsigned NOT NULL DEFAULT '0.00',
  `seller_id` int(11) NOT NULL DEFAULT '0',
  `valid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_combo` WRITE;
/*!40000 ALTER TABLE `sbillit_combo` DISABLE KEYS */;

INSERT INTO `sbillit_combo` (`id`, `item_name1`, `item_number1`, `item_price1`, `item_name2`, `item_number2`, `item_price2`, `item_name3`, `item_number3`, `item_price3`, `item_name4`, `item_number4`, `item_price4`, `item_name5`, `item_number5`, `item_price5`, `seller_id`, `valid`)
VALUES
	(1,'chicken wings',1,9.00,'buffalo',2,12.13,NULL,0,0.00,NULL,0,0.00,NULL,0,0.00,1,0),
	(2,NULL,0,0.00,NULL,0,0.00,NULL,0,0.00,NULL,0,0.00,NULL,0,0.00,0,0),
	(3,'chicken wings',1,9.00,'buffalo',2,12.13,NULL,0,0.00,NULL,0,0.00,NULL,0,0.00,1,0);

/*!40000 ALTER TABLE `sbillit_combo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_config`;

CREATE TABLE `sbillit_config` (
  `key` varchar(255) NOT NULL DEFAULT '',
  `value` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_debt
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_debt`;

CREATE TABLE `sbillit_debt` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `amount_due` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:normal, 1:finished, 2:expired;',
  `message_id` int(11) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_friend
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_friend`;

CREATE TABLE `sbillit_friend` (
  `user_id` int(11) unsigned NOT NULL,
  `friend_id` int(11) unsigned NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:normal, 1: blocked',
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_friend_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_friend_request`;

CREATE TABLE `sbillit_friend_request` (
  `id` int(11) unsigned NOT NULL,
  `from_user_id` int(11) unsigned NOT NULL,
  `to_user_id` int(11) unsigned NOT NULL,
  `status` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '0:sending,1:sending failed; 3:pending, 4: rejected, 5:approved',
  `message` varchar(255) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_invite
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_invite`;

CREATE TABLE `sbillit_invite` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `message_id` int(11) unsigned DEFAULT NULL,
  `new_message` text,
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_message`;

CREATE TABLE `sbillit_message` (
  `id` int(11) unsigned NOT NULL,
  `type` smallint(4) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order`;

CREATE TABLE `sbillit_order` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `type` smallint(5) unsigned NOT NULL,
  `title` varchar(256) NOT NULL DEFAULT '',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '0:n/a, 1:created, 2:shared, 3:expiring, 4:expired, 5:failed, 6:success',
  `description` text,
  `store_phone` varchar(128) DEFAULT NULL,
  `picture_1` varchar(255) DEFAULT NULL,
  `picture_2` varchar(255) DEFAULT NULL,
  `picture_3` varchar(255) DEFAULT NULL,
  `expired_at` int(10) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_order` WRITE;
/*!40000 ALTER TABLE `sbillit_order` DISABLE KEYS */;

INSERT INTO `sbillit_order` (`id`, `user_id`, `type`, `title`, `amount`, `status`, `description`, `store_phone`, `picture_1`, `picture_2`, `picture_3`, `expired_at`, `created_at`, `updated_at`)
VALUES
	(1,3,0,'',1999.00,1,NULL,NULL,NULL,NULL,NULL,1387377411,1387373811,1387373811),
	(2,3,0,'',176.00,1,NULL,NULL,NULL,NULL,NULL,1387377555,1387373960,1387373960),
	(3,3,0,'',444.00,1,NULL,NULL,NULL,NULL,NULL,1387377657,1387374057,1387374057),
	(4,2,1,'',245.90,2,NULL,NULL,NULL,NULL,NULL,1387700432,1387696833,1387696833),
	(5,2,1,'',548.00,2,NULL,NULL,NULL,NULL,NULL,1387700660,1387697065,1387697065),
	(6,2,1,'',154.00,2,NULL,NULL,NULL,NULL,NULL,1387700955,1387697355,1387697355),
	(7,2,1,'',545.00,2,NULL,NULL,NULL,NULL,NULL,1387701228,1387697628,1387697628),
	(8,2,1,'',54.00,2,NULL,NULL,NULL,NULL,NULL,1387701859,1387698259,1387698259),
	(9,2,1,'',54.00,2,NULL,NULL,NULL,NULL,NULL,1387702062,1387698462,1387698462),
	(10,2,1,'',548.00,2,NULL,NULL,NULL,NULL,NULL,1387702716,1387699116,1387699116),
	(11,2,1,'',845.00,2,NULL,NULL,NULL,NULL,NULL,1387703645,1387700045,1387700045),
	(12,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387703747,1387700147,1387700147),
	(13,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387703861,1387700261,1387700261),
	(14,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387704114,1387700514,1387700514),
	(15,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387704288,1387700688,1387700688),
	(16,2,1,'',548.00,2,NULL,NULL,NULL,NULL,NULL,1387704674,1387701074,1387701074),
	(17,2,1,'',8484.00,2,NULL,NULL,NULL,NULL,NULL,1387704873,1387701273,1387701273),
	(18,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387705060,1387701460,1387701460),
	(19,2,1,'',858.00,2,NULL,NULL,NULL,NULL,NULL,1387705162,1387701562,1387701562),
	(20,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387705282,1387701682,1387701682),
	(21,2,1,'',54.00,2,NULL,NULL,NULL,NULL,NULL,1387705449,1387701849,1387701849),
	(22,2,1,'',848.00,2,NULL,NULL,NULL,NULL,NULL,1387705515,1387701915,1387701915),
	(23,2,1,'',548.00,2,NULL,NULL,NULL,NULL,NULL,1387705617,1387702017,1387702017),
	(24,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387705751,1387702151,1387702151),
	(25,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387706046,1387702446,1387702446),
	(26,2,1,'',548.00,2,NULL,NULL,NULL,NULL,NULL,1387706104,1387702504,1387702504),
	(27,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387706255,1387702655,1387702655),
	(28,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387706427,1387702827,1387702827),
	(29,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387706514,1387702914,1387702914),
	(30,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387706617,1387703017,1387703017),
	(31,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387706693,1387703093,1387703093),
	(32,2,1,'',88.00,2,NULL,NULL,NULL,NULL,NULL,1387706873,1387703273,1387703273),
	(33,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387707439,1387703839,1387703839),
	(34,2,1,'',588.00,2,NULL,NULL,NULL,NULL,NULL,1387708380,1387704780,1387704780),
	(35,2,1,'',555.00,2,NULL,NULL,NULL,NULL,NULL,1387708495,1387704895,1387704895),
	(36,2,1,'',555.00,2,NULL,NULL,NULL,NULL,NULL,1387708667,1387705067,1387705067),
	(37,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387708936,1387705336,1387705336),
	(38,2,1,'',55.00,2,NULL,NULL,NULL,NULL,NULL,1387709421,1387705821,1387705821),
	(39,2,1,'',25.00,2,NULL,NULL,NULL,NULL,NULL,1387709597,1387705997,1387705997),
	(40,2,1,'',55.00,2,NULL,NULL,'/home/mgsys/static/images/40-quickOrder.gif',NULL,NULL,1387710210,1387706610,1387706695),
	(41,2,1,'',55.00,2,NULL,NULL,'/home/mgsys/static/images/41-quickOrder.gif',NULL,NULL,1387710425,1387706825,1387706839),
	(42,2,0,'',0.00,2,NULL,NULL,NULL,NULL,NULL,1389795194,1389791594,1389791594),
	(43,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389795454,1389791873,1389791873),
	(44,2,0,'',217.70,2,NULL,NULL,NULL,NULL,NULL,1389795655,1389792055,1389792055),
	(45,2,0,'',217.70,2,NULL,NULL,NULL,NULL,NULL,1389795695,1389792095,1389792095),
	(46,2,0,'',431.25,2,NULL,NULL,NULL,NULL,NULL,1389795833,1389792233,1389792233),
	(47,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389796041,1389792441,1389792441),
	(48,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389796286,1389792686,1389792686),
	(49,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389796380,1389792780,1389792780),
	(50,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389796419,1389792819,1389792819),
	(51,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389796424,1389792824,1389792824),
	(52,2,0,'',108.85,2,NULL,NULL,NULL,NULL,NULL,1389796608,1389793008,1389793008),
	(53,2,0,'',372.85,2,NULL,NULL,NULL,NULL,NULL,1389796788,1389793188,1389793188),
	(54,2,1,'',222.00,2,NULL,NULL,NULL,NULL,NULL,1389797097,1389793497,1389793497),
	(55,2,1,'',224.00,2,NULL,NULL,NULL,NULL,NULL,1389797316,1389793716,1389793716),
	(56,2,0,'',108.85,0,NULL,NULL,NULL,NULL,NULL,1389797354,1389793754,1389793754);

/*!40000 ALTER TABLE `sbillit_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_order_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_comment`;

CREATE TABLE `sbillit_order_comment` (
  `id` int(11) unsigned NOT NULL,
  `order_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `message` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:normal, 1:private',
  `at_user_id` int(11) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_item`;

CREATE TABLE `sbillit_order_item` (
  `order_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `item_num` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price` decimal(11,2) NOT NULL DEFAULT '0.00',
  `item_name` varchar(128) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_order_item` WRITE;
/*!40000 ALTER TABLE `sbillit_order_item` DISABLE KEYS */;

INSERT INTO `sbillit_order_item` (`order_id`, `user_id`, `item_num`, `item_price`, `item_name`, `created_at`, `updated_at`)
VALUES
	(52,1,7,15.55,'???',1389793020,1389793020),
	(53,1,7,15.55,'???',1389793188,1389793188),
	(53,2,8,33.00,'Jgjhgshgdjgs',1389793188,1389793188),
	(56,1,7,15.55,'???',1389793754,1389793754);

/*!40000 ALTER TABLE `sbillit_order_item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_order_share
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_share`;

CREATE TABLE `sbillit_order_share` (
  `order_id` int(11) unsigned NOT NULL,
  `phone` varchar(32) NOT NULL DEFAULT '',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '0:n/a, 1:agreed, 2:rejected',
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_order_share` WRITE;
/*!40000 ALTER TABLE `sbillit_order_share` DISABLE KEYS */;

INSERT INTO `sbillit_order_share` (`order_id`, `phone`, `user_id`, `status`, `created_at`, `updated_at`)
VALUES
	(10,'0',0,0,1387699116,1387699116),
	(10,'13818180795',2,0,1387699116,1387699116),
	(10,'13818180796',3,0,1387699116,1387699116),
	(11,'13818180795',2,0,1387700045,1387700045),
	(11,'13818180796',3,0,1387700045,1387700045),
	(12,'13818180795',2,0,1387700147,1387700147),
	(13,'13818180795',2,0,1387700261,1387700261),
	(14,'13818180795',2,0,1387700514,1387700514),
	(15,'13818180795',2,0,1387700688,1387700688),
	(15,'18601732616',1,0,1387700688,1387700688),
	(16,'13818180795',2,0,1387701074,1387701074),
	(17,'13818180795',2,0,1387701273,1387701273),
	(18,'13818180795',2,0,1387701460,1387701460),
	(19,'13818180795',2,0,1387701562,1387701562),
	(20,'13818180795',2,0,1387701682,1387701682),
	(21,'13818180795',2,0,1387701849,1387701849),
	(22,'13818180795',2,0,1387701915,1387701915),
	(23,'13818180795',2,0,1387702017,1387702017),
	(24,'13818180795',2,0,1387702151,1387702151),
	(25,'13818180795',2,0,1387702446,1387702446),
	(26,'13818180795',2,0,1387702504,1387702504),
	(27,'13818180795',2,0,1387702655,1387702655),
	(28,'13818180795',2,0,1387702827,1387702827),
	(29,'13818180795',2,0,1387702914,1387702914),
	(30,'13818180795',2,0,1387703017,1387703017),
	(31,'13818180795',2,0,1387703093,1387703093),
	(32,'13818180795',2,0,1387703273,1387703273),
	(33,'13818180795',2,0,1387703839,1387703839),
	(34,'13818180795',2,0,1387704780,1387704780),
	(35,'13818180795',2,0,1387704895,1387704895),
	(36,'13818180795',2,0,1387705067,1387705067),
	(37,'13818180795',2,0,1387705336,1387705336),
	(38,'13818180795',2,0,1387705821,1387705821),
	(39,'13818180795',2,0,1387705997,1387705997),
	(40,'13818180795',2,0,1387706610,1387706610),
	(41,'13818180795',2,0,1387706825,1387706825),
	(42,'13918180796',0,1,1389791594,1389791594),
	(42,'13918180797',0,1,1389791594,1389791594),
	(43,'13918180796',0,1,1389791953,1389791953),
	(43,'13918180797',0,1,1389791957,1389791957),
	(44,'13918180796',0,1,1389792055,1389792055),
	(44,'13918180797',0,1,1389792055,1389792055),
	(45,'13918180796',0,1,1389792095,1389792095),
	(45,'13918180797',0,1,1389792095,1389792095),
	(46,'13918180796',1,1,1389792233,1389792233),
	(46,'13918180798',3,1,1389792233,1389792233),
	(47,'13918180796',1,1,1389792441,1389792441),
	(47,'13918180798',3,1,1389792441,1389792441),
	(48,'13918180796',1,1,1389792686,1389792686),
	(48,'13918180798',3,1,1389792686,1389792686),
	(49,'13918180796',1,1,1389792780,1389792780),
	(49,'13918180798',3,1,1389792780,1389792780),
	(50,'13918180796',1,1,1389792819,1389792819),
	(51,'13918180796',1,1,1389792824,1389792824),
	(52,'13918180796',1,1,1389793008,1389793008),
	(53,'13918180796',1,1,1389793188,1389793188),
	(53,'13918180798',3,1,1389793188,1389793188),
	(54,'13818180795',2,0,1389793497,1389793497),
	(54,'13818180796',3,0,1389793497,1389793497),
	(55,'13818180795',2,0,1389793716,1389793716),
	(55,'13818180796',3,0,1389793716,1389793716),
	(55,'13818180797',2,0,1389793716,1389793716),
	(56,'13918180796',1,1,1389793754,1389793754),
	(56,'13918180798',3,1,1389793754,1389793754);

/*!40000 ALTER TABLE `sbillit_order_share` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_order_thumbup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_thumbup`;

CREATE TABLE `sbillit_order_thumbup` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `title` varchar(128) DEFAULT NULL,
  `updated_at` int(10) NOT NULL DEFAULT '0',
  `created_at` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sbillit_order_thumbup` WRITE;
/*!40000 ALTER TABLE `sbillit_order_thumbup` DISABLE KEYS */;

INSERT INTO `sbillit_order_thumbup` (`order_id`, `user_id`, `title`, `updated_at`, `created_at`)
VALUES
	(5,2,'Lala',1390902784,1390902784),
	(55,2,'haha',1390901200,1390901200);

/*!40000 ALTER TABLE `sbillit_order_thumbup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_seller
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_seller`;

CREATE TABLE `sbillit_seller` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '',
  `phone` varchar(128) NOT NULL DEFAULT '',
  `address` varchar(256) DEFAULT NULL,
  `rate` int(5) NOT NULL DEFAULT '0',
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_seller` WRITE;
/*!40000 ALTER TABLE `sbillit_seller` DISABLE KEYS */;

INSERT INTO `sbillit_seller` (`id`, `name`, `phone`, `address`, `rate`, `created_at`, `updated_at`)
VALUES
	(1,'xiwan','18221114531','China',5,0,0);

/*!40000 ALTER TABLE `sbillit_seller` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_user`;

CREATE TABLE `sbillit_user` (
  `id` int(11) unsigned NOT NULL,
  `phone` varchar(32) NOT NULL DEFAULT '0',
  `banned` tinyint(2) unsigned NOT NULL DEFAULT '0',
  `point` int(11) unsigned NOT NULL DEFAULT '0',
  `sms_token` char(4) NOT NULL DEFAULT '',
  `sms_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '0:invalid;1:valid',
  `sms_expired_at` int(11) unsigned NOT NULL,
  `invite_id` int(11) unsigned NOT NULL DEFAULT '0',
  `nickname` varchar(128) NOT NULL DEFAULT '',
  `password` varchar(64) DEFAULT NULL,
  `country` varchar(128) DEFAULT NULL,
  `city` varchar(128) DEFAULT NULL,
  `created_at` int(10) unsigned DEFAULT NULL,
  `updated_at` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_user` WRITE;
/*!40000 ALTER TABLE `sbillit_user` DISABLE KEYS */;

INSERT INTO `sbillit_user` (`id`, `phone`, `banned`, `point`, `sms_token`, `sms_flag`, `sms_expired_at`, `invite_id`, `nickname`, `password`, `country`, `city`, `created_at`, `updated_at`)
VALUES
	(1,'0',0,0,'5444',0,1387370347,0,'13818180795',NULL,NULL,NULL,1387368547,1387370071),
	(2,'13818180795',0,0,'5161',1,1390901754,0,'13818180795',NULL,NULL,NULL,1387368786,1390899955),
	(3,'138181810795',0,0,'8637',1,1387375446,0,'138181810795',NULL,NULL,NULL,1387373646,1387373652),
	(4,'18221114531',0,0,'5405',1,1387708127,0,'18221114531',NULL,NULL,NULL,1387706327,1387706347);

/*!40000 ALTER TABLE `sbillit_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_user_session
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_user_session`;

CREATE TABLE `sbillit_user_session` (
  `user_id` int(11) unsigned NOT NULL,
  `session` char(128) NOT NULL DEFAULT '',
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  `expired_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `session` (`session`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_user_session` WRITE;
/*!40000 ALTER TABLE `sbillit_user_session` DISABLE KEYS */;

INSERT INTO `sbillit_user_session` (`user_id`, `session`, `created_at`, `updated_at`, `expired_at`)
VALUES
	(2,'BD88B3810A197CD600FB56A4A12A10A1',1387369045,1390902851,1390906451),
	(3,'E707E81C856D96DD84658704A05CCF3D',1387373652,1387374057,1387377657),
	(4,'68A3E1E170362B4297B4D5342859025B',1387706347,1387706459,1387710059);

/*!40000 ALTER TABLE `sbillit_user_session` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_account`;

CREATE TABLE `seq_account` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_account` WRITE;
/*!40000 ALTER TABLE `seq_account` DISABLE KEYS */;

INSERT INTO `seq_account` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_account_flow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_account_flow`;

CREATE TABLE `seq_account_flow` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_account_flow` WRITE;
/*!40000 ALTER TABLE `seq_account_flow` DISABLE KEYS */;

INSERT INTO `seq_account_flow` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_account_flow` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_account_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_account_log`;

CREATE TABLE `seq_account_log` (
  `id` int(11) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `seq_account_log` WRITE;
/*!40000 ALTER TABLE `seq_account_log` DISABLE KEYS */;

INSERT INTO `seq_account_log` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_account_log` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_alarm
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_alarm`;

CREATE TABLE `seq_alarm` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_alarm` WRITE;
/*!40000 ALTER TABLE `seq_alarm` DISABLE KEYS */;

INSERT INTO `seq_alarm` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_alarm` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_bill`;

CREATE TABLE `seq_bill` (
  `id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_bill` WRITE;
/*!40000 ALTER TABLE `seq_bill` DISABLE KEYS */;

INSERT INTO `seq_bill` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_bill` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_debt
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_debt`;

CREATE TABLE `seq_debt` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_debt` WRITE;
/*!40000 ALTER TABLE `seq_debt` DISABLE KEYS */;

INSERT INTO `seq_debt` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_debt` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_friend_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_friend_request`;

CREATE TABLE `seq_friend_request` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_friend_request` WRITE;
/*!40000 ALTER TABLE `seq_friend_request` DISABLE KEYS */;

INSERT INTO `seq_friend_request` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_friend_request` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_invite
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_invite`;

CREATE TABLE `seq_invite` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_invite` WRITE;
/*!40000 ALTER TABLE `seq_invite` DISABLE KEYS */;

INSERT INTO `seq_invite` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_invite` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_item`;

CREATE TABLE `seq_item` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_item` WRITE;
/*!40000 ALTER TABLE `seq_item` DISABLE KEYS */;

INSERT INTO `seq_item` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_order`;

CREATE TABLE `seq_order` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_order` WRITE;
/*!40000 ALTER TABLE `seq_order` DISABLE KEYS */;

INSERT INTO `seq_order` (`id`)
VALUES
	(56);

/*!40000 ALTER TABLE `seq_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_order_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_order_comment`;

CREATE TABLE `seq_order_comment` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_order_comment` WRITE;
/*!40000 ALTER TABLE `seq_order_comment` DISABLE KEYS */;

INSERT INTO `seq_order_comment` (`id`)
VALUES
	(0);

/*!40000 ALTER TABLE `seq_order_comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seq_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_user`;

CREATE TABLE `seq_user` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `seq_user` WRITE;
/*!40000 ALTER TABLE `seq_user` DISABLE KEYS */;

INSERT INTO `seq_user` (`id`)
VALUES
	(4);

/*!40000 ALTER TABLE `seq_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
