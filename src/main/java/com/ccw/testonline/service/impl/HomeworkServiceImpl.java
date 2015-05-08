package com.ccw.testonline.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.testonline.bean.ClassHwBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.service.CommonServiceImpl;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.entity.Homework;
import com.ccw.testonline.exception.DaoException;
import com.ccw.testonline.service.HomeworkService;

@Service(value="homeworkService")
@Transactional(readOnly=true)
public class HomeworkServiceImpl extends CommonServiceImpl<Homework> implements
		HomeworkService {

	@Override
	public PageVO<Object> findFinishedStudent(ClassHwBean classHwBean,
			PageBean pageBean) throws DaoException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("classId", classHwBean.getClassId());
		paramMap.put("teaHwId", classHwBean.getTeaHwId());
		Integer count = commonDao
				.getNum(paramMap, "com.ccw.testonline.entity.StudentHomework",
						"getFinishedCount");
		 
		return commonDao
				.findListByPage(paramMap, pageBean, null, null,
						"com.ccw.testonline.entity.StudentHomework",
						"findFinishedStudent", count);
	}
	
	@Override
	public PageVO<Object> findUnFinishedStudent(ClassHwBean classHwBean,
			PageBean pageBean) throws DaoException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("classId", classHwBean.getClassId());
		paramMap.put("teaHwId", classHwBean.getTeaHwId());
		Integer count = commonDao
				.getNum(paramMap, "com.ccw.testonline.entity.StudentHomework",
						"getUnfinishedCount");
		PageVO<Object> pageVO =commonDao
				.findListByPage(paramMap, pageBean, null, null,
						"com.ccw.testonline.entity.StudentHomework",
						"findUnfinishedStudent", count);
		
		return pageVO;
	}
}
