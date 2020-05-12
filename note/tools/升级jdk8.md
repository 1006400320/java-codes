0、引言

> 最近公司打算JDK(1.7--> 1.8) 特此记录下 

#### 1、 查看正在运行的程序，关闭后升级

```shell script
ps -ef|grep tomcat

# 关闭
./xxx/apache-tomcat-xxx/bin/shutdown.sh
```

#### 2、 下载解压jdk8  

```shell script
wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141-linux-x64.tar.gz"
```


#### 3、修改配置文件

```shell script
vim /etc/profile
# 修改 JAVA_HOME

source /etc/profile

# 检查java版本
java -version
```

#### 4、修改maven配置文件 pom.xml

```xml
	<build>
        <!-- 省略... -->
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.6.1</version>
				<configuration>
					<source>8</source>
					<target>8</target>
				</configuration>
			</plugin>
		</plugins>
     </build>
```



#### 5、tomcat配置文件修改

##### 5.1 Tomcat不指定jdk版本

```shell
vim apache-tomcat-xxx/bin/setclasspath.sh
```

<span style="color:red">如果这里设置了JAVA_HOME，一定要注释掉（如下图）</span>

![1.png](http://ww1.sinaimg.cn/large/005CzYvJgy1ge9cz83rqcj30k9085gm8.jpg)

![2.png](F:\temp_photo\2)

##### 5.2 jvm 参数修改（ catalina.sh）

**在JDK1.8中，取消了PermGen，取而代之的是Metaspace，所以PermSize和MaxPermSize参数失效，取而代之的是元空间，原始配置如下：**

```shell script
JAVA_OPTS=``" -server -Xms2048m -Xmx2048m -Xss1024k -XX:PermSize=256m -XX:MaxPermSize=512m"
```

**所以，升级JDK之后应该改成如下配置：**

```shell script
JAVA_OPTS=``" -server -Xms2048m -Xmx2048m -Xss1024k -XX:Metaspace=256m -XX:MaxMetaspace=512m"
```

#### 6、确保项目spring版本为4.x+

**检查项目使用的spring版本，如果低于4.x，需要改成4.x+**

因为jdk8 不兼容 spring 3.x

#### 7、重启项目



#### 8、配置可切换java版本（配置了后不好删除，不要随意加）

```shell script
# /etc/profile.d
export JAVA_HOME=/usr/local/jdk1.8.0_11
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
PATH=$PATH:$JAVA_HOME/bin

# 添加进 alternatives
alternatives --install /usr/bin/java java /usr/local/jdk1.8.0_11/bin/java 3
alternatives --install /usr/bin/java java /usr/local/jdk1.7.0_67/bin/java 4

# 切换java版本
alternatives --config java
 
# 从 alternatives 移除
alternatives --remove java /usr/local/jdk1.7.0_67/bin/java
alternatives --remove java /usr/local/jdk1.8.0_11/bin/java
```





































