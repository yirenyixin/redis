/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : finalproject

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 21/12/2022 10:02:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `personid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `balance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`personid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES ('10002', '843621');
INSERT INTO `wallet` VALUES ('10003', '1000');
INSERT INTO `wallet` VALUES ('10004', '1000');
INSERT INTO `wallet` VALUES ('10011', '413527');
INSERT INTO `wallet` VALUES ('10012', '417347');
INSERT INTO `wallet` VALUES ('10013', '416597');
INSERT INTO `wallet` VALUES ('10014', '415797');
INSERT INTO `wallet` VALUES ('10015', '417727');
INSERT INTO `wallet` VALUES ('10016', '415147');
INSERT INTO `wallet` VALUES ('10017', '418137');
INSERT INTO `wallet` VALUES ('10018', '452907');
INSERT INTO `wallet` VALUES ('10019', '452777');
INSERT INTO `wallet` VALUES ('10020', '452307');
INSERT INTO `wallet` VALUES ('10021', '452317');
INSERT INTO `wallet` VALUES ('10022', '453197');
INSERT INTO `wallet` VALUES ('10023', '452717');

SET FOREIGN_KEY_CHECKS = 1;
