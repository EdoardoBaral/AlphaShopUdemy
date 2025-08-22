package com.xantrix.webapp.repository;

import com.xantrix.webapp.entities.DettListini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrezziRepository extends JpaRepository<DettListini, Integer> {
	
	//JPQL
	@Query(value = "select b from Listini a join a.dettListini b where b.codArt = :codart and a.id = :idlist")
	DettListini selByCodArtAndList(@Param("codart") String codArt, @Param("idlist") String listino);
}
