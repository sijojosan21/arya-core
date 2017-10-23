package com.sijojosan.arya.core.dal.repository;

import org.springframework.data.repository.CrudRepository;

import com.sijojosan.arya.core.dal.entities.UserTokenEntity;


public interface UserTokenRepository extends CrudRepository<UserTokenEntity, Long>{

	UserTokenEntity findByUserName(String userName);
	
}
