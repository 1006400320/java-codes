@echo off  
if exist src rd /S /Q src  
mkdir src\main\java  
mkdir src\main\resources  
java -cp plugins-1.0.jar;mybatis-generator-core-1.3.6.jar;slf4j-api-1.7.25.jar;dom4j-2.1.0.jar;jstl-1.2.jar;freemarker-2.3.26-incubating.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite
pause 