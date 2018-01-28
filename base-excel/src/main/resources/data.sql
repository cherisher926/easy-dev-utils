DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `avatar` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `last_login_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态字段',
  `role` varchar(30) NOT NULL DEFAULT '1' COMMENT '角色字段',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (`id`, `username`, `email`, `avatar`, `last_login_date`, `status`, `role`, `gmt_create`, `gmt_update`)
VALUES
	(1,'IT小生','niudear@foxmail.com','https://avatars3.githubusercontent.com/u/13889883?v=4','2017-09-16 09:47:01',1,'1','2017-09-09 11:02:02','2017-09-09 11:02:02');

INSERT INTO `user` (`id`, `username`, `email`, `avatar`, `last_login_date`, `status`, `role`, `gmt_create`, `gmt_update`)
VALUES
	(2,'IT大生','quding@foxmail.com','https://avatars3.githubusercontent.com/u/13889883?v=4','2017-09-16 09:47:01',1,'1','2017-09-09 11:02:02','2017-09-09 11:02:02');

