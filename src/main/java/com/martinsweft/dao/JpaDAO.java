package com.martinsweft.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;

public interface JpaDAO<K, E> {


	void persist(E entity);
    void remove(E entity);
    E merge(E entity);
    void refresh(E entity);
    E findById(K id);
   // List<E> findAll();

   // Integer removeAll();	

}
