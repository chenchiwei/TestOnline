package com.ccw.testonline.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.testonline.common.service.CommonServiceImpl;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.service.TeacherService;

@Service("teacherService")
@Transactional(readOnly = true)
public class TeacherServiceImpl extends CommonServiceImpl<Teacher> implements
		TeacherService {

	private static final String sqlNamespace = "com.ccw.testonline.entity.Teacher";

	public Teacher getByTeaId(Integer Id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teaId", Id);
		Teacher teacher = (Teacher) super.commonDao.findOne(paramMap,
				sqlNamespace, "selectByPrimaryKey");

		return teacher;

	}

	@Override
	public Object getBy(Map<String, Object> paramMap,String sqlNamespace,String exectueName) {
		return super.commonDao.findOne(paramMap,
				sqlNamespace, exectueName);
	}
}
