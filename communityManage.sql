drop database  if exists `communityManage`;


CREATE DATABASE `communityManage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use communityManage;

drop table if exists `user`;
create table `user`(
    `id` int(11) not null AUTO_INCREMENT comment '用户唯一id--主键',
    `username` varchar(30)  comment '用户名不能重复',
    `password` varchar(255)  comment '用户密码',
    `sex` varchar(16) DEFAULT NULL comment '用户性别',
    `dept` varchar(64)  comment '所属院系',
    `phone` varchar(22)  comment '联系电话',
    `name` varchar(64)  comment '姓名',
    `image` varchar(255) DEFAULT NULL comment '用户头像',
    `grade` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `period` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `community`;
create table `community`(
    `id` int(11) not null AUTO_INCREMENT comment '社团编号',
    `name` varchar(64)  comment '社团名称',
    `description` text  comment '社团简介',
    `u_id` varchar(64)  comment '社长',
    `type_id` int DEFAULT NULL comment '社团类型',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    primary key (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


drop table if exists `activity`;
create table `activity`(
    `id` int(11) not null AUTO_INCREMENT comment '活动编号',
    `name` varchar(255)  comment '活动名称',
    `views` int DEFAULT NULL comment '浏览量',
    `content` text  comment '活动简介',
    `u_id` int DEFAULT NULL comment '活动发布者id',
    `community_id` int(11)  comment '举办活动的社团编号',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    primary key (id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `apply`;
create table `apply`(
    `id` int(11) not null AUTO_INCREMENT comment '申请编号',
    `u_id` int(11)  comment '申请人id',
    `community_id` int(11)  comment '申请社团的id',
    `content` text  comment '申请理由',
    `state` enum('0','1') comment '申请状态',
    `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '申请时间',
    primary key (id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `notice`;
create table `notice`(
    `id` int(11) not null AUTO_INCREMENT comment '通知编号',
    `title` varchar(64)  comment '通知的标题',
    `content` text comment '通知的内容',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
    `u_id` int(11)  comment '通知发布者id',
    `community_id` int(11)  comment '通知所属社团编号',
    primary key (id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `transaction`;
create table `transaction`(
    `id` int(11) not null AUTO_INCREMENT comment '交易编号',
    `type` varchar(16)  comment '交易类型(支出，收入)',
    `activity_id` int(11)  comment '活动编号',
    `money` double default null comment '交易金额',
    `remark` varchar(255) default null comment '交易备注',
    primary key (id)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `message`;
create table `message`(
    `id` int(11) not null AUTO_INCREMENT  PRIMARY KEY comment '留言id',
    `content` text  comment '留言内容',
    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
    `reply_time` timestamp null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '回复时间',
    `u_id` int(11)  comment '留言人id',
    `rpy_u_id` int(11)  comment '回复人id',
    `rpy_cnt` longtext comment '回复内容'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `role`;
create table `role`(
    `id` int(2) not null comment '角色id',
    `name` varchar(30)  comment '角色名称',
    `description` varchar(100) comment '角色介绍'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `department`;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT comment '部门id',
  `name` varchar(255) DEFAULT NULL comment '部门名称',
  `description` longtext comment '部门简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `type`;
CREATE TABLE `type` (
  `id` int NOT NULL AUTO_INCREMENT comment '类型id',
  `name` varchar(255) DEFAULT NULL comment '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




drop table if exists `user_role`;
create table `user_role`(
    `id` int(11) not null AUTO_INCREMENT comment 'id',
    `u_id` int(11) not null comment '用户id',
    `r_id`  int(11) not null comment '角色id',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

drop table if exists `user_community`;
create table `user_community`(
    `u_id` int(11) not null comment '用户id',
    `community_id` int(11) not null comment '社团id'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;


drop table if exists `community_activity`;
create table `community_activity`(
    `community_id` int(11) not null comment '社团id',
    `activity_id` int(11) not null comment '活动id'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;


insert into user (id, username, password, sex, dept, phone, name, image, grade, period) VALUES (null,'admin','123456','man','计算机与科学','123456789','陈奕迅','  ','大一',null);


insert into user (id, username, password, sex, dept, phone, name, image, grade, period) VALUES (null,'user','123456','man','计算机与科学','123456789','周杰伦','  ','大一',null);

insert into role (id, name, description) VALUES (1,'user','普通用户');
insert into role (id, name, description) VALUES (2,'admin','系统管理员');

insert into user_role (u_id, r_id) VALUES (1,2);
insert into user_role (u_id, r_id) VALUES (1,1);
insert into user_role (u_id, r_id) VALUES (2,1);


insert into community values (null,'动漫社','我是动漫社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'文学社','我是文学社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'吉他社','我是吉他社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'轮滑社','我是轮滑社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'爱心社','我是爱心社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'民乐社','我是民乐社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'戏剧社','我是戏剧社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'书画社','我是书画社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'跆拳道社','我是跆拳道社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'网球社','我是网球社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'读书社','我是读书社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'围棋社','我是围棋社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'羽毛球社','我是羽毛球社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'乒乓球社','我是乒乓球社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'篮球社','我是篮球社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'足球社','我是足球社','1',1,'2021-05-22 19:36:00');
insert into community values (null,'排球社','我是排球社','1',1,'2021-05-22 19:36:00');

insert into type values (null,'体育类');
insert into type values (null,'学术类');
insert into type values (null,'志愿服务类');
insert into type values (null,'外语类');
insert into type values (null,'艺术类');

insert into user_community values (1,1);
insert into user_community values (1,2);
insert into user_community values (2,1);
insert into user_community values (2,2);
insert into user_community values (2,3);
insert into user_community values (1,5);


