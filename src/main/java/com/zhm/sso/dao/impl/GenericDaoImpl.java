package com.zhm.sso.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.ClassUtils;

import com.zhm.sso.dao.GenericDao;

public class GenericDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements GenericDao<T, PK> {
	
	protected String nameSpacePrefix;
	protected Class<T> persistentClass;
	
	public GenericDaoImpl(final Class<T> persistentClass) {
    	this.persistentClass = persistentClass;
    	nameSpacePrefix = ClassUtils.getShortName(this.persistentClass) + ".";
    }
	
	public void insert(final T object) throws RuntimeException{
		try
		{
			getSqlSession().insert(nameSpacePrefix + INSERT, object);
		}catch(RuntimeException re)
		{
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public T select(PK id) {
		return (T)getSqlSession().selectOne(nameSpacePrefix + SELECT, id);
	}
	
	public void update(T object) {
		getSqlSession().update(nameSpacePrefix + UPDATE, object);
	}

	public void delete(PK id) {
		getSqlSession().delete(nameSpacePrefix + DELETE, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> selectAll() {
		return (List<T>) getSqlSession().selectList(nameSpacePrefix + SELECTALL);
	}
	
}
