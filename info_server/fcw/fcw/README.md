环境及依赖
========

* CentOS 6.3+
* JDK 8
* Redis 3+

开发注意点
========

* GoSchema.SYS	系统类别
* GoSchema.MAIN	不需要分表类别
* GoSchema.EXT	分表类别


通信包结构
========

基础包
-----

客户端与代理服务器间、以及服务器间通信时，使用该结构
	
	length + 4{
		head(21314) + length + 4 + body
	 	  4			      1      		∞ 
	}

内部发布
======

该操作适合发布内部测试版本，只需在项目根目录执行以下Maven命令：

	mvn clean package

正确执行后，可在各子项目的“target”目录中获得发布包（.tar.gz）。

正式发布
======

发布准备
------

检出（CheckOut）待发布版本代码，修改项目配置文件（.properties），通过命令行进入该目录，执行以下Maven命令：

	mvn release:prepare -DautoVersionSubmodules=true -Dusername=username -Dpassword=password
	
其中“username”和“password”需替换为有效的用户名和密码。

该命令会提升版本号（中途将提示用户输入或选择默认值），并按照项目pom.xml文件内的定义创建相应SVN标签（tag）。

该命令将执行所有子工程的测试代码，可能会消耗较长时间。

打包
---

如果前一步正确执行完成， 再次执行以下Maven命令：

	mvn release:perform -Dgoals=package -Darguments="-Dmaven.test.skip=true"
	
该命令会检出上一步创建的标签，并执行“package”命令。

由于在创建标签前已经进行了测试，当前通过参数跳过测试步骤。

异常回滚
------

如果在“发布准备”阶段Maven命令未正确执行完成，必须执行以下命令用以回滚项目及SVN状态：

	mvn release:rollback

如果在“打包”阶段Maven命令未正确执行完成，除了必须执行上述命令外，还需要删除SVN上相关标签（tag）。

获取发布版本
--------

“发布准备”和“打包”均正常执行完成后，可在项目根目录下的“\target\checkout”内获取各子项目的发布包（.tar.gz）。

其它常用命令
---------

* mvn release:update-versions
* mvn release:clean
* mvn release:branch

TODO
====

* 虚拟机参数需要再调整，在性能测试时确认
* 对多客户端广播消息时，当前实现会反复调用“encode”方法，可以直接转化为ByteBuf进行优化。

