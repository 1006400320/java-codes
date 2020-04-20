
# 常用命令

##  查看系统参数
- 一、查看CPU信息
    - lscpu
    - cat /proc/cpuinfo

- 二、查看CPU位数  getconf LONG_BIT

- 三、查看内存使用情况
    - free -m
    - cat /proc/meminfo

- 四、查看系统内核版本
    - uname -a
    - uname -r
    - more /etc/*release
    - more /etc/issue
    - more /proc/version

- 五、查看磁盘信息
    - df -h
    - cd /data && du -h --max-depth=1
    - du -h --max-depth=1
    - du -h
    - lsblk
    - fdisk -l

- 六、查看网络信息
    - cat /etc/sysconfig/network-scripts/ifcfg-eth0
    - cat /etc/sysconfig/network-scripts/ifcfg-l0
    - ifconfig
    - 查看网络带宽：
      - ethtool eth0，查看SPEED参数

- 七、查看服务器名称  hostname

- 八、查看操作系统版本  cat /proc/version

- 九、[Linux top命令详解](https://www.cnblogs.com/ftl1012/p/top.html)

## 防火墙
- 查看防火墙开放的端口 firewall-cmd --list-all
- 设置防火墙开放的端口号
  - firewall-cmd --add-service=http -permanent
  - firewall-cmd --add-port=80/tcp --permanent
- 重启防火墙 firewall-cmd -reload

- 关闭防火墙 systemctl stop firewalld.service
- 查看防火墙的状态 firewall-cmd --state
- 禁止firewall开机启动 systemctl disable firewalld.service

## 虚拟机网络
```shell script

vim /etc/sysconfig/network-scripts/ifcfg-eth0
```

## java环境变量
```bash
#java environment
export JAVA_HOME=/usr/java/jdk1.8.0_144
export CLASSPATH=.:${JAVA_HOME}/jre/lib/rt.jar:${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar
export PATH=$PATH:${JAVA_HOME}/bin
```

## [find](https://www.runoob.com/linux/linux-comm-find.html)
- 查找目录：`find /（查找范围） -name '查找关键字' -type d`
- 查找文件：`find /（查找范围） -name '*查找关键字*' `



##  curl
curl ly.mama.cn/main/index

## 传输文件
- 上传   rz   receive zmodem
- 下载   sz	send zmoden

##  强制杀死进程
kill -9 pid

## scp
###  1. 安装scp
 yum install -y openssh-clients
###  2.命令参数
OPTIONS：
-v 和大多数 linux命令中的-v意思一样，用来显示进度。可以用来查看连接、认证、或是配置错误
-C 使能压缩选项
-P 选择端口
-r 复制目录

###  3.命令
- 复制文件（本地>>远程）：scp /cloud/data/test.txt root@10.21.156.6:/cloud/data/
- 复制文件（远程>>远程）：scp root@10.21.156.6:/cloud/data/test.txt /cloud/data/
- 复制目录（本地>>远程）：scp -r /cloud/data root@10.21.156.6:/cloud/data/
- 复制目录（远程>>本地）：scp -r root@10.21.156.6:/cloud/data/  /cloud/data/

## ps
ps -ef
ps aux | grep xxx

## npm
### 设置镜像
```shell script
# 查看registry
npm config get registry
http://registry.npmjs.org  # 默认registry

npm config set registry http://registry.npm.taobao.org  //将registry设置为淘宝镜像

# 注意
# 如果自己本地已经设置为淘宝镜像，在publish本地node包时要改回原地址 不然会报401错误
npm config set registry http://registry.npmjs.org 
```
### 常用命令

- `npm install <name>`安装nodejs的依赖包  
   eg：npm install express 就会默认安装express的最新版本，也可以通过在后面加版本号的方式安装指定版本，如npm install express@3.0.6
- `npm install <name> -g`  将包安装到全局环境中  
   但是代码中，直接通过require()的方式是没有办法调用全局安装的包的。全局的安装是供命令行使用的，就好像全局安装了vmarket后，就可以在命令行中直接运行vm命令
- `npm install <name> --save`  安装的同时，将信息写入package.json中  
   项目路径中如果有package.json文件时，直接使用npm install方法就可以根据dependencies配置安装所有的依赖包 
   这样代码提交到github时，就不用提交node_modules这个文件夹了。
- `npm init`  会引导你创建一个package.json文件，包括名称、版本、作者这些信息等
- `npm remove <name>`移除
- `npm update <name>`更新
- `npm ls` 列出当前安装的了所有包
- `npm root` 查看当前包的安装路径
- `npm root -g`  查看全局的包的安装路径
- `npm help`  帮助，如果要单独查看install命令的帮助，可以使用的npm help install

### npm安装后，运行显示【不是内部或外部命令】

如：安装vue

```shell
cnpm install -g @vue/cli

# 安装成功后
vue -V 
'vue' 不是内部或外部命令
```

需要把以下目录文件配置到环境变量中。：

- 安装的prefix目录
- 安装的prefix目录下的 node_global（这个跟上一个可能是一样的）
- 安装的prefix目录下的 node_cache
- 安装的prefix目录下的 node_modules

查看第一个目录

```shell
npm config get prefix
C:\Program Files\nodejs\node-global
```

在这个文件夹，或者是外面的一层的文件夹下，就有其他几个文件夹了（也找到对应的目录放进去就好了）

几个文件夹都是放在类似位置的。

比如：

```
C:\Program Files\nodejs\node_modules
C:\Program Files\nodejs\node-global
C:\Program Files\nodejs\npm_cache
C:\Program Files\nodejs\npm_modules
```

全部添加完**环境变量**之后，在**重启cmd**，就是可以使用vue了。



查看端口
netstat -a
netstat -lntup

查看某个端口详细的信息：lsof -i:4000

sudo -i

Jenkins发包
sudo /data/script/tour/admin.sh origin/feature/LY-5248
sudo /data/script/tour/main.sh origin/feature/LY-5248

df -h


ll
由大到小排序: ls -Sl
从小到大排序: ls -Slr

时间从新到旧: ls -lt
时间从旧到新: ls -lrt

## vim

vim格式化代码与块缩进  
    (1) 按两下小写g，即gg，定位光标到第一行。
    (2) 按住Shift+v，即大写V，进入可视化编辑的列编辑模式。
    (3) Shift+g，即大写G，选中整个代码。
    (4) 按下等号=，格式化所有代码。

u:撤销
Ctrl + R:回退

vim/vi 搜索（/searchword） --> 回车 --> 按n选择下一个（按N选择上一个）

dd:删除游标所在的一整行(常用)
删除多行

1. 首先在命令模式下，输入`:set nu`显示行号；
2. 通过行号确定你要删除的行；
3. 命令输入“：32,65d”,回车键，32-65行就被删除了
如果无意中删除错了，可以使用‘u’键恢复（命令模式下）

u : 撤回上一次编辑

#

- `cp -r a b` ：复制文件夹