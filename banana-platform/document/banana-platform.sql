/*
Navicat MySQL Data Transfer

Source Server         : c.all-dream.com
Source Server Version : 50720
Source Host           : c.all-dream.com:3306
Source Database       : testb

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-24 14:05:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `brief` varchar(200) DEFAULT NULL,
  `content` longtext NOT NULL,
  `click` int(11) NOT NULL DEFAULT '0',
  `type` varchar(20) NOT NULL DEFAULT 'PAGE',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `moduleId` varchar(50) NOT NULL DEFAULT 'top',
  `mkey` varchar(20) DEFAULT NULL COMMENT 'key，唯一键，页面唯一标识',
  `canDelete` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可删除，可修key，默认可以',
  `category` varchar(50) DEFAULT NULL,
  `canComment` tinyint(4) NOT NULL DEFAULT '1',
  `commentCount` int(11) NOT NULL DEFAULT '0',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `markdown` text NOT NULL,
  `projectId` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mkey_UNIQUE` (`mkey`),
  KEY `index_mod_type_cat_seq_time` (`moduleId`,`type`,`category`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('152611998276001000007', 'V8.0.0使用视频-用户版', 'V8.0.0使用视频-用户版，觉得有用的可以去https://gitee.com/CrapApi/CrapApi 上支持下，有钱的捐赠的咖啡钱，没钱的捧个人场，fork下、star一下', '<p data-source-line=\"3\">V8.0.0使用视频-用户版，觉得有用的可以去 <a href=\"https://gitee.com/CrapApi/CrapApi\">https://gitee.com/CrapApi/CrapApi</a> 上支持下，有钱的捐赠的咖啡钱，没钱的捧个人场，fork下、star一下</p>\n<iframe height=\"498\" width=\"100%\" src=\"http://player.youku.com/embed/XMzU4NjQwODIzNg==\" frameborder=\"0\" \'allowfullscreen\'=\"\"></iframe>', '1', 'ARTICLE', '2', '2018-05-12 10:13:03', '152611971673309000002', null, '1', '帮助文档', '1', '0', '1', 'V8.0.0使用视频-用户版，觉得有用的可以去 https://gitee.com/CrapApi/CrapApi 上支持下，有钱的捐赠的咖啡钱，没钱的捧个人场，fork下、star一下\n\n&lt;iframe height=498 width=100% src=\'http://player.youku.com/embed/XMzU4NjQwODIzNg==\' frameborder=0 \'allowfullscreen\'&gt;&lt;/iframe&gt;', '152611968062607000001');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(50) NOT NULL,
  `articleId` varchar(50) NOT NULL,
  `content` varchar(200) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `reply` varchar(200) NOT NULL DEFAULT '',
  `updateTime` timestamp NOT NULL DEFAULT '2015-12-31 00:00:00',
  `userName` varchar(50) NOT NULL COMMENT '匿名',
  `avatarUrl` varchar(500) NOT NULL DEFAULT 'resources/avatar/avatar0.jpg' COMMENT '用户头像 ',
  PRIMARY KEY (`id`),
  KEY `index_articleId_seq_time` (`articleId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for debug
-- ----------------------------
DROP TABLE IF EXISTS `debug`;
CREATE TABLE `debug` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '文件夹名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `interfaceId` varchar(50) DEFAULT '',
  `moduleId` varchar(50) NOT NULL,
  `method` varchar(10) NOT NULL,
  `url` varchar(500) NOT NULL,
  `params` varchar(1000) NOT NULL,
  `headers` varchar(1000) NOT NULL,
  `paramType` varchar(100) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `uid` varchar(50) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`),
  KEY `index_status_seq_createTime` (`status`,`sequence`,`createTime`),
  KEY `index_userId_seq_createTime` (`interfaceId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of debug
-- ----------------------------

-- ----------------------------
-- Table structure for error
-- ----------------------------
DROP TABLE IF EXISTS `error`;
CREATE TABLE `error` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `errorCode` varchar(50) NOT NULL COMMENT '错误码编码',
  `errorMsg` varchar(128) NOT NULL COMMENT '错误码描述',
  `projectId` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`),
  KEY `index_mod_seq_time` (`projectId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of error
-- ----------------------------
INSERT INTO `error` VALUES ('152611980854003000004', '000001', '错误码1', '152611968062607000001', '2018-05-12 10:10:09', '1', '0');

-- ----------------------------
-- Table structure for hot_search
-- ----------------------------
DROP TABLE IF EXISTS `hot_search`;
CREATE TABLE `hot_search` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `times` int(11) NOT NULL DEFAULT '0' COMMENT '搜索次数',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '2015-12-31 00:00:00',
  `keyword` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '搜索关键字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyword_UNIQUE` (`keyword`),
  KEY `index_times` (`times`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of hot_search
-- ----------------------------

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `url` varchar(200) NOT NULL COMMENT 'api链接',
  `method` varchar(50) NOT NULL COMMENT ' 请求方式',
  `param` text COMMENT '参数列表',
  `paramRemark` text COMMENT '请求参数备注',
  `requestExam` text COMMENT '请求示例',
  `responseParam` text COMMENT '返回参数说明',
  `errorList` text COMMENT '接口错误码列表',
  `trueExam` text COMMENT '正确返回示例',
  `falseExam` text COMMENT '错误返回示例',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用;0不可用；1可用;-1 删除',
  `moduleId` varchar(50) NOT NULL COMMENT '所属模块ID',
  `interfaceName` varchar(100) NOT NULL COMMENT '接口名',
  `remark` text,
  `errors` text COMMENT '错误码、错误码信息',
  `updateBy` varchar(100) DEFAULT NULL,
  `updateTime` timestamp NOT NULL DEFAULT '2015-12-31 00:00:00',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` varchar(20) NOT NULL DEFAULT '1.0' COMMENT '版本号',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `header` text,
  `fullUrl` varchar(255) NOT NULL DEFAULT '',
  `monitorType` int(11) NOT NULL DEFAULT '0' COMMENT '监控类型，多选：\nNetwork("网络异常",1),Include("包含指定字符串",2),NotInclude("不包含指定字符串",3),NotEqual("不等于指定字符串",4);	\n',
  `monitorText` varchar(500) NOT NULL DEFAULT '' COMMENT '监控比较内容',
  `monitorEmails` varchar(200) DEFAULT NULL,
  `isTemplate` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否是模板',
  `projectId` varchar(50) NOT NULL DEFAULT '',
  `contentType` varchar(45) NOT NULL DEFAULT 'application/json' COMMENT '接口返回contentType',
  PRIMARY KEY (`id`),
  KEY `Index_fullUrl` (`fullUrl`),
  KEY `index_mod_seq_time` (`moduleId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES ('152611979419112000003', '/test.json', 'GET,', 'form=[{\"name\":\"test\",\"def\":\"test\",\"remark\":\"test\",\"necessary\":\"true\",\"inUrl\":\"false\",\"type\":\"string\"}]', '[]', '请求地址:http://test.com/test.json\r\n请求头:\r\n	header=1\r\n请求参数:\r\n	test=test', '[{\"deep\":\"0\",\"name\":\"test\",\"remark\":\"tet\",\"type\":\"string\",\"necessary\":\"true\"}]', '000001,', '{\n}', '{}', '0', '152611971673309000002', '测试接口1', '<h3>我是测试项目</h3>', '[{\"createTime\":{\"date\":12,\"day\":6,\"hours\":18,\"minutes\":10,\"month\":4,\"seconds\":9,\"time\":1526119809000,\"timezoneOffset\":-480,\"year\":118},\"errorCode\":\"000001\",\"errorMsg\":\"错误码1\",\"id\":\"152611980854003000004\",\"projectId\":\"152611968062607000001\",\"sequence\":0,\"status\":1}]', 'userName：admin | trueName：超级管理员', '2018-05-12 10:09:54', '2018-05-12 10:09:54', '1.0.1', '1', '[{\"name\":\"header\",\"def\":\"1\",\"remark\":\"备注\",\"necessary\":\"true\",\"type\":\"string\"}]', 'http://test.com/test.json', '0', '', null, '\0', '152611968062607000001', 'application/json');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `modelClass` varchar(50) NOT NULL,
  `modelName` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `updateBy` varchar(50) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `identy` varchar(50) NOT NULL COMMENT '数据唯一主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('152611982039908000005', '1', '2018-05-12 10:10:20', '0', 'InterfaceWithBLOBs', '接口', 'UPDATE', '超级管理员', '测试接口1', '{\"contentType\":\"application/json\",\"createTime\":{\"date\":12,\"day\":6,\"hours\":18,\"minutes\":9,\"month\":4,\"seconds\":54,\"time\":1526119794000,\"timezoneOffset\":-480,\"year\":118},\"errorList\":\"\",\"errors\":\"[]\",\"falseExam\":\"{}\",\"fullUrl\":\"http://test.com/test.json\",\"header\":\"[{\\\"name\\\":\\\"header\\\",\\\"def\\\":\\\"1\\\",\\\"remark\\\":\\\"备注\\\",\\\"necessary\\\":\\\"true\\\",\\\"type\\\":\\\"string\\\"}]\",\"id\":\"152611979419112000003\",\"interfaceName\":\"测试接口1\",\"isTemplate\":false,\"method\":\"GET,\",\"moduleId\":\"152611971673309000002\",\"monitorEmails\":\"\",\"monitorText\":\"\",\"monitorType\":0,\"param\":\"form=[{\\\"name\\\":\\\"test\\\",\\\"def\\\":\\\"test\\\",\\\"remark\\\":\\\"test\\\",\\\"necessary\\\":\\\"true\\\",\\\"inUrl\\\":\\\"false\\\",\\\"type\\\":\\\"string\\\"}]\",\"paramRemark\":\"[]\",\"projectId\":\"152611968062607000001\",\"remark\":\"<h3>我是测试项目<\\/h3>\",\"requestExam\":\"请求地址:http://test.com/test.json\\r\\n请求头:\\r\\n\\theader=1\\r\\n请求参数:\\r\\n\\ttest=test\",\"responseParam\":\"[{\\\"deep\\\":\\\"0\\\",\\\"name\\\":\\\"test\\\",\\\"remark\\\":\\\"tet\\\",\\\"type\\\":\\\"string\\\",\\\"necessary\\\":\\\"true\\\"}]\",\"sequence\":1,\"status\":0,\"trueExam\":\"{\\n}\",\"updateBy\":\"userName：admin | trueName：超级管理员\",\"updateTime\":{\"date\":12,\"day\":6,\"hours\":18,\"minutes\":9,\"month\":4,\"seconds\":54,\"time\":1526119794000,\"timezoneOffset\":-480,\"year\":118},\"url\":\"/test.json\",\"version\":\"1.0.1\"}', '152611979419112000003');
INSERT INTO `log` VALUES ('152611988182408000006', '1', '2018-05-12 10:11:22', '1', 'Module', '模块', 'UPDATE', '超级管理员', '我是测试模块1', '{\"canDelete\":1,\"category\":\"分类1,分类2\",\"createTime\":{\"date\":12,\"day\":6,\"hours\":18,\"minutes\":8,\"month\":4,\"seconds\":37,\"time\":1526119717000,\"timezoneOffset\":-480,\"year\":118},\"id\":\"152611971673309000002\",\"name\":\"我是测试模块1\",\"projectId\":\"152611968062607000001\",\"remark\":\"我是测试模块1\",\"sequence\":1,\"status\":1,\"templateId\":\"\",\"url\":\"http://test.com\",\"userId\":\"admin\",\"version\":0}', '152611971673309000002');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL DEFAULT '导航菜单编号',
  `menuName` varchar(50) NOT NULL COMMENT '菜单名称',
  `menuUrl` varchar(200) DEFAULT NULL COMMENT '菜单链接',
  `roleIds` varchar(512) DEFAULT NULL COMMENT '角色可见集合  （ID之间以逗号分隔）',
  `parentId` varchar(50) DEFAULT '0',
  `iconRemark` varchar(100) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL COMMENT '前端菜单、后台菜单',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`),
  KEY `index_parentId` (`parentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('152612004179705000008', '快捷菜单', null, null, '0', '<i class=\"iconfont\"></i>', 'FRONT', '2018-05-12 10:14:02', '1', '1');
INSERT INTO `menu` VALUES ('152612008276705000009', 'v8.0.0用户使用视频', 'index.do#/152611968062607000001/article/detail/152611971673309000002/ARTICLE/152611998276001000007', null, '152612004179705000008', null, 'FRONT', '2018-05-12 10:14:43', '1', '1');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` varchar(50) NOT NULL COMMENT '所属模块ID',
  `name` varchar(100) NOT NULL COMMENT '所属模块名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '模块地址',
  `canDelete` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1：可删除，0：不可删除',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `userId` varchar(50) NOT NULL DEFAULT '',
  `projectId` varchar(50) NOT NULL DEFAULT '',
  `templateId` varchar(50) DEFAULT NULL COMMENT '接口模板ID',
  `version` int(11) NOT NULL DEFAULT '0',
  `category` varchar(200) NOT NULL DEFAULT '' COMMENT '文章分类，多个分类以逗号分割，每个分类最多10个字',
  PRIMARY KEY (`id`),
  KEY `index_uid_seq_time` (`userId`,`sequence`,`createTime`),
  KEY `index_pid_seq_time` (`projectId`,`sequence`,`createTime`),
  KEY `index_templateId` (`templateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('152611971673309000002', '我是测试模块1', '2018-05-12 10:08:37', '1', '1', 'http://test.com', '1', '我是测试模块1', 'admin', '152611968062607000001', null, '0', '分类1,分类2,帮助文档');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` varchar(50) NOT NULL COMMENT '项目ID',
  `name` varchar(100) NOT NULL COMMENT '项目名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '2：推荐项目，3，管理员管理项目，4，管理管理&推荐项目，-1：默认debug项目',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `userId` varchar(50) NOT NULL DEFAULT '',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:私有项目，2公开项目，3公开推荐',
  `password` varchar(45) DEFAULT NULL,
  `cover` varchar(200) NOT NULL DEFAULT 'resources/images/cover.png' COMMENT '项目封面',
  `luceneSearch` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否允许建立Lucene搜索',
  PRIMARY KEY (`id`),
  KEY `index_status_seq_createTime` (`status`,`sequence`,`createTime`),
  KEY `index_userId_seq_createTime` (`userId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('152611968062607000001', '测试项目', '2018-05-12 10:08:01', '2', '1', '我是测试项目', 'admin', '2', null, 'resources/images/cover.png', '1');

-- ----------------------------
-- Table structure for project_user
-- ----------------------------
DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user` (
  `id` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `sequence` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `projectId` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `addModule` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以添加模块',
  `delModule` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可以删除模块',
  `modModule` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可是修改模块',
  `addInter` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以添加接口',
  `delInter` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可以删除接口',
  `modInter` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以修改接口',
  `addArticle` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以添加文章',
  `delArticle` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可以删除文章',
  `modArticle` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以修改文章',
  `addSource` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以添加资源',
  `delSource` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可以删除资源',
  `modSource` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以修改资源',
  `addDict` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以添加数据字典',
  `delDict` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可以删除数据字典',
  `modDict` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以修改数据字典',
  `userEmail` varchar(45) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `addError` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以添加错误码',
  `delError` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可以删除错误码',
  `modError` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可以修改错误码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_user` (`userId`,`projectId`),
  KEY `index_uid_seq_time` (`userId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_user
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) NOT NULL COMMENT '角色ID',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `auth` text NOT NULL,
  `authName` text,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'renren', '0 0/30 * * * ?', '0', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `mkey` varchar(20) NOT NULL,
  `value` varchar(500) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `type` varchar(10) NOT NULL DEFAULT 'TEXT' COMMENT '设置类型（IMAGE,LINK,TEXT）',
  `canDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1：可删除，0：不可删除',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `show` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否在前端显示，1：是，0：否',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`mkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('0', 'tes', 'test555', 'test555', '2017-12-29 19:44:49', '1', 'TEXT', '0', '1', '1');
INSERT INTO `setting` VALUES ('062f01ae-e50b-4dd3-808b-b4a6d65eeadc', 'LOGO', 'http://api.crap.cn/resources/upload/images/2016-04-27/231357cAgbyp.png', '网站主logo，可以直接在value中填写绝对链接地址，也可以自行上传图片', '2016-03-29 04:23:18', '1', 'IMAGE', '0', '90', '1');
INSERT INTO `setting` VALUES ('8a95bc2f-ea61-4dd6-8163-d9c520b28181', 'VISITCODE', 'true', '游客访问私密模块输入密码的同时是否需要输入图像验证码？true为需要，其他为不需要', '2016-03-31 10:07:14', '0', 'TEXT', '0', '90', '1');
INSERT INTO `setting` VALUES ('98ecca1b-f762-4cd3-831a-4042b36419d8', 'VERIFICATIONCODE', 'false', '是否开启安全登录？ture为开启，其他为不开启，开启后登录将需要输入图片验证码', '2016-03-31 10:07:58', '0', 'TEXT', '0', '90', '1');
INSERT INTO `setting` VALUES ('b97a3a75-c1c3-42cc-b944-8fb5ac5c5f49', 'SECRETKEY', 'crapApiKey', '秘钥，用于cookie加密等', '2016-03-30 17:04:40', '0', 'TEXT', '0', '90', '1');
INSERT INTO `setting` VALUES ('de94c622-02fc-4b39-9cc5-0c24870ac21f', 'TITLE', 'CrapApi|Api接口管理系统', '站点标题', '2016-03-30 11:09:13', '1', 'TEXT', '0', '90', '1');
INSERT INTO `setting` VALUES ('e0dec957-5043-4c6e-9225-960fbc401116', 'ICON', 'http://api2.crap.cn/resources/upload/images/2016-04-04/063633hG35aC.ico', '站点ICON图标（浏览器标题栏图标）', '2016-03-30 11:49:41', '1', 'IMAGE', '0', '100', '1');
INSERT INTO `setting` VALUES ('e2a493a7-c4f0-4cbb-832f-4495a7074252', 'LOGINBG', 'https://dn-coding-net-production-static.qbox.me/d58141c9-9a0c-40b0-a408-5935fd70670f.jpg', '登陆背景图', '2016-08-25 00:02:28', '1', 'IMAGE', '0', '100', '1');
INSERT INTO `setting` VALUES ('e2a493a7-c888-4cbb-832f-4495a7074252', 'TITLEBG', 'https://dn-coding-net-production-static.qbox.me/3c9bcbc0-15dc-4a6f-a81f-5112936b7773.jpg', '头部标题背景图：resources/images/project.jpg,为空则显示主色调', '2016-08-25 00:02:28', '1', 'IMAGE', '0', '99', '1');
INSERT INTO `setting` VALUES ('ecd676c2-0b04-4b4a-a049-4a825221a6d0', 'BG_COLOR', '#f7f7f7', '前端显示背景颜色', '2016-04-28 05:07:37', '1', 'COLOR', '0', '98', '1');
INSERT INTO `setting` VALUES ('ef157b7f-cc53-4a41-9679-d243d478023d', 'COMMENTCODE', 'true', '游客评论是否需要输入验证码', '2016-04-14 14:47:29', '1', 'TEXT', '0', '90', '1');
INSERT INTO `setting` VALUES ('f1c8dc8b-9cd8-4839-b38a-1cea3ceb3942', 'MAX_WIDTH', '1200', '前端显示最大宽度（数字，建议：900-1200）', '2016-04-28 05:07:37', '1', 'TEXT', '0', '90', '1');
INSERT INTO `setting` VALUES ('fff-1111-d4839-b38a-898343435462', 'ANONYMOUS_COMMENT', 'false', '是否允许匿名评论, true:允许', '2017-08-06 07:55:00', '1', 'TEXT', '0', '100', '1');
INSERT INTO `setting` VALUES ('fff-8888-d4839-b38a-898343435462', 'ICONFONT', '//at.alicdn.com/t/font_afbmuhv5zc15rk9', '图标地址（cdn图标库或本地图标库）', '2018-05-01 12:27:00', '1', 'ICONFONT', '0', '100', '1');
INSERT INTO `setting` VALUES ('fff-9191-d4839-b38a-898343435462', 'DESCRIPTION', '免费开源的API接口管理系统、文档管理系统。系统特点：极致简单、开源开放、技术前沿。主要功能有：接口管理、接口调试、文档管理、数据字典管理、文章管理....。', '搜索引描述', '2018-05-04 14:53:11', '1', 'TEXT', '0', '100', '1');
INSERT INTO `setting` VALUES ('fff-9999-d4839-b38a-898343435462', 'KEYWORDS', 'CrapApi,api,crap,接口管理,应用接口管理,开源接口管理,开源api接口管理,api接口管理', '搜索引擎关键字', '2018-05-04 14:53:11', '1', 'TEXT', '0', '100', '1');
INSERT INTO `setting` VALUES ('foc8dc8b-9cd8-4839-b38a-1cea3ceb3942', 'FOOTER_BG_COLOR', '#000000', '前端顶部、底部颜色 #383942', '2016-04-28 05:07:37', '1', 'COLOR', '0', '91', '1');
INSERT INTO `setting` VALUES ('fpc8dc8b-9cd8-4839-b38a-1cea3ceb3942', 'FOOTER_COLOR', '#a9a9a9', '前端顶部、底部字体颜色 #a9a9a9', '2016-04-28 05:07:37', '1', 'COLOR', '0', '97', '1');
INSERT INTO `setting` VALUES ('fpmbdc00-9cd8-4839-b38a-1cea3ceb3945', 'LUCENE_DIR', '/usr/local/crap/lucene', 'Lucene全文检索文件存储地址', '2016-06-06 05:07:37', '1', 'TEXT', '0', '22', '1');
INSERT INTO `setting` VALUES ('fpmbdc8b-9cd8-4839-b38a-1cea3ceb3942', 'MAIN_COLOR', '#407aaa', '网站主色调，默认#CC6699 #007742', '2016-04-28 05:07:37', '1', 'COLOR', '0', '100', '1');
INSERT INTO `setting` VALUES ('fpmbdc8b-9cd8-4839-b38a-1cea3ceb3945', 'FONT_FAMILY', '\"Lantinghei SC\", \"Open Sans\", Arial, \"Hiragino Sans GB\", \"Microsoft YaHei\", \"STHeiti\", \"WenQuanYi Micro Hei\", SimSun, sans-serif;', '网站字体', '2016-06-06 05:07:37', '1', 'FONTFAMILY', '0', '77', '1');
INSERT INTO `setting` VALUES ('fpmbdc8b-9cd8-4839-b38a-1cea3ceb3999', 'ADORN_COLOR', '#f82c1d', '装饰背景颜色:左侧导航条背景颜色 #1c2034', '2016-06-06 05:07:37', '1', 'COLOR', '0', '26', '1');
INSERT INTO `setting` VALUES ('fpmbdc8b-9cd8-4839-b38a-898343435462', 'INDEX_PAGE', 'dashboard.htm', '前端首页：只能以index.do、font/ 开头的url', '2017-06-11 09:57:39', '1', 'INDEXPAGE', '0', '66', '1');

-- ----------------------------
-- Table structure for source
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `id` varchar(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sequence` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `updateTime` timestamp NOT NULL DEFAULT '2015-12-31 00:00:00',
  `moduleId` varchar(50) NOT NULL DEFAULT '0' COMMENT '模块ID',
  `remark` varchar(5000) NOT NULL DEFAULT '' COMMENT '备注',
  `filePath` varchar(200) NOT NULL DEFAULT '' COMMENT '文件目录',
  `projectId` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `index_mod_seq_time` (`moduleId`,`sequence`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of source
-- ----------------------------

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'modules/sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'modules/job/schedule.html', null, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'modules/sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL DEFAULT '',
  `trueName` varchar(50) NOT NULL DEFAULT '' COMMENT '用户真实姓名或昵称',
  `roleId` varchar(1024) NOT NULL DEFAULT '',
  `roleName` varchar(1024) NOT NULL DEFAULT '',
  `auth` varchar(1024) NOT NULL DEFAULT '',
  `authName` varchar(1024) NOT NULL DEFAULT '',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `type` tinyint(4) NOT NULL DEFAULT '100' COMMENT '用户类型：1普通用户，100：管理员',
  `email` varchar(45) DEFAULT NULL,
  `avatarUrl` varchar(500) NOT NULL DEFAULT '' COMMENT '用户头像',
  `loginType` int(11) NOT NULL DEFAULT '0' COMMENT '0：账号登陆，1：github登陆，2：码云',
  `thirdlyId` varchar(100) DEFAULT NULL COMMENT '第三方唯一ID',
  `passwordSalt` varchar(20) DEFAULT NULL COMMENT '密码MD5盐',
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginType_userName` (`userName`,`loginType`),
  UNIQUE KEY `loginType_Email` (`email`,`loginType`),
  KEY `index_thirdlyId` (`thirdlyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'admin', '96e79218965eb72c92a549dd5a330112', '超级管理员', 'super,', '超级管理员,', '', '', '2016-03-29 04:24:00', '1', '0', '100', 'ihsantang@163.com', '', '0', '', null);
