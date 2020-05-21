### 前端工具


#### sweetalert2


[文档](https://sweetalert2.github.io/v8.html)  
[github](https://github.com/sweetalert2/sweetalert2)


####[ javascript Date format(js日期格式化)](https://www.cnblogs.com/zhangpengshou/archive/2012/07/19/2599053.html)


#### [js实现replaceAll方法](https://blog.csdn.net/fukaiit/article/details/83245943)
``` javascript
// 给string对象添加原型方法replaceAll()
String.prototype.replaceAll = function(s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}

// 这样就可以像使用replace方法一样使用replaceAll方法：
var str = "dogdogdog";
var str2 = str.replaceAll("dog", "cat");
console.log(str2);
```
