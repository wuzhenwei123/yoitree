
部署说明
1. 数据库文件 : src/com/conf/db_mysql.properties
2. 系统文件配置：src/com/conf/system.linux.properties/system.windows.properties
	2.1 upload_file_root 文件上传跟目录
	2.1 head_path 头像上传目录，绝对路径是upload_file_root + head_path
3.	tomcat配置
	3.1 server.xml  <Context docBase="D:/axt/online/pic" path="/pic" reloadable="true"/>  docBase路径是 配置文件里面的跟目录(upload_file_root)
4.	开发页面DEMO
	4.1 webapp/demo  里面都是静态页面demo 如果在开发中遇到要添加的页面元素，可以去这里面查找
5.	快速开发
	5.1 src/com/base/production/Production.java 是用来构建基本组件入口，根据里面配置，可以快速构建一个模块
	5.2 数据库配置在 jdbc/JDBCTemplate
	