package com.ccw.testonline.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.testonline.bean.StudentBean;
import com.ccw.testonline.common.service.CommonServiceImpl;
import com.ccw.testonline.entity.Student;
import com.ccw.testonline.exception.ServiceException;
import com.ccw.testonline.service.StudentService;

@Service("studentService")
@Transactional(readOnly=true)
public class StudentServiceImpl extends CommonServiceImpl<Student> implements StudentService {
	
	private static final String sqlNamespace = "com.ccw.testonline.entity.Student";

	@Override
	public Student getByStuId(Integer Id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stuId", Id);
		Student student = (Student) super.commonDao.findOne(paramMap,
				sqlNamespace, "findById");

		return student;

	}

	@Override
	public int editforadmin(StudentBean editVo) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
