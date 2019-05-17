<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝源Eloan-P2P平台</title>
<%@ include file="common/links-tpl.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery.form.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css" />

<script type="text/javascript">
	$(function() {
		if($("#showBindPhoneModal").size() > 0){
            $("#showBindPhoneModal").click(function(){
                //弹出 绑定手机的模态框
                $('#bindPhoneModal').modal('show');
            });
            
            $("#sendVerifyCode").click(function(){
                //手机号码的验证, 非空, 格式, 有吗绑定
                var _this = $(this);
                _this.attr("disabled",true);  //禁用
                $.ajax({
                    url:"/p2p-website/sendVerifyCode.do",
                    type:"post",
                    dataType:"json",
                    data:{phoneNumber:$("#phoneNumber").val()},
                    success:function(data){
                        if(data.success){  //成功
                            var sec = 90;
                            var timer = setInterval(function(){
                                sec--;
                                if(sec > 0){
                                    _this.text(sec+"秒后重新发送验证码");
                                }else{
                                    clearInterval(timer);
                                    _this.attr("disabled",false);
                                    _this.text("重新发送验证码");
                                }
                            },1000);
                        }else{ //失败
                            _this.attr("disabled",false);
                            $.messager.popup(data.msg);
                        }
                    }
                    
                });
            });
            
            //给保存按钮添加一个点击事件,提交表单,用于绑定手机
            $("#bindPhoneForm").ajaxForm(function(data){
                if(data.success){
                    //刷新页面
                    location.reload();
                }else{
                    $.messager.popup(data.msg);
                }
            });
            $("#bindPhone").click(function(){
                $("#bindPhoneForm").submit();
            });
        }
        
        //邮件绑定:
        if($("#showBindEmailModal").size()>0){
            $("#showBindEmailModal").click(function(){
                //清空文本框的内容
                $("#email").val("");
                $("#bindEmailModal").modal("show");
            });
            $("#bindEmail").click(function(){
                $("#bindEmailForm").ajaxSubmit(function(data){
                    if(data.success){
                        location.reload();
                    }else{
                        $.messager.popup(data.msg);
                    }
                });
            });
            
            
        }
        
	});
</script>
</head>
<body>
	<!-- 网页顶部导航 -->
	<%@ include file="common/head-tpl.jsp"%>
	<!-- 网页导航 -->
	<c:set var="currentNav" value="personal"></c:set>
	<%@ include file="common/navbar-tpl.jsp"%>

	<div class="container">
		<div class="row">
			<!--导航菜单-->
			<div class="col-sm-3">
				<c:set var="currentMenu" value="personal"></c:set>
				<%@ include file="common/leftmenu-tpl.jsp"%>
			</div>
			<!-- 功能页面 -->
			<div class="col-sm-9">
				<div class="panel panel-default">
					<div class="panel-body el-account">
						<div class="el-account-info">
							<div class="pull-left el-head-img">
								<img class="icon" src="${pageContext.request.contextPath}/images/ms.png" />
							</div>
							<div class="pull-left el-head">
								<p>用户名:${logininfo.username}</p>
								<p>
									最后登录时间：
																	<fmt:formatDate value="${userinfoAndAccount.nowTime}" pattern="yyyy-MM-dd HH:mm:ss" />

								</p>
							</div>

							<div class="pull-left"
								style="text-align: center; width: 400px; margin: 30px auto 0px auto;">
								<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/recharge.do">账户充值</a> <a
									class="btn btn-danger btn-lg" href="${pageContext.request.contextPath}/moneyWithdraw.do">账户提现</a>
							</div>
							<div class="clearfix"></div>
						</div>

						<div class="row h4 account-info">
							<div class="col-sm-4">
								账户总额：<span class="text-primary">${userinfoAndAccount.account.usableAmount+userinfoAndAccount.account.freezedAmount+userinfoAndAccount.account.unReceivePrincipal}元</span>
							</div>
							<div class="col-sm-4">
								可用金额：<span class="text-primary">${userinfoAndAccount.account.usableAmount}元</span>
							</div>
							<div class="col-sm-4">
								冻结金额：<span class="text-primary">${userinfoAndAccount.account.freezedAmount}元</span>
							</div>
						</div>

						<div class="row h4 account-info">
							<div class="col-sm-4">
								待收利息：<span class="text-primary">${userinfoAndAccount.account.unReceiveInterest}元</span>
							</div>
							<div class="col-sm-4">
								待收本金：<span class="text-primary">${userinfoAndAccount.account.unReceivePrincipal}元</span>
							</div>
							<div class="col-sm-4">
								待还本息：<span class="text-primary">${userinfoAndAccount.account.unReturnAmount}元</span>
							</div>
						</div>

						<div class="el-account-info top-margin">
							<div class="row">
								<div class="col-sm-4">
									<div class="el-accoun-auth">
										<div class="el-accoun-auth-left">
											<img src="${pageContext.request.contextPath}/images/shiming.png" />
										</div>
										<div class="el-accoun-auth-right">
											<h5>实名认证</h5>
											<c:choose>
												<c:when test="${userinfo.isRealAuth}">
													<p>
														已认证 <a href="#">查看</a>
													</p>
												</c:when>
												<c:otherwise>
													<p>
														未认证 <a href="#">马上认证 </a>
													</p>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="clearfix"></div>
										<p class="info">实名认证之后才能在平台投资</p>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="el-accoun-auth">
										<div class="el-accoun-auth-left">
											<img src="${pageContext.request.contextPath}/images/shouji.jpg" />
										</div>
										<div class="el-accoun-auth-right">
											<h5>手机认证</h5>
											<c:choose>
												<c:when test="${userinfo.isPhoneAuth}">
													<p>
														已认证 <a href="#">查看</a>
													</p>
												</c:when>
												<c:otherwise>
													<p>
														未认证 <a href="javascript:;" id="showBindPhoneModal">马上绑定</a>
													</p>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="clearfix"></div>
										<p class="info">可以收到系统操作信息,并增加使用安全性</p>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="el-accoun-auth">
										<div class="el-accoun-auth-left">
											<img src="images/youxiang.jpg" />
										</div>
										<div class="el-accoun-auth-right">
											<h5>邮箱认证</h5>
											<c:choose>
												<c:when test="${userinfo.isMailAuth}">
													<p>
														已绑定 <a href="#">修改</a>
													</p>
												</c:when>
												<c:otherwise>
													<p>
														未绑定 <a href="javascript:;" id="showBindEmailModal">马上绑定</a>
													</p>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="clearfix"></div>
										<p class="info">您可以设置邮箱来接收重要信息</p>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4">
									<div class="el-accoun-auth">
										<div class="el-accoun-auth-left">
											<img src="${pageContext.request.contextPath}/images/baozhan.jpg" />
										</div>
										<div class="el-accoun-auth-right">
											<h5>VIP会员</h5>
											<c:choose>
												<c:when test="${userinfo.isVIP}">
													<p>VIP用户</p>
												</c:when>
												<c:otherwise>
													<p>
														普通用户 <a href="#">申请会员</a>
													</p>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="clearfix"></div>
										<p class="info">VIP会员，让你更快捷的投资</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="bindPhoneModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">绑定手机</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="bindPhoneForm" method="post"
						action="/p2p-website/bindPhone.do">
						<div class="form-group">
							<label for="phoneNumber" class="col-sm-2 control-label">手机号:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="phoneNumber"
									name="phoneNumber" />
								<button id="sendVerifyCode" class="btn btn-primary"
									type="button" autocomplate="off">发送验证码</button>
							</div>
						</div>
						<div class="form-group">
							<label for="verifyCode" class="col-sm-2 control-label">验证码:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="verifyCode"
									name="verifyCode" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="bindPhone">保存</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="bindEmailModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">绑定邮箱</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="bindEmailForm" method="post"
						action="/p2p-website/sendEmail.do">
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">Email:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="email" name="email" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="bindEmail">保存</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="common/footer-tpl.jsp"%>
</body>
</html>