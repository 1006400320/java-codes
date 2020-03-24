### RESTful

-  Restful web service是一种常见的rest的应用,是遵守了rest风格的web服务;
- rest式的web服务是一种ROA(The Resource-Oriented Architecture)(**面向资源的架构**).




url | method | mean 
:-: | :-: | :-: | :-: | :-:
/emp/1  | GET | 查询id=1的emp
/emp/1  | DELETE | 删除id=1的emp,实验中直接删除会报405错误，但是采用$.ajax异步删除就没问题
/emp/1  | PUT | 更新emp
/emp/1  | POST | 新增emp