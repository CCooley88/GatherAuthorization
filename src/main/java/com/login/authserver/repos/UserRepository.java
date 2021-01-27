package com.login.authserver.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.login.authserver.model.AuthUser;

public interface UserRepository extends CrudRepository<AuthUser, Integer>{
	 @Query(value = "SELECT * FROM user where email =?1", nativeQuery = true)
		List<AuthUser> findByUserName(String email);
}