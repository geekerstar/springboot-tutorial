<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${username}</title>
    <style type="text/css">
        h1 {
            color: red;
            font-size: 40px;
        }

        hr {
            color: darkblue;
            font-size: 30px;
        }
    </style>
</head>
<body>
<h1>${username}</h1>
<#assign var="好好学习，天天向上！">
<font size="18px">${var}</font>

<hr size="10px" color="red">

<h1>字符串</h1>
<h2>字符串常用内建函数</h2>
<ul>
    <#assign a='hello'>
    <#assign b ='world'>
    <li>连接</li>
    <font size="18px" color="red">${a+b}</font>
    <br>
    <li>截取</li>
    <font size="18px" color="blue">${(a+b)?substring(5,8)}</font>
    <br>
    <li>长度</li>
    <font size="18px" color="#f0f8ff">${(a+b)?length}</font>
    <br>
    <li>大写</li>
    <font size="18px" color="#faebd7">${(a+b)?upper_case}</font>
    <br>
    <li>小写</li>
    <font size="18px" color="aqua">${(a+b)?lower_case}</font>
    <br>
    <li>index_of</li>
    <font color="#7fffd4" size="18px">${(a+b)?index_of('w')}</font>
    <br>
    <li>replace</li>
    <font size="18px" color="#ffe4c4">${(a+b)?replace('o','abc')}</font>
    <br>
    <li>last_index_of</li>
    <font color="#ff7f50" size="18px">${(a+b)?last_index_of('o')}</font>
</ul>

<hr>
<hr>
<hr>

<h1>内建函数</h1>
<h2>1、字符串内建函数</h2>
<ul>
    <#list "a|b|c|d"?split("|") as item>
        <li>${item}</li>
    </#list>
</ul>
<br>
<h2>字符串转日期</h2>
<ul>
    <#assign var1="12/12/2019" ? date("MM/dd/yyyy")/>
    <#assign var2="11:11:22" ? time("HH:mm:ss")/>
    <#assign var3="2019-08-02 11:11" ? datetime("yyyy-MM-dd HH:mm")/>
    <li>${var1}</li>
    <li>${var2}</li>
    <li>${var3}</li>
</ul>
<br>
<h2>数字类型的内建函数</h2>
<ul>
    <#assign numVar1=314.15926/>
    <li>格式化：${numVar1?string("0.##")}</li>
    <li>四舍五入：${numVar1?round}</li>
    <li>去掉小数点：${numVar1?floor}</li>
    <li>进1：${numVar1?ceiling}</li>
</ul>
<br>
<h2>list内建函数</h2>
<ul>
    <#assign listVar1=[1,2,3,4,5,6,7,8,9,10,11,12]/>

    <li>分块处理取长度：${listVar1?chunk(4)?size}</li>
    <li>长度：${listVar1?size}</li>
    <#list listVar1?chunk(4)?last as item>
        <li>${item}</li>
    </#list>
    <li>取list第一个值：${listVar1?first}</li>
    <li>取list最后一个值：${listVar1?last}</li>
</ul>
<br>
<h2>其他内建函数</h2>
<ul>
    <#assign sVar = 'helloworld'/>
    <li>${sVar?is_number?string('yes','no')}</li>
    <li>${sVar?has_content?string('yes','no')}</li>
    <li>${"1"+"2"?eval}</li>
    <li>${(1+2)?eval}</li>
    <li>${"1"+"2"?is_string?string('yes','no')}</li>
    <li>${("1"+"2")?is_string?string('yes','no')}</li>
</ul>

<hr>
<hr>
<hr>

<h1>macro,nested,return：实战demo</h1>
<h2>1.macro:宏指令</h2>
<ul>
    <li>FreeMarker:无参数的macro</li>
    <div>
        <#macro test>
            <font color="red" size="18px">无参数的macro</font>
        </#macro>
        <@test/>
    </div>

    <li>Freemarker2:有参数的macro</li>
    <div>
        <#macro test param1 param2>
            <font color="#ff7f50" size="18px">我是有参数的macro，param1=${param1},param2=${param2}</font>
            <br>
        </#macro>
        <@test param1="java" param2="python"/>
    </div>

    <li>Freemarker3:有参数的macro</li>
    <div>
        <#macro test param1 param2="javascript">
            <font color="blue" size="18px">我是有默认参数的macro，param1=${param1},param2=${param2}</font>
            <br>
        </#macro>
        <@test param1="java" param2="hello python"/>
    </div>

    <li>Freemarker4:有多个参数的macro</li>
    <div>
        <#macro test param1 param2="python" paramExt...>
            <font color="blue" size="18px">我是有参数的macro,param1=${param1},param2=${param2}</font>
            <br>
            <font color="#ff7f50" size="18px">${paramExt['param3']}</font>
            <font color="red" size="18px">${paramExt['param4']}</font>
        </#macro>
        <@test param1="java" param2="python" param3="node.js" param4="html"/>
    </div>
</ul>
<hr>

<h2>2、nested</h2>
<div>
    <ul>
        <#macro test param1="java111">
            ${param1}
            <br>
            <#nested param1,"我的nested参数">
            <br>
        </#macro>
        <li>调用</li>
        <div>
            <@test param1="java";loopVar1,loopVar2>
                <font color="aqua" size="18px">hello ${loopVar1},${loopVar2}</font><br>
            </@test>

            <@test param1="python";loopVar1>
                hello ${loopVar1}
                <br>
            </@test>
        </div>
    </ul>

    <hr>

    <div>
        <h2>3、函数</h2>
        <ul>
            <#function doAdd param1 param2>
                <#return param1+param2>
            </#function>
            <li>调用</li>
            <div>调用doAdd函数：${doAdd(100,100)}</div>
        </ul>
    </div>


</body>
</html>
