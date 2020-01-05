#创建用户表
CREATE TABLE USER (
	id BIGINT ( 20 ) PRIMARY KEY NOT NULL COMMENT '主键',
	NAME VARCHAR ( 30 ) DEFAULT NULL COMMENT '姓名',
	age INT ( 11 ) DEFAULT NULL COMMENT '年龄',
	email VARCHAR ( 50 ) DEFAULT NULL COMMENT '邮箱',
	manager_id BIGINT ( 20 ) DEFAULT NULL COMMENT '直属上级id',
	create_time DATETIME DEFAULT NULL COMMENT '创建时间',
	update_time DATETIME DEFAULT NULL COMMENT '修改时间',
	version INT ( 11 ) DEFAULT '1' COMMENT '版本',
	deleted INT ( 1 ) DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
	CONSTRAINT manager_fk FOREIGN KEY ( manager_id ) REFERENCES USER ( id )
) ENGINE = INNODB CHARSET = UTF8;#初始化数据：
INSERT INTO USER ( id, NAME, age, email, manager_id, create_time )
VALUES
	( 1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20' ),
	( 1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22' ),
	( 1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16' ),
	( 1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15' ),
	( 1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16' );