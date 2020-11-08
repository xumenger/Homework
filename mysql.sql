CREATE DATABASE SortTest;

use SortTest;

-- TestMain 表
CREATE TABLE IF NOT EXISTS `TestMain`(
   `testId` BIGINT AUTO_INCREMENT,   -- 主键
   `testType` VARCHAR(50),           -- 排序算法
   PRIMARY KEY ( `testId` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TestDetail 表
CREATE TABLE IF NOT EXISTS `TestDetail`(
   `detailId` BIGINT AUTO_INCREMENT,   -- 主键
   `testId` BIGINT NOT NULL,           -- 外键，关联TestMain
   `testData`  VARCHAR(1023),          -- 测试数据集合(数据之间用,连接)
   `biginTime` datetime,               -- 测试开始时间
   `endTime` datetime,                 -- 测试结束时间
   PRIMARY KEY ( `detailId` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;