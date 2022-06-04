/*
Navicat MySQL Data Transfer

Source Server         : 毕设
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : android_company_attendance

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2022-03-16 09:00:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `register_user`
-- ----------------------------
DROP TABLE IF EXISTS `register_user`;
CREATE TABLE `register_user` (
  `id` bigint(20) NOT NULL,
  `userName` varchar(255) NOT NULL COMMENT '用户名',
  `userPass` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `head_image` varchar(555) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `adrress` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注册用户';

-- ----------------------------
-- Records of register_user
-- ----------------------------
INSERT INTO `register_user` VALUES ('1370657172803788802', '100001', '123456', '张三', '2022-03-11 16:45:24', 'http://192.168.3.2/images/deb7a052-783e-4cd4-aea6-74830a8ff125.jpg', '女', '1369940894036209665', '18932297111', '100001');
INSERT INTO `register_user` VALUES ('1370657172883480577', '100002', '123456', '李四', '2022-03-11 16:45:10', null, null, '计算机系', null, '100002');
INSERT INTO `register_user` VALUES ('1370657172883480579', '100003', '123456', '王五', '2022-03-11 16:45:10', null, null, '计算机系', null, '100003');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'url地址',
  `sort` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `menu_flag` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '是否是菜单(字典)',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` varchar(32) COLLATE utf8_bin DEFAULT 'ENABLE' COMMENT '菜单状态(字典)',
  `new_page_flag` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '是否打开新页面的标识(字典)',
  `open_flag` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '是否打开(字典)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('105', 'system', '0', '[0],', '系统管理', 'layui-icon layui-icon-set', '#', '20', '1', 'Y', null, 'ENABLE', null, '1', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('106', 'mgr', 'system', '[0],[system],', '系统账号管理', '', '/mgr', '22', '2', 'Y', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('107', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', null, '/mgr/add', '1', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('108', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', null, '/mgr/edit', '2', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('109', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', null, '/mgr/delete', '3', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('110', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', null, '/mgr/reset', '4', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('111', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', null, '/mgr/freeze', '5', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('112', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', null, '/mgr/unfreeze', '6', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('113', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', null, '/mgr/setRole', '7', '3', 'N', null, 'ENABLE', null, '0', null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('114', 'role', 'system', '[0],[system],', '角色管理', null, '/role', '2', '2', 'Y', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('115', 'role_add', 'role', '[0],[system],[role],', '添加角色', null, '/role/add', '1', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('116', 'role_edit', 'role', '[0],[system],[role],', '修改角色', null, '/role/edit', '2', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('117', 'role_remove', 'role', '[0],[system],[role],', '删除角色', null, '/role/remove', '3', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('118', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', null, '/role/setAuthority', '4', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('119', 'menu', 'system', '[0],[system],', '菜单管理', null, '/menu', '4', '2', 'Y', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('120', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', null, '/menu/add', '1', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('121', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', null, '/menu/edit', '2', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('122', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', null, '/menu/remove', '3', '3', 'N', null, 'ENABLE', null, '0', null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('150', 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', '4', '3', 'N', null, 'ENABLE', null, null, null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('151', 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', '5', '3', 'N', null, 'ENABLE', null, null, null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('162', 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', '5', '3', 'N', null, 'ENABLE', null, null, null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('163', 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', '6', '3', 'N', null, 'ENABLE', null, null, null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('164', 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', '7', '3', 'N', null, 'ENABLE', null, null, null, '2019-03-29 16:32:27', null, '1');
INSERT INTO `sys_menu` VALUES ('165', 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', '8', '3', 'N', null, 'ENABLE', null, null, null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('166', 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', '9', '3', 'N', null, 'ENABLE', null, null, null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('167', 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', '10', '3', 'N', null, 'ENABLE', null, null, null, '2021-03-20 14:06:14', null, '1');
INSERT INTO `sys_menu` VALUES ('1369919363436498946', 'ATTEND_TIME', '0', '[0],', '考勤设置', 'fa-star', '/attendTime', '999', '1', 'Y', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-20 13:24:28', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363436498947', 'ATTEND_TIME_ADD', 'ATTEND_TIME', '[0],[ATTEND_TIME],', '公司考勤时间添加', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-20 13:24:28', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363436498948', 'ATTEND_TIME_EDIT', 'ATTEND_TIME', '[0],[ATTEND_TIME],', '公司考勤时间修改', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-20 13:24:28', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363436498949', 'ATTEND_TIME_DELETE', 'ATTEND_TIME', '[0],[ATTEND_TIME],', '公司考勤时间删除', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-20 13:24:28', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363562328065', 'ATTENDANCE', '0', '[0],', '员工考勤', 'fa-star', '/attendance', '999', '1', 'Y', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 15:53:22', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363570716674', 'ATTENDANCE_ADD', 'ATTENDANCE', '[0],[ATTENDANCE],', '员工考勤添加', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 15:53:22', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363570716675', 'ATTENDANCE_EDIT', 'ATTENDANCE', '[0],[ATTENDANCE],', '员工考勤修改', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 15:53:22', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363570716676', 'ATTENDANCE_DELETE', 'ATTENDANCE', '[0],[ATTENDANCE],', '员工考勤删除', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 15:53:22', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363671379970', 'DEPT', '0', '[0],', '部门管理', 'fa-star', '/dept', '999', '1', 'Y', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 16:04:19', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363671379971', 'DEPT_ADD', 'DEPT', '[0],[DEPT],', '部门表添加', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 16:04:19', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363671379972', 'DEPT_EDIT', 'DEPT', '[0],[DEPT],', '部门表修改', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 16:04:19', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363671379973', 'DEPT_DELETE', 'DEPT', '[0],[DEPT],', '部门表删除', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-11 16:04:19', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363801403393', 'LEAVE', '0', '[0],', '请假管理', 'fa-star', '/leave', '999', '1', 'Y', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-25 08:32:41', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363801403394', 'LEAVE_ADD', 'LEAVE', '[0],[LEAVE],', '请假管理添加', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-25 08:32:41', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363801403395', 'LEAVE_EDIT', 'LEAVE', '[0],[LEAVE],', '请假管理修改', 'fa-star', '', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-25 08:32:41', '1', '1');
INSERT INTO `sys_menu` VALUES ('1369919363801403396', 'LEAVE_DELETE', 'LEAVE', '[0],[LEAVE],', '请假管理删除', 'fa-star', '/LEAVE_DELETE', '999', '2', 'N', '', 'ENABLE', '', '', '2021-03-11 15:53:22', '2021-03-25 08:32:41', '1', '1');
INSERT INTO `sys_menu` VALUES ('1373151882361434114', '585888', '0', '[0],', '员工管理', '', '/registerUser', '1000', '1', 'Y', null, 'ENABLE', null, null, '2021-03-20 13:58:15', '2021-10-16 16:44:36', '1', '1');

-- ----------------------------
-- Table structure for `sys_relation`
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation` (
  `relation_id` bigint(20) NOT NULL COMMENT '主键',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES ('1369921882044706817', '1369919363562328065', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882061484034', '1369919363570716674', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882090844161', '1369919363570716675', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882111815681', '1369919363570716676', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882136981505', '1369919363801403393', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882153758722', '1369919363801403394', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882183118849', '1369919363801403395', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1369921882208284674', '1369919363801403396', '1359012706808463361');
INSERT INTO `sys_relation` VALUES ('1373152143595270145', '105', '1');
INSERT INTO `sys_relation` VALUES ('1373152143633018882', '114', '1');
INSERT INTO `sys_relation` VALUES ('1373152143653990401', '115', '1');
INSERT INTO `sys_relation` VALUES ('1373152143683350529', '116', '1');
INSERT INTO `sys_relation` VALUES ('1373152143716904962', '117', '1');
INSERT INTO `sys_relation` VALUES ('1373152143742070786', '118', '1');
INSERT INTO `sys_relation` VALUES ('1373152143771430913', '162', '1');
INSERT INTO `sys_relation` VALUES ('1373152143796596738', '163', '1');
INSERT INTO `sys_relation` VALUES ('1373152143821762561', '164', '1');
INSERT INTO `sys_relation` VALUES ('1373152143846928386', '119', '1');
INSERT INTO `sys_relation` VALUES ('1373152143872094209', '120', '1');
INSERT INTO `sys_relation` VALUES ('1373152143888871425', '121', '1');
INSERT INTO `sys_relation` VALUES ('1373152143922425857', '122', '1');
INSERT INTO `sys_relation` VALUES ('1373152143947591681', '150', '1');
INSERT INTO `sys_relation` VALUES ('1373152143981146113', '151', '1');
INSERT INTO `sys_relation` VALUES ('1373152143997923330', '106', '1');
INSERT INTO `sys_relation` VALUES ('1373152144023089153', '107', '1');
INSERT INTO `sys_relation` VALUES ('1373152144044060673', '108', '1');
INSERT INTO `sys_relation` VALUES ('1373152144086003714', '109', '1');
INSERT INTO `sys_relation` VALUES ('1373152144102780929', '110', '1');
INSERT INTO `sys_relation` VALUES ('1373152144127946753', '111', '1');
INSERT INTO `sys_relation` VALUES ('1373152144153112578', '112', '1');
INSERT INTO `sys_relation` VALUES ('1373152144169889794', '113', '1');
INSERT INTO `sys_relation` VALUES ('1373152144203444225', '165', '1');
INSERT INTO `sys_relation` VALUES ('1373152144228610049', '166', '1');
INSERT INTO `sys_relation` VALUES ('1373152144245387265', '167', '1');
INSERT INTO `sys_relation` VALUES ('1373152144283136001', '1369919363436498946', '1');
INSERT INTO `sys_relation` VALUES ('1373152144316690434', '1369919363436498947', '1');
INSERT INTO `sys_relation` VALUES ('1373152144350244865', '1369919363436498948', '1');
INSERT INTO `sys_relation` VALUES ('1373152144383799297', '1369919363436498949', '1');
INSERT INTO `sys_relation` VALUES ('1373152144408965121', '1369919363562328065', '1');
INSERT INTO `sys_relation` VALUES ('1373152144438325250', '1369919363570716674', '1');
INSERT INTO `sys_relation` VALUES ('1373152144463491074', '1369919363570716675', '1');
INSERT INTO `sys_relation` VALUES ('1373152144488656897', '1369919363570716676', '1');
INSERT INTO `sys_relation` VALUES ('1373152144505434114', '1369919363671379970', '1');
INSERT INTO `sys_relation` VALUES ('1373152144538988546', '1369919363671379971', '1');
INSERT INTO `sys_relation` VALUES ('1373152144568348673', '1369919363671379972', '1');
INSERT INTO `sys_relation` VALUES ('1373152144601903106', '1369919363671379973', '1');
INSERT INTO `sys_relation` VALUES ('1373152144635457537', '1369919363801403393', '1');
INSERT INTO `sys_relation` VALUES ('1373152144660623361', '1369919363801403394', '1');
INSERT INTO `sys_relation` VALUES ('1373152144685789186', '1369919363801403395', '1');
INSERT INTO `sys_relation` VALUES ('1373152144710955009', '1369919363801403396', '1');
INSERT INTO `sys_relation` VALUES ('1373152144727732226', '1373151882361434114', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL COMMENT '主键id',
  `pid` bigint(20) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '提示',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '0', '超级管理员', 'administrator', '1', '1', null, null, null, null);
INSERT INTO `sys_role` VALUES ('1359012706808463361', '1', '普通管理员', '普通管理员', '3', null, '2021-02-09 13:34:13', '2021-03-20 22:41:44', '1', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL COMMENT '主键id',
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `account` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '性别(字典)',
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `role_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id(多个逗号隔开)',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id(多个逗号隔开)',
  `status` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '状态(字典)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='员工管理表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'http://192.168.3.2/images/7ee6ed48-12f8-4ab8-88e2-735da97bdf45.png', 'admin', '6d8fa749c57c288520e4be9de1b23736', '6zqfr', '超级管理员', '2018-11-16 00:00:00', 'M', 'sn93@qq.com', '18200000000', '1', '1369940894036209665', 'ENABLE', '2021-10-15 08:49:53', null, '2021-10-15 09:11:47', '24', '25');
INSERT INTO `sys_user` VALUES ('1358028318281003010', 'http://192.168.3.2/images/a33f1f06-9b05-43be-81c1-01a85302c63f.jpg', '1000001', '7c716316ba6e8e8205ca83c44f4b026f', 'hccm2', '张三', '2021-10-14 00:00:00', 'M', '102222', '18932297586', '1359012706808463361', '1369940894036209665', 'ENABLE', '2021-10-15 20:22:37', '1', '2021-10-15 00:21:36', '1', null);
INSERT INTO `sys_user` VALUES ('1370017415803305986', 'http://192.168.3.2/images/cf35fbce-75f1-41cb-a208-e721b250669d.jpg', 'user123', '46787aa2043a1f949c91b14f354a04cc', 'vnzgm', '王五', '2021-10-11 00:00:00', 'M', '1000055', '18955555555', '1359012706808463361', '1369940894036209665', 'ENABLE', '2021-10-15 22:23:00', '1', '2021-10-15 00:21:24', '1', null);

-- ----------------------------
-- Table structure for `t_attendance`
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance`;
CREATE TABLE `t_attendance` (
  `attend_id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '考勤员工',
  `time` datetime DEFAULT NULL COMMENT '考勤时间',
  `type` varchar(255) DEFAULT NULL COMMENT '考勤类型',
  `pic` varchar(1255) DEFAULT NULL COMMENT '打卡照片',
  `no` varchar(255) DEFAULT NULL COMMENT '工号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `time_id` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工考勤';

-- ----------------------------
-- Records of t_attendance
-- ----------------------------
INSERT INTO `t_attendance` VALUES ('1374885137720799233', '1370657172803788802', '2022-03-15 08:45:36', '正常打卡', 'http://192.168.3.2/images/b6628493-36f4-4c14-86da-7f089ba15bc3.jpg', '100001', '张三', '1373150112168013826', '在南区-118幢附近(121.15914,33.3651)');

-- ----------------------------
-- Table structure for `t_attend_time`
-- ----------------------------
DROP TABLE IF EXISTS `t_attend_time`;
CREATE TABLE `t_attend_time` (
  `time` bigint(20) NOT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '公司上班时间',
  `end_time` datetime DEFAULT NULL COMMENT '公司下班时间',
  `address` varchar(255) DEFAULT NULL COMMENT '打卡地点',
  `mark` varchar(255) DEFAULT NULL COMMENT '打卡说明',
  PRIMARY KEY (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤打卡设置';

-- ----------------------------
-- Records of t_attend_time
-- ----------------------------
INSERT INTO `t_attend_time` VALUES ('1373150112168013826', '2022-03-15 08:00:00', '2022-03-15 21:00:00', '上海市区北京路8888号', '请各位同事准时打卡考勤');

-- ----------------------------
-- Table structure for `t_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `mark` varchar(255) DEFAULT NULL COMMENT '部门备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1369940894036209665', '研发部', '研发部');
INSERT INTO `t_dept` VALUES ('1374884660153151490', '人事部门', '人事部门');

-- ----------------------------
-- Table structure for `t_leave`
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `leave_id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '员工',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `mark` varchar(5000) DEFAULT NULL COMMENT '审批回复',
  `status` int(11) DEFAULT NULL COMMENT '审批状态',
  `reason` varchar(5000) DEFAULT NULL COMMENT '事由',
  `type` varchar(255) DEFAULT NULL COMMENT '类型缺勤原因、出差时间、休假情况',
  PRIMARY KEY (`leave_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请假管理';

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES ('1374885225381752834', '1370657172803788802', '2022-03-12 10:10:00', null, '上班过后请在人事出备案\n', '1', '我要请假一天啊', '请假');
