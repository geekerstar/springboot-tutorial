<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test3</title>
    <style type="text/css">
        body {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>freemarker实现遍历List集合</h1>
<ul>
    <li>集合list</li>
    <#list mylist as item>
        <font color="red" size="20px">${item}</font>
        <br>
    </#list>
</ul>
<ul>
    <li>集合list实体对象</li>
    <#list student as item>
        <font color="red" size="10px">name=${item.name}</font>
        <font color="red" size="10px">name=${item.sex}</font>
        <br>
    </#list>
</ul>
<hr>

<h1>if表达式</h1>
<ul>
    <li>if</li>
    <#assign var =99/>
    <#if var ==99>
        <font color="red">var=99</font>
    </#if>
    <br>

    <#if var == 99>
        <font color="red">var=99</font>
    <#else>
        <font color="blue">var!=99</font>
    </#if>

    <br>

    <#if var &gt; 99>
        <font color="red">var大于99</font>
    <#elseif var==99>
        <font color="blue">var等于99</font>
    <#else>
        <font color="green">var小于99</font>
    </#if>

    <br>

    <li>if多条件 || && ！</li>
    <#assign var ='python'>
    <#if var == 'python' || var == 'java'>
        <font color="red">python or java</font><br>
    </#if>

    <#if var = 'python' && var?length ==6>
        <font color="#9acd32">python length 6</font><br>
    </#if>

    <#if (var == 'python' && var?length ==6)|| (var == 'java')>
        <font color="blue">Python length 6 或者 Java</font>
    </#if>

    <#if !((var == 'python' && var?length ==6) || (var =='java'))>
        <font color="#7fffd4">python length 6或者Java</font><br>
    </#if>

</ul>

</body>
</html>
