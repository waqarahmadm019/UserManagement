package com.usersmanagement.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.beans.factory.annotation.Autowired;


@Repository
@Transactional
public abstract class GenericDaoImplementation< T extends Serializable > {
	 
	   private Class< T > targetClass;
	 
	   @PersistenceContext
	   EntityManager entityManager;
	   
	   protected GenericDaoImplementation(Class<T> targetClass) {
	        this.targetClass = targetClass;
	    }
	 
	   public void setClazz( Class< T > targetClass ){
	      this.targetClass = targetClass;
	   }
	 
	   public T findOne( int id ){
	      return entityManager.find( targetClass, id );
	   }
	   public List< T > findAll(){
	      return entityManager.createQuery( "from " + targetClass.getName() )
	       .getResultList();
	   }
	 
	   public void save( T entity ){
	      entityManager.persist( entity );
	   }
	 
	   public void update( T entity ){
	      entityManager.merge( entity );
	   }
	 
	   public void delete( T entity ){
	      entityManager.remove( entity );
	   }
	   public void deleteById( int entityId ){
	      T entity = findOne( entityId );
	      delete( entity );
	   }
	}
