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

 Date: 21/12/2022 10:01:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addressee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '1', '1', '0', '1', '10011', '1');
INSERT INTO `address` VALUES (2, '2', '2', '1', '2', '10012', '2');
INSERT INTO `address` VALUES (3, '3', '3', '1', '3', '10013', '3');
INSERT INTO `address` VALUES (4, '4', '4', '1', '4', '10014', '4');
INSERT INTO `address` VALUES (5, '5', '5', '1', '5', '10015', '5');
INSERT INTO `address` VALUES (6, '6', '6', '1', '6', '10016', '6');
INSERT INTO `address` VALUES (7, '7', '7', '1', '7', '10017', '7');
INSERT INTO `address` VALUES (8, '8', '8', '1', '8', '10018', '8');
INSERT INTO `address` VALUES (9, '9', '9', '1', '9', '10019', '9');
INSERT INTO `address` VALUES (10, '10', '10', '1', '10', '10020', '10');
INSERT INTO `address` VALUES (11, '11', '11', '1', '11', '10021', '11');
INSERT INTO `address` VALUES (12, '12', '12', '1', '12', '10022', '12');
INSERT INTO `address` VALUES (13, '13', '13', '1', '13', '10023', '13');
INSERT INTO `address` VALUES (15, 'a', 'a', '1', 'a', '10011', 'a');

SET FOREIGN_KEY_CHECKS = 1;
