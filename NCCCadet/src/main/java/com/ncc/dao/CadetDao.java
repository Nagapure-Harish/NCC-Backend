package com.ncc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.ncc.modal.Cadet;

@RestController
public interface CadetDao extends JpaRepository<Cadet,Serializable>{

	

	Optional<Cadet> findByRegNo(String regno);

	List<Cadet> findByDeleteStatusNot(int i);

	List<Cadet> findByDeleteStatus(int i);

	Optional<Cadet> findByMobile(Long mobile);

	
  
}
