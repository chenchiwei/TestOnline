package com.ccw.testonline.dao;

import com.ccw.testonline.entity.ClassCourse;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Integer stuHwId);

    int insert(ClassCourse record);

    int insertSelective(ClassCourse record);

    ClassCourse selectByPrimaryKey(Integer stuHwId);

    int updateByPrimaryKeySelective(ClassCourse record);

    int updateByPrimaryKey(ClassCourse record);
}