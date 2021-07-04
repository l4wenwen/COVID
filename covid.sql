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

 Date: 04/07/2021 16:40:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `classNum` int(0) NOT NULL AUTO_INCREMENT,
  `className` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`classNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '软工193');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `collegeNum` int(0) NOT NULL AUTO_INCREMENT,
  `collegeName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`collegeNum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (0, 'ADMIN');
INSERT INTO `college` VALUES (1, '信息学院');
INSERT INTO `college` VALUES (2, '艺术学院');
INSERT INTO `college` VALUES (3, '生化学院');
INSERT INTO `college` VALUES (4, '电气学院');
INSERT INTO `college` VALUES (5, '经管学院');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `majorNum` int(0) NOT NULL AUTO_INCREMENT,
  `collegeNum` int(0) NOT NULL,
  `majorName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`majorNum`) USING BTREE,
  INDEX `collegeNum`(`collegeNum`) USING BTREE,
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`collegeNum`) REFERENCES `college` (`collegeNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, 1, '软件工程');
INSERT INTO `major` VALUES (2, 2, '服装设计');
INSERT INTO `major` VALUES (3, 3, '化工制药');
INSERT INTO `major` VALUES (4, 4, '自动化');
INSERT INTO `major` VALUES (5, 5, '经济学');
INSERT INTO `major` VALUES (6, 1, '计算机科学');
INSERT INTO `major` VALUES (7, 1, '数字媒体');
INSERT INTO `major` VALUES (8, 4, '建筑智能及其自动化');

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `stateNum` int(0) NOT NULL AUTO_INCREMENT,
  `userNum` char(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stateTime` date NOT NULL,
  `isTemperature` tinyint(1) NOT NULL,
  `isCovid` tinyint(1) NOT NULL,
  `isLikeCovid` tinyint(1) NOT NULL,
  `quarantine` int(0) NOT NULL,
  `isRecentArea` tinyint(1) NOT NULL,
  `isRecentCountry` tinyint(1) NOT NULL,
  `isRecentPeople` tinyint(1) NOT NULL,
  `isSymptom` tinyint(1) NOT NULL,
  `isAbnormal` tinyint(1) NOT NULL,
  `healthCodeType` int(0) NOT NULL,
  `isOutSchool` tinyint(1) NOT NULL,
  `isOutCity` tinyint(1) NOT NULL,
  PRIMARY KEY (`stateNum`) USING BTREE,
  INDEX `userNum`(`userNum`) USING BTREE,
  CONSTRAINT `state_ibfk_1` FOREIGN KEY (`userNum`) REFERENCES `user` (`userNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userNum` char(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `collegeNum` int(0) NULL DEFAULT NULL,
  `majorNum` int(0) NULL DEFAULT NULL,
  `classNum` int(0) NULL DEFAULT NULL,
  `userType` int(0) NOT NULL,
  `userName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `telephone` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userNum`) USING BTREE,
  INDEX `collegeNum`(`collegeNum`) USING BTREE,
  INDEX `majorNum`(`majorNum`) USING BTREE,
  INDEX `classNum`(`classNum`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`collegeNum`) REFERENCES `college` (`collegeNum`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`majorNum`) REFERENCES `major` (`majorNum`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`classNum`) REFERENCES `class` (`classNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000001', 1, 1, 1, 1, '吴楚', '5d7cf5d65143b6772b73e8ed32715188', 1, NULL);
INSERT INTO `user` VALUES ('10000002', 2, 2, 1, 1, '郭敏黄', 'cb642a1cd757e6dcf67312b6a8a81fe1', 0, NULL);
INSERT INTO `user` VALUES ('10000003', 3, 3, 1, 1, '张语', '195b3a44e1fd50078eb4130f2cb68469', 0, NULL);
INSERT INTO `user` VALUES ('10000004', 4, 4, 1, 1, '郭怡', '332e11fa7c9591b6aa3ad242867eea66', 1, NULL);
INSERT INTO `user` VALUES ('10000005', 5, 5, 1, 1, '张怡', 'c07e8cd1cb222d01d51a9ed88ab6cd4e', 1, NULL);
INSERT INTO `user` VALUES ('20000001', 1, 1, 1, 2, '马海泊', '5d7cf5d65143b6772b73e8ed32715188', 1, NULL);
INSERT INTO `user` VALUES ('20000002', 1, 1, 1, 2, '张杰诚', 'cb642a1cd757e6dcf67312b6a8a81fe1', 0, NULL);
INSERT INTO `user` VALUES ('20000003', 1, 1, 1, 2, '赵峰', '195b3a44e1fd50078eb4130f2cb68469', 1, NULL);
INSERT INTO `user` VALUES ('20000004', 1, 1, 1, 2, '郭发', '332e11fa7c9591b6aa3ad242867eea66', 0, NULL);
INSERT INTO `user` VALUES ('20000005', 1, 1, 1, 2, '刘雨', 'c07e8cd1cb222d01d51a9ed88ab6cd4e', 0, NULL);
INSERT INTO `user` VALUES ('20000006', 1, 1, 1, 2, '张峰杰', 'fb6c4c3d81e540783a9a2e0f603bbb26', 1, NULL);
INSERT INTO `user` VALUES ('20000007', 2, 2, 1, 2, '马杰黄', 'a34acf1005ae8d75711e3f3a83e9cc61', 1, NULL);
INSERT INTO `user` VALUES ('20000008', 2, 2, 1, 2, '林怡海', '9afa05d453389ca4b72b84a63a54c21d', 0, NULL);
INSERT INTO `user` VALUES ('20000009', 2, 2, 1, 2, '赵峰', '20c18451b72b8c3a25e7cd08a4263400', 0, NULL);
INSERT INTO `user` VALUES ('20000010', 2, 2, 1, 2, '孙杰峰', '972330ecd9b758659ecc43b1b07625f6', 0, NULL);
INSERT INTO `user` VALUES ('20000011', 2, 2, 1, 2, '马语', '630b7ac00527981796782c3fdb76263e', 0, NULL);
INSERT INTO `user` VALUES ('20000012', 2, 2, 1, 2, '吴芳发', 'bf8bbafc67b5569f0b2087a3c6da6dff', 1, NULL);
INSERT INTO `user` VALUES ('20000013', 3, 3, 1, 2, '林峰怡', 'edf0ea15bb9c2e87fc037938f9936387', 1, NULL);
INSERT INTO `user` VALUES ('20000014', 3, 3, 1, 2, '赵雨楚', '328c9c67c9d17cffa3275edb18e2b955', 1, NULL);
INSERT INTO `user` VALUES ('20000015', 3, 3, 1, 2, '王诚', 'e32470e0ddb6310c3651aeb3e9459e70', 1, NULL);
INSERT INTO `user` VALUES ('20000016', 3, 3, 1, 2, '张峰', '8935319b77b3d529474b74da103f44df', 0, NULL);
INSERT INTO `user` VALUES ('20000017', 3, 3, 1, 2, '陈楚', '704495770545441003084d566f9bb593', 0, NULL);
INSERT INTO `user` VALUES ('20000018', 3, 3, 1, 2, '孙芳海', '53f17bfe25737ada3c9a3470f79cd55b', 1, NULL);
INSERT INTO `user` VALUES ('20000019', 4, 4, 1, 2, '何怡海', 'b2da979df0dd3968e04b800a895d7261', 0, NULL);
INSERT INTO `user` VALUES ('20000020', 4, 4, 1, 2, '马楚楚', '825f929a27af655b80730b95d38b3819', 1, NULL);
INSERT INTO `user` VALUES ('20000021', 4, 4, 1, 2, '张黄泊', '3048c43149c8200f7185c2595530171d', 0, NULL);
INSERT INTO `user` VALUES ('20000022', 4, 4, 1, 2, '杨诚楚', 'c8344d2301b08227ce0c8371ff3c4552', 0, NULL);
INSERT INTO `user` VALUES ('20000023', 4, 4, 1, 2, '杨怡', 'd3e17e3e8e361d0db21883389c4957e7', 1, NULL);
INSERT INTO `user` VALUES ('20000024', 4, 4, 1, 2, '马发', '38def3dcc44b04720540cb52b9343225', 0, NULL);
INSERT INTO `user` VALUES ('20000025', 5, 5, 1, 2, '赵黄', '486a0ba0e5b41743411b57d372b6ab99', 1, NULL);
INSERT INTO `user` VALUES ('20000026', 5, 5, 1, 2, '杨杰诚', '0f22e6baf0390d4b7ad068d2388961f1', 0, NULL);
INSERT INTO `user` VALUES ('20000027', 5, 5, 1, 2, '马敏泊', 'db61fcc6477f7f82fb9e08de0409f8f4', 0, NULL);
INSERT INTO `user` VALUES ('20000028', 5, 5, 1, 2, '郭黄楚', '233fcc64958f2a8d780b048dbbb13b94', 1, NULL);
INSERT INTO `user` VALUES ('20000029', 5, 5, 1, 2, '高语', '08e53c15b1b2193a5442a505e20fe7fa', 1, NULL);
INSERT INTO `user` VALUES ('20000030', 5, 5, 1, 2, '黄芳泊', '35c2aa90f1de3f38d3001be5cf98e5db', 0, NULL);
INSERT INTO `user` VALUES ('admin', NULL, NULL, NULL, 0, 'admin', '7f3d0e8c0d7fba9d0b887a797ede072e', 0, NULL);

-- ----------------------------
-- Table structure for vacation
-- ----------------------------
DROP TABLE IF EXISTS `vacation`;
CREATE TABLE `vacation`  (
  `vacationNum` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userNum` char(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `startTime` datetime(0) NOT NULL,
  `endTime` datetime(0) NOT NULL,
  `requestTime` datetime(0) NOT NULL,
  `way` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` int(0) NOT NULL,
  PRIMARY KEY (`vacationNum`) USING BTREE,
  INDEX `userNum`(`userNum`) USING BTREE,
  CONSTRAINT `vacation_ibfk_1` FOREIGN KEY (`userNum`) REFERENCES `user` (`userNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vacation
-- ----------------------------

-- ----------------------------
-- View structure for statistic
-- ----------------------------
DROP VIEW IF EXISTS `statistic`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `statistic` AS select `b`.`totalNum` AS `totalNum`,`a`.`filledNum` AS `filledNum`,`a`.`highRiskNum` AS `highRiskNum`,`a`.`passRiskAreaNum` AS `passRiskAreaNum`,`a`.`stateTime` AS `stateTime` from ((select count(distinct `c`.`userNum`) AS `filledNum`,count(((`c`.`isCovid` = 1) or (0 <> NULL))) AS `highRiskNum`,count(((`c`.`isRecentArea` = 1) or (0 <> NULL))) AS `passRiskAreaNum`,`c`.`stateTime` AS `stateTime` from (select `state`.`stateNum` AS `stateNum`,`state`.`userNum` AS `userNum`,`state`.`stateTime` AS `stateTime`,`state`.`isTemperature` AS `isTemperature`,`state`.`isCovid` AS `isCovid`,`state`.`isLikeCovid` AS `isLikeCovid`,`state`.`quarantine` AS `quarantine`,`state`.`isRecentArea` AS `isRecentArea`,`state`.`isRecentCountry` AS `isRecentCountry`,`state`.`isRecentPeople` AS `isRecentPeople`,`state`.`isSymptom` AS `isSymptom`,`state`.`isAbnormal` AS `isAbnormal`,`state`.`healthCodeType` AS `healthCodeType`,`state`.`isOutSchool` AS `isOutSchool`,`state`.`isOutCity` AS `isOutCity` from `state` where `state`.`userNum` in (select `user`.`userNum` from `user` where (`user`.`userType` = 2))) `c` group by `c`.`stateTime`) `a` join (select count(0) AS `totalNum` from `user` where (`user`.`userType` = 2)) `b`);

-- ----------------------------
-- View structure for statistic_divide
-- ----------------------------
DROP VIEW IF EXISTS `statistic_divide`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `statistic_divide` AS select `c`.`collegeNum` AS `collegeNum`,`b`.`totalNum` AS `totalNum`,count(distinct `c`.`userNum`) AS `filledNum`,count(((`c`.`isCovid` = 1) or (0 <> NULL))) AS `highRiskNum`,count(((`c`.`isRecentArea` = 1) or (0 <> NULL))) AS `passRiskAreaNum`,`c`.`stateTime` AS `stateTime` from ((select `state`.`stateNum` AS `stateNum`,`state`.`userNum` AS `userNum`,`state`.`stateTime` AS `stateTime`,`state`.`isTemperature` AS `isTemperature`,`state`.`isCovid` AS `isCovid`,`state`.`isLikeCovid` AS `isLikeCovid`,`state`.`quarantine` AS `quarantine`,`state`.`isRecentArea` AS `isRecentArea`,`state`.`isRecentCountry` AS `isRecentCountry`,`state`.`isRecentPeople` AS `isRecentPeople`,`state`.`isSymptom` AS `isSymptom`,`state`.`isAbnormal` AS `isAbnormal`,`state`.`healthCodeType` AS `healthCodeType`,`state`.`isOutSchool` AS `isOutSchool`,`state`.`isOutCity` AS `isOutCity`,`user`.`collegeNum` AS `collegeNum` from (`state` join `user`) where ((`state`.`userNum` = `user`.`userNum`) and (`user`.`userType` = 2))) `c` join (select `user`.`collegeNum` AS `collegeNum`,count(0) AS `totalNum` from `user` where (`user`.`userType` = 2) group by `user`.`collegeNum`) `b`) where (`c`.`collegeNum` = `b`.`collegeNum`) group by `c`.`stateTime`,`c`.`collegeNum`,`b`.`totalNum`;

SET FOREIGN_KEY_CHECKS = 1;
