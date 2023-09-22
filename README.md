

## 依赖

- teclan-easyexcel 
``` 
git lone https://github.com/teclan/teclan-easyexcel
cd teclan-easyexcel
mvn install -D maven.test.skip=true
```
在 pom.xml 的 `<dependencies>` 块中添加如下依赖

``` 
<dependency>
    <groupId>com.teclan</groupId>
    <artifactId>teclan-easyexcel</artifactId>
    <version>0.1.0</version>
</dependency>
```

## 配置文件 

见`{project.dir}/conf/application.json` 

```json
 {
  "database": "mysql",

  "mysql": {
    "driver": "com.mysql.cj.jdbc.Driver",
    "url":"jdbc:mysql://localhost:3306/cwl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC",
    "username": "teclan",
    "password":"123..."
  },

  "h2": {
    "driver": "org.h2.Driver",
    "url":"jdbc:h2:file:db/lottery",
    "username": "teclan",
    "password":"123..."
  }

}
```

```mysql
# 获取 mysql 数据文件路径 
show variables like '%datadir%';
```