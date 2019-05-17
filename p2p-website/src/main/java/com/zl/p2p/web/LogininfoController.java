package com.zl.p2p.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zl.p2p.exception.LogininfoException;
import com.zl.p2p.pojo.Logininfo;
import com.zl.p2p.service.ILogininfoService;
import com.zl.p2p.util.JsonResult;

@Controller
public class LogininfoController{
	@Autowired
	private ILogininfoService logininfoService;
	@RequestMapping(path="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult login(String username,String password,HttpServletRequest request) {
		JsonResult js = new JsonResult();
		boolean isOk;
		try {
			isOk = logininfoService.queryByUsernameAndPassword(username,password,Logininfo.USERTYPE_NORMAL,request.getRemoteAddr());
			if(isOk==false) {
				js.setSuccess(false);
				js.setMsg("用户名或密码错误");
			}
		} catch (LogininfoException e) {
			js.setSuccess(false);
			js.setMsg(e.getMessage());
		}
		return js;
	}
	@RequestMapping(path="/checkUsername.do",method=RequestMethod.POST)
	@ResponseBody
	public boolean  checkUsername(String username){
		return  logininfoService.queryByUsernameAndUsertype(username,Logininfo.USERTYPE_NORMAL);
	}
	
	@RequestMapping(path="register.do",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult register(String username,String password) {
		JsonResult js = new JsonResult();
		try {
			logininfoService.insert(username,password,Logininfo.USERTYPE_NORMAL);
		} catch (LogininfoException e) {
			// TODO Auto-generated catch block
			js.setSuccess(false);
		}
		return js;
		
	}
}
