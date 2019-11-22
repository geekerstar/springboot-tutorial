## fastjson

#### 配置fastjson

> 方法一
- 1、启动类继承``extends WebMvcConfigurerAdapter``
- 2、覆盖方法``configureMessageConverters``


> 方法二
- 1、在启动类中注入``Bean:HttpMessageConverters``

> 启动访问
```json
{
"createTime": "2019-08-09 17:09",
"id": 1,
"name": "Demo"
}
```
