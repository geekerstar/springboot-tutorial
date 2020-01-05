### Mybatis-Plus

---

0. 概述
1. 逻辑删除
2. 自动填充
3. 乐观锁
4. 性能分析
5. 多租户
6. 动态表
7. SQL注入器

---

1.1 逻辑删除

1.1.1 添加``application``配置
```yaml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-value: 1
      logic-not-delete-value: 0
```

1.1.2 创建Configuration
```java
@Configuration
public class MybatisPlusConfiguration {

    /*
     * 3.1.1以下需要配置
     */

    /*@Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }*/

}
```

1.1.3 实体类加注解
```java
    @TableLogic
    private Integer deleted;
```

1.1.4 测试
```java
    @Test
    public void deletedById() {
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println(rows);
    }
```

1.1.5 结果
```
DEBUG==>  Preparing: UPDATE user SET deleted=1 WHERE id=? AND deleted=0 
DEBUG==>  Parameters: 1094592041087729666(Long)
DEBUG<==  Updates: 1
```

2.1 自动填充

2.1.1 实体类加注解
```java
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
```

2.1.2 创建填充处理器
```java
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insertFill");
        setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("updateFill");
        setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}

```

2.1.3 测试
```java
    @Test
    public void insert() {
        User user = User.builder().name("自动填充").age(20).email("111@qq.com")
                .managerId(1094592041087729666L).build();
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void updateById() {
        User user = User.builder().name("自动填充").age(22).email("111@qq.com")
                .id(1094592041087729666L).build();
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }
```

2.1.4 结果
```
insertFill
DEBUG==>  Preparing: INSERT INTO user ( id, name, age, email, manager_id, create_time ) VALUES ( ?, ?, ?, ?, ?, ? ) 
DEBUG==> Parameters: 1171669303842385921(Long), 自动填充(String), 20(Integer), 111@qq.com(String), 1094592041087729666(Long), 2019-09-11T14:18:25.261(LocalDateTime)
DEBUG<==    Updates: 1

updateFill
DEBUG==>  Preparing: UPDATE user SET name=?, age=?, email=?, update_time=? WHERE id=? AND deleted=0 
DEBUG==> Parameters: 自动填充(String), 22(Integer), 111@qq.com(String), 2019-09-11T14:21:15.261(LocalDateTime), 1094592041087729666(Long)
DEBUG<==    Updates: 0
```

2.1.5 优化填充处理器
```java
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            System.out.println("insertFill");
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateFill", metaObject);
        if (val == null) {
            System.out.println("updateFill");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
```

3.1 乐观锁

3.1.1 配置乐观锁插件
```java
@Configuration
public class MybatisPlusConfiguration {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
```

3.1.2 实体类加注解
```java
    @Version
    private Integer version;
```

3.1.3 测试
```java
    @Test
    public void updateById() {
        User res = userMapper.selectById(1171669303842385921L);

        User user = User.builder().name("自动填充").age(22).email("111@baomidou.com")
                .id(1171669303842385921L).version(res.getVersion()).build();
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }
```

3.1.4 结果
```
DEBUG==>  Preparing: SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user WHERE id=? AND deleted=0 
DEBUG==> Parameters: 1171669303842385921(Long)
TRACE<==    Columns: id, name, age, email, manager_id, create_time, update_time, version
TRACE<==        Row: 1171669303842385921, 自动填充, 20, 111@qq.com, 1094592041087729666, 2019-09-11 06:18:25, null, 1
DEBUG<==      Total: 1
updateFill
DEBUG==>  Preparing: UPDATE user SET name=?, age=?, email=?, update_time=?, version=? WHERE id=? AND version=? AND deleted=0 
DEBUG==> Parameters: 自动填充(String), 22(Integer), 111@baomidou.com(String), 2019-09-11T14:36:39.911(LocalDateTime), 2(Integer), 1171669303842385921(Long), 1(Integer)
DEBUG<==    Updates: 1
1
```

3.1.5 特别说明:

> - 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
> - 整数类型下 newVersion = oldVersion + 1
> - newVersion 会回写到 entity 中
> - 仅支持 updateById(id) 与 update(entity, wrapper) 方法
> - 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!

4.1 性能分析

4.1.1 添加插件
```java
    /**
     * SQL执行效率插件
     * <p>
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
```

4.1.2 测试

配置运行环境``-Dspring.profiles.active=dev``

结果
```
DEBUG==>  Preparing: UPDATE user SET deleted=1 WHERE id=? AND deleted=0 
DEBUG==> Parameters: 1094592041087729666(Long)
DEBUG<==    Updates: 0
 Time：0 ms - ID：com.don.mp.dao.UserMapper.deleteById
Execute SQL：UPDATE user SET deleted=1 WHERE id=1094592041087729666 AND deleted=0
```

4.2 SQL分析

4.2.1 p6spy 依赖引入
```xml
  <dependency>
    <groupId>p6spy</groupId>
    <artifactId>p6spy</artifactId>
    <version>最新版本</version>
  </dependency>
```

4.2.2 application.yml 配置
```yaml
spring:
  datasource:
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:h2:mem:test
    ...
```

4.2.3 spy.properties 配置
```properties
module.log=com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory
# 自定义日志打印
logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
#日志输出到控制台
appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger
# 使用日志系统记录 sql
#appender=com.p6spy.engine.spy.appender.Slf4JLogger
# 设置 p6spy driver 代理
deregisterdrivers=true
# 取消JDBC URL前缀
useprefix=true
# 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.
excludecategories=info,debug,result,batch,resultset
# 日期格式
dateformat=yyyy-MM-dd HH:mm:ss
# 实际驱动可多个
#driverlist=org.h2.Driver
# 是否开启慢SQL记录
outagedetection=true
# 慢SQL记录标准 2 秒
outagedetectioninterval=2
```

4.2.4 执行输出
```
DEBUG==>  Preparing: SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user WHERE deleted=0 
DEBUG==> Parameters: 
 Consume Time：20 ms 2019-09-11 15:15:50
 Execute SQL：SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user WHERE deleted=0
```

4.3 注意！
> - driver-class-name 为 p6spy 提供的驱动类
> - url 前缀为 jdbc:p6spy 跟着冒号为对应数据库连接地址
> - 该插件有性能损耗，不建议生产环境使用。

5.1 多租户SQL解析器

5.1.1 执行
```
DEBUG==>  Preparing: SELECT id, name, age, email, manager_id, create_time, update_time, version FROM user WHERE user.manager_id = 1171669303842385921 AND deleted = 0 
DEBUG==> Parameters: 
Consume Time：18 ms 2019-09-11 15:32:26
Execute SQL：SELECT id, name, age, email, manager_id, create_time, update_time, version FROM user WHERE user.manager_id = 1171669303842385921 AND deleted = 0

DEBUG<==      Total: 0
```

6.1 动态表

6.1.1 PaginationInterceptor
```java
    /*
     动态表名 SQL 解析器
     */
    DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
    dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(2) {{
        put("user", (metaObject, sql, tableName) -> {
            // metaObject 可以获取传入参数，这里实现你自己的动态规则
            return myTableName.get();
        });
    }});
    sqlParserList.add(dynamicTableNameParser);
```

6.1.2 测试
```java
    @Test
    public void select() {
        MybatisPlusConfiguration.myTableName.set("user_2019");

        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
```

6.1.3 结果
```
DEBUG==>  Preparing: SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user_2019 WHERE deleted=0 
DEBUG==> Parameters: 
```

7.1 Sql注入器

7.1.1 DeleteAllMethod
```java
public class DeleteAllMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 执行的sql
        String sql = "delete from " + tableInfo.getTableName();
        // mapper接口方法名
        String method = "deleteAll";

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
```

7.1.2 MySqlInjector
```java
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());
        return methodList;
    }

}
```

7.1.3 mapper
```java
int deleteAll();
```

7.1.4 测试

7.2 选装件InsertBatchSomeColumn

7.2.1 MySqlInjector
```java
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete()));
        return methodList;
    }

}
```

7.2.2 mapper
```java
int insertBatchSomeColumn(List<User> list);
```

7.2.3 测试
```java
    @Test
    public void insertBatchSomeColumn() {
        User user1 = User.builder().name("自动填充11").age(20).email("111@qq.com")
                .managerId(1094592041087729666L).build();
        User user2 = User.builder().name("自动填充22").age(20).email("111@qq.com")
                .managerId(1094592041087729666L).build();
        List<User> list = Arrays.asList(user1, user2);
        int rows = userMapper.insertBatchSomeColumn(list);
        System.out.println(rows);
    }
```
```
DEBUG==>  Preparing: INSERT INTO user (id,name,age,email,manager_id,create_time,update_time,version) VALUES (?,?,?,?,?,?,?,?) , (?,?,?,?,?,?,?,?) 
DEBUG==> Parameters: 1171723746591907841(Long), 自动填充11(String), 20(Integer), 111@qq.com(String), 1094592041087729666(Long), 2019-09-11T17:54:45.430(LocalDateTime), null, null, 1171723746633850882(Long), 自动填充22(String), 20(Integer), 111@qq.com(String), 1094592041087729666(Long), 2019-09-11T17:54:45.430(LocalDateTime), null, null

```
