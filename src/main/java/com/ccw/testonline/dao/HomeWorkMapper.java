package com.ccw.testonline.dao;

import com.ccw.testonline.entity.Homework;

public interface HomeWorkMapper {
    int deleteByPrimaryKey(Integer teaHwId);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(Integer teaHwId);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKey(Homework record);
}