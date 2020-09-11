CREATE TABLE `grade_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID、主键',
  `field_name` varchar(20) DEFAULT NULL COMMENT '属性名称',
  `menu_type` int(11) DEFAULT NULL COMMENT '菜单类型 1-主体  2-债券  3-质押券  4-对手方',
  `is_parent` int(11) DEFAULT NULL COMMENT '是否是父节点1-是   2-否',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点ID',
  `is_valid` int(11) DEFAULT NULL COMMENT '是否有效  1-有效  0-无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;



CREATE TABLE `grade_field_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(20) DEFAULT NULL COMMENT '用户ID',
  `field_id` int(11) DEFAULT NULL COMMENT '字段ID',
  `is_valid` int(11) DEFAULT NULL COMMENT '是否有效 1-有效  2-无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;