

# Maven vs Gradle

Maven缺点：

- XML格式的，XML的一个依赖至少需要5行，而gradle只需1行；
- XML文件不太灵活，不利于在构建过程中添加一些自定义逻辑；
- 对新版java支持不足，比如为了编译java11，需要更新内置的Maven插件。

gradle优点：

-  按约定声明构建和建设。

-  强大的支持多工程的构建。

-  强大的依赖管理（基于Apache Ivy），提供最大的便利去构建工程。

-  全力支持已有的 Maven 或者Ivy仓库基础建设。

-  支持传递性依赖管理，在不需要远程仓库和pom.xml和ivy配置文件的前提下。

-  基于groovy脚本构建，其build脚本使用groovy语言编写。

-  具有广泛的领域模型支持构建。

-  深度 API。

-  易迁移。

-   自由和开放源码，Gradle是一个开源项目，基于 ASL 许可。



### 示例汇总

这是示例代码汇总得到的一个完整脚本：

### Java 示例 - 一个完整构建脚本

```groovy
build.gradle
apply plugin: 'java'
apply plugin: 'eclipse'
sourceCompatibility = 1.5
version = '1.0'
jar {
    manifest {
        attributes 'Implementation-Title': 'Gradle Quickstart', 'Implementation-Version': version
    }
}
repositories {
    mavenCentral()
}
dependencies {
    compile group: 'commons-collections', name: 'commons-collections', version: '3.2'
    testCompile group: 'junit', name: 'junit', version: '4.+'
}
test {
    systemProperties 'property': 'value'
}
uploadArchives {
    repositories {
       flatDir {
           dirs 'repos'
       }
    }
}
```