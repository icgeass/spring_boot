CREATE DATABASE `spring_boot` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `dict` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type` TINYINT(4) DEFAULT NULL COMMENT '字典类型，1，分类，2，标签，3，链接，4，历史，5，社交，6，站点信息，7，系统配置',
  `dict_key` VARCHAR(255) DEFAULT NULL COMMENT '字典键',
  `dict_value` VARCHAR(255) COMMENT '字典值',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `modified_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yn` TINYINT(4) DEFAULT NULL COMMENT '是否有效，1，有效，0，无效',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='字典';


insert  into `dict`(`id`,`dict_type`,`dict_key`,`dict_value`,`remark`,`modified_time`,`created_time`,`yn`) values (1,6,'title','Spring Boot Demo',NULL,'2020-04-22

