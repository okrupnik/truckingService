<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="block">
    <div id="login">
        <form method="post" action="${pageContext.request.contextPath}/Controller?command=login_page">
            <img src="${pageContext.request.contextPath}/img/text1.gif" alt="" width="56" height="11" />
            <input type="text" class="input" name="userlogin" value=""/>
            <img src="${pageContext.request.contextPath}/img/text2.gif" alt="" width="56" height="11" />
            <input type="password" class="input" name="userpassword" value=""/><br />            
            <input type="checkbox" class="checkbox" />
            <img src="${pageContext.request.contextPath}/img/text3.gif" alt="" width="70" height="13" /><br />
            <input type="submit" class="button" value="Enter" /><br />
        </form>
        <a href="${pageContext.request.contextPath}/Controller?command=create_user_page" class="create">Create account</a>
        <a href="${pageContext.request.contextPath}/Controller?command=forgot_password_page" class="forgot">forgot login/password?</a>	    
    </div>
</div>