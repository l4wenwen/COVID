/*
 Navicat MySQL Data Transfer

 Source Server         : stu_info
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : covid

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 01/07/2021 00:22:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dept_no` int(0) NOT NULL,
  `dept_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`dept_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_type` int(0) NOT NULL,
  `dept_no` int(0) NULL DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '123', 2, 1, '123');
INSERT INTO `user` VALUES ('t1', 't1', 1, 1, 't1');

-- ----------------------------
-- Table structure for vacation
-- ----------------------------
DROP TABLE IF EXISTS `vacation`;
CREATE TABLE `vacation`  (
  `vid` int(0) NOT NULL AUTO_INCREMENT,
  `vreason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `vstart_time` datetime(0) NOT NULL,
  `vend_time` datetime(0) NOT NULL,
  `vstate` int(0) NOT NULL,
  `user_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `vrequest_time` datetime(0) NULL DEFAULT NULL,
  `vtransport` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`vid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vacation
-- ---------------------------
INSERT INTO `vacation` VALUES (5, '肚子疼', '2021-06-30 21:24:00', '2021-06-30 20:27:00', 1, '123', '2021-06-30 20:24:00', '高铁');
INSERT INTO `vacation` VALUES (6, '肚子疼', '2021-06-05 20:36:00', '2021-06-30 22:36:00', 2, '123', '2021-06-30 20:36:00', '高铁');
INSERT INTO `vacation` VALUES (9, '12123', '2021-06-30 23:22:00', '2021-06-30 23:21:00', 1, '123', '2021-06-30 23:19:00', '12');
INSERT INTO `vacation` VALUES (11, '12123', '2021-07-01 02:13:00', '2021-07-01 00:16:00', 1, '123', '2021-07-01 00:13:00', '高铁');

SET FOREIGN_KEY_CHECKS = 1;
