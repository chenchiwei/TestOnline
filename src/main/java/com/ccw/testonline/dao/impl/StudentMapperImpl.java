package com.ccw.testonline.dao.impl;

import org.springframework.stereotype.Repository;

import com.ccw.testonline.common.dao.CommonDaoImpl;
import com.ccw.testonline.dao.StudentMapper;
import com.ccw.testonline.entity.Student;

@Repository
public class StudentMapperImpl extends CommonDaoImpl<Student> implements StudentMapper {
	
}
