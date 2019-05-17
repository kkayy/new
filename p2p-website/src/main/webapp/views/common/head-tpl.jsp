<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="el-header" >
    <div class="container" style="position: relative">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/">首页</a></li>
                <c:choose>
                	<c:when test="${empty logininfo }">
                		<li><a href="${pageContext.request.contextPath}/login.html">登录</a></li>
                		<li><a href="${pageContext.request.contextPath}/register.html">快速注册</a></li>
               		</c:when>
               		<c:otherwise>
		                <li>
		                      <a class="el-current-user" href="${pageContext.request.contextPath}/queryInformation.do">${logininfo.username}</a>
		                </li>
		                <li><a  href="#">账户充值  </a></li>
		                <li><a  href="${pageContext.request.contextPath}/logout.do">注销 </a></li>
                    </c:otherwise>
                </c:choose>
            <li><a href="#">帮助</a></li>
        </ul>
    </div>
</div>