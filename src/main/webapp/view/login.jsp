<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/login" method="post">
   <div class="col-sm-5"></div>
   <div class="col-sm-7" style="font-size: 30px">用户登录</div><br/>
   
   <div class="col-sm-5"></div>
   <div class="col-sm-7">
     <div style="align-content: center;">用户名</div>
     <input type="text" id="username" name="username"  placeholder="请输入名字">
     <span id="namemessage" style="color: red; display: none;">名字不能为空</span>
   </div>
  
   
   <div class="col-sm-5"></div>
   
   <div class="col-sm-7">
     <div style="align-content:center; ">密码</div>
     <input type="password" id="password" name="password"  placeholder="请输入密码">
     <span id="passmessage" style="color: red; display: none;">密码不能为空</span>
   </div>
   
   
   <div class="col-sm-6"></div>
   
   <div class="col-sm-6">
     <input class="btn btn-lg" type="submit" value="登录">
   </div>
   
   </form>
</body>


</html>