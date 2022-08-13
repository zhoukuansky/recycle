/*
 Navicat Premium Data Transfer

 Source Server         : 114.115.243.22
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 114.115.243.22:3306
 Source Schema         : recycle

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 09/03/2019 11:23:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grades
-- ----------------------------
DROP TABLE IF EXISTS `grades`;
CREATE TABLE `grades`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grades_server` int(11) NOT NULL,
  `grades_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `grades_describ` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ord_order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Relationship_9`(`ord_order_id`) USING BTREE,
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`ord_order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `logIP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `logAction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `logContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flagID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_c_id` int(11) NOT NULL,
  `user_b_id` int(11) NULL DEFAULT NULL,
  `rub_id` int(11) NOT NULL,
  `order_rub_infor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_moneny` float NULL DEFAULT NULL,
  `order_time_begin` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_time_deal` datetime(0) NULL DEFAULT NULL,
  `order_time_finish` datetime(0) NULL DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Relationship_3`(`user_c_id`) USING BTREE,
  INDEX `FK_Relationship_4`(`user_b_id`) USING BTREE,
  INDEX `FK_Relationship_5`(`rub_id`) USING BTREE,
  CONSTRAINT `FK_Relationship_3` FOREIGN KEY (`user_c_id`) REFERENCES `user_cre` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`user_b_id`) REFERENCES `user_buy` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`rub_id`) REFERENCES `rubbish` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rubbish
-- ----------------------------
DROP TABLE IF EXISTS `rubbish`;
CREATE TABLE `rubbish`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rubbis_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rub_price` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rubbish_type`(`rubbis_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tp_address
-- ----------------------------
DROP TABLE IF EXISTS `tp_address`;
CREATE TABLE `tp_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `add_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_buy
-- ----------------------------
DROP TABLE IF EXISTS `user_buy`;
CREATE TABLE `user_buy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_buy_TEL`(`tel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_buy_worker
-- ----------------------------
DROP TABLE IF EXISTS `user_buy_worker`;
CREATE TABLE `user_buy_worker`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_b_id` int(11) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_b_w_TEL`(`tel`) USING BTREE,
  INDEX `FK_ӵ��`(`user_b_id`) USING BTREE,
  CONSTRAINT `FK_ӵ��` FOREIGN KEY (`user_b_id`) REFERENCES `user_buy` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_cre
-- ----------------------------
DROP TABLE IF EXISTS `user_cre`;
CREATE TABLE `user_cre`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_cre_TEL`(`tel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_sup
-- ----------------------------
DROP TABLE IF EXISTS `user_sup`;
CREATE TABLE `user_sup`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_sup_TEL`(`tel`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
