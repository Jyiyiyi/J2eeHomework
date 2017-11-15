<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书目录</title>
<style>
body{
	text-align:center;
}
.bookimg{
	height:200px;
	width: 110px;
}
.t1{
	width: 50px;
}
.t2{
	width: 300px;
}
.t3{
	width: 110px;
}
.t4{
	height:20px;
	width: 110px;
	text-align:left;
}
td{
	display:inline-block;
	padding:0;
}
p{
	display:block;
	cursor: pointer;
	border: 1px solid #a7a6ab;
	width:10px;
	height:10px;
	float:right;
}
input{
	width:90px;
	padding:0;
}
.inc{
	font-size:10px;
	margin-top:0px;
}s
.dec{
	font-size:10px;
	margin-right:-12px;
	margin-top:10px;
}
.jump{
	cursor: pointer;
}
</style>
<script type="text/javascript">
    function validate(str) {
    	var xmlhttp;
    	if (str=="")
    	  {
    	  document.getElementById("usermsg").innerHTML="";
    	  return;
    	  }
    	if (window.XMLHttpRequest)
    	  {// code for IE7+, Firefox, Chrome, Opera, Safari
    	  xmlhttp=new XMLHttpRequest();
    	  }
    	else
    	  {// code for IE6, IE5
    	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    	  }
    	xmlhttp.onreadystatechange=function()
    	  {
    	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    	    {
    	    document.getElementById("usermsg").innerHTML=xmlhttp.responseText;
    	    }
    	  }
    	xmlhttp.open("GET","check?name="+str,true);
    	xmlhttp.send();
    }
</script>
</head>
<body>
<form action="add" method="POST">
书名：<input name="name" onblur="validate(this.value)" id="name"/>
<span id="usermsg"></span>
价格：<input name="price"/>
<button style="submit">添加</button>
</form>
	<table>
		<tr>
			<td class="t1">序号</td><td class="t2">书名</td><td class="t3">价格</td>
		</tr>
		<c:forEach var="book" items="${items}">
			<tr>
				<td class="t1">${book.id}</td><td class="t2">${book.name}</td><td  class="t3">${book.price}</td>
			</tr>
		</c:forEach>
		<tr><a href="list?page=1">首页</a></tr>||
		<c:choose>
			<c:when test="${page == 1}">
				<tr><a href="list?page=${page}">上一页</a></tr>||
			</c:when>
			<c:otherwise>
				<tr><a href="list?page=${page-1}">上一页</a></tr>||
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page == total}">
				<tr><a href="list?page=${page}">下一页</a></tr>||
			</c:when>
			<c:otherwise>
				<tr><a href="list?page=${page+1}">下一页</a></tr>||
			</c:otherwise>
		</c:choose>
		<tr><a href="list?page=${total}">末页</a></tr>
	</table>
</body>
</html>