
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

### 防火墙
- 查看防火墙开放的端口 firewall-cmd --list-all
- 设置防火墙开放的端口号
  - firewall-cmd --add-service=http -permanent
  - firewall-cmd --add-port=80/tcp --permanent
- 重启防火墙 firewall-cmd -reload

### [find](https://www.runoob.com/linux/linux-comm-find.html)
- 查找目录：`find /（查找范围） -name '查找关键字' -type d`
- 查找文件：`find /（查找范围） -name '*查找关键字*' `



###  curl ly.mama.cn/main/index

### 传输文件
上传   rz   receive zmodem
下载   sz	send zmoden

###  强制杀死进程
kill -9 pid

### ps
ps -ef
ps aux | grep xxx

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

	----------------------- Linux ---vim---------------------------		
	[shell]vim格式化代码与块缩进
	 
	(1) 按两下小写g，即gg，定位光标到第一行。
	(2) 按住Shift+v，即大写V，进入可视化编辑的列编辑模式。
	(3) Shift+g，即大写G，选中整个代码。
	(4) 按下等号=，格式化所有代码。
	
	u:撤销
	Ctrl + R:回退
	
	vim/vi 搜索（/searchword） --> 回车 --> 按n选择下一个（按N选择上一个）
	
	dd:删除游标所在的一整行(常用)

	u : 撤回上一次编辑
	Ctrl+R : 重复上一次编辑

#
- `cp -r a b` ：复制文件夹