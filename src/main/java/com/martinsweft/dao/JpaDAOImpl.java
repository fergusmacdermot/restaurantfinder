package com.martinsweft.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public abstract class JpaDAOImpl<K, E> implements JpaDAO<K, E>{

	protected Class<E> entityClass;
		
	@PersistenceContext protected EntityManager entityManager;
	  
      @SuppressWarnings("unchecked")
      public JpaDAOImpl() {
          ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                  .getGenericSuperclass();
          this.entityClass = (Class<E>) genericSuperclass
                  .getActualTypeArguments()[1]; 
      }
      @Transactional
      public void persist(E entity) {
    	  entityManager.persist(entity);
      }
      public void remove(E entity) {
    	  entityManager.remove(entity);
      }
      public E merge(E entity) {
          return entityManager.merge(entity);
      }
      public void refresh(E entity) {
    	  entityManager.refresh(entity);
      }
      public E findById(K id) {
          return entityManager.find(entityClass, id);
      }
      public E flush(E entity) {
    	  entityManager.flush();
          return entity;
      }
}
