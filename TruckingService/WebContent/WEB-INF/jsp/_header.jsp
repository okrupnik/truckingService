<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="header">
    <a href="${pageContext.request.contextPath}/">
    <img src="${pageContext.request.contextPath}/img/logo.gif" alt=""
        width="322" height="127" class="logo" /></a>
    <div id="meta">
        <a href="${pageContext.request.contextPath}/" class="meta1">Home</a> 
        <a href="#" class="meta2">Search</a> 
        <a href="#" class="meta3">Contacts</a>
    </div>
    <div id="menu">
        <ul>
            <li id="active"><a
                href="${pageContext.request.contextPath}/jsp/main.jsp">Home</a></li>
            <li><a href="index2.html">Services</a></li>           
            <li><a href="#">About Service</a></li>
            <li><a href="#">FAQ</a></li>
            <li><a href="#">Forum</a></li>
        </ul>
    </div>
    <div id="submenu">
        <!-- <ul class="first">
            <li><a href="#" id="over">Mission</a></li>
            <li><a href="#">Company</a></li>
            <li><a href="#">History</a></li>
            <li><a href="#">News</a></li>
        </ul> -->
    </div>
</div>