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

 Date: 21/12/2022 10:01:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` int NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('商店', '10002', '123', 1, 'tx.png');
INSERT INTO `person` VALUES ('用户1', '10011', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('商店1', '10003', '123', 1, 'tx.png');
INSERT INTO `person` VALUES ('商店3', '10004', '123', 1, 'tx.png');
INSERT INTO `person` VALUES ('用户2', '10012', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户3', '10013', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户4', '10014', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户5', '10015', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户6', '10016', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户7', '10017', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户8', '10018', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户9', '10019', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户10', '10020', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户11', '10021', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户12', '10022', '123', 0, 'tx.png');
INSERT INTO `person` VALUES ('用户13', '10023', '123', 0, 'tx.png');

SET FOREIGN_KEY_CHECKS = 1;
