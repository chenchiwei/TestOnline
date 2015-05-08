package com.ccw.testonline.service;

import com.ccw.testonline.bean.ChangePwdBean;
import com.ccw.testonline.security.UserBase;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 用户业务类<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月12日 上午8:47:18 <br>
 *
 */
public interface UserService {
	
	public boolean login(UserBase userBase);

	public boolean logout();

	public boolean changePwd(ChangePwdBean bean);
}
