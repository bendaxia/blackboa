# blackboa

#### 介绍
	blackboa是一款数据库元数据信息生成工具，根据信息生成html文件

#### 软件架构
	SpringBoot1.5.4
	swagger2.7.0


#### 安装教程

1. 修改src/main/resources目录下的application.properties配置文件


#### 使用说明

1. 配置好数据库信息后启动访问[http://localhost/swagger-ui.html](http://localhost/swagger-ui.html)
2. 接口/blackboa/data/table共有五个参数
	1. table 表名 必填
	2. title 标题 必填
	3. tableImplication 表说明 选填
	4. fileName 输出文件名 必填
	5. author 作者 必填

#### 日志

# 20190314 #
- 1.0.0版本发布
- 表信息生成

# 20190320 #
- 1.1.0版本发布
- 新增视图信息生成
- 新增存储过程信息生成

# 20190322 #
- 1.2.0版本发布
- 加入mysql、oralce、sqlserver支持
- 新增function明细
- 代码优化

#### 下载
- [v1.0.0](https://gitee.com/bendaxia/blackboa/releases/blackboa-v1.0.0)
- [v1.1.0](https://gitee.com/bendaxia/blackboa/releases/blackboa-v1.1.0) 
- [v1.2.0](https://gitee.com/bendaxia/blackboa/releases/blackboa-v1.2.0) 