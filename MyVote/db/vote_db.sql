/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : vote_db

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-01-21 11:55:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `STATUS` int(11) DEFAULT '0',
  `TYPE` int(11) DEFAULT NULL,
  `IS_VALID` int(11) DEFAULT '1',
  `SORT_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `QUESTION_ID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `sort_index` (`SORT_NUM`),
  KEY `FK_OPTION_TO_QUESTION_idx` (`QUESTION_ID`),
  CONSTRAINT `FK_OPTION_TO_QUESTION` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='待选列表问题';

-- ----------------------------
-- Records of option
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `STATUS` int(11) DEFAULT '0',
  `TYPE` int(11) DEFAULT NULL,
  `IS_VALID` int(11) DEFAULT '1',
  `SORT_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `EXPIRED_DATE` datetime DEFAULT NULL,
  `TOPIC_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `sort_index` (`SORT_NUM`),
  KEY `FK_QUESTION_TOPIC` (`TOPIC_ID`),
  CONSTRAINT `FK_QUESTION_TOPIC` FOREIGN KEY (`TOPIC_ID`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='问卷表';

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of topic
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `IS_VOTEED` int(11) DEFAULT '0',
  `IS_VALID` int(11) DEFAULT '1',
  `SORT_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `WEIGHT` int(11) DEFAULT NULL COMMENT '权重',
  `DEPARTMNET_NAME` varchar(45) DEFAULT NULL COMMENT '部门名称',
  `LOGIN_NAME` varchar(45) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(120) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`ID`),
  KEY `sort_index` (`SORT_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `QUESTION_ID` varchar(45) DEFAULT NULL,
  `OPTION_ID` varchar(45) DEFAULT NULL,
  `WEIGHT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VOTE_USER_idx` (`USER_ID`),
  KEY `FK_VOTE_QUESTION_idx` (`QUESTION_ID`),
  KEY `FK_VOTE_OPTION_idx` (`OPTION_ID`),
  CONSTRAINT `FK_VOTE_OPTION` FOREIGN KEY (`OPTION_ID`) REFERENCES `option` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VOTE_QUESTION` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VOTE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of vote
-- ----------------------------
