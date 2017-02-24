package com.swjtu.cn.service;
 
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WangYu
 * Date: 15/10/8
 * Time: 下午2:10
 */
public interface BaseService<T> {

    @Transactional(isolation= Isolation.READ_COMMITTED,rollbackFor=Throwable.class)
    public int add(T obj);

    @Transactional(isolation=Isolation.READ_COMMITTED,rollbackFor=Throwable.class)
    public int update(T obj);

    @Transactional(isolation=Isolation.READ_COMMITTED,rollbackFor=Throwable.class)
    public int delete(T obj);

    public T get(int id); 
}
