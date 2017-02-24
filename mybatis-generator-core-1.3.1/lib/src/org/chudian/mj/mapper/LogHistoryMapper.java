package org.chudian.mj.mapper;

import org.chudian.mj.bean.LogHistory;

public interface LogHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogHistory record);

    int insertSelective(LogHistory record);

    LogHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogHistory record);

    int updateByPrimaryKey(LogHistory record);
}