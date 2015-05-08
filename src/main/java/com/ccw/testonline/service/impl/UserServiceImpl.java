package com.ccw.testonline.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.testonline.bean.ChangePwdBean;
import com.ccw.testonline.common.service.CommonServiceImpl;
import com.ccw.testonline.common.utils.MD5Utils;
import com.ccw.testonline.entity.Admin;
import com.ccw.testonline.entity.Student;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.security.TestOnlineToken;
import com.ccw.testonline.security.UserBase;
import com.ccw.testonline.service.UserService;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl extends CommonServiceImpl<Object> implements UserService {
	
	public boolean login(UserBase userBase) throws InvalidSessionException,AuthenticationException{
		boolean result=false;
		String username = userBase.getUsername();
		String password = userBase.getPassword();
		Integer type = userBase.getType();
		TestOnlineToken token = new TestOnlineToken(username, password, null, type);
		try {
			Subject user = SecurityUtils.getSubject();
			if(user.isAuthenticated()) {
				result=true;
			}else{
				user.login(token);
				UserBase userBaseVo = (UserBase)  user.getPrincipal();
				Session session = user.getSession();
				session.setAttribute("userBase", userBaseVo);
				result=true;
				/*test*/
				System.out.println(type);
			}
		} catch (InvalidSessionException e) {
			System.out.println("登录失败！");
			throw e;
		} catch (AuthenticationException e) {
			System.out.println("用户名或密码不正确！");
			throw e;
		}
		return result;
		
	}

	@Override
	public boolean logout() {
		boolean result=false;
		Subject user = SecurityUtils.getSubject();
		if(user!=null){
			user.logout();
			result=true;
		}
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean changePwd(ChangePwdBean bean) {
		boolean result=false;
		if(bean.getType()==0){
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("username", bean.getUsername());
			int flag=0;
			Admin admin=(Admin) commonDao.findOne(paramMap, "com.ccw.testonline.entity.Admin", "findOne");
			if(admin.getPassword().equalsIgnoreCase(MD5Utils.MD5((bean.getOldpwd())))){
				admin.setPassword(MD5Utils.MD5(bean.getNewpwd()));
				flag=commonDao.update(admin, "com.ccw.testonline.entity.Admin", "changePwd");
			}
			if(flag>0){
				result=true;
			}
			
		}else if(bean.getType()==1){
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("teaId", Integer.parseInt(bean.getUsername()));
			int flag=0;
			Teacher teacher=(Teacher) commonDao.findOne(paramMap, "com.ccw.testonline.entity.Teacher", "selectByPrimaryKey");
			if(teacher.getTeaPassword().equalsIgnoreCase(MD5Utils.MD5((bean.getOldpwd())))){
				teacher.setTeaPassword(MD5Utils.MD5(bean.getNewpwd()));
				flag=commonDao.update(teacher, "com.ccw.testonline.entity.Teacher", "changePwd");
			}
			if(flag>0){
				result=true;
			}
		}else if(bean.getType()==2){
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("stuId", Integer.parseInt(bean.getUsername()));
			int flag=0;
			Student student=(Student) commonDao.findOne(paramMap, "com.ccw.testonline.entity.Student", "findById");
			if(student.getStuPassword().equalsIgnoreCase(MD5Utils.MD5((bean.getOldpwd())))){
				student.setStuPassword(MD5Utils.MD5(bean.getNewpwd()));
				flag=commonDao.update(student, "com.ccw.testonline.entity.Student", "changePwd");
			}
			if(flag>0){
				result=true;
			}
		}else{
			
		}
		return result;
	}
	
}
