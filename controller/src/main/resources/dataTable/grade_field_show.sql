/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : jingdong_test

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 11/09/2020 13:46:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grade_field_show
-- ----------------------------
DROP TABLE IF EXISTS `grade_field_show`;
CREATE TABLE `grade_field_show`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户ID',
  `field_id` int(11) NULL DEFAULT NULL COMMENT '字段ID',
  `is_valid` int(11) NULL DEFAULT NULL COMMENT '是否有效 1-有效  2-无效',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade_field_show
-- ----------------------------
INSERT INTO `grade_field_show` VALUES (1, 1, 1, 1, '2020-09-11 10:28:57', '2020-09-11 10:29:01');
INSERT INTO `grade_field_show` VALUES (2, 1, 5, 1, '2020-09-11 10:30:33', '2020-09-11 10:30:38');
INSERT INTO `grade_field_show` VALUES (3, 1, 6, 1, '2020-09-11 10:30:56', '2020-09-11 10:31:01');
INSERT INTO `grade_field_show` VALUES (4, 1, 7, 1, '2020-09-11 10:31:05', '2020-09-11 10:31:10');
INSERT INTO `grade_field_show` VALUES (5, 1, 8, 1, '2020-09-11 10:31:16', '2020-09-11 10:31:21');

SET FOREIGN_KEY_CHECKS = 1;
