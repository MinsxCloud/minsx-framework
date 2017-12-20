package com.minsx.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsx.core.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>{
	

}
