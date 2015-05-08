package com.ccw.testonline.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.dom4j.DocumentException;

import com.ccw.testonline.common.utils.PrivilegeUtils;
import com.ccw.testonline.entity.Admin;
import com.ccw.testonline.entity.Student;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.exception.SecurityServiceException;
import com.ccw.testonline.service.StudentService;
import com.ccw.testonline.service.TeacherService;

public class TestOnlineRealm extends AuthorizingRealm {

	@Resource(name="teacherService")
	private TeacherService teacherService;
	
	@Resource(name="studentService")
	private StudentService studentService;

	public TestOnlineRealm() {
		super.setName("TestOnlineRealm");
	}

	/**
	 * 获取权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserBase userBase = (UserBase) principals.getPrimaryPrincipal();
		List<String> perms = new ArrayList<String>();
		try {
			PrivilegeUtils privilegeUtils=new PrivilegeUtils();
			perms=privilegeUtils.readPrivilege(userBase.getType());
		} catch (DocumentException e) {
			System.err.println("授权信息错误");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		/*test*/
		System.out.println("权限列表:");
		for (String str : perms) {
			System.out.println("我拥有的权限:" + str);
		}
		info.addStringPermissions(perms);
		return info;
	}

	/**
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		TestOnlineToken inToken = (TestOnlineToken) token;
		String userName = inToken.getUsername();
		Integer type=inToken.getType();
		UserBase user = null;
		try {
			if(type==1){//教师
				Integer id=Integer.parseInt(userName);
				Teacher teacher=teacherService.getByTeaId(id);
				if(teacher!=null){
					user=new UserBase(teacher.getTeaId()+"", teacher.getTeaPassword(),teacher.getTeaName(), 1);
				}else{
					throw new SecurityServiceException();
				}
			}else if(type==2){//学生
				Integer id=Integer.parseInt(userName);
				Student student=studentService.getByStuId(id);
				if(student!=null){
					user=new UserBase(student.getStuId()+"", student.getStuPassword(),student.getStuName(), 2);
				}else{
					throw new SecurityServiceException();
				}
				
			}else if(type==0){
				Map<String, Object> paramMap=new HashMap<String, Object>();
				paramMap.put("username", userName);
				Admin admin=(Admin) teacherService.getBy(paramMap, "com.ccw.testonline.entity.Admin", "findOne");
				if(admin!=null){
					user=new UserBase(admin.getUsername(), admin.getPassword(),admin.getUsername(), 0);
				}else{
					throw new SecurityServiceException();
				}
			}else{
			
				throw new SecurityServiceException();
			}
			
		} catch (SecurityServiceException e) {
			throw new AuthenticationException();
		}
		ByteSource pswdBs = new SimpleByteSource(Hex.decode(user.getPassword()));
		return new SimpleAuthenticationInfo(user,pswdBs , getName());
	}  

}
