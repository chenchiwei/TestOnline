package com.ccw.testonline.dao;

import com.ccw.testonline.entity.TeacherCourse;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer teaId);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer teaId);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);
}