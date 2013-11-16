# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: sbillit
# Generation Time: 2013-11-16 03:25:17 +0000
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



# Dump of table sbillit_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_item`;

CREATE TABLE `sbillit_item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '0:n/a',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `discount_price` decimal(10,2) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
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
  `phone` varchar(128) DEFAULT NULL,
  `picture_1` varchar(255) DEFAULT NULL,
  `picture_2` varchar(255) DEFAULT NULL,
  `picture_3` varchar(255) DEFAULT NULL,
  `expired_at` int(10) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_order_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_comment`;

CREATE TABLE `sbillit_order_comment` (
  `id` int(11) unsigned NOT NULL,
  `order_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `message` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:normal, 1:private',
  `at_user_d` int(11) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_item`;

CREATE TABLE `sbillit_order_item` (
  `order_id` int(11) unsigned NOT NULL,
  `item_id` int(11) unsigned NOT NULL,
  `item_num` int(11) unsigned NOT NULL DEFAULT '0',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `item_name` varchar(128) DEFAULT NULL,
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_order_share
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_order_share`;

CREATE TABLE `sbillit_order_share` (
  `order_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '0:n/a, 1:agreed, 2:rejected',
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sbillit_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_user`;

CREATE TABLE `sbillit_user` (
  `id` int(11) unsigned NOT NULL,
  `password` varchar(225) NOT NULL DEFAULT '',
  `point` int(11) unsigned NOT NULL DEFAULT '0',
  `banned` tinyint(2) NOT NULL DEFAULT '0',
  `invite_id` int(10) unsigned NOT NULL DEFAULT '0',
  `sns_id` varchar(255) NOT NULL DEFAULT '',
  `sns_type` tinyint(6) NOT NULL DEFAULT '0' COMMENT '0:n/a, 1:phone, 2:wechat, 3:qq, 4:weibo',
  `sns_token` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `country` varchar(128) DEFAULT NULL,
  `city` varchar(128) DEFAULT NULL,
  `last_login_at` int(10) unsigned DEFAULT NULL,
  `created_at` int(10) unsigned DEFAULT NULL,
  `updated_at` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sbillit_user` WRITE;
/*!40000 ALTER TABLE `sbillit_user` DISABLE KEYS */;

INSERT INTO `sbillit_user` (`id`, `password`, `point`, `banned`, `invite_id`, `sns_id`, `sns_type`, `sns_token`, `nickname`, `country`, `city`, `last_login_at`, `created_at`, `updated_at`)
VALUES
	(1,'',0,0,0,'',0,NULL,'aaabbbb',NULL,NULL,NULL,NULL,NULL),
	(2,'',0,0,0,'',0,NULL,'xiwan',NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `sbillit_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sbillit_user_authtoken
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sbillit_user_authtoken`;

CREATE TABLE `sbillit_user_authtoken` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `authtoken` varchar(256) NOT NULL DEFAULT '',
  `created_at` int(10) DEFAULT NULL,
  `updated_at` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table seq_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_account`;

CREATE TABLE `seq_account` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_account_flow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_account_flow`;

CREATE TABLE `seq_account_flow` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_account_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_account_log`;

CREATE TABLE `seq_account_log` (
  `id` int(11) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table seq_alarm
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_alarm`;

CREATE TABLE `seq_alarm` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_bill`;

CREATE TABLE `seq_bill` (
  `id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_debt
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_debt`;

CREATE TABLE `seq_debt` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_friend_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_friend_request`;

CREATE TABLE `seq_friend_request` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_invite
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_invite`;

CREATE TABLE `seq_invite` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_item`;

CREATE TABLE `seq_item` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_order`;

CREATE TABLE `seq_order` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_order_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_order_comment`;

CREATE TABLE `seq_order_comment` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_user`;

CREATE TABLE `seq_user` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table seq_user_authtoken
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seq_user_authtoken`;

CREATE TABLE `seq_user_authtoken` (
  `id` int(11) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
