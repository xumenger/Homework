项目直接导入到Eclipse 中

## 测试1

TestSort.java 是测试IntegerSort 和UserInfoSort 是否正确的测试类

## 创建数据库和数据表

要求先安装MySQL

```sql
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
```

## 最终完成

Homework.java 实现了作业最终的要求

test1()、test2()、test3()、test4() 分别实现了每个题目

