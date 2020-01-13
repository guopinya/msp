-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: msp
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sq_circle`
--

DROP TABLE IF EXISTS `sq_circle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_circle` (
  `id` varchar(20) NOT NULL COMMENT '圈子ID',
  `name` varchar(15) DEFAULT NULL COMMENT '圈子名称',
  `image` varchar(100) DEFAULT NULL COMMENT '圈子图片',
  `depict` varchar(100) DEFAULT NULL COMMENT '圈子描述',
  `user_num` int(11) unsigned DEFAULT NULL COMMENT '用户数量',
  `post_num` int(11) unsigned DEFAULT NULL COMMENT '帖子数量',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_circle_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='圈子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_circle`
--

LOCK TABLES `sq_circle` WRITE;
/*!40000 ALTER TABLE `sq_circle` DISABLE KEYS */;
INSERT INTO `sq_circle` VALUES ('1215199854361264130','测试圈子','/sq/circle/default_small.png,/sq/circle/default_large.png','这是一个测试圈子',0,0,NULL,'0','2020-01-09 17:13:17','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 17:13:17','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_circle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_circle_master`
--

DROP TABLE IF EXISTS `sq_circle_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_circle_master` (
  `id` varchar(20) NOT NULL COMMENT '圈子达人ID',
  `circle_id` varchar(20) DEFAULT NULL COMMENT '圈子ID',
  `master_id` varchar(20) DEFAULT NULL COMMENT '达人ID（用户ID）',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_circle_master_unique` (`circle_id`,`master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='圈子达人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_circle_master`
--

LOCK TABLES `sq_circle_master` WRITE;
/*!40000 ALTER TABLE `sq_circle_master` DISABLE KEYS */;
INSERT INTO `sq_circle_master` VALUES ('1215477119808655361','1215199854361264130','1157124509135577090',NULL,'0','2020-01-10 11:35:02','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 11:35:02','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_circle_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_crowd`
--

DROP TABLE IF EXISTS `sq_crowd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_crowd` (
  `id` varchar(20) NOT NULL COMMENT '关注ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `title` varchar(20) NOT NULL COMMENT '众筹标题',
  `image` varchar(500) NOT NULL COMMENT '众筹图片',
  `content` varchar(1300) NOT NULL COMMENT '众筹内容',
  `start_time` varchar(13) NOT NULL COMMENT '开始时间（13位时间戳）',
  `close_time` varchar(13) NOT NULL COMMENT '关闭时间（13位时间戳）',
  `joined_num` int(11) unsigned NOT NULL COMMENT '已参与人数',
  `preset_num` int(11) unsigned NOT NULL COMMENT '预设参与人数',
  `total_amount` int(11) unsigned NOT NULL COMMENT '众筹金额',
  `single_amount` int(11) unsigned NOT NULL COMMENT '单份金额',
  `is_carousel` tinyint(1) unsigned DEFAULT NULL COMMENT '是否轮播',
  `is_recommend` tinyint(1) unsigned DEFAULT NULL COMMENT '是否推荐',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_crowd_title_unique` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='众筹表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_crowd`
--

LOCK TABLES `sq_crowd` WRITE;
/*!40000 ALTER TABLE `sq_crowd` DISABLE KEYS */;
INSERT INTO `sq_crowd` VALUES ('1215542718496714754','1157124509135577090','测试众筹','/sq/crowd/default.png,/sq/crowd/default.png','这是一个测试众筹','1578642729233','1579506713000',2,10,400000,40000,1,0,769278895,'0','2020-01-10 15:55:42','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 17:13:08','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_crowd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_crowd_master`
--

DROP TABLE IF EXISTS `sq_crowd_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_crowd_master` (
  `id` varchar(20) NOT NULL COMMENT '关注ID',
  `crowd_id` varchar(20) NOT NULL COMMENT '众筹ID',
  `master_id` varchar(20) NOT NULL COMMENT '达人ID（用户ID）',
  `full_name` varchar(20) NOT NULL COMMENT '收货姓名',
  `cellphone` varchar(11) NOT NULL COMMENT '收货手机号',
  `quantity` int(11) unsigned NOT NULL COMMENT '购买数量',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_crowd_master_unique` (`crowd_id`,`master_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='众筹达人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_crowd_master`
--

LOCK TABLES `sq_crowd_master` WRITE;
/*!40000 ALTER TABLE `sq_crowd_master` DISABLE KEYS */;
INSERT INTO `sq_crowd_master` VALUES ('1215562203366645762','1215542718496714754','1157124509135577090','测试姓名','13500000000',2,NULL,'0','2020-01-10 17:13:08','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 17:13:08','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_crowd_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_find`
--

DROP TABLE IF EXISTS `sq_find`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_find` (
  `id` varchar(20) NOT NULL COMMENT '话题ID',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `type` varchar(1) DEFAULT NULL COMMENT '搜索类型 0全部 1帖子 2圈子 3用户',
  `keyword` varchar(10) DEFAULT NULL COMMENT '搜索关键字',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_find_keyword_unique` (`type`,`user_id`,`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='搜索表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_find`
--

LOCK TABLES `sq_find` WRITE;
/*!40000 ALTER TABLE `sq_find` DISABLE KEYS */;
INSERT INTO `sq_find` VALUES ('1215520331407544321','1157124509135577090','0','测试',NULL,'0','2020-01-10 14:26:45','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 14:26:55','0:0:0:0:0:0:0:1','1157124509135577090'),('1215520610035159042','1157124509135577090','0','测试1',NULL,'0','2020-01-10 14:27:51','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 14:27:51','0:0:0:0:0:0:0:1','1157124509135577090'),('1215520653236490242','1157124509135577090','0','测试2',NULL,'0','2020-01-10 14:28:02','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 14:28:02','0:0:0:0:0:0:0:1','1157124509135577090'),('1215520782832095234','1202111739337224194','0','测试',NULL,'0','2020-01-10 14:28:33','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 14:28:45','0:0:0:0:0:0:0:1','1202111739337224194'),('1215520795297566722','1202111739337224194','0','测试1',NULL,'0','2020-01-10 14:28:36','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 14:28:36','0:0:0:0:0:0:0:1','1202111739337224194');
/*!40000 ALTER TABLE `sq_find` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_follow`
--

DROP TABLE IF EXISTS `sq_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_follow` (
  `id` varchar(20) NOT NULL COMMENT '关注ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `target_id` varchar(20) NOT NULL COMMENT '关注用户ID',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_follow_user_target_unique` (`user_id`,`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_follow`
--

LOCK TABLES `sq_follow` WRITE;
/*!40000 ALTER TABLE `sq_follow` DISABLE KEYS */;
INSERT INTO `sq_follow` VALUES ('1215287579038949378','1157124509135577090','1202111739337224194',NULL,'0','2020-01-09 23:01:52','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 23:01:52','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_post`
--

DROP TABLE IF EXISTS `sq_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_post` (
  `id` varchar(20) NOT NULL COMMENT '帖子ID',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `topic_id` varchar(20) DEFAULT NULL COMMENT '话题ID',
  `circle_id` varchar(20) DEFAULT NULL COMMENT '圈子ID',
  `title` varchar(30) DEFAULT NULL COMMENT '帖子标题',
  `image` varchar(500) DEFAULT NULL COMMENT '帖子图片',
  `content` varchar(1300) DEFAULT NULL COMMENT '帖子内容',
  `like_num` int(11) unsigned DEFAULT NULL COMMENT '点赞数量',
  `browse_num` int(11) unsigned DEFAULT NULL COMMENT '浏览数量',
  `comment_num` int(11) unsigned DEFAULT NULL COMMENT '评论数量',
  `is_recommend` tinyint(1) unsigned DEFAULT NULL COMMENT '是否推荐',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_post_title_unique` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_post`
--

LOCK TABLES `sq_post` WRITE;
/*!40000 ALTER TABLE `sq_post` DISABLE KEYS */;
INSERT INTO `sq_post` VALUES ('1215221249384337409','1202111739337224194','1215218912276205569','1215199854361264130','测试帖子','/sq/post/default.png,/sq/post/default.png','这是一个测试帖子',0,0,0,1,NULL,'0','2020-01-09 18:38:18','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 18:38:18','0:0:0:0:0:0:0:1','1157124509135577090'),('1215292422688305153','1157124509135577090','1215218912276205569','1215199854361264130','测试帖子2','/sq/post/default.png,/sq/post/default.png','这是一个测试帖子',0,0,0,1,NULL,'0','2020-01-09 23:21:07','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 23:21:07','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_post_master`
--

DROP TABLE IF EXISTS `sq_post_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_post_master` (
  `id` varchar(20) NOT NULL COMMENT '帖子达人ID',
  `post_id` varchar(20) DEFAULT NULL COMMENT '帖子ID',
  `master_id` varchar(20) DEFAULT NULL COMMENT '达人ID（用户ID）',
  `act_type` varchar(1) DEFAULT NULL COMMENT '操作类型 0浏览 1点赞 2评论',
  `content` varchar(100) DEFAULT NULL COMMENT '评论内容',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子达人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_post_master`
--

LOCK TABLES `sq_post_master` WRITE;
/*!40000 ALTER TABLE `sq_post_master` DISABLE KEYS */;
INSERT INTO `sq_post_master` VALUES ('1215300898806288386','1215221249384337409','1157124509135577090','2','测试评论',NULL,'0','2020-01-09 23:54:48','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 23:54:48','0:0:0:0:0:0:0:1','1157124509135577090'),('1215450331552301058','1215221249384337409','1157124509135577090','0',NULL,NULL,'0','2020-01-10 09:48:36','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-10 09:48:36','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_post_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq_topic`
--

DROP TABLE IF EXISTS `sq_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_topic` (
  `id` varchar(20) NOT NULL COMMENT '话题ID',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '父话题ID',
  `name` varchar(15) DEFAULT NULL COMMENT '话题名称',
  `image` varchar(100) DEFAULT NULL COMMENT '话题图片',
  `post_num` int(11) unsigned DEFAULT NULL COMMENT '帖子数量',
  `sort_number` int(11) unsigned DEFAULT NULL COMMENT '显示顺序',
  `is_display` varchar(1) DEFAULT '0' COMMENT ' 是否隐藏 0否 1是 ',
  `init_time` datetime DEFAULT NULL,
  `init_addr` varchar(64) DEFAULT NULL,
  `init_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_addr` varchar(64) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sq_topic_name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq_topic`
--

LOCK TABLES `sq_topic` WRITE;
/*!40000 ALTER TABLE `sq_topic` DISABLE KEYS */;
INSERT INTO `sq_topic` VALUES ('1215218339481079810','0','测试父话题','/sq/topic/default.png',0,NULL,'0','2020-01-09 18:26:44','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 18:26:44','0:0:0:0:0:0:0:1','1157124509135577090'),('1215218912276205569','1215218339481079810','测试话题','/sq/topic/default.png',0,NULL,'0','2020-01-09 18:29:01','0:0:0:0:0:0:0:1','0:0:0:0:0:0:0:1','2020-01-09 18:29:01','0:0:0:0:0:0:0:1','1157124509135577090');
/*!40000 ALTER TABLE `sq_topic` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-13 13:10:48
