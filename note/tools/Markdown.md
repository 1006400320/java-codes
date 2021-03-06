
#### 标题
```markdown
# 一级标题
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题
```

#### 字体
**粗体**  
~~删除线~~  
*斜体*  


#### 列表
```markdown
- 无序列表a
- 无序列表b
- 无序列表c


1. 有序列表1
2. 有序列表2
3. 有序列表3
```
####  水平线
---


#### 文字超链接
[文字超链接](http://www.baidu.com)


#### 图片
![图片](http://ww1.sinaimg.cn/large/005CzYvJgy1gcyaa9r5ojj30qo16gdh0.jpg)

####  引用
> 我就是引用了

####  代码
```
代码块
```
`行内代码`


####  空格
```markdown
空格： &nbsp; 
```

#### 复选框
- [ ] a
- [x] b

#### 表格

name | age | sex | address | phone
:-: | :-: | :-: | :-: | :-:
张三 | 18 | 男 | 阿拉斯加 | 110 
李四 | 81| 女 | 冰岛 | 112 

#### 流程图、时序图、甘特图
##### mermaid 画图
[mermaid官网文档](https://mermaid-js.github.io/mermaid/#/flowchart)  
> <span style="color:red">tips: vscode可以装插件 `Markdown Preview Mermaid Support` 方便查看 `mermaid` 画的图</span>
> 1. 修改设置：
![markdown_mermaid_setting.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gds0n4t0xlj30mk0cpaci.jpg)
##### 1. 横向流程图源码格式：
```mermaid
%% graph LR 横向
graph LR
A[方形] -->B(圆角)
    B --> C{条件a}
    C -->|a=1| D[结果1]
    C -->|a=2| E[结果2]
    F[横向流程图]
```

##### 2. 竖向流程图源码格式：

```mermaid
%% graph TD 竖向
graph TD
A[方形] --> B(圆角)
    B --> C{条件a}
    C --> |a=1| D[结果1]
    C --> |a=2| E[结果2]
    F[竖向流程图]
```
##### 3. 标准流程图源码格式：

```flow
st=>start: 开始框
op=>operation: 处理框
cond=>condition: 判断框(是或否?)
sub1=>subroutine: 子流程
io=>inputoutput: 输入输出框
e=>end: 结束框
st->op->cond
cond(yes)->io->e
cond(no)->sub1(right)->op
```

#####  4. 标准流程图源码格式（横向）：

```flow
st=>start: 开始框
op=>operation: 处理框
cond=>condition: 判断框(是或否?)
sub1=>subroutine: 子流程
io=>inputoutput: 输入输出框
e=>end: 结束框
st(right)->op(right)->cond
cond(yes)->io(bottom)->e
cond(no)->sub1(right)->op
```
##### 5. UML时序图源码样例：

```sequence
对象A->对象B: 对象B你好吗?（请求）
Note right of 对象B: 对象B的描述
Note left of 对象A: 对象A的描述(提示)
对象B-->对象A: 我很好(响应)
对象A->对象B: 你真的好吗？
```
##### 6. UML时序图源码复杂样例：

```sequence
Title: 标题：复杂使用
对象A->对象B: 对象B你好吗?（请求）
Note right of 对象B: 对象B的描述
Note left of 对象A: 对象A的描述(提示)
对象B-->对象A: 我很好(响应)
对象B->小三: 你好吗
小三-->>对象A: 对象B找我了
对象A->对象B: 你真的好吗？
Note over 小三,对象B: 我们是朋友
participant C
Note right of C: 没人陪我玩
```
##### 7. UML标准时序图样例：

```mermaid
%% 时序图例子,-> 直线，-->虚线，->>实线箭头
  sequenceDiagram
    participant 张三
    participant 李四
    张三->王五: 王五你好吗？
    loop 健康检查
        王五->王五: 与疾病战斗
    end
    Note right of 王五: 合理 食物 <br/>看医生...
    李四-->>张三: 很好!
    王五->李四: 你怎么样?
    李四-->王五: 很好!
```
##### 8. 甘特图样例：

```mermaid
%% 语法示例
        gantt
        dateFormat  YYYY-MM-DD
        title 软件开发甘特图
        section 设计
        需求                      :done,    des1, 2014-01-06,2014-01-08
        原型                      :active,  des2, 2014-01-09, 3d
        UI设计                     :         des3, after des2, 5d
    未来任务                     :         des4, after des3, 5d
        section 开发
        学习准备理解需求                      :crit, done, 2014-01-06,24h
        设计框架                             :crit, done, after des2, 2d
        开发                                 :crit, active, 3d
        未来任务                              :crit, 5d
        耍                                   :2d
        section 测试
        功能测试                              :active, a1, after des3, 3d
        压力测试                               :after a1  , 20h
        测试报告                               : 48h
```

#### 数学公式
$$
\mathbf{V}_1 \times \mathbf{V}_2 =  \begin{vmatrix}
\mathbf{i} & \mathbf{j} & \mathbf{k} \\
\frac{\partial X}{\partial u} &  \frac{\partial Y}{\partial u} & 0 \\
\frac{\partial X}{\partial v} &  \frac{\partial Y}{\partial v} & 0 \\
\end{vmatrix}
${$tep1}{\style{visibility:hidden}{(x+1)(x+1)}}
$$





