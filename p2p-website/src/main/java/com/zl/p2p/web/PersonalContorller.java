package com.zl.p2p.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zl.p2p.pojo.Account;
import com.zl.p2p.pojo.Realauth;
import com.zl.p2p.pojo.Userinfo;
import com.zl.p2p.pojo.UserinfoAndAccount;
import com.zl.p2p.service.IPersonalService;
import com.zl.p2p.service.IUserinfoService;
import com.zl.p2p.service.IVerifyCodeService;
import com.zl.p2p.util.JsonResult;
import com.zl.p2p.util.RequireLogin;
import com.zl.p2p.util.UserContext;

@Controller
public class PersonalContorller {
	@Autowired
	private IPersonalService personalService;
	@Autowired
	private IVerifyCodeService verifyCodeService;
	@Autowired
	private IUserinfoService userinfoService;
	
	@RequestMapping("/queryInformation.do")
	public ModelAndView queryInformation() {
	
		Userinfo userinfo = new Userinfo();
		Account account = new Account();
		ModelAndView modelAndView = new ModelAndView();
		//从session域中获得id
		Long id =UserContext.getCurrent().getId();
		//根据id查询得到userinfo 对象
		userinfo=personalService.queryUserinfoById(id);
		//根据id查询得到account 对象
		account=personalService.queryAccountById(id);
		//获取包装了时间与userinfo的类
		UserinfoAndAccount userinfoAndAccount = new UserinfoAndAccount();
		//得到当前时间与userinfo和account对象设置进userinfoAndTime中
		userinfoAndAccount.setNowTime(new Date());
		userinfoAndAccount.setUserinfo(userinfo);
		userinfoAndAccount.setAccount(account);
		System.out.println(userinfoAndAccount);
		modelAndView.addObject("userinfoAndAccount", userinfoAndAccount);
		modelAndView.setViewName("views/personal");
		return modelAndView;
	}
	@RequestMapping(path="/realAuth_save.do")
	public void realAuth_save(Realauth realauth,@RequestParam MultipartFile load) {
		
	}
//	@PostMapping("/sendVerifyCode.do")
//	@RequireLogin
//	@ResponseBody
//	public JsonResult sendVerifyCode(String phonenumber) {
//		JsonResult js = new JsonResult();
//		validationService.sendValidation(phonenumber);
//		return js;
//	}
	@PostMapping("/sendVerifyCode.do")
	@RequireLogin
	@ResponseBody
	public JsonResult sendVerifyCode(String phoneNumber){
		JsonResult  jsonResult = new JsonResult();
		try{
			verifyCodeService.sendVerifyCode(phoneNumber);
		}catch(RuntimeException e){
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 绑定手机
	 * @param phoneNumber
	 * @param verifyCode
	 * @return
	 */
	@PostMapping("/bindPhone.do")
	@RequireLogin
	@ResponseBody
	public JsonResult bindPhone(String phoneNumber,String verifyCode){
		JsonResult jsonResult = new JsonResult();
		try{
			userinfoService.bindPhone(phoneNumber, verifyCode);
		}catch(RuntimeException e){
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
	

	/**
	 * 手机验证
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/checkPhone.do")
	@ResponseBody
	public boolean checkPhone(String phoneNumber) {
		return  userinfoService.checkPhone(phoneNumber);
	}
}
