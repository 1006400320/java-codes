#!/bin/sh
echo +----------------------------------------------------------+
echo + A tools for code generator:  +
echo +----------------------------------------------------------+
if [ ! -d "src" ]; then
  mkdir -p src/main/java
  mkdir -p src/main/resources
fi
java -cp extend-1.0.jar:mybatis-generator-core-1.3.6.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite





