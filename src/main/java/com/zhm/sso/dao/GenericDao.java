package com.zhm.sso.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {

	public final static String INSERT = "insert";
	public final static String SELECT = "select";
	public final static String UPDATE = "update";
	public final static String DELETE = "delete";
	public final static String SELECTALL = "selectAll";
	
	

	/**
     * Generic method to insert an object - handles insert.
     * @param object the object to insert
     */
    public void insert(final T object);

    /**
     * Generic method to select an object based on class and id
     * @param id the identifier (primary key) of the object to select
     * @return the persisted object
     */
    public T select(final PK id);

    /**
     * Generic method to update an object - handles update.
     * @param object the object to update
     */
    public void update(final T object);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     */
    public void delete(final PK id);
    

    /**
     * 将所有记录用List返回，注意，如果记录多的话，会引起内存问�?
     * @return List of all records
     */
    public List<T> selectAll();
  
    
}
