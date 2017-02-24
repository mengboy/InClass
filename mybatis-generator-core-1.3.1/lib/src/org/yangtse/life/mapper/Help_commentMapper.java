package org.yangtse.life.mapper;

import org.yangtse.life.bean.Help_comment;

public interface Help_commentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Help_comment record);

    int insertSelective(Help_comment record);

    Help_comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Help_comment record);

    int updateByPrimaryKey(Help_comment record);
}