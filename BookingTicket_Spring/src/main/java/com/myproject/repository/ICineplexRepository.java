package com.myproject.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myproject.entity.Cineplex;

@Repository
public interface ICineplexRepository extends JpaRepository<Cineplex, Integer> {
	Cineplex findByName(String name);

	@Query ("SELECT c FROM cineplex c")
	public Page<Cineplex> findAllPaging(Pageable pageable);
}
