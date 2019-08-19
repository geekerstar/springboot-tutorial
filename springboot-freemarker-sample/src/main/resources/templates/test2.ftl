<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>freemarker</title>
</head>
<body>

<h1>取值：</h1>
<ul>
    <li>整数：<font size="20px" color="red">${intVar}</font></li>
    <li>长整数：<font size="20px" color="blue">${LongVar}</font></li>
    <li>双精度：<font size="20px" color="#9acd32">${doubleVar}</font></li>
    <li>字符串：<font size="20px" color="red">${stringVar}</font></li>
    <li>布尔值：<font size="20px" color="red">${booleanVar?string('yes','no')}</font></li>
    <li>日期类型java.util.Date：<font size="20px" color="red">${dateVar?string('yyyy-MM-dd HH:mm:ss')}</font></li>
    <li>空值：<font size="20px" color="green">${nullVar1!}</font></li>
    <li>空值：<font size="20px" color="red">${nullVar!'我是默认值'}</font></li>
    <li>不存在的变量：<font size="20px" color="red">${变量!'我是默认变量'}</font></li>
    <li>不存在的变量1：<font size="20px" color="red">${free!'我是默认变量'}</font></li>
</ul>
<hr>
<ul>
    <li>赋值&运算</li>
    <#assign a=100/>
    a=<font color="red">${a}</font><br>
    a+100=<font color="blue">${a+100}</font>
</ul>
</body>
</html>
