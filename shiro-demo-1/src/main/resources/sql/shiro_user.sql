use shiro_demo;
set utf-8;

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `salt` int(11) default 0 COMMENT '盐(用于shiro给密码加密)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
