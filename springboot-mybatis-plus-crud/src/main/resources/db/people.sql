/*
 Navicat Premium Data Transfer

 Source Server         : geekerstar
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 19/12/2019 20:51:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `manager_fk` (`manager_id`) USING BTREE,
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of people
-- ----------------------------
BEGIN;
INSERT INTO `people` VALUES (1087982257332887551, 'dyz-ann', 18, 'dyz@163.com', NULL, '2019-07-09 10:00:34');
INSERT INTO `people` VALUES (1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20');
INSERT INTO `people` VALUES (1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22');
INSERT INTO `people` VALUES (1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16');
INSERT INTO `people` VALUES (1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15');
INSERT INTO `people` VALUES (1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
