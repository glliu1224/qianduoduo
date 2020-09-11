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

 Date: 11/09/2020 13:45:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grade_field
-- ----------------------------
DROP TABLE IF EXISTS `grade_field`;
CREATE TABLE `grade_field`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID、主键',
  `field_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '菜单类型 1-主体  2-债券  3-质押券  4-对手方',
  `is_parent` int(11) NULL DEFAULT NULL COMMENT '是否是父节点1-是   2-否',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父节点ID',
  `is_valid` int(11) NULL DEFAULT NULL COMMENT '是否有效  1-有效  0-无效',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade_field
-- ----------------------------
INSERT INTO `grade_field` VALUES (1, '主体基本信息', 1, 1, 0, 1, '2020-09-11 10:18:15', '2020-09-11 10:18:20');
INSERT INTO `grade_field` VALUES (2, '主体基本信息', 2, 1, 0, 1, '2020-09-11 10:18:48', '2020-09-11 10:18:54');
INSERT INTO `grade_field` VALUES (3, '主体基本信息', 3, 1, 0, 1, '2020-09-11 10:19:10', '2020-09-11 10:19:14');
INSERT INTO `grade_field` VALUES (4, '主体基本信息', 4, 1, 0, 1, '2020-09-11 10:19:33', '2020-09-11 10:19:38');
INSERT INTO `grade_field` VALUES (5, '主体名称', 1, 0, 1, 1, '2020-09-11 10:20:34', '2020-09-11 10:20:39');
INSERT INTO `grade_field` VALUES (6, '企业性质', 1, 0, 1, 1, '2020-09-11 10:21:07', '2020-09-11 10:21:13');
INSERT INTO `grade_field` VALUES (7, '所属地区', 1, 0, 1, 1, '2020-09-11 10:21:32', '2020-09-11 10:21:37');
INSERT INTO `grade_field` VALUES (8, '第一大股东', 1, 0, 1, 1, '2020-09-11 10:21:53', '2020-09-11 10:21:58');
INSERT INTO `grade_field` VALUES (9, '持股比例', 1, 0, 1, 1, '2020-09-11 10:22:15', '2020-09-11 10:22:21');
INSERT INTO `grade_field` VALUES (10, '实际控制人', 1, 0, 1, 1, '2020-09-11 10:24:52', '2020-09-11 10:24:56');
INSERT INTO `grade_field` VALUES (11, '法定代表人', 1, 0, 1, 1, '2020-09-11 10:25:12', '2020-09-11 10:25:18');
INSERT INTO `grade_field` VALUES (12, '董事长', 1, 0, 1, 1, '2020-09-11 10:25:37', '2020-09-11 10:25:43');

SET FOREIGN_KEY_CHECKS = 1;
