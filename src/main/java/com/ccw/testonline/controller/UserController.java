package com.ccw.testonline.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccw.testonline.bean.ChangePwdBean;
import com.ccw.testonline.common.utils.ValidateCodeUtils;
import com.ccw.testonline.security.UserBase;
import com.ccw.testonline.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public  Map<String,Object> login(UserBase userBase){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			Session session = SecurityUtils.getSubject().getSession();
			String code = (String) session.getAttribute("code");
			if(code != null && userBase.getValidateCode().equalsIgnoreCase(code)) {
				boolean result=userService.login(userBase);
				if(result){
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("message", "用户名或错误！");
				}
				
			} else {
				map.put("success", false);
				map.put("message", "验证码信息错误！");
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", "用户名或错误！");
			e.printStackTrace();
		}
		return map;
		
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String,Object> logout(HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			boolean result=userService.logout();
			if(result){
				map.put("success", true);
			}else{
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
		
	}
	
	/**
	 * 修改用户密码
	 * @param session
	 * @return
	 */
	@RequestMapping("/changepwd")
	@ResponseBody
	public Map<String,Object> changepwd(ChangePwdBean bean){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			boolean result=userService.changePwd(bean);
			if(result){
				map.put("success", true);
			}else{
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("success", false);
			e.printStackTrace();
		}
		return map;
		
	}
	
	//生成验证码
	@RequestMapping("/createVali")
	@ResponseBody
	public void createVali(HttpServletResponse response){
		//Map<String,Object> map=new HashMap<String, Object>();
		
		Map<String, Object> imageAndCode = ValidateCodeUtils.createVerifyImage(
				250, 150, 4);
		try {
			Object image = imageAndCode.get("image");
			Object code = imageAndCode.get("code");
			Session currentSession = SecurityUtils.getSubject().getSession();
			currentSession.setAttribute("code", code);
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 240);
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write((BufferedImage) image, "JPEG", sos);
			sos.flush();
			sos.close();
		} catch (InvalidSessionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
