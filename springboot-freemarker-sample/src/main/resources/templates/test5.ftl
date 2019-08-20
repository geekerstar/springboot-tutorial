<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test5</title>
</head>
<body>

<h1>自定义函数</h1>
<h2>1.自定义函数(整数排序sort_int)</h2>
<ul>
    <#assign myList=[2,5,9,1,0,6,3,1,10,4]/>
    <li>未排序</li>
    <#list myList as item>
        ${item},
    </#list>
    <br/>
    <li>排序</li>
    <#list sort_int(myList) as item>
        ${item},
    </#list>
</ul>
<hr size="10" color="red">

<h1>List的指令</h1>
<h2>1,list常用指令sort升序</h2>
<ul>
    <#assign myList=[0,3,7,9,1,4,8,6,2,5]/>
    <#list myList?sort as item>
        ${item_index}:${item}  <br/>
    </#list>

    <br/>
</ul>
<hr size="10" color="blue">

<h1>List的指令</h1>
<h2>2,list常用指令resverse倒序</h2>
<ul>
    <#assign myList1=[0,3,7,9,1,4,8,6,2,5]/>
    <#list myList1?sort?reverse as item>
        ${item_index}:${item}  <br/>
    </#list>
    长度：${myList1?size}<br/>
    下标取值：${myList1[3]}<br/>
    <br/>
</ul>


</body>
</html>
