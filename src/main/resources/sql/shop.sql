/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-05-09 18:15:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `auth_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sort_order` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '用户管理', '', '1');
INSERT INTO `menu` VALUES ('2', '0', '权限管理', '', '2');
INSERT INTO `menu` VALUES ('3', '0', '商品管理', '', '3');
INSERT INTO `menu` VALUES ('4', '0', '订单管理', '', '4');
INSERT INTO `menu` VALUES ('5', '0', '数据统计', '', '5');
INSERT INTO `menu` VALUES ('6', '1', '用户列表', 'users', '6');
INSERT INTO `menu` VALUES ('7', '2', '角色列表', 'roles', '8');
INSERT INTO `menu` VALUES ('8', '2', '权限列表', 'rights', '9');

-- ----------------------------
-- Table structure for rights
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(20) NOT NULL COMMENT '权限名称',
  `pid` smallint(6) unsigned NOT NULL COMMENT '父id',
  `controller` varchar(32) NOT NULL DEFAULT '' COMMENT '控制器',
  `method` varchar(32) NOT NULL DEFAULT '' COMMENT '操作方法',
  `level` enum('0','2','1') NOT NULL DEFAULT '0' COMMENT '权限等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of rights
-- ----------------------------
INSERT INTO `rights` VALUES ('101', '商品管理', '0', '', '', '0');
INSERT INTO `rights` VALUES ('102', '订单管理', '0', '', 'order', '0');
INSERT INTO `rights` VALUES ('103', '权限管理', '0', '', '', '0');
INSERT INTO `rights` VALUES ('104', '商品列表', '101', 'Goods', 'index', '1');
INSERT INTO `rights` VALUES ('105', '添加商品', '104', 'Goods', 'tianjia', '2');
INSERT INTO `rights` VALUES ('107', '订单列表', '102', 'Order', 'index', '1');
INSERT INTO `rights` VALUES ('109', '添加订单', '107', 'Order', 'tianjia', '2');
INSERT INTO `rights` VALUES ('110', '用户列表', '125', 'Manager', 'index', '1');
INSERT INTO `rights` VALUES ('111', '角色列表', '103', 'Role', 'index', '1');
INSERT INTO `rights` VALUES ('112', '权限列表', '103', 'Permission', 'index', '1');
INSERT INTO `rights` VALUES ('115', '分类参数', '101', 'Type', 'index', '1');
INSERT INTO `rights` VALUES ('116', '商品修改', '104', 'Goods', 'upd', '2');
INSERT INTO `rights` VALUES ('117', '商品删除', '104', 'Goods', 'del', '2');
INSERT INTO `rights` VALUES ('121', '商品分类', '101', '', '', '1');
INSERT INTO `rights` VALUES ('122', '添加分类', '121', '', '', '2');
INSERT INTO `rights` VALUES ('123', '删除分类', '121', '', '', '2');
INSERT INTO `rights` VALUES ('125', '用户管理', '0', '', '', '0');
INSERT INTO `rights` VALUES ('129', '添加角色', '111', '', '', '2');
INSERT INTO `rights` VALUES ('130', '删除角色', '111', '', '', '2');
INSERT INTO `rights` VALUES ('131', '添加用户', '110', '', '', '2');
INSERT INTO `rights` VALUES ('132', '删除用户', '110', '', '', '2');
INSERT INTO `rights` VALUES ('133', '更新用户', '110', '', '', '2');
INSERT INTO `rights` VALUES ('134', '角色授权', '111', '', '', '2');
INSERT INTO `rights` VALUES ('135', '取消角色授权', '111', '', '', '2');
INSERT INTO `rights` VALUES ('136', '获取用户详情', '110', '', '', '2');
INSERT INTO `rights` VALUES ('137', '分配用户角色', '110', '', '', '2');
INSERT INTO `rights` VALUES ('138', '获取角色列表', '111', '', '', '2');
INSERT INTO `rights` VALUES ('139', '获取角色详情', '111', '', '', '2');
INSERT INTO `rights` VALUES ('140', '更新角色信息', '111', '', '', '2');
INSERT INTO `rights` VALUES ('141', '更新角色权限', '111', '', '', '2');
INSERT INTO `rights` VALUES ('142', '获取参数列表', '115', '', '', '2');
INSERT INTO `rights` VALUES ('143', '创建商品参数', '115', '', '', '2');
INSERT INTO `rights` VALUES ('144', '删除商品参数', '115', '', '', '2');
INSERT INTO `rights` VALUES ('145', '数据统计', '0', '', '', '0');
INSERT INTO `rights` VALUES ('146', '数据报表', '145', '', '', '1');
INSERT INTO `rights` VALUES ('147', '查看权限', '112', '', '', '2');
INSERT INTO `rights` VALUES ('148', '查看数据', '146', '', '', '2');
INSERT INTO `rights` VALUES ('149', '获取分类详情', '121', '', '', '2');
INSERT INTO `rights` VALUES ('150', '更新商品图片', '104', '', '', '2');
INSERT INTO `rights` VALUES ('151', '更新商品属性', '104', '', '', '2');
INSERT INTO `rights` VALUES ('152', '更新商品状态', '104', '', '', '2');
INSERT INTO `rights` VALUES ('153', '获取商品详情', '104', '', '', '2');
INSERT INTO `rights` VALUES ('154', '订单更新', '107', '', '', '2');
INSERT INTO `rights` VALUES ('155', '获取订单详情', '107', '', '', '2');
INSERT INTO `rights` VALUES ('156', '分类参数添加', '101', '', '', '2');
INSERT INTO `rights` VALUES ('157', '分类参数删除', '101', '', '', '2');
INSERT INTO `rights` VALUES ('158', '分类参数详情', '101', '', '', '2');
INSERT INTO `rights` VALUES ('159', '设置管理状态', '110', '', '', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` smallint(6) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `rights_ids` varchar(512) NOT NULL DEFAULT '' COMMENT '权限ids,1,2,5',
  `rights_controllers` text COMMENT '控制器-操作,控制器-操作,控制器-操作',
  `role_desc` text,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('30', '主管', '101,0,104,116,115,142,143,144,121,122,123,149,102,107,109,103,111,129,130,134,135,138,139,140,141,112,147,125,110,131,132,133,136,137,145,146,148', 'Goods-index,Goods-tianjia,Category-index,Order-showlist,Brand-index', '技术负责人');
INSERT INTO `role` VALUES ('31', '测试角色', '101,0,104,105,116,117,115,142,143,144,121,122,123,149,103,111,129,134,138,112,147', 'Goods-showlist,Goods-tianjia,Category-showlist,Order-showlist,Order-dayin,Order-tianjia', '测试角色描述');
INSERT INTO `role` VALUES ('34', '测试角色2', '0,105,116,142,143,122', null, '测试描述12');
INSERT INTO `role` VALUES ('39', '大发送到', '101,0,104,105,116', null, '阿斯蒂芬');
INSERT INTO `role` VALUES ('40', 'test', '102,0,107,109,154,155,145,146,148', null, 'test');
INSERT INTO `role` VALUES ('41', 'dsdf', '', null, 'sf ');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` int(4) DEFAULT NULL,
  `mg_state` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '12062481@qq.com', '15928985518', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('2', 'test1', 'test1', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('3', 'test2', 'test2', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('4', 'test3', 'test3', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('5', 'test4', 'test4', '12062481@qq.com', '15928985512', '1', '0', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('6', 'test5', 'test5', '12062481@qq.com', '15928985512', '1', '0', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('7', 'test6', 'test6', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('8', 'test7', 'test7', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('9', 'test8', 'test8', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('10', 'test9', 'test9', '12062481@qq.com', '15928985512', '1', '0', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('11', 'test10', 'test10', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('12', 'test11', 'test11', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('13', 'test12', 'test12', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('14', 'test13', 'test13', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('15', 'test14', 'test14', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('16', 'test15', 'test15', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('17', 'test16', 'test16', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('18', 'test17', 'test17', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('19', 'test18', 'test18', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('20', 'test19', 'test19', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('21', 'test20', 'test20', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('22', 'test21', 'test21', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('23', 'test22', 'test22', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('24', 'test23', 'test23', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('25', 'test24', 'test24', '12062481@qq.com', '15928985512', '1', '1', '2020-05-03 23:04:56', '2020-05-03 23:04:59');
INSERT INTO `user` VALUES ('29', 'hghjghj', 'ghjghjghj', 'jkhjk@mm.com', '17396256293', null, null, null, null);
