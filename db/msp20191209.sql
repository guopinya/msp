/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : 127.0.0.1:3306
Source Database       : msp

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2019-12-09 17:11:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for career
-- ----------------------------
DROP TABLE IF EXISTS `career`;
CREATE TABLE `career` (
  `career_id` varchar(20) NOT NULL,
  `career_name` varchar(20) DEFAULT NULL,
  `career_cert_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`career_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位表';

-- ----------------------------
-- Records of career
-- ----------------------------
INSERT INTO `career` VALUES ('1201804258060922881', '仨', '1');

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `file_id` varchar(32) NOT NULL COMMENT '文件ID',
  `file_name` varchar(20) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(20) DEFAULT NULL COMMENT '文件路径',
  `file_suffix` varchar(10) DEFAULT NULL COMMENT '文件后缀',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件记录表';

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('4f2f8d09b5a7082a503344c1fa1c846c', '1197802424925233153', '/file/20191122/', '.jpg');
INSERT INTO `file_info` VALUES ('5a5bc9f340564104e4764181e0477a41', '1198854063887020033', '/file/20191125/', '.jpg');

-- ----------------------------
-- Table structure for msg_search
-- ----------------------------
DROP TABLE IF EXISTS `msg_search`;
CREATE TABLE `msg_search` (
  `id` varchar(32) NOT NULL,
  `area_id` varchar(32) DEFAULT '0' COMMENT '归属地',
  `name` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `type` varchar(10) DEFAULT NULL COMMENT '搜索类型：所有/项目/商品/艺人/门店',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 搜索表 ';

-- ----------------------------
-- Records of msg_search
-- ----------------------------

-- ----------------------------
-- Table structure for msp_attr
-- ----------------------------
DROP TABLE IF EXISTS `msp_attr`;
CREATE TABLE `msp_attr` (
  `id` varchar(32) NOT NULL,
  `area_id` varchar(32) DEFAULT '0' COMMENT '归属地',
  `parent_id` varchar(32) NOT NULL DEFAULT '0',
  `attr_name` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `attr_code` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `image` varchar(255) DEFAULT NULL,
  `attr_desc` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `type` varchar(10) DEFAULT NULL COMMENT '类型（商品/项目）',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 属性表 ';

-- ----------------------------
-- Records of msp_attr
-- ----------------------------
INSERT INTO `msp_attr` VALUES ('1198904105196924930', '0', '0', '一级分类A', 'ds', '/file/20191122/1197802424925233153.jpg', null, 'project', '1', '0', null, null, null, null, null, null);
INSERT INTO `msp_attr` VALUES ('1198908384393637889', '0', '0', '一级分类B', '2121', '/file/20191122/1197802424925233153.jpg', null, 'project', '2', '0', null, null, null, null, null, null);
INSERT INTO `msp_attr` VALUES ('1201336427422732289', '0', '1198904105196924930', 'A-1', null, '/file/20191122/1197802424925233153.jpg', null, 'project', '1', '0', null, null, null, null, null, null);
INSERT INTO `msp_attr` VALUES ('1201337842706690049', '0', '1198908384393637889', 'B-1', null, null, null, 'project', '2', '0', null, null, null, null, null, null);
INSERT INTO `msp_attr` VALUES ('1201338169233256449', '0', '1198908384393637889', 'B-2', null, null, null, 'project', '1', '0', null, null, null, null, null, null);
INSERT INTO `msp_attr` VALUES ('1201338393083260929', '0', '1201338169233256449', 'B-2-1', null, null, null, 'project', '3', '0', null, null, null, null, null, null);
INSERT INTO `msp_attr` VALUES ('1201342510803464194', '0', '1201336427422732289', 'A-1-1', null, '/file/20191125/1198854063887020033.jpg', null, 'project', '1', '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for msp_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `msp_evaluate`;
CREATE TABLE `msp_evaluate` (
  `id` varchar(20) NOT NULL,
  `order_id` varchar(20) DEFAULT NULL,
  `project_id` varchar(20) DEFAULT NULL,
  `shop_id` varchar(20) DEFAULT NULL,
  `servicer_id` varchar(20) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL COMMENT '评分',
  `detail` varchar(255) DEFAULT NULL COMMENT '评价',
  `banner` varchar(255) DEFAULT NULL COMMENT '图片',
  `state` varchar(255) DEFAULT NULL COMMENT '状态：审核通过，审核拒绝',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 评价表 ';

-- ----------------------------
-- Records of msp_evaluate
-- ----------------------------
INSERT INTO `msp_evaluate` VALUES ('32424', '213123123', '1201346450630684673', '1199637443389988865', '1202111739337224194', '1202071801405202434', null, null, null, 'no', null, '0', '2019-12-09 16:53:17', null, null, '2019-12-09 16:53:21', null, null);

-- ----------------------------
-- Table structure for msp_order
-- ----------------------------
DROP TABLE IF EXISTS `msp_order`;
CREATE TABLE `msp_order` (
  `id` varchar(32) NOT NULL,
  `order_no` varchar(32) NOT NULL COMMENT '单号',
  `user_id` varchar(32) DEFAULT NULL,
  `project_id` varchar(32) DEFAULT NULL COMMENT '项目',
  `area_id` varchar(20) DEFAULT NULL,
  `shop_id` varchar(32) DEFAULT NULL COMMENT '项目',
  `servicer_id` varchar(32) DEFAULT NULL COMMENT '项目',
  `state` varchar(32) DEFAULT '0' COMMENT '状态',
  `origin` varchar(32) DEFAULT '0' COMMENT '下单方式',
  `pre_time` datetime DEFAULT NULL COMMENT '预约时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `service_time` datetime DEFAULT NULL COMMENT '服务时间',
  `succ_time` datetime DEFAULT NULL COMMENT '完成时间',
  `eva_time` datetime DEFAULT NULL COMMENT '评价时间',
  `remark` varchar(255) DEFAULT NULL,
  `base_amount` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付价',
  `credit_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵扣',
  `pay_type` varchar(10) DEFAULT NULL COMMENT '支付方式（微信）',
  `order_project_image` varchar(255) DEFAULT NULL,
  `order_project_name` varchar(255) DEFAULT NULL COMMENT '下单时项目',
  `order_project_tag` varchar(255) DEFAULT NULL COMMENT '下单时项目',
  `order_project_price` decimal(10,0) DEFAULT NULL COMMENT '下单时项目',
  `order_shop_name` varchar(32) DEFAULT NULL COMMENT '项目',
  `order_shop_addr` varchar(32) DEFAULT NULL COMMENT '项目',
  `order_shop_phone` varchar(32) DEFAULT NULL COMMENT '项目',
  `order_servicer_name` varchar(32) DEFAULT NULL COMMENT '艺人名',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 项目 ';

-- ----------------------------
-- Records of msp_order
-- ----------------------------
INSERT INTO `msp_order` VALUES ('213123123', 'MSP2342342342', '1202111739337224134', '1201346450630684673', '310105', '1199637443389988865', '1202111739337224194', '0', '0', '2019-12-09 16:53:32', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2019-12-09 16:53:32', null, null, '2019-12-09 16:53:32', null, null);

-- ----------------------------
-- Table structure for msp_project
-- ----------------------------
DROP TABLE IF EXISTS `msp_project`;
CREATE TABLE `msp_project` (
  `id` varchar(32) NOT NULL,
  `area_id` varchar(32) DEFAULT '0' COMMENT '归属地',
  `attr_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '分类',
  `project_name` varchar(64) DEFAULT NULL COMMENT '项目名',
  `no` varchar(64) DEFAULT NULL COMMENT '编号',
  `image` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `tag` varchar(64) DEFAULT NULL COMMENT '标签',
  `base_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `price` decimal(10,2) DEFAULT NULL COMMENT '售价',
  `detail` text COMMENT '详情',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 项目 ';

-- ----------------------------
-- Records of msp_project
-- ----------------------------
INSERT INTO `msp_project` VALUES ('1201346450630684673', '0', '1201336427422732289', '项目1', 'df', '/file/20191122/1197802424925233153.jpg', null, 'tag1 tag2 tag3', '34.00', '34.00', '<p>&nbsp;43&nbsp;</p>', '43', '0', null, null, null, null, null, null);
INSERT INTO `msp_project` VALUES ('1201347140052627458', '0', '1201338169233256449', 'sa', 'd', '/file/20191125/1198854063887020033.jpg', null, 'sa', '2.00', '24334.00', '<p>ew</p>', '2', '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for msp_shop
-- ----------------------------
DROP TABLE IF EXISTS `msp_shop`;
CREATE TABLE `msp_shop` (
  `id` varchar(32) NOT NULL,
  `parent_id0` varchar(32) NOT NULL DEFAULT '0' COMMENT '区代理',
  `parent_id1` varchar(32) NOT NULL DEFAULT '0' COMMENT '督导',
  `parent_id2` varchar(32) NOT NULL DEFAULT '0' COMMENT '加盟商',
  `parent_id3` varchar(32) NOT NULL DEFAULT '0' COMMENT '店长',
  `area_id` varchar(32) DEFAULT '0' COMMENT '归属地',
  `shop_name` varchar(64) DEFAULT NULL COMMENT '店名',
  `no` varchar(64) DEFAULT NULL COMMENT '编号',
  `image` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `tag` varchar(64) DEFAULT NULL COMMENT '标签',
  `state` varchar(10) DEFAULT NULL COMMENT '营业状态',
  `state_flag` varchar(10) DEFAULT NULL COMMENT '营业状态改变方式（自动/手动）',
  `stime` varchar(32) DEFAULT NULL COMMENT '营业开始时间',
  `etime` varchar(32) DEFAULT NULL COMMENT '营业结束时间',
  `addr` varchar(64) DEFAULT NULL COMMENT '店地址',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `is_hot` varchar(64) DEFAULT NULL COMMENT '是否热门',
  `is_recommd` varchar(10) DEFAULT NULL COMMENT '是否推荐',
  `detail` text,
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 店铺 ';

-- ----------------------------
-- Records of msp_shop
-- ----------------------------
INSERT INTO `msp_shop` VALUES ('1199637443389988865', '0', '0', '1202108062128001025', '0', '310105', 'sadf', 'as a', '/file/20191122/1197802424925233153.jpg', null, 'asd a', 'open', 'auto', '00:05:00', '00:03:00', 'as ', 'sda', 'yes', 'yes', '<p>as</p>', '1', '0', null, null, null, null, null, null);
INSERT INTO `msp_shop` VALUES ('1201331242931605505', '0', '0', '0', '0', '0', 's', 'as', null, null, 'sa', 'off', 'manual', '00:04:05', '00:03:05', 'as', 'sa', 'yes', 'yes', '', '1', '0', null, null, null, null, null, null);
INSERT INTO `msp_shop` VALUES ('1202534903234007042', '0', '0', '1202108062128001025', '0', '310101', '店铺3.0', '30', '/file/20191122/1197802424925233153.jpg', null, '新店 创意', 'open', null, '00:00:00', '00:00:00', '漕河泾开发区', '18137805807', 'yes', 'yes', '<p>水电费</p>', '1', '0', '2019-12-05 18:27:18', null, '1157124509135577090', '2019-12-05 18:27:18', null, '1157124509135577090');
INSERT INTO `msp_shop` VALUES ('1202536144093700097', '0', '0', '1202108062128001025', '0', '310104', '店铺4.0', '40', null, null, '创意 优先', 'open', null, '00:00:00', '00:00:00', '徐家汇', '18137805807', 'yes', 'yes', '<p>&nbsp;先</p>', '2', '0', '2019-12-05 18:32:13', '127.0.0.1', '127.0.0.1', '2019-12-05 18:32:13', '127.0.0.1', '1157124509135577090');

-- ----------------------------
-- Table structure for system_area
-- ----------------------------
DROP TABLE IF EXISTS `system_area`;
CREATE TABLE `system_area` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT '0',
  `area_name` varchar(64) DEFAULT NULL,
  `area_code` varchar(128) DEFAULT NULL,
  `abbre` varchar(64) DEFAULT NULL,
  `level` varchar(2) DEFAULT NULL,
  `citycode` varchar(16) DEFAULT NULL,
  `zipcode` varchar(16) DEFAULT NULL,
  `baiducode` varchar(255) DEFAULT NULL,
  `lng` varchar(128) DEFAULT NULL,
  `lat` varchar(128) DEFAULT NULL,
  `is_hot` varchar(2) DEFAULT NULL,
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 地区表 ';

-- ----------------------------
-- Records of system_area
-- ----------------------------
INSERT INTO `system_area` VALUES ('100000', '0', '中国', 'China', '中国', '0', '', '', '', '116.3683244', '39.915085', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310000', '100000', '上海', 'Shanghai', '上海', '1', '310100', '', '', '121.472644', '31.231706', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310100', '310000', '上海市', 'Shanghai', '上海', '2', '021', '200000', '289', '121.472644', '31.231706', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('3101001', '310000', '上海市1', 'Shanghai', '上海', '2', '021', '200000', '289', '121.472644', '31.231706', '0', '0', '0', '2018-06-04 14:19:03', '', '', '2018-06-04 14:19:03', '', '');
INSERT INTO `system_area` VALUES ('310101', '310100', '黄浦区', 'Huangpu', '黄浦', '3', '021', '200001', '', '121.49295', '31.22337', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310104', '310100', '徐汇区', 'Xuhui', '徐汇', '3', '021', '200030', '', '121.43676', '31.18831', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310105', '310100', '长宁区', 'Changning', '长宁', '3', '021', '200050', '', '121.42462', '31.22036', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310106', '310100', '静安区', 'Jing\'an', '静安', '3', '021', '200040', '', '121.4444', '31.22884', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310107', '310100', '普陀区', 'Putuo', '普陀', '3', '021', '200333', '', '121.39703', '31.24951', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310108', '310100', '闸北区', 'Zhabei', '闸北', '3', '021', '200070', '', '121.44636', '31.28075', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310109', '310100', '虹口区', 'Hongkou', '虹口', '3', '021', '200086', '', '121.48162', '31.27788', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310110', '310100', '杨浦区', 'Yangpu', '杨浦', '3', '021', '200082', '', '121.526', '31.2595', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310112', '310100', '闵行区', 'Minhang', '闵行', '3', '021', '201100', '', '121.38162', '31.11246', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310113', '310100', '宝山区', 'Baoshan', '宝山', '3', '021', '201900', '', '121.4891', '31.4045', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310114', '310100', '嘉定区', 'Jiading', '嘉定', '3', '021', '201800', '', '121.2655', '31.37473', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310115', '310100', '浦东新区', 'Pudong', '浦东', '3', '021', '200135', '', '121.5447', '31.22249', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310116', '310100', '金山区', 'Jinshan', '金山', '3', '021', '200540', '', '121.34164', '30.74163', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310117', '310100', '松江区', 'Songjiang', '松江', '3', '021', '201600', '', '121.22879', '31.03222', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310118', '310100', '青浦区', 'Qingpu', '青浦', '3', '021', '201700', '', '121.12417', '31.14974', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310120', '310100', '奉贤区', 'Fengxian', '奉贤', '3', '021', '201400', '', '121.47412', '30.9179', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);
INSERT INTO `system_area` VALUES ('310230', '310100', '崇明县', 'Chongming', '崇明', '3', '021', '202150', '', '121.39758', '31.62278', '0', '0', '0', '2018-06-04 14:19:03', null, null, '2018-06-04 14:19:03', null, null);

-- ----------------------------
-- Table structure for system_banner
-- ----------------------------
DROP TABLE IF EXISTS `system_banner`;
CREATE TABLE `system_banner` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `area_id` varchar(32) DEFAULT '0' COMMENT '归属地',
  `image` varchar(50) DEFAULT NULL COMMENT '图片',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `obj_id` varchar(20) DEFAULT NULL COMMENT '对象ID',
  `link` varchar(100) DEFAULT NULL COMMENT '链接',
  `rich_text` mediumtext COMMENT '富文本',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 轮播图表 ';

-- ----------------------------
-- Records of system_banner
-- ----------------------------
INSERT INTO `system_banner` VALUES ('1197802425537601538', '310104', '/file/20191122/1197802424925233153.jpg', '仨', 'link', null, 'https://blog.csdn.net/weixin_42500670/article/details/81629677', '', '1', '0', null, null, null, null, null, null);
INSERT INTO `system_banner` VALUES ('1198854065967394818', '310114', '/file/20191125/1198854063887020033.jpg', 'asf', 'none', null, '', '', '1', '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for system_feedback
-- ----------------------------
DROP TABLE IF EXISTS `system_feedback`;
CREATE TABLE `system_feedback` (
  `id` varchar(20) NOT NULL COMMENT '唯一标识',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `content` varchar(500) DEFAULT NULL COMMENT '反馈内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='意见反馈表';

-- ----------------------------
-- Records of system_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` varchar(20) NOT NULL COMMENT '菜单ID',
  `parent_id` varchar(20) DEFAULT '0' COMMENT '父菜单ID',
  `type` varchar(1) DEFAULT '0' COMMENT '菜单类型',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `title` varchar(30) DEFAULT NULL COMMENT '菜单标题',
  `icon` varchar(40) DEFAULT NULL COMMENT '菜单图标',
  `jump` varchar(50) DEFAULT NULL COMMENT '路由地址',
  `sort_number` int(5) DEFAULT NULL COMMENT '排序数字',
  `is_last_level` tinyint(1) DEFAULT '1' COMMENT '是否最后一级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1183586993354637314', '0', '0', 'user', '用户管理', 'layui-icon-user', '', '1', '0');
INSERT INTO `system_menu` VALUES ('1183587597435076609', '1183586993354637314', '0', 'front', '前端用户', '', '', '2', '1');
INSERT INTO `system_menu` VALUES ('1183587657858220033', '1183586993354637314', '0', 'back', '后端用户', '', '', '4', '1');
INSERT INTO `system_menu` VALUES ('1183587657858220034', '1183586993354637314', '0', 'super', '平台用户', '', '', '4', '1');
INSERT INTO `system_menu` VALUES ('1183589579528929281', '1183586993354637314', '0', 'role', '角色管理', '', '', '3', '1');
INSERT INTO `system_menu` VALUES ('1183654537281220609', '0', '0', 'system', '系统管理', 'layui-icon-set', '', '4', '0');
INSERT INTO `system_menu` VALUES ('1183654641845219329', '1183654537281220609', '0', 'menu', '菜单管理', '', '', '1', '1');
INSERT INTO `system_menu` VALUES ('1186593012678656001', '1183586993354637314', '0', 'career', '身份管理', '', '', '1', '0');
INSERT INTO `system_menu` VALUES ('1186848803050590209', '1183654537281220609', '0', 'banner', '轮播图', '', '', '2', '1');
INSERT INTO `system_menu` VALUES ('1187303854462316545', '1183654537281220609', '0', 'feedback', '意见反馈', '', '', '4', '1');
INSERT INTO `system_menu` VALUES ('1187663821719470082', '1186593012678656001', '1', 'modify', '修改', '', '', '1', '1');
INSERT INTO `system_menu` VALUES ('1198894492611366913', '1199158239552696322', '0', 'attr', '分类管理', '', '', '5', '1');
INSERT INTO `system_menu` VALUES ('1199158239552696322', '0', '0', 'msp', '项目管理', '', '', '3', '0');
INSERT INTO `system_menu` VALUES ('1199158313879957506', '1199158239552696322', '0', 'project', '项目管理', '', '', '1', '1');
INSERT INTO `system_menu` VALUES ('1199158366434586625', '0', '0', 'msp', '店铺管理', '', '', '3', '0');
INSERT INTO `system_menu` VALUES ('1199158439872655362', '1199158366434586625', '0', 'shop', '店铺管理', '', '', '1', '1');
INSERT INTO `system_menu` VALUES ('1199158506474008578', '1199158239552696322', '0', 'order', '订单管理', '', '', '2', '1');
INSERT INTO `system_menu` VALUES ('1201347727066443777', '1183654537281220609', '0', 'area', '地区管理', '', '', '5', '1');
INSERT INTO `system_menu` VALUES ('1203875689666043906', '1199158239552696322', '0', 'evaluate', '评价', '', '', '4', '1');

-- ----------------------------
-- Table structure for system_param
-- ----------------------------
DROP TABLE IF EXISTS `system_param`;
CREATE TABLE `system_param` (
  `id` varchar(32) NOT NULL,
  `area_id` varchar(32) DEFAULT '0' COMMENT '归属地',
  `parent_id` varchar(32) NOT NULL,
  `key` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `value` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `desc` varchar(64) DEFAULT NULL COMMENT '搜索词',
  `type` varchar(10) DEFAULT NULL COMMENT '参数类型',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 参数表 ';

-- ----------------------------
-- Records of system_param
-- ----------------------------

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` varchar(20) NOT NULL COMMENT '角色ID',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色备注',
  `hold_permits` varchar(1000) DEFAULT NULL COMMENT '持有权限',
  `hold_menu_ids` varchar(1500) DEFAULT NULL COMMENT '持有菜单ID',
  `is_set` varchar(255) DEFAULT NULL COMMENT '是否是固定的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1185093768851587073', '管理员', '拥有全部权限', '1187663821719470082|', '1183586993354637314|1186593012678656001|1187663821719470082|1183587597435076609|1183589579528929281|1183587657858220033|1183587657858220034|1199158239552696322|1199158313879957506|1199158506474008578|1203875689666043906|1198894492611366913|1199158366434586625|1199158439872655362|1183654537281220609|1183654641845219329|1186848803050590209|1187303854462316545|1201347727066443777|', 'no');
INSERT INTO `system_role` VALUES ('1187637908193771521', '区代理', '固定身份', '', '1183586993354637314|1186593012678656001|1187663821719470082|1183587597435076609|1183589579528929281|1183587657858220033|1199158239552696322|1199158313879957506|1199158506474008578|1198894492611366913|1199158366434586625|1199158439872655362|1183654537281220609|1183654641845219329|1186848803050590209|1187303854462316545|1201347727066443777|1183587657858220034|', 'yes');
INSERT INTO `system_role` VALUES ('1187637908193771522', '督导', '固定身份', '', '1183586993354637314|1186593012678656001|1187663821719470082|1183587597435076609|1183589579528929281|1183587657858220033|1199158239552696322|1199158313879957506|1199158506474008578|1198894492611366913|1199158366434586625|1199158439872655362|1183654537281220609|1183654641845219329|1186848803050590209|1187303854462316545|1201347727066443777|1183587657858220034|', 'yes');
INSERT INTO `system_role` VALUES ('1187637908193771523', '加盟商', '固定身份', '', '1183586993354637314|1186593012678656001|1187663821719470082|1183587597435076609|1183589579528929281|1183587657858220033|1199158239552696322|1199158313879957506|1199158506474008578|1198894492611366913|1199158366434586625|1199158439872655362|1183654537281220609|1183654641845219329|1186848803050590209|1187303854462316545|1201347727066443777|1183587657858220034|', 'yes');
INSERT INTO `system_role` VALUES ('1187637908193771524', '店长', '固定身份', '', '1183586993354637314|1186593012678656001|1187663821719470082|1183587597435076609|1183589579528929281|1183587657858220033|1199158239552696322|1199158313879957506|1199158506474008578|1198894492611366913|1199158366434586625|1199158439872655362|1183654537281220609|1183654641845219329|1186848803050590209|1187303854462316545|1201347727066443777|1183587657858220034|', 'yes');
INSERT INTO `system_role` VALUES ('1202535130905022465', '艺人', '手艺人', null, '1183586993354637314|1186593012678656001|1187663821719470082|1183587597435076609|1183589579528929281|1183587657858220033|1183587657858220034|1199158239552696322|1199158313879957506|1199158506474008578|1198894492611366913|', 'no');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(20) NOT NULL COMMENT '用户ID',
  `role_id` varchar(20) DEFAULT NULL COMMENT '角色ID',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `status` varchar(10) DEFAULT NULL COMMENT '用户状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_pid0` varchar(20) DEFAULT '0',
  `user_pid1` varchar(20) DEFAULT '0',
  `user_pid2` varchar(20) DEFAULT '0',
  `shop_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1157124509135577090', '1185093768851587073', 'admin', '123456', '/file/20191122/1197802424925233153.jpg', '管理员', '13511111111', 'freeze', '2019-08-02 11:02:36', '2019-11-21 18:08:01', '0', '0', '0', null);
INSERT INTO `system_user` VALUES ('1202070359684530177', '1187637908193771521', 'area', '123456', '/user/1141245074473861122.png', 'area1', '18137805807', 'normal', '2019-12-04 11:47:05', null, '0', '0', '0', null);
INSERT INTO `system_user` VALUES ('1202071801405202434', '1187637908193771522', 'du', '123456', '/user/1141245074473861122.png', 'DU', '18137805807', 'normal', '2019-12-04 11:47:05', null, '0', '0', '0', null);
INSERT INTO `system_user` VALUES ('1202108062128001025', '1187637908193771523', 'jia', '123456', '/user/1141245074473861122.png', 'jia', '18137805807', 'normal', '2019-12-04 14:11:11', null, '1202070359684530177', '1202071801405202434', '', null);
INSERT INTO `system_user` VALUES ('1202111739337224194', '1187637908193771524', 'dian', '123456', '/user/1141245074473861122.png', 'dian', '18137805806', 'normal', '2019-12-04 14:25:47', null, '请选择', '请选择', '0', '1199637443389988865');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `user_type` varchar(1) DEFAULT '0' COMMENT '用户类型',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `sex` bit(1) DEFAULT NULL,
  `signature` varchar(20) DEFAULT NULL COMMENT '签名',
  `domain` varchar(10) DEFAULT NULL COMMENT '据点',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `recent_login` datetime DEFAULT NULL COMMENT '最近登录',
  `avatar_path` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `back_ground_path` varchar(100) DEFAULT '/file/resources/user/background.png',
  `user_level` int(2) DEFAULT NULL COMMENT '用户等级',
  `vip_level` int(2) DEFAULT NULL COMMENT 'VIP等级',
  `status` varchar(15) DEFAULT 'normal' COMMENT '状态',
  `good_sum` mediumtext,
  `ry_token` varchar(120) DEFAULT NULL COMMENT '融云令牌',
  `user_pid0` varchar(20) CHARACTER SET utf8 DEFAULT '0',
  `user_pid1` varchar(20) CHARACTER SET utf8 DEFAULT '0',
  `shop_id` varchar(20) CHARACTER SET utf8 DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1202071801405202434', '0', '18137805807', '18137805807', null, '', null, '2019-12-04 11:47:05', null, '/user/1141245074473861122.png', '/file/resources/user/background.png', '0', '1', 'normal', null, null, '0', '1202111739337224194', '1201331242931605505');
INSERT INTO `user` VALUES ('1202111739337224194', '0', '18137805806', '18137805806', null, '123456', null, '2019-12-04 14:25:47', null, '/user/1141245074473861122.png', '/file/resources/user/background.png', null, null, 'normal', null, null, '0', '1202071801405202434', '1199637443389988865');

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `auth_type` varchar(10) NOT NULL COMMENT '授权类型',
  `auth_secret` varchar(32) DEFAULT NULL COMMENT '授权密钥',
  PRIMARY KEY (`user_id`,`auth_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户授权表';

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('1202071801405202434', 'password', '89792a37233e3116d2c373cfaf34ee33');
INSERT INTO `user_auth` VALUES ('1202071801405202434', 'plaintext', '');
INSERT INTO `user_auth` VALUES ('1202111739337224194', 'password', '89792a37233e3116d2c373cfaf34ee33');
INSERT INTO `user_auth` VALUES ('1202111739337224194', 'plaintext', '');

-- ----------------------------
-- Table structure for user_capital
-- ----------------------------
DROP TABLE IF EXISTS `user_capital`;
CREATE TABLE `user_capital` (
  `id` varchar(20) NOT NULL COMMENT '资金主键',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户主键',
  `capital_title` varchar(32) DEFAULT NULL COMMENT '资金标题',
  `capital_type` varchar(1) DEFAULT NULL COMMENT '变更类型（0：系统增加，1系统扣除，2客户端充值，3客户端提现，4客户端消费,5退款）',
  `capital_state` varchar(32) DEFAULT NULL COMMENT '支付状态(0成功-1错误-2用户取消,3未支付)',
  `capital_total` decimal(11,2) DEFAULT NULL COMMENT '资金总额',
  `capital_price` decimal(11,2) DEFAULT NULL COMMENT '操作金额',
  `capital_note` varchar(128) DEFAULT NULL COMMENT '资金备注',
  `payment_order` varchar(64) DEFAULT NULL COMMENT '支付订单',
  `merchants_order` varchar(32) DEFAULT NULL COMMENT '商户订单，和order中的订单号一样',
  `capital_back_order` varchar(64) DEFAULT NULL COMMENT '退款订单号',
  `capital_pay_type` varchar(1) DEFAULT NULL COMMENT '支付方式（0，支付宝，1，微信，2银行卡，3其他）',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户资金';

-- ----------------------------
-- Records of user_capital
-- ----------------------------

-- ----------------------------
-- Table structure for user_career
-- ----------------------------
DROP TABLE IF EXISTS `user_career`;
CREATE TABLE `user_career` (
  `user_id` varchar(20) NOT NULL,
  `career_first_id` varchar(20) DEFAULT NULL,
  `career_second_id` varchar(20) DEFAULT NULL,
  `career_third_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_career
-- ----------------------------

-- ----------------------------
-- Table structure for user_credit
-- ----------------------------
DROP TABLE IF EXISTS `user_credit`;
CREATE TABLE `user_credit` (
  `id` varchar(20) NOT NULL COMMENT '积分主键',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户主键',
  `credit_title` varchar(32) DEFAULT NULL COMMENT '积分标题',
  `credit_type` varchar(2) DEFAULT NULL COMMENT '积分类型（注册积分，购物积分,签到积分）',
  `credit_state` varchar(1) DEFAULT NULL COMMENT '积分类型(0:增加、1:减少)',
  `credit_total` int(11) DEFAULT NULL COMMENT '积分总额',
  `credit_price` int(11) DEFAULT NULL COMMENT '积分数值',
  `credit_note` varchar(128) DEFAULT NULL COMMENT '积分备注',
  `sort_number` int(11) DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏0否1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卓米记录';

-- ----------------------------
-- Records of user_credit
-- ----------------------------
