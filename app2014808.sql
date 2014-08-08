/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.35 : Database - app
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`app` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `app`;

/*Table structure for table `apk_version` */

DROP TABLE IF EXISTS `apk_version`;

CREATE TABLE `apk_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version_no` varchar(20) NOT NULL COMMENT 'apk 版本号',
  `apk_path` varchar(255) NOT NULL COMMENT 'APK包下载路径',
  `modify_content` varchar(1000) DEFAULT NULL COMMENT '版本修改内容描述',
  `publish_date` date DEFAULT NULL COMMENT '版本发布日期',
  `is_force` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否强制更新 0不强制 1强制 默认0不强制',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='APK 版本管理';

/*Data for the table `apk_version` */

insert  into `apk_version`(`id`,`version_no`,`apk_path`,`modify_content`,`publish_date`,`is_force`,`create_time`,`creater`) values (1,'v1.0.0','12321','123213','2014-05-23',1,'2014-05-23 10:20:55',NULL),(2,'212211','111','111','2014-05-21',0,'2014-05-23 22:54:35',NULL);

/*Table structure for table `app_activate` */

DROP TABLE IF EXISTS `app_activate`;

CREATE TABLE `app_activate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `device_id` varchar(100) NOT NULL COMMENT '激活设备的唯一序号',
  `mobile_type` varchar(50) DEFAULT NULL COMMENT '手机型号',
  `android_version` varchar(50) DEFAULT NULL COMMENT 'android版本',
  `create_time` datetime DEFAULT NULL COMMENT '激活时间',
  `from_name` varchar(50) DEFAULT NULL COMMENT '来源推广商名称',
  `from_no` varchar(50) DEFAULT NULL COMMENT '来源推广商编码',
  PRIMARY KEY (`id`),
  KEY `idx_app_activate_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `app_activate` */

insert  into `app_activate`(`id`,`device_id`,`mobile_type`,`android_version`,`create_time`,`from_name`,`from_no`) values (1,'21321313123','HUAWEI','4.1','2014-05-01 07:17:43','360','360'),(2,'fdsafdsfak1221k333','HUAWEI111','4.3',NULL,'ç¾åº¦','BD');

/*Table structure for table `app_file` */

DROP TABLE IF EXISTS `app_file`;

CREATE TABLE `app_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `owner_id` bigint(20) NOT NULL COMMENT '属于对象',
  `image_type` smallint(6) NOT NULL COMMENT '1个人头像，2说说图片，3单聊图片，4群聊图片',
  `image_path` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `upload_time` datetime NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `app_file` */

insert  into `app_file`(`id`,`owner_id`,`image_type`,`image_path`,`upload_time`) values (1,16,1,'21/111.jpg','2014-07-24 09:09:35'),(2,5,2,'21321321/321/ddd.jpg','2014-07-24 10:08:29'),(3,5,2,'111/ddd/ss.jpg/','2014-07-24 10:08:29'),(4,6,2,'21321321/321/ddd.jpg/','2014-07-24 17:00:24'),(5,23,2,'21/111.jpg','2014-08-06 09:03:41'),(6,23,2,'21/dd.jpg','2014-08-06 09:03:41'),(7,24,2,'21/111.jpg','2014-08-06 09:06:09'),(8,24,2,'21/dd.jpg','2014-08-06 09:06:09'),(9,25,2,'21/111.jpg','2014-08-06 09:08:33'),(10,25,2,'21/dd.jpg','2014-08-06 09:08:33'),(11,26,2,'21221/3221/dd2d.jpg/','2014-08-06 09:09:21'),(12,27,2,'21221/3221/dd2d.jpg/','2014-08-06 09:09:41'),(13,28,2,'upload/20140807/99fff870c5c94033ab18189adaa52251.jpg','2014-08-07 10:38:02'),(14,28,2,'upload/20140807/c1c5732a072541459a16662509300de6.jpg','2014-08-07 10:38:02'),(15,28,2,'upload/20140807/1f6b1a79b2c9411d977c00bd8ea611c7.jpg','2014-08-07 10:38:03'),(16,28,2,'upload/20140807/bc2d14741ae7455783855047f62e0d73.jpg','2014-08-07 10:38:03');

/*Table structure for table `app_user` */

DROP TABLE IF EXISTS `app_user`;

CREATE TABLE `app_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_account` varchar(50) NOT NULL COMMENT '用户账号',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `user_password` varchar(50) NOT NULL COMMENT '用户密码',
  `user_sex` smallint(6) DEFAULT NULL COMMENT '性别  0 男 1 女',
  `user_mobile` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `user_birthday` date DEFAULT NULL COMMENT '用户生日',
  `user_address` varchar(200) DEFAULT NULL COMMENT '地址',
  `user_image` varchar(255) DEFAULT NULL COMMENT '用户头像图标',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `android_version` varchar(50) DEFAULT NULL COMMENT '系统版本号',
  `status` smallint(6) DEFAULT '1' COMMENT '状态 0禁用1正常',
  `last_login_time` datetime DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `position_x` float(17,7) DEFAULT NULL,
  `position_y` float(17,7) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`),
  UNIQUE KEY `idx_user_account` (`user_account`),
  KEY `idx_user_reg_time` (`register_time`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='APP 用户';

/*Data for the table `app_user` */

insert  into `app_user`(`id`,`user_account`,`user_name`,`user_password`,`user_sex`,`user_mobile`,`user_birthday`,`user_address`,`user_image`,`register_time`,`android_version`,`status`,`last_login_time`,`location`,`position_x`,`position_y`,`remark`) values (1,'she.yj','佘宇军','e10adc3949ba59abbe56e057f20f883e',NULL,'13400111100','2014-05-22','深圳市南山区西丽','21/111.jpg;21/dd.jpg','2014-05-09 14:30:15','4.1',1,NULL,NULL,NULL,NULL,'第一个注册'),(2,'test','富士达21','888888',1,'88888111','2014-05-06','213213',NULL,'2014-05-20 14:31:12','4.1',1,'2014-05-26 15:12:08',NULL,NULL,NULL,'第一个注册'),(3,'15911112222','dfg','e10adc3949ba59abbe56e057f20f883e',1,'13590909090','2001-01-01','you are sure',NULL,'2014-05-26 15:02:42',NULL,1,NULL,NULL,NULL,NULL,'第一个注册'),(4,'15944412222','321','e10adc3949ba59abbe56e057f20f883e',1,'13590909090','2001-01-01','you are sure',NULL,'2014-05-26 15:05:02',NULL,1,NULL,NULL,NULL,NULL,'第一个注册'),(7,'159455512222','3221','e10adc3949ba59abbe56e057f20f883e',1,'13590909090','2001-01-01','you are sure',NULL,'2014-05-26 15:07:49',NULL,1,NULL,NULL,NULL,NULL,'第一个注册'),(8,'seres','大数据','96e79218965eb72c92a549dd5a330112',1,'111111',NULL,NULL,NULL,'2014-06-12 08:54:24',NULL,1,NULL,NULL,NULL,NULL,'第一个注册'),(9,'15986607830','电话','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,'2014-07-14 20:04:34',NULL,1,'2014-07-14 20:15:11',NULL,NULL,NULL,'第一个注册'),(10,'shetest','shhheeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 08:25:31',NULL,1,NULL,NULL,120.1235580,30.6553211,'第一个注册'),(11,'shet3est','shhh3eeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 08:48:49',NULL,1,NULL,NULL,120.1236572,30.6553211,'第一个注册'),(12,'shet4est','shhh4eeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 08:50:36',NULL,1,NULL,NULL,120.1246567,30.6553211,'第一个注册'),(13,'shet5est','shhh5eeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 08:51:12',NULL,1,NULL,NULL,120.1346588,30.6553211,'第一个注册'),(14,'shet6est','shhh6eeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 08:57:56',NULL,1,NULL,NULL,NULL,NULL,'第一个注册'),(15,'shet7est','shhh7eeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 08:59:05',NULL,1,NULL,NULL,121.4942017,31.2745991,'第一个注册'),(16,'shet8est','shhh8eeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;21/dd.jpg','2014-07-24 09:09:35',NULL,1,NULL,NULL,120.1234589,30.6543217,'第一个注册'),(17,'shet9est','shh9heeh','96e79218965eb72c92a549dd5a330112',1,'88888',NULL,NULL,'21/111.jpg;','2014-07-24 10:13:21',NULL,1,NULL,NULL,NULL,NULL,'第一个注册');

/*Table structure for table `group_msg` */

DROP TABLE IF EXISTS `group_msg`;

CREATE TABLE `group_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) NOT NULL COMMENT '群组ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `user_account` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `user_image` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `msg_content` varchar(500) DEFAULT NULL COMMENT '消息内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_group_msg_id` (`group_id`),
  CONSTRAINT `fk_group_msg_id` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='群组聊天消息表';

/*Data for the table `group_msg` */

insert  into `group_msg`(`id`,`group_id`,`user_id`,`user_name`,`user_account`,`user_image`,`msg_content`,`create_time`) values (1,5,1,'佘宇军','she.yj','21/111.jpg;21/dd.jpg','111111111111111111',NULL),(2,5,1,'佘宇军','she.yj','21/111.jpg;21/dd.jpg','2222222222','2014-08-06 14:43:42');

/*Table structure for table `group_user` */

DROP TABLE IF EXISTS `group_user`;

CREATE TABLE `group_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `master_id` bigint(20) DEFAULT NULL COMMENT '群主ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_account` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `group_id` bigint(20) DEFAULT NULL COMMENT '群组ID',
  `user_image` varchar(200) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`),
  KEY `fk_group_id` (`group_id`),
  CONSTRAINT `fk_group_id` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `group_user` */

insert  into `group_user`(`id`,`master_id`,`user_id`,`user_account`,`user_name`,`group_id`,`user_image`) values (2,1,1,'she.yj','佘宇军',5,'21/111.jpg;21/dd.jpg'),(3,NULL,2,'test','富士达21',5,NULL),(4,1,1,'she.yj','佘宇军',6,'21/111.jpg;21/dd.jpg');

/*Table structure for table `group_user_apply` */

DROP TABLE IF EXISTS `group_user_apply`;

CREATE TABLE `group_user_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL COMMENT '群组ID',
  `group_name` varchar(50) NOT NULL COMMENT '群组名称',
  `apply_type` smallint(6) NOT NULL COMMENT '申请类型 0 群主邀请，1个人申请',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `user_account` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `user_image` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 0申请中1审核通过2审核拒绝',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `group_user_apply` */

/*Table structure for table `system_dict` */

DROP TABLE IF EXISTS `system_dict`;

CREATE TABLE `system_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID ',
  `code_type` varchar(50) NOT NULL COMMENT '字典类型',
  `code_name` varchar(50) NOT NULL COMMENT '字典KEY',
  `code_value` varchar(50) NOT NULL COMMENT '字典值',
  `status` varchar(255) DEFAULT '1' COMMENT '状态 0 未启用 1启用',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `sort_value` bigint(20) DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `system_dict` */

insert  into `system_dict`(`id`,`code_type`,`code_name`,`code_value`,`status`,`remark`,`sort_value`) values (1,'SYSTEM_PARAM_STATUS','禁用','0','1',NULL,2),(2,'SYSTEM_PARAM_STATUS','启用','1','1',NULL,1),(3,'SYSTEM_PARAM_TPYE','APP参数','1','1',NULL,1),(4,'SYSTEM_PARAM_TPYE','后台参数','2','1',NULL,2),(5,'APP_VERSION_TYPE','不强制更新','0','1',NULL,1),(6,'APP_VERSION_TYPE','强制更新','1','1',NULL,2),(7,'APP_USER_STATUS','禁用','0','1',NULL,1),(8,'APP_USER_STATUS','正常','1','1',NULL,2),(9,'USER_TALK_STATUS','不可见','0','1',NULL,1),(10,'USER_TALK_STATUS','可见','1','1',NULL,2),(11,'USER_TALK_TYPE','所有人','1','1',NULL,1),(12,'USER_TALK_TYPE','好友','2','1',NULL,2),(13,'TALK_RESPONSE_STATUS','不可见','0','1',NULL,1),(14,'TALK_RESPONSE_STATUS','可见','1','1',NULL,2);

/*Table structure for table `system_menu` */

DROP TABLE IF EXISTS `system_menu`;

CREATE TABLE `system_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父节点ID',
  `menu_type` smallint(6) DEFAULT NULL COMMENT '菜单类型  0导航 1菜单',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `menu_sort` int(11) DEFAULT NULL COMMENT '排序',
  `menu_flag` smallint(6) DEFAULT NULL COMMENT '菜单标记 0无效 1有效',
  `menu_remark` varchar(200) DEFAULT NULL COMMENT '菜单备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

/*Data for the table `system_menu` */

insert  into `system_menu`(`menu_id`,`menu_name`,`parent_id`,`menu_type`,`menu_url`,`menu_sort`,`menu_flag`,`menu_remark`) values ('1','应用管理平台','0',0,NULL,1,1,'菜单根节点'),('10','激活用户查询','4',1,'system/app_activate/list',10,1,'激活用户查询'),('2','系统管理','1',0,NULL,2,1,'系统管理'),('3','系统用户管理','2',1,'system/system_user/list',3,1,'系统用户管理'),('4','应用管理','1',0,NULL,4,1,'应用管理'),('5','说说管理','4',1,'system/user_talk/list',5,1,'说说管理'),('6','APK版本管理','2',1,'system/app_version/list',6,1,'APK版本管理'),('7','参数管理','2',1,'system/system_param/list',7,1,'参数管理'),('8','用户管理','4',1,'system/app_user/list',8,1,'用户管理'),('9','评论管理','4',1,'system/talk_response/list',9,1,'评论管理');

/*Table structure for table `system_param` */

DROP TABLE IF EXISTS `system_param`;

CREATE TABLE `system_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(50) NOT NULL COMMENT '参数名称',
  `param_value` varchar(50) NOT NULL COMMENT '参数值',
  `status` smallint(6) DEFAULT '1' COMMENT '状态 0 禁用1 启动',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `param_type` smallint(6) DEFAULT '1' COMMENT '参数类型，1 APP参数 2后台参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统参数表';

/*Data for the table `system_param` */

insert  into `system_param`(`id`,`param_name`,`param_value`,`status`,`remark`,`param_type`) values (2,'ERERWuuu','12',1,'1111',1);

/*Table structure for table `system_role` */

DROP TABLE IF EXISTS `system_role`;

CREATE TABLE `system_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_remark` varchar(200) DEFAULT NULL COMMENT '角色备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `system_role` */

insert  into `system_role`(`role_id`,`role_name`,`role_remark`) values ('1','系统管理员','系统管理员');

/*Table structure for table `system_role_menu` */

DROP TABLE IF EXISTS `system_role_menu`;

CREATE TABLE `system_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID ',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`),
  CONSTRAINT `role_menu` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

/*Data for the table `system_role_menu` */

insert  into `system_role_menu`(`role_id`,`menu_id`) values ('1','1'),('1','10'),('1','2'),('1','3'),('1','4'),('1','5'),('1','6'),('1','7'),('1','8'),('1','9');

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `userid` varchar(32) NOT NULL COMMENT 'PK',
  `username` varchar(160) NOT NULL COMMENT '中文姓名',
  `login_name` varchar(200) NOT NULL COMMENT '登陆名',
  `login_password` varchar(200) NOT NULL COMMENT '登陆密码',
  `mobile_phone` varchar(60) DEFAULT NULL COMMENT '手机号码',
  `status` varchar(4) DEFAULT '1' COMMENT '1=正常2=锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `system_user` */

insert  into `system_user`(`userid`,`username`,`login_name`,`login_password`,`mobile_phone`,`status`,`create_time`,`update_time`,`remark`) values ('08a694816bc14dff9186c4fafb9fbe2e','she840707','sheyujun1','e10adc3949ba59abbe56e057f20f883e','123222','2','2014-05-20 15:21:20','2014-05-20 15:29:06','1111'),('5db02080cfce4ddfab66ba32c911b2ba','she840707','sheyujun','e10adc3949ba59abbe56e057f20f883e','123','1',NULL,NULL,'11111'),('888888','admin','admin','e10adc3949ba59abbe56e057f20f883e',NULL,'1','2014-05-20 10:18:26','2014-05-20 10:18:31','系统管理员'),('ae5384a21aef46b990d8c949a73c7a35','she840707@163.com','13213213','e10adc3949ba59abbe56e057f20f883e','12321','1','2014-05-20 16:04:44',NULL,'');

/*Table structure for table `system_user_role` */

DROP TABLE IF EXISTS `system_user_role`;

CREATE TABLE `system_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID ',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID ',
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT `user_role` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `system_user_role` */

insert  into `system_user_role`(`user_id`,`role_id`) values ('888888','1');

/*Table structure for table `talk_response` */

DROP TABLE IF EXISTS `talk_response`;

CREATE TABLE `talk_response` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `talk_id` bigint(20) NOT NULL COMMENT '说说ID',
  `from_user_id` bigint(20) NOT NULL,
  `from_user_name` varchar(50) DEFAULT NULL,
  `to_user_id` bigint(20) NOT NULL,
  `to_user_name` varchar(50) DEFAULT NULL,
  `response_msg` varchar(500) DEFAULT NULL,
  `response_time` datetime DEFAULT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态 0不可见 1可见',
  PRIMARY KEY (`id`),
  KEY `fk_talk_res_id` (`talk_id`),
  KEY `idx_talk_res_time` (`response_time`),
  CONSTRAINT `fk_talk_res_id` FOREIGN KEY (`talk_id`) REFERENCES `user_talk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户评论表';

/*Data for the table `talk_response` */

insert  into `talk_response`(`id`,`talk_id`,`from_user_id`,`from_user_name`,`to_user_id`,`to_user_name`,`response_msg`,`response_time`,`status`) values (1,1,1,'111',1,'111','123213','2014-05-24 08:27:30',1),(2,1,2,'test',1,'111','2321321321','2014-05-24 08:28:04',1),(3,2,1,'1111',2,'test','1212','2014-05-24 08:28:56',1),(5,3,1,'test',2,'test','you are right!','2014-05-27 10:32:09',1),(6,3,1,'test',2,'test','you are right!','2014-05-27 10:32:10',1);

/*Table structure for table `user_attention` */

DROP TABLE IF EXISTS `user_attention`;

CREATE TABLE `user_attention` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL,
  `attention_user_id` bigint(20) NOT NULL,
  `attention_user_name` varchar(50) DEFAULT NULL COMMENT '关注用户名称',
  PRIMARY KEY (`id`),
  KEY `fk_attention_user_id` (`user_id`),
  KEY `fk_attentiond_user_id` (`attention_user_id`),
  CONSTRAINT `fk_attentiond_user_id` FOREIGN KEY (`attention_user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `fk_attention_user_id` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户关注表';

/*Data for the table `user_attention` */

insert  into `user_attention`(`id`,`user_id`,`attention_user_id`,`attention_user_name`) values (1,1,2,'test');

/*Table structure for table `user_friend` */

DROP TABLE IF EXISTS `user_friend`;

CREATE TABLE `user_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL,
  `friend_user_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '加为好友时间',
  `friend_user_name` varchar(50) DEFAULT NULL COMMENT '好友名称',
  `status` smallint(6) DEFAULT '1' COMMENT '状态 1申请中 2申请通过 3 申请拒绝',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_frient_user_id` (`user_id`),
  KEY `fk_friend_id` (`friend_user_id`),
  KEY `idx_friend_create_time` (`create_time`),
  CONSTRAINT `fk_friend_id` FOREIGN KEY (`friend_user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `fk_frient_user_id` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户好友表';

/*Data for the table `user_friend` */

insert  into `user_friend`(`id`,`user_id`,`friend_user_id`,`create_time`,`friend_user_name`,`status`,`remark`) values (1,1,2,'2014-05-24 08:25:43','test',2,NULL),(2,1,3,NULL,'sss',1,'give me');

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_name` varchar(100) DEFAULT NULL COMMENT '群组名称',
  `group_account` varchar(50) DEFAULT NULL,
  `master_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态 0 禁用1 正常',
  `position_x` float(15,7) DEFAULT NULL,
  `position_y` float(15,7) DEFAULT NULL,
  `group_image` varchar(300) DEFAULT NULL COMMENT '群组图片',
  `remark` varchar(300) DEFAULT NULL COMMENT '群组备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='群组表';

/*Data for the table `user_group` */

insert  into `user_group`(`id`,`group_name`,`group_account`,`master_id`,`create_time`,`location`,`status`,`position_x`,`position_y`,`group_image`,`remark`) values (5,'test',NULL,1,'2014-08-06 14:24:50',NULL,1,121.4922028,31.2646008,'/111/eee.jpg','suibian...'),(6,'test1',NULL,1,'2014-08-06 15:00:23',NULL,1,121.4922028,31.2646999,'/111/eee.jpg','suibian...');

/*Table structure for table `user_talk` */

DROP TABLE IF EXISTS `user_talk`;

CREATE TABLE `user_talk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `image_path` varchar(1000) DEFAULT NULL COMMENT '图片地址',
  `talk_content` varchar(500) DEFAULT NULL,
  `talk_type` smallint(6) NOT NULL DEFAULT '1' COMMENT '说说发布范围 1所有人  2好友',
  `location` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '状态 0 不见 1可见',
  PRIMARY KEY (`id`),
  KEY `fk_talk_user_id` (`user_id`),
  KEY `idx_talk_create_time` (`create_time`),
  CONSTRAINT `fk_talk_user_id` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户说说表';

/*Data for the table `user_talk` */

insert  into `user_talk`(`id`,`user_id`,`user_name`,`image_path`,`talk_content`,`talk_type`,`location`,`create_time`,`status`) values (1,1,'test',NULL,'111',1,'111','2014-05-01 08:26:30',1),(2,2,'test',NULL,'213213',1,'231','2014-05-13 08:26:47',1),(3,1,'test','21321321/321/ddd.jpg/','thanks',1,NULL,NULL,1),(4,1,'test','21321321/321/ddd.jpg;111/ddd/ss.jpg/','telll mei dsaf',1,NULL,'2014-07-24 10:07:16',1),(5,1,'test','21321321/321/ddd.jpg;111/ddd/ss.jpg/','telll mei dsaf',1,NULL,'2014-07-24 10:08:29',1),(6,1,'test','21321321/321/ddd.jpg/','telll mei dsaf',1,NULL,'2014-07-24 17:00:24',1),(7,1,'test','1111','1111',1,NULL,NULL,1),(8,1,'test','2321','11',1,NULL,NULL,1),(9,1,'test','111','111',1,NULL,NULL,1),(10,1,'test','111','111',1,NULL,NULL,1),(11,1,'test','1111','111',1,NULL,NULL,1),(12,1,'test','111','111',1,NULL,NULL,1),(13,1,'test','11','11',1,NULL,NULL,1),(14,1,'test','111','111',1,NULL,NULL,1),(15,1,'test','111','111',1,NULL,NULL,1),(16,1,'test','111','111',1,NULL,NULL,1),(17,1,'test','111','111',1,NULL,NULL,1),(18,1,'test','111','111',1,NULL,NULL,1),(19,1,'test','111','111',1,NULL,NULL,1),(20,1,'test','1111','111',1,NULL,NULL,1),(21,1,'test','222','222',1,NULL,NULL,1),(22,1,'佘宇军',NULL,'telll mei dsaf',1,NULL,'2014-08-06 09:02:46',1),(23,1,'佘宇军','21/111.jpg;21/dd.jpg','telll 111mei dsaf',1,NULL,'2014-08-06 09:03:41',1),(24,1,'佘宇军','21/111.jpg;21/dd.jpg','telll mei dsaf',1,NULL,'2014-08-06 09:06:09',1),(25,1,'佘宇军','21/111.jpg;21/dd.jpg','telll mei dsaf',1,NULL,'2014-08-06 09:08:32',1),(26,1,'佘宇军','21/111.jpg;21/dd.jpg','telll mei dsaf',1,NULL,'2014-08-06 09:09:07',1),(27,1,'佘宇军','21/111.jpg;21/dd.jpg','telll mei dsaf',1,NULL,'2014-08-06 09:09:41',1),(28,1,'佘宇军','21/111.jpg;21/dd.jpg','æ¤æ¤è®¡è¾',1,NULL,'2014-08-07 10:37:45',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
