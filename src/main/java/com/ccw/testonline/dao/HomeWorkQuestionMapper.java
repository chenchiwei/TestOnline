package com.ccw.testonline.dao;

import com.ccw.testonline.entity.HomeWorkQuestion;

public interface HomeWorkQuestionMapper {
    int deleteByPrimaryKey(Integer teaHwQueId);

    int insert(HomeWorkQuestion record);

    int insertSelective(HomeWorkQuestion record);

    HomeWorkQuestion selectByPrimaryKey(Integer teaHwQueId);

    int updateByPrimaryKeySelective(HomeWorkQuestion record);

    int updateByPrimaryKey(HomeWorkQuestion record);
}